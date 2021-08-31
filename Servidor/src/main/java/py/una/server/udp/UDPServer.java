package py.una.server.udp;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import py.una.bd.CamaDAO;
import py.una.bd.HospitalDAO;
import py.una.entidad.CamaJSON;
import py.una.entidad.Hospital;
import py.una.entidad.HospitalJSON;
import py.una.entidad.Cama;
import py.una.entidad.CamaJSON;

public class UDPServer {
	
	
    public static void main(String[] a){
        
        // Variables
        int puertoServidor = 9876;
       
        
        try {
            //1) Creamos el socket Servidor de Datagramas (UDP)
            DatagramSocket serverSocket = new DatagramSocket(puertoServidor);
			System.out.println("Servidor de Sistema de Camas de Hospitales - UDP ");
			
            //2) buffer de datos a enviar y recibir
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

			
            //3) Servidor siempre esperando
            while (true) {

                receiveData = new byte[1024];

                DatagramPacket receivePacket =
                        new DatagramPacket(receiveData, receiveData.length);


                System.out.println("Esperando a algun cliente... ");

                // 4) Receive LLAMADA BLOQUEANTE
                serverSocket.receive(receivePacket);
				
				System.out.println("________________________________________________");
                System.out.println("Aceptamos un paquete");

                // Datos recibidos e Identificamos quien nos envio
                String datoRecibido = new String(receivePacket.getData());
                datoRecibido = datoRecibido.trim();
                System.out.println("DatoRecibido ");
                
                
                
                //----------------------------------------------------------
                
                //VERIFICACION DE DATOS ANTES DE INSTANCIAR
                //System.out.println("[Completo] aberrr.... "+datoRecibido);
                
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(datoRecibido.trim());
                JSONObject jsonObject = (JSONObject) obj;
                
                String tipo_objeto = (String) jsonObject.get("tipo_objeto");
                //System.out.println("[Tipo de dato ] "+tipo_objeto);
                
                
                //-------------------------------------------------------------

                
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();

                System.out.println("De : " + IPAddress + ":" + port);
                //System.out.println("Operacion Recibida : " + c.getOperacion());
                String ok="ok";
                String auxmensaje=new String();
                
                
                if(tipo_objeto.equals("hospital")) {
                	
                	
                	Hospital h = HospitalJSON.stringObjeto(datoRecibido);
                	//Hospital h = new Hospital();
                	HospitalDAO hdao = new HospitalDAO(); 
                	
                	List<Hospital> lista = new ArrayList<Hospital>();
                	lista = hdao.seleccionar_todos(); 
             		
                	String hospitales_disponibles = new String();
                	
                	
             		for (Hospital i: lista){
             			
             			hospitales_disponibles = i.getIdHospital() + " " + i.getNombre()+"";
             			h.getDetalleHospitales().add(hospitales_disponibles);
             			//System.out.println(hospitales_disponibles);
             		}
             		
             	
             		HospitalJSON json = new HospitalJSON();
                     
                     h.setMensajeResponse(ok);
                     h.setEstadoResponse(0);
                     
                     String str = new String();
                     str =  json.objetoString(h);
                     
          			System.out.println(str);

                	 sendData = str.getBytes();
                     
	            }else if(tipo_objeto.equals("cama")) {
	            	
	            	 CamaDAO cdao = new CamaDAO();
	                
	                //System.out.println("DatoRecibido: " + datoRecibido );
	                //Hospital h = HospitalJSON.stringObjeto(datoRecibido);
	                Cama c = CamaJSON.stringObjeto(datoRecibido);
	
	                List<Cama> lista = new ArrayList<Cama>();
	                
	               switch ((int)c.getOperacion()) {
	                    case 6:
	                        lista = cdao.ver_estado();
	                        for (Cama caux : lista) {
	                            String detalle="Hospital " + caux.getIdHospital() + " - " + "Cama " + caux.getIdCama() + " : " + caux.getEstado();
	                            c.getDetalleCamas().add(detalle);
	                        }
	                        c.setMensajeResponse(ok);
	                        c.setEstadoResponse(0);
	                       break;
	                    case 5:
	                        lista = cdao.seleccionarporID(c.getIdHospital());
	                        for (Cama caux : lista) {
	                            String detalle="Hospital " + caux.getIdHospital() + " - " + "Cama " + caux.getIdCama() + " : " + caux.getEstado();
	                            c.getDetalleCamas().add(detalle);
	                        }
	                        c.setMensajeResponse(ok);
	                        c.setEstadoResponse(0);
	                       break;
	                    
	                    case 1:
	                        auxmensaje=cdao.crearCama(c);
	                        if(auxmensaje.equals(ok)){
	                            c.setMensajeResponse(ok);
	                            c.setEstadoResponse(0);
	                        }else{
	                            c.setMensajeResponse(auxmensaje);
	                            c.setEstadoResponse(2);
	                        };
	                        break;
	                    case 2:
	                        auxmensaje=cdao.eliminarCama(c.getIdCama());
	                        if(auxmensaje.equals(ok)){
	                            c.setMensajeResponse(ok);
	                            c.setEstadoResponse(0);
	                        }else{
	                            c.setMensajeResponse(auxmensaje);
	                            c.setEstadoResponse(2);
	                        };
	                        break;
	                    case 3: //ocupar
	                    	c.setEstado("ocupada");
	                        auxmensaje=cdao.ocupacion(c);
	                        if(auxmensaje.equals(ok)){
	                            c.setMensajeResponse(ok);
	                            c.setEstadoResponse(0);
	                        }else{
	                            c.setMensajeResponse(auxmensaje);
	                            c.setEstadoResponse(2);
	                        };
	                        break;
	                    case 4: //desocupar
	                    	c.setEstado("desocupada");
	                    	auxmensaje=cdao.ocupacion(c);
	                        if(auxmensaje.equals(ok)){
	                            c.setMensajeResponse(ok);
	                            c.setEstadoResponse(0);
	                        }else{
	                            c.setMensajeResponse(auxmensaje);
	                            c.setEstadoResponse(2);
	                        };
	                        break;
	                            
	                   default:
	                        c.setMensajeResponse("Transaccion Indeterminada");
	                        c.setEstadoResponse(-1);
	                       break;
	               }
	                /*try {
	                	pdao.insertar(h);
	                	System.out.println("Hospital insertado exitosamente en la Base de datos");
	                    h.setEstadoResponse(1);// transaccion exitosa
	                    h.setMensajeResponse("ok"); //palabra "ok"
	                }catch(Exception e) {
	                	System.out.println("Hospital NO insertado en la Base de datos, razón: " + e.getLocalizedMessage());
	                    h.setEstadoResponse(2); // mayor a 1, ocurrio un error
	                    h.setMensajeResponse(e.getLocalizedMessage());
	                    
	                }*/
	               
	
	                //TODO: POR HACER
	                /*
	                    ***************************CON ESTO RESPONDEMOS AL CLIENTE DESDE EL SERVIDOR***********************************
	                    ● estado, donde:
	                    ○ "0" corresponde a una transacción exitosa.
	                    ○ "-1" transacción indeterminada.
	                    ○ Mayor a "1" un código de error o mensaje relacionado a la transacción.
	                    ● mensaje
	                    ○ Palabra “ok” si no existe error.
	                    ○ El detalle el error si existe.
	
	                    ***************************ESTO CREO QUE VA EN EL CLIENTE***********************************
	                    ● tipo_operacion: <- 
	                    ○ 1: ver_estado
	                    ○ 2: crear_cama
	                    ○ 3: eliminar_cama
	                    ○ 4: ocupar_cama
	                    ○ 5: desocupar_cama
	                    ○ Otros a considerar
	                */
	
	                // Enviamos la respuesta inmediatamente a ese mismo cliente
	                // Es no bloqueante
	                sendData = CamaJSON.objetoString(c).getBytes();
	            }
                
                
                
	            
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port);

                serverSocket.send(sendPacket);

            }

        } catch (Exception ex) {
        	ex.printStackTrace();
            System.exit(1);
        }

    }
}  

