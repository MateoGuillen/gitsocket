//comentario
//comentario de martin
package py.una.cliente.udp;


import java.io.*;
import java.lang.*;
import java.net.*;
import java.util.Scanner;


import py.una.entidad.Hospital;
import py.una.entidad.HospitalJSON;
import py.una.entidad.Cama;
import py.una.entidad.CamaJSON;

class UDPClient {
	
	
	public String request(String datoPaquete, String a[]) {
	
        String direccionServidor = "127.0.0.1";

				//Primer Parcial Matias Gonzalez
				String cadenaMG = "Primer Parcial";

        if (a.length > 0) {
            direccionServidor = a[0];
        }

        int puertoServidor = 9876;
        
        
        try {
		
			DatagramSocket clientSocket = new DatagramSocket();
			
			InetAddress IPAddress = InetAddress.getByName(direccionServidor);
			System.out.println("Intentando conectar a = " + IPAddress + ":" + puertoServidor +  " via UDP...");
			
			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];
			
			
            		        
	        sendData = datoPaquete.getBytes();
	        System.out.println("Enviando los datos al servidor...");
	        //System.out.println("Enviar " + datoPaquete + " al servidor. ("+ sendData.length + " bytes)");
	        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);
	
	        clientSocket.send(sendPacket);
	        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	
	        System.out.println("Esperando respuesta...");
			//HOLA MUNDO PARA EL PRIMER PARCIAL DE DISTRI
	
	        clientSocket.setSoTimeout(10000);
	
	            try {
	                // ESPERAMOS LA RESPUESTA, BLOQUENTE
	                clientSocket.receive(receivePacket);
	
	                String respuesta = new String(receivePacket.getData());
	                		
	                InetAddress returnIPAddress = receivePacket.getAddress();
	                int port = receivePacket.getPort();
	
	                System.out.println("Respuesta desde =  " + returnIPAddress + ":" + port+"\n");
	           
	                clientSocket.close();
	                
	                return respuesta;
	
	            } catch (SocketTimeoutException ste) {
	
	                System.out.println("TimeOut: El paquete udp se asume perdido.");
	                clientSocket.close();
	            }
	           
			
			
        } catch (UnknownHostException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
		        
        return "paquete_perdido";  		
}
        
	


	
    public static void main(String a[]) throws Exception {
    	
    	//PARA CONECTARSE A SERVIDOR
    	BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    	UDPClient cliente = new UDPClient();
    	            
		
    	//OBTENCION DE TODOS LOS HOSPITALES DISPONIBLES EN BD
    	Hospital h = new Hospital();
    	String datoPaquete = new String();
    	datoPaquete=HospitalJSON.objetoString(h);
    	
    	//SOLICITUD Y RESPUESTA
    	String respuesta = new String();
    	respuesta = cliente.request(datoPaquete, a);
    	
    	if(respuesta.equals("paquete_perdido")) {
    		 System.out.println("No se pudo conectar con servidor, intentelo mas tarde");
    		 System.exit(0);
    		
    	}
    	
    	//System.out.println(respuesta);
    	
    	
    	//PASO DE JSON A OBJETO
    	 HospitalJSON json = new HospitalJSON();
    	 Hospital h1 = json.stringObjeto(respuesta.trim());
    	
    	 System.out.println("\nEstado: " + h1.getEstadoResponse() );
         System.out.println("Mensaje: " + h1.getMensajeResponse() );
    	 
         //LISTA DE HOSPITALES
    	 System.out.println("\nHOSPITALES:\n\tid Nombre");
    	 for(String tmp: h1.getDetalleHospitales()) {
             System.out.println("\t"+tmp);
         }
    	 
        
    	 
         System.out.println("\nIngrese el id del hospital: ");
         int id_hospital= Integer.parseInt(inFromUser.readLine());
          
         
         while(true) {
        	 System.out.println("\t\tHOSPITAL "+id_hospital);        	 
        	 System.out.println("\n1 Nueva cama\n2 Eliminar cama\n3 Ocupar cama\n4 Desocupar cama \n5 Ver camas \n6 Ver camas de todos los hospitales \n7 Salir");
	         int operacion= Integer.parseInt(inFromUser.readLine());	         
	         datoPaquete = new String();
	         Cama c;
	         long id_cama;
	         
	         switch (operacion) {
	             case 1:
	                 c = new Cama(id_hospital,operacion);
	                 datoPaquete=CamaJSON.objetoString(c); 
	                break;
	             case 2:
	            	 System.out.println("Ingrese el id_cama del hospital "+id_hospital+", que quiere eliminar: ");
	                 id_cama = Long.parseLong(inFromUser.readLine());
	                 c=new Cama(id_hospital,id_cama,operacion);
	                 datoPaquete=CamaJSON.objetoString(c);
	                 break;
	            	 
	             case 3:
	            	 System.out.println("Ingrese el id_cama del hospital "+id_hospital+",que quiere ocupar: ");
	                 id_cama = Long.parseLong(inFromUser.readLine());
	                 c=new Cama(id_hospital,id_cama,operacion);
	                 datoPaquete=CamaJSON.objetoString(c);
	                 break;
	            	 
	             case 4:
	            	 System.out.println("Ingrese el id_cama del hospital "+id_hospital+",que quiere desocupar: ");
	                 id_cama = Long.parseLong(inFromUser.readLine());
	                 c=new Cama(id_hospital,id_cama,operacion);
	                 datoPaquete=CamaJSON.objetoString(c);
	                 break;
	             
	             case 5:
	            	 c=new Cama(id_hospital,operacion);
	                 datoPaquete=CamaJSON.objetoString(c);
	                 break;
	                 
	             case 6:
	            	 c=new Cama(id_hospital,operacion);
	                 datoPaquete=CamaJSON.objetoString(c);
	                 break;
	            	 
	             case 7:
	            	 System.out.println("____________________________________________");
	            	 System.exit(0);
	            default:
	            	System.out.println("Operacion no valida");
	                break;
	        }
	        
	         //request
	        String rta = new String();
	     	respuesta = cliente.request(datoPaquete, a);
	     	
	     	//response
	     	if(rta.equals("paquete_perdido")) {
	     		 System.out.println("No se pudo conectar con servidor, intentelo mas tarde");
	     		
	     	}else {
	     		
	     		Cama presp = CamaJSON.stringObjeto(respuesta.trim());
                if(operacion==5 || operacion==6){
	            	 System.out.println("\t\tEstado de las camas");
                    for(String tmp: presp.getDetalleCamas()) {
                        System.out.println(" * " +tmp);
                    }
                    System.out.println("Estado: " + presp.getEstadoResponse() );
                    System.out.println("Mensaje: " + presp.getMensajeResponse() );
    
                }else{
                    System.out.println("Estado: " + presp.getEstadoResponse() );
                    System.out.println("Mensaje: " + presp.getMensajeResponse() );
                }
                
	     		
	     	}
	     	
	     	
	         
	         System.out.println("\n");
	         
	         
         }
        
         
         
    }
        
    	
} 

