package py.una.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.STRING;

import py.una.entidad.Cama;

public class CamaDAO {
 
	/**
	 * 
	 * @param condiciones 
	 * @return
	 */
    // getCama : retorna todos las camas de la bd
	public List<Cama> ver_estado() {
		String query = "SELECT id_hospital, id_cama, estado FROM public.cama ORDER BY id_hospital ASC ";
		
		List<Cama> lista = new ArrayList<Cama>();
        //List<String> lista2 = new ArrayList<String>();
		
		Connection conn = null; 
        try 
        {
            //conectamos a la bd
        	conn = Bd.connect();
            //ejecutamos una query a la bd y recuperamos en un ResulSet
        	ResultSet rs = conn.createStatement().executeQuery(query);

            //pasamos el ResulSet a List para retornarlo
        	while(rs.next()) {
        		Cama c = new Cama();
                //String s= new String();
                c.setIdHospital(rs.getInt(1));
        		c.setIdCama(rs.getInt(2));
        		c.setEstado(rs.getString(3));


        		
        		lista.add(c);
                //lista2.add(s);
        	}
        	
        } catch (SQLException ex) {
            System.out.println("Error en la seleccion: " + ex.getMessage());
            Cama c2=new Cama();
            c2.setEstadoResponse(2);
            lista.add(c2);
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
                Cama c2=new Cama();
                c2.setEstadoResponse(2);
                 lista.add(c2);
        	}
        }
		return lista;

	}
	/*
    // getCamaById : retorna una Cama por su ID
	public List<Cama> seleccionarporID(long id_hospital) {
        String query = "SELECT id_hospital, nombre FROM public.hospital WHERE id_hospital = ? ";

		List<Cama> lista = new ArrayList<Cama>();
		
		Connection conn = null; 
        try 
        {
            //conectamos a la bd
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(query);
        	pstmt.setLong(1, id_hospital);
        	
        	ResultSet rs = pstmt.executeQuery();

        	while(rs.next()) {
        		Cama p = new Cama();
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
    */
	
    // setHospital : agremos una Cama a la bd
    public String crearCama(Cama c) throws SQLException {

        String query = "INSERT INTO public.cama(id_hospital) "
                + "VALUES(?)";
        String mensaje=new String("ok");
        long id = 0;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, c.getIdCama()); //las tienen que ser iguales a =1
            pstmt.setLong(1, c.getIdHospital());  //o salta un error=Error en la insercion: El índice de la columna está fuera de rango: 2, número de columnas: 1.
 
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
                    mensaje=ex.getMessage();
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la insercion: " + ex.getMessage());
            mensaje=ex.getMessage();
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
                mensaje=ef.getMessage();
        	}
        }
        	
        return mensaje;
    	
    	
    }
	
    // putCama : actualizamos una cama
    
    /*
        ocupacion(new Cama(2,"ocupada"));
        ocupacion(new Cama(2,"desocupada"));
    */
    public String ocupacion(Cama c) throws SQLException { 

        String query = "UPDATE public.cama SET estado = ? WHERE id_cama = ? ";
        String mensaje=new String("ok");
        long id = 0;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            
            pstmt.setString(1, c.getEstado());
            pstmt.setLong(2, c.getIdCama());
 
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
                    mensaje=ex.getMessage();
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la actualizacion: " + ex.getMessage());
            mensaje=ex.getMessage();
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
                mensaje=ef.getMessage();
        	}
        }
        return mensaje;
    }
    
    // deleteById : borramos una cama de la bd
    public String eliminarCama(long id_cama) throws SQLException {

        String query = "DELETE FROM public.cama WHERE id_cama = ? ";
        String mensaje=new String("ok");
        long id = 0;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setLong(1, id_cama);
 
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
                    mensaje=ex.getMessage();
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la eliminación: " + ex.getMessage());
            mensaje=ex.getMessage();
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
                mensaje=ef.getMessage();
        	}
        }
        return mensaje;
    }
    

}
