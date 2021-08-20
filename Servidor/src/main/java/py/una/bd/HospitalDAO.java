package py.una.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import py.una.entidad.Hospital;

public class HospitalDAO {
 
	/**
	 * 
	 * @param condiciones 
	 * @return
	 */
    // getHospital : retorna todos los hospitales de la bd
	public List<Hospital> seleccionar() {
		String query = "SELECT id_hospital, nombre FROM public.hospital ";
		
		List<Hospital> lista = new ArrayList<Hospital>();
		
		Connection conn = null; 
        try 
        {
            //conectamos a la bd
        	conn = Bd.connect();
            //ejecutamos una query a la bd y recuperamos en un ResulSet
        	ResultSet rs = conn.createStatement().executeQuery(query);

            //pasamos el ResulSet a List para retornarlo
        	while(rs.next()) {
        		Hospital h = new Hospital();
        		h.setIdHospital(rs.getInt(1));
        		h.setNombre(rs.getString(2));
        		
        		lista.add(h);
        	}
        	
        } catch (SQLException ex) {
            System.out.println("Error en la seleccion: " + ex.getMessage());
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
		return lista;

	}
	
    // getHospitalbyId : retorna un Hospital por su ID
	public List<Hospital> seleccionarporID(long id_hospital) {
        String query = "SELECT id_hospital, nombre FROM public.hospital WHERE id_hospital = ? ";

		List<Hospital> lista = new ArrayList<Hospital>();
		
		Connection conn = null; 
        try 
        {
            //conectamos a la bd
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(query);
        	pstmt.setLong(1, id_hospital);
        	
        	ResultSet rs = pstmt.executeQuery();

        	while(rs.next()) {
        		Hospital p = new Hospital();
        		p.setIdHospital(rs.getInt(1));
        		p.setNombre(rs.getString(2));

        		lista.add(p);
        	}
        	
        } catch (SQLException ex) {
            System.out.println("Error en la seleccion: " + ex.getMessage());
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
		return lista;

	}
	
    // setHospital : agremos un Hospital a la bd
    public long insertar(Hospital h) throws SQLException {

        String query = "INSERT INTO public.hospital(nombre) "
                + "VALUES(?)";
 
        long id = 0;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, h.getIdHospital()); //las tienen que ser iguales a =1
            pstmt.setString(1, h.getNombre());  //o salta un error=Error en la insercion: El índice de la columna está fuera de rango: 2, número de columnas: 1.
 
            long affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la insercion: " + ex.getMessage());
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
        	
        return id;
    	
    	
    }
	
    // putHospital : actualizamos un hospital
    public long actualizar(Hospital h) throws SQLException {

        String query = "UPDATE public.hospital SET nombre = ? WHERE id_hospital = ? ";
 
        long id = 0;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            
            pstmt.setString(1, h.getNombre());
            pstmt.setLong(2, h.getIdHospital());
 
            long affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la actualizacion: " + ex.getMessage());
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
        return id;
    }
    
    // deleteById : borramos un hospital de la bd
    public long borrar(long id_hospital) throws SQLException {

        String query = "DELETE FROM public.hospital WHERE id_hospital = ? ";
 
        long id = 0;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setLong(1, id_hospital);
 
            long affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la eliminación: " + ex.getMessage());
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
        return id;
    }
    

}
