package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.general.IDao;
import config.Connection.Conne;
import modelos.Persona;

public class PersonaDao implements IDao<Persona> {
    private Conne con;

    @Override
    public Persona setEntity(ResultSet rs){
        try{
            Persona persona = new Persona();
            persona.setCedula(rs.getString("cedula"));	
            persona.setNombre(rs.getString("nombre"));	
            persona.setApellido(rs.getString("apellido"));	
            persona.setDireccion(rs.getString("direccion"));	
            persona.setTelefono(rs.getString("telefono"));
            return persona;
        }
        catch(SQLException e){
            String msg = "Error asignando los datos obtenidos\n" + e.getMessage();
            System.out.println(msg);
            return null;
        }
    }

    @Override
    public Persona get(String cedula) {
		try {
			con = new Conne();
            con.open();
            String sql = "SELECT * FROM persona where cedula =\"?\"";
            String[] params = {cedula};
            ResultSet rs = con.execQuery(sql, params);
            
            rs.next();
            Persona persona = setEntity(rs);
            return persona;	
		} 
        catch (SQLException e) {
			String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            return null;
		} 
        finally {
            con.close();
        }		
    }

    @Override
    public List<Persona> getAll() {
		try {
            List<Persona> list = new ArrayList<Persona>();
			con = new Conne();
            con.open();
			String sql = "SELECT * FROM persona";
			ResultSet rs = con.execQuery(sql);
			while (rs.next()) {
                Persona persona = setEntity(rs);
				list.add(persona);
			}
            return list;
		} 
        catch (SQLException e) {
			String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            return null;
		}
        finally {
            con.close();
        }
    }

    @Override
    public void save(Persona persona) {
		try {
			con = new Conne();
            con.open();
			String sql = "INSERT INTO Persona(nombre, apellido, direccion, telefono) VALUE(?,?,?,?)";
            String[] params = {
                persona.getNombre(),
                persona.getApellido(),
                persona.getDireccion(),
                persona.getTelefono()
            };
            con.execQuery(sql, params);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			con.close();
		}
    }

    @Override
    public void update(Persona persona) {
		try {
			con = new Conne();
            con.open();
            String sql = "UPDATE persona SET"
                + " nombre=\"?\" apellido=\"?\" telefono=\"?\" direccion=\"?\""
                + " WHERE cedula = \"?\"";
            String[] params = {
                persona.getNombre(),
                persona.getApellido(),
                persona.getTelefono(),
                persona.getDireccion(),
                persona.getCedula()
            };
            con.execQuery(sql, params);
        } catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			con.close();
		}
    }

    @Override
    public void delete(Persona person) {
		try {
			con = new Conne();
            con.open();
			String sql = "DELETE FROM persona WHERE cedula = \"?\"";
            String[] params = {person.getCedula()};
            con.execQuery(sql, params);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			con.close();
		}    
    }

}
