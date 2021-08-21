package py.una.entidad;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CamaJSON {


    public static void main(String[] args) throws Exception {
    	CamaJSON representacion = new CamaJSON();
    	
    	System.out.println("Ejemplo de uso 1: pasar de objeto a string");
    	Cama c = new Cama();
    	c.setIdHospital(1);
        c.setIdCama(1);
        c.setEstado("desocupada");
        c.setEstadoResponse(1);
        c.setMensajeResponse("ok");
        c.setOperacion(2);
        c.getDetalleCamas().add("Algoritmos y Estructuras de Datos 2");
    	c.getDetalleCamas().add("Quimica");
    	c.getDetalleCamas().add("Ingles");

    	
    	String r1 = representacion.objetoString(c);
    	System.out.println(r1);
    	
    	
    	System.out.println("\n*********************************************************a****************");
    	System.out.println("\nEjemplo de uso 2: pasar de string a objeto");
    	String un_string = "{\"id_hospital\":2,\"id_cama\":2,\"estado\":\"desocupada\",\"estadoResponse\":1,\"mensajeResponse\":\"ok\",\"operacion\":3}";
    	
    	Cama r2 = representacion.stringObjeto(un_string);
    	System.out.println(r2.id_hospital + " " +r2.id_cama + " " +r2.estado + " " + r2.estadoResponse + " " + r2.mensajeResponse + " "+ r2.operacion);
    }
    
    public static String objetoString(Cama c) {	
    	
		JSONObject obj = new JSONObject();
        obj.put("id_hospital", c.getIdHospital());
        obj.put("id_cama", c.getIdCama());
        obj.put("estado",c.getEstado());
        obj.put("estadoResponse", c.getEstadoResponse());
        obj.put("mensajeResponse", c.getMensajeResponse());
        obj.put("operacion", c.getOperacion());


        JSONArray list = new JSONArray();
        
        for(String temp: c.getDetalleCamas()){
        	list.add(temp);
        }
       // if(list.size() > 0) {
        	obj.put("camas", list);
        //}
        


        return obj.toJSONString();
    }
    
    
    public static Cama stringObjeto(String str) throws Exception {
    	Cama c = new Cama();
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(str.trim());
        JSONObject jsonObject = (JSONObject) obj;

        //long id_hospital = (long) jsonObject.get("id_hospital");
        c.setIdHospital((long) jsonObject.get("id_hospital"));
        c.setIdCama((long) jsonObject.get("id_cama"));
        c.setEstado((String) jsonObject.get("estado"));
        c.setEstadoResponse((long) jsonObject.get("estadoResponse"));
        c.setMensajeResponse((String) jsonObject.get("mensajeResponse"));
        c.setOperacion((long) jsonObject.get("operacion"));


        JSONArray msg = (JSONArray) jsonObject.get("camas");
        Iterator<String> iterator = msg.iterator();
        while (iterator.hasNext()) {
        	c.getDetalleCamas().add(iterator.next());
        }


        return c;
	}

}
