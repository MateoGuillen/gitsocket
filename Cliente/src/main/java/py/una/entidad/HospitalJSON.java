package py.una.entidad;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HospitalJSON {


    public static void main(String[] args) throws Exception {
    	HospitalJSON representacion = new HospitalJSON();
    	
    	System.out.println("Ejemplo de uso 1: pasar de objeto a string");
    	Hospital h = new Hospital();
    	h.setIdHospital(1);
        h.setNombre("HospitalTest");
        h.setEstadoResponse(1);
        h.setMensajeResponse("ok");
    	
    	String r1 = representacion.objetoString(h);
    	System.out.println(r1);
    	
    	
    	System.out.println("\n*********************************************************a****************");
    	System.out.println("\nEjemplo de uso 2: pasar de string a objeto");
    	String un_string = "{\"id_hospital\":2,\"nombre\":\"HospitalTest2\",\"estadoResponse\":2,\"mensajeResponse\":\"Ocurrio un ERROR\"}";
    	
    	Hospital r2 = representacion.stringObjeto(r1);
    	System.out.println(r2.id_hospital + " " +r2.nombre + " " + r2.estadoResponse + " " + r2.mensajeResponse);
    }
    
    public static String objetoString(Hospital h) {	
    	
		JSONObject obj = new JSONObject();
		obj.put("tipo_objeto", h.get_tipo_objeto());
        obj.put("id_hospital", h.getIdHospital());
        obj.put("nombre", h.getNombre());
        obj.put("estadoResponse", h.getEstadoResponse());
        obj.put("mensajeResponse", h.getMensajeResponse());

        return obj.toJSONString();
    }
    
    
    public static Hospital stringObjeto(String str) throws Exception {
    	Hospital h = new Hospital();
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(str.trim());
        JSONObject jsonObject = (JSONObject) obj;

        //long id_hospital = (long) jsonObject.get("id_hospital");
        h.setIdHospital((long) jsonObject.get("id_hospital"));
        h.setNombre((String) jsonObject.get("nombre"));
        h.setEstadoResponse((long) jsonObject.get("estadoResponse"));
        h.setMensajeResponse((String) jsonObject.get("mensajeResponse"));
        
        JSONArray msg = (JSONArray) jsonObject.get("hospitales");
        Iterator<String> iterator = msg.iterator();
        while (iterator.hasNext()) {
        	h.getDetalleHospitales().add(iterator.next());
        }

      
        return h;
	}

}
