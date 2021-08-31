package py.una.entidad;
import java.util.ArrayList;
import java.util.List;

public class Cama {

	//atributos de la clase

	//COLUMNAS DE LA TABLA Cama en la bd
	long id_cama; //PK en la bd
	long id_hospital; //FK en la bd
	String estado; //ocupada o desocupada se guarda en la bd, desocupada por default
	
	String tipo_objeto = "cama";

	//ESTO SE USA PARA RESPONDER AL CLIENTE
	long estadoResponse; 	// 1   ,  		0 , 			2
	String mensajeResponse; // ok , mensajeError, Operacion indeterminada
	
	List<String> camas;


	
	long operacion; //Para enviar desde el cliente y que el servidor sepa que hacer

	//constructores
	public Cama(){
		camas = new ArrayList<String>();
	}

	public Cama(long id_hospital){
		camas = new ArrayList<String>();
		this.id_hospital = id_hospital;
	}

	public Cama(long id_hospital, String estado){
		camas = new ArrayList<String>();
		this.id_hospital = id_hospital;
		this.estado = estado;
	}

	public Cama(long id_hospital, long operacion){
		camas = new ArrayList<String>();
		this.id_hospital = id_hospital;
		this.operacion = operacion;
	}
	public Cama(long id_hospital, long id_cama,long operacion){
		camas = new ArrayList<String>();
		this.id_hospital = id_hospital;
		this.id_cama=id_cama;
		this.operacion = operacion;
	}
	public Cama(long id_hospital, long id_cama,String estado,long operacion){
		camas = new ArrayList<String>();
		this.id_hospital = id_hospital;
		this.id_cama=id_cama;
		this.estado=estado;
		this.operacion = operacion;
	}
	



	//getters y settters

	public long getIdCama() {
		return id_cama;
	}

	public void setIdCama(long id_cama) {
		this.id_cama = id_cama;
	}

	public long getIdHospital() {
		return id_hospital;
	}

	public void setIdHospital(long id_hospital) {
		this.id_hospital = id_hospital;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public long getOperacion() {
		return operacion;
	}

	public void setOperacion(long operacion) {
		this.operacion = operacion;
	}

	public List<String> getDetalleCamas() {
		return camas;
	}

	public void setDetalleCamas(List<String> camas) {
		this.camas = camas;
	}

	
	public String get_tipo_objeto() {
		return this.tipo_objeto;
	}
}
