package py.una.entidad;
//import java.util.ArrayList;
//import java.util.List;

public class Hospital {

	//atributos de la clase
	long id_hospital; 
	String nombre;

	//ESTO SE USA PARA RESPONDER AL CLIENTE
	long estadoResponse;
	String mensajeResponse;

	//constructores
	public Hospital(){
		//
	}
	public Hospital(long hid_hospital, String hnombre){
		this.id_hospital = hid_hospital;
		this.nombre = hnombre;
		
		
	}
	public Hospital(String hnombre){
		this.nombre = hnombre;
	}
	//getters y settters

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

}
