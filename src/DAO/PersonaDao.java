package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
            persona.setCorreo(rs.getString("correo"));
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
            String sql = "SELECT * FROM persona where cedula =? AND deleted_at IS NULL";
            String[] params = {cedula};
            ResultSet rs = con.execQuery(sql, params);
            
            if(con.isResultSetEmpty(rs)) return null;
            Persona persona = setEntity(rs);
            return persona;	
		} 
        catch (Exception e) {
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
			String sql = "SELECT * FROM persona WHERE deleted_at IS NULL";
			ResultSet rs = con.execQuery(sql);
            if(con.isResultSetEmpty(rs)) return list;
			do {
                Persona persona = setEntity(rs);
				list.add(persona);
			} while (rs.next());
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
			String sql = "INSERT INTO Persona(cedula, nombre, apellido, direccion, telefono, correo) VALUES(?, ?,?,?,?,?)";
            String[] params = {
                persona.getCedula(),
                persona.getNombre(),
                persona.getApellido(),
                persona.getDireccion(),
                persona.getTelefono(),
                persona.getCorreo()
            };
            con.execMutation(sql, params);
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
                + " nombre=?, apellido=?, telefono=?, direccion=?, correo=?"
                + " WHERE cedula = ? AND deleted_at IS NULL";
            String[] params = {
                persona.getNombre(),
                persona.getApellido(),
                persona.getTelefono(),
                persona.getDireccion(),
                persona.getCorreo(),
                persona.getCedula()
            };
            con.execMutation(sql, params);
        } catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			con.close();
		}
    }

    @Override
    public void delete(Persona persona) {
		try {
			con = new Conne();
            con.open();
            Timestamp now = new Timestamp(new Date().getTime());
			String sql = "UPDATE persona SET deleted_at = '" + now.toString() +  "' WHERE cedula = ? AND deleted_at IS NULL";
            String[] params = {
                persona.getCedula()
            };
            con.execMutation(sql, params);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			con.close();
		}    
    }

}
