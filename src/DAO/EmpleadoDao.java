package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.general.IDao;
import config.Connection.Conne;
import modelos.Empleado;

public class EmpleadoDao implements IDao<Empleado> {
    private Conne con;

    @Override
    public Empleado setEntity(ResultSet rs){
        try{
            Empleado empleado = new Empleado();
            empleado.setCedula(rs.getString("cedula"));	
            empleado.setNombre(rs.getString("nombre"));	
            empleado.setApellido(rs.getString("apellido"));	
            empleado.setDireccion(rs.getString("direccion"));	
            empleado.setTelefono(rs.getString("telefono"));
            empleado.setId(rs.getString("id"));
            return empleado;
        }
        catch(SQLException e){
            String msg = "Error asignando los datos obtenidos\n" + e.getMessage();
            System.out.println(msg);
            return null;
        }
    }

    public EmpleadoDao() {}
    
    @Override
    public Empleado get(String id) {
		try {
			con = new Conne();
            con.open();
            String sql = "SELECT id, nombre, apellido, cedula, direccion, telefono"
                + " FROM empleado e JOIN persona p ON e.cedula = p.cedula where id =\"?\"";
            String[] params = {id};
            ResultSet rs = con.execQuery(sql, params);
            
            rs.next();
            Empleado empleado = setEntity(rs);
            return empleado;	
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
    public List<Empleado> getAll() {
		try {
            List<Empleado> list = new ArrayList<Empleado>();
			con = new Conne();
            con.open();
            String sql = "SELECT id, nombre, apellido, cedula, direccion, telefono"
                + " FROM empleado e JOIN persona p ON e.cedula = p.cedula";			
            ResultSet rs = con.execQuery(sql);
			while (rs.next()) {
                Empleado empleado = setEntity(rs);
				list.add(empleado);
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
    public void save(Empleado empleado) {
		try {
			con = new Conne();
            con.open();
			String sql = "INSERT INTO Persona(nombre, apellido, direccion, telefono) VALUE(?,?,?,?)";
            String[] params = {
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getDireccion(),
                empleado.getTelefono()
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
    public void update(Empleado empleado) {
		try {
			con = new Conne();
            con.open();
            String sql = "UPDATE persona SET"
                + " nombre=\"?\" apellido=\"?\" telefono=\"?\" direccion=\"?\""
                + " WHERE cedula = \"?\"";
            String[] params = {
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getTelefono(),
                empleado.getDireccion(),
                empleado.getCedula()
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
    public void delete(Empleado empleado) {
		try {
			con = new Conne();
            con.open();
			String sql = "DELETE FROM persona WHERE id = \"?\"";
            String[] params = {empleado.getId()};
            con.execQuery(sql, params);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			con.close();
		}
    }
}