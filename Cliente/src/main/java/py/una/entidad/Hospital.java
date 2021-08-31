package py.una.entidad;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;

public class Hospital {

	//atributos de la clase
	long id_hospital; 
	String nombre;
	
	String tipo_objeto = "hospital";

	//ESTO SE USA PARA RESPONDER AL CLIENTE
	long estadoResponse;
	String mensajeResponse;
	List<String> hospitales;

	//constructores
	public Hospital(){
		hospitales = new ArrayList<String>();

	}
	public Hospital(long hid_hospital, String hnombre){
		this.id_hospital = hid_hospital;
		this.nombre = hnombre;
		hospitales = new ArrayList<String>();
		
	}
	public Hospital(String hnombre){
		this.nombre = hnombre;
		hospitales = new ArrayList<String>();
	}
	//getters y settters
	

	public List<String> getDetalleHospitales() {
		return hospitales;
	}

	public void setDetalleHospitales(List<String> hospitales) {
		this.hospitales = hospitales;
	}

	public long getIdHospital() {
		return id_hospital;
	}

	public void setIdHospital(long id_hospital) {
		this.id_hospital = id_hospital;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getEstadoResponse() {
		return estadoResponse;
	}

	public void setEstadoResponse(long estadoResponse) {
		this.estadoResponse = estadoResponse;
	}

	public String getMensajeResponse() {
		return mensajeResponse;
	}

	public void setMensajeResponse(String mensajeResponse) {
		this.mensajeResponse = mensajeResponse;
	}

	
	public String get_tipo_objeto() {
		return this.tipo_objeto;
	}

}
