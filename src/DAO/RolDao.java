package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
            String sql = "SELECT * FROM rol where id =? AND deleted_at IS NULL";
            String[] params = {id};
            ResultSet rs = con.execQuery(sql, params);
            
            if(con.isResultSetEmpty(rs)) return null;
            Rol rol = setEntity(rs);
            return rol;	
		} 
        catch (Exception e) {
			String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            e.printStackTrace();
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
			String sql = "SELECT * FROM rol WHERE deleted_at IS NULL";
			ResultSet rs = con.execQuery(sql);
            if(con.isResultSetEmpty(rs)) return list;
			do {
                Rol rol = setEntity(rs);
				list.add(rol);
			} while (rs.next());
            return list;
		} 
        catch (SQLException e) {
			String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            e.printStackTrace();
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
			String sql = "INSERT INTO Rol(id, nombre) VALUES(?,?)";
            String[] params = {
                rol.getId(),
                rol.getNombre()
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
    public void update(Rol rol) {
		try {
			con = new Conne();
            con.open();
            String sql = "UPDATE rol SET nombre=? WHERE id = ? AND deleted_at IS NULL";
            String[] params = {
                rol.getNombre(),
                rol.getId()
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
    public void delete(Rol rol) {
		try {
			con = new Conne();
            con.open();
            Timestamp now = new Timestamp(new Date().getTime());
			String sql = "UPDATE rol SET deleted_at = " + now.toString() + " WHERE id = ? AND deleted_at IS NULL";
            String[] params = {rol.getId()};
            con.execMutation(sql, params);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			con.close();
		}    
    }


    public Rol getByUser(String userId) {
		try {
			con = new Conne();
            con.open();
            String sql = "SELECT r.id, r.nombre FROM usuario u JOIN rol r"
                        + " on u.rol_id = r.id WHERE u.id = ?"
                        + " AND u.deleted_at is NULL AND r.deleted_at is NULL";
            String[] params = {userId};
            ResultSet rs = con.execQuery(sql, params);
            
            if(con.isResultSetEmpty(rs)) return null;
            Rol rol = setEntity(rs);
            return rol;	
		} 
        catch (Exception e) {
			String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            e.printStackTrace();
            return null;
		} 
        finally {
            con.close();
        }		
    }

    @Override
    public Rol getHistoric(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
