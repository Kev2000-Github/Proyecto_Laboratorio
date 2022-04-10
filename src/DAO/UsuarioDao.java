package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.general.IDao;
import modelos.Empleado;
import modelos.Rol;
import modelos.Usuario;
import config.Connection.Conne;

public class UsuarioDao implements IDao<Usuario> {    
    private Conne con;
    
    @Override
    public Usuario setEntity(ResultSet rs){
        try{
            RolDao rolDao = new RolDao();
            Rol rol = rolDao.get(rs.getString("rol_id"));
            EmpleadoDao empleadoDao = new EmpleadoDao();
            Empleado emp = empleadoDao.get(rs.getString("empleado_id"));

            Usuario usuario = new Usuario();
            usuario.setId(rs.getString("id"));	
            usuario.setUsername(rs.getString("username"));
            usuario.setPassword(rs.getString("password"));
            usuario.setRol(rol);
            usuario.setEmpleado(emp);
            return usuario;
        }
        catch(SQLException e){
            String msg = "Error asignando los datos obtenidos\n" + e.getMessage();
            System.out.println(msg);
            return null;
        }
    }

    @Override
    public Usuario get(String id) {
		try {
			con = new Conne();
            con.open();
            String sql = "SELECT * FROM usuario where id =? AND deleted_at IS NULL";
            String[] params = {id};
            ResultSet rs = con.execQuery(sql, params);
            if(con.isResultSetEmpty(rs)) return null;
            Usuario usuario = setEntity(rs);
            return usuario;	
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
    public List<Usuario> getAll() {
		try {
            List<Usuario> list = new ArrayList<Usuario>();
			con = new Conne();
            con.open();
			String sql = "SELECT * FROM usuario WHERE deleted_at IS NULL";
			ResultSet rs = con.execQuery(sql);
            if(con.isResultSetEmpty(rs)) return null;
			do {
                Usuario usuario = setEntity(rs);
				list.add(usuario);
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
    public void save(Usuario usuario) {
		try {
			con = new Conne();
            con.open();
			String sql = "INSERT INTO usuario(id, empleado_id, rol_id, username, password) VALUES(?,?,?,?,?)";
            String[] params = {
                usuario.getId(),
                usuario.getEmpleado().getId(),
                usuario.getRol().getId(),
                usuario.getUsername(),
                usuario.getPassword()
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
    public void update(Usuario usuario) {
		try {
			con = new Conne();
            con.open();
            String sql = "UPDATE usuario SET"
                + " empleado_id=?, rol_id=?, username=?, password=? WHERE id = ? AND deleted_at IS NULL";
            String[] params = {
                usuario.getEmpleado().getId(),
                usuario.getRol().getId(),
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getId()
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
    public void delete(Usuario usuario) {
		try {
			con = new Conne();
            con.open();
            Timestamp now = new Timestamp(new Date().getTime());
			String sql = "UPDATE usuario SET deleted_at = " + now.toString() + " WHERE id = ? AND deleted_at IS NULL";
            String[] params = {usuario.getId()};
            con.execMutation(sql, params);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			con.close();
		}    
    }

}