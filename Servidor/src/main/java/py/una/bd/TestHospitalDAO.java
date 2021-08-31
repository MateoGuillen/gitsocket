package py.una.bd;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import py.una.entidad.Hospital;

public class TestHospitalDAO {

	
	
	public static void main(String args[]) throws SQLException{
		
		HospitalDAO hdao = new HospitalDAO();
		
		
//		hdao.insertar(new Hospital("TestHospital6") );
//		hdao.insertar(new Hospital("TestHospital7") );
//		hdao.insertar(new Hospital("TestHospital8") );
//		hdao.insertar(new Hospital("TestHospital9") );
//		
//		
//		hdao.actualizar(new Hospital(7,"TestPut") );
//		
//		hdao.borrar(9);
//		
		//List<Hospital> lista = hdao.seleccionarporID(1);
		
		List<Hospital> lista = hdao.seleccionar_todos(); 
		
		
		for (Hospital h: lista){
			System.out.println(h.getIdHospital() + " " + h.getNombre());
		}
	}
	
	
}
