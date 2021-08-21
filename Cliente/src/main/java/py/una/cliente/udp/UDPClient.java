package py.una.cliente.udp;


import java.io.*;
import java.net.*;

import py.una.entidad.Hospital;
import py.una.entidad.HospitalJSON;
import py.una.entidad.Cama;
import py.una.entidad.CamaJSON;

class UDPClient {

    public static void main(String a[]) throws Exception {

        // Datos necesario
        String direccionServidor = "127.0.0.1";

        if (a.length > 0) {
            direccionServidor = a[0];
        }

        int puertoServidor = 9876;
        
        try {

            BufferedReader inFromUser =
                    new BufferedReader(new InputStreamReader(System.in));

            DatagramSocket clientSocket = new DatagramSocket();

            InetAddress IPAddress = InetAddress.getByName(direccionServidor);
            System.out.println("Intentando conectar a = " + IPAddress + ":" + puertoServidor +  " via UDP...");

            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
            /*
            System.out.print("Ingrese el número de cédula (debe ser numérico): ");
            String strcedula = inFromUser.readLine();
            Long cedula = 0L;
            try {
            	cedula = Long.parseLong(strcedula);
            }catch(Exception e1) {
            	
            }
            */
            
            System.out.println("Ingrese el id del hospital: ");
            long id_hospital = Long.parseLong(inFromUser.readLine());
            System.out.println("Ver el estado actual de todos los hospitales: 1");
            System.out.println("Crear Cama UTI: 2");
            System.out.println("Eliminar Cama UTI: 3");
            System.out.println("Ocupar Cama UTI: 4");
            System.out.println("Desocupar Cama UTI: 5");
            System.out.println("Ingrese una operacion: ");
            int operacion= Integer.parseInt(inFromUser.readLine());
            String datoPaquete = new String();
            switch (operacion) {
                case 1:
                    Cama c = new Cama(id_hospital,operacion);
                    datoPaquete=CamaJSON.objetoString(c); 
                   break;
                case 2:
                    Cama c2 = new Cama(id_hospital,operacion);
                    datoPaquete=CamaJSON.objetoString(c2);                    
                    break;
                case 3:
                    System.out.println("Ingrese el id de la cama que quiere eliminar: ");
                    long id_cama = Long.parseLong(inFromUser.readLine());
                    Cama c3=new Cama(id_hospital,id_cama,operacion);
                    datoPaquete=CamaJSON.objetoString(c3);
                    break;
                case 4:
                    System.out.println("Ingrese el id de la cama que quiere ocupar: ");
                    long id_cama2 = Long.parseLong(inFromUser.readLine());
                    Cama c4 = new Cama(id_hospital,id_cama2,"ocupada",operacion);
                    datoPaquete=CamaJSON.objetoString(c4);
                    break;
                case 5:
                    System.out.println("Ingrese el id de la cama que quiere desocupar: ");
                     long id_cam3 = Long.parseLong(inFromUser.readLine());
                     Cama c5 = new Cama(id_hospital,id_cam3,"desocupada",operacion);
                     datoPaquete=CamaJSON.objetoString(c5);
                    break;
               default:
                    //operacion indeterminada
                   break;
           }

            //System.out.print("Ingrese el apellido: ");
            //String apellido = inFromUser.readLine();
            
            
            
            
            sendData = datoPaquete.getBytes();
            System.out.println("Enviando los datos al servidor...");
            //System.out.println("Enviar " + datoPaquete + " al servidor. ("+ sendData.length + " bytes)");
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);

            clientSocket.send(sendPacket);

            DatagramPacket receivePacket =
                    new DatagramPacket(receiveData, receiveData.length);

            System.out.println("Esperamos si viene la respuesta.");

            //Vamos a hacer una llamada BLOQUEANTE entonces establecemos un timeout maximo de espera
            clientSocket.setSoTimeout(10000);

            try {
                // ESPERAMOS LA RESPUESTA, BLOQUENTE
                clientSocket.receive(receivePacket);

                String respuesta = new String(receivePacket.getData());
                Cama presp = CamaJSON.stringObjeto(respuesta.trim());
                
                InetAddress returnIPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();

                System.out.println("Respuesta desde =  " + returnIPAddress + ":" + port);
                if(operacion==1){
                    for(String tmp: presp.getDetalleCamas()) {
                        System.out.println(" ==> " +tmp);
                    }
                    System.out.println("Estado: " + presp.getEstadoResponse() );
                    System.out.println("Mensaje: " + presp.getMensajeResponse() );
    
                }else{
                    System.out.println("Estado: " + presp.getEstadoResponse() );
                    System.out.println("Mensaje: " + presp.getMensajeResponse() );
                }
                

                //System.out.println("Asignaturas: ");
                /*
                for(String tmp: presp.getAsignaturas()) {
                	System.out.println(" -> " +tmp);
                }
                */

            } catch (SocketTimeoutException ste) {

                System.out.println("TimeOut: El paquete udp se asume perdido.");
            }
           clientSocket.close();
        } catch (UnknownHostException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
} 

