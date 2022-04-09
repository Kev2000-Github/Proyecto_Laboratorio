package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.general.IDao;
import config.Connection.Conne;
import modelos.Permiso;
import modelos.Rol;

public class RolDao implements IDao<Rol> {
    private Conne con;

    @Override
    public Rol setEntity(ResultSet rs){
        try{
            Rol rol = new Rol();
            rol.setId(rs.getString("id"));
            rol.setNombre(rs.getString("nombre"));	
            PermisoDao permisoDao = new PermisoDao();
            List<Permiso> permisos = permisoDao.getPermisoDeRol(rol.getId());
            rol.setPermisos(permisos);
            return rol;
        }
        catch(SQLException e){
            String msg = "Error asignando los datos obtenidos\n" + e.getMessage();
            System.out.println(msg);
            return null;
        }
    }

    @Override
    public Rol get(String id) {
		try {
			con = new Conne();
            con.open();
            String sql = "SELECT * FROM rol where id =\"?\"";
            String[] params = {id};
            ResultSet rs = con.execQuery(sql, params);
            
            rs.next();
            Rol rol = setEntity(rs);
            return rol;	
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
    public List<Rol> getAll() {
		try {
            List<Rol> list = new ArrayList<Rol>();
			con = new Conne();
            con.open();
			String sql = "SELECT * FROM rol";
			ResultSet rs = con.execQuery(sql);
			while (rs.next()) {
                Rol rol = setEntity(rs);
				list.add(rol);
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
    public void save(Rol rol) {
		try {
			con = new Conne();
            con.open();
			String sql = "INSERT INTO Rol(id, nombre) VALUE(?,?)";
            String[] params = {
                rol.getId(),
                rol.getNombre()
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
    public void update(Rol rol) {
		try {
			con = new Conne();
            con.open();
            String sql = "UPDATE rol SET nombre=\"?\"";
            String[] params = {
                rol.getNombre()
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
    public void delete(Rol rol) {
		try {
			con = new Conne();
            con.open();
			String sql = "DELETE FROM rol WHERE id = \"?\"";
            String[] params = {rol.getId()};
            con.execQuery(sql, params);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			con.close();
		}    
    }

}
