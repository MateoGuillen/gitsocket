package py.una.server.udp;

import java.io.*;
import java.net.*;

import py.una.bd.HospitalDAO;
import py.una.entidad.Hospital;
import py.una.entidad.HospitalJSON;
import py.una.entidad.Hospital;
import py.una.entidad.HospitalJSON;

public class UDPServer {
	
	
    public static void main(String[] a){
        
        // Variables
        int puertoServidor = 9876;
        HospitalDAO pdao = new HospitalDAO();
        
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
                //System.out.println("DatoRecibido: " + datoRecibido );
                Hospital h = HospitalJSON.stringObjeto(datoRecibido);

                InetAddress IPAddress = receivePacket.getAddress();

                int port = receivePacket.getPort();

                System.out.println("De : " + IPAddress + ":" + port);
                System.out.println("Hospital Recibido : " + h.getNombre());
                
                try {
                	pdao.insertar(h);
                	System.out.println("Hospital insertado exitosamente en la Base de datos");
                    h.setEstadoResponse(1);// transaccion exitosa
                    h.setMensajeResponse("ok"); //palabra "ok"
                }catch(Exception e) {
                	System.out.println("Hospital NO insertado en la Base de datos, razón: " + e.getLocalizedMessage());
                    h.setEstadoResponse(2); // mayor a 1, ocurrio un error
                    h.setMensajeResponse(e.getLocalizedMessage());
                    
                }
               

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
                sendData = HospitalJSON.objetoString(h).getBytes();
                DatagramPacket sendPacket =
                        new DatagramPacket(sendData, sendData.length, IPAddress,port);

                serverSocket.send(sendPacket);

            }

        } catch (Exception ex) {
        	ex.printStackTrace();
            System.exit(1);
        }

    }
}  

