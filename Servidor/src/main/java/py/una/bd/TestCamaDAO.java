package py.una.bd;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import py.una.entidad.Cama;

public class TestCamaDAO {

	
	
	public static void main(String args[]) throws SQLException{
		
		CamaDAO cdao = new CamaDAO();
		

//		cdao.crearCama(new Cama(1) );//insertar nueva cama en el hospital con id_hospital = 1 
//		cdao.crearCama(new Cama(1) );//insertar nueva cama en el hospital con id_hospital = 1	
//		cdao.crearCama(new Cama(2) );//insertar nueva cama en el hospital con id_hospital = 2
//		cdao.crearCama(new Cama(2) );//insertar nueva cama en el hospital con id_hospital = 2
//		
//		
//		cdao.ocupacion(new Cama(8,"ocupada")); //ocupar cama con id_Cama=8
//        cdao.ocupacion(new Cama(5,"desocupada"));//desocupar cama con id_Cama=5
//		
//		cdao.eliminarCama(3);
//		
		List<Cama> lista = cdao.seleccionarporID(1);
		
		//List<Cama> lista = cdao.ver_estado();

		for (Cama c : lista) {
			System.out.println("HospitalX" + c.getIdHospital() + " - " + "Cama" + c.getIdCama() + " : " + c.getEstado());
		}
	}
	
	
}
