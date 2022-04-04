package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

import modelos.Usuario;
import config.Connection.Conne;
//MOCK IMPLEMENTATION, se necesita conexion a bd para obtener la data real
public class UsuarioDao implements IDao<Usuario> {
    private Vector<Usuario> usuarios = new Vector();
    
    public UsuarioDao() {
        usuarios.add(new Usuario("kevin","admin"));
        usuarios.add(new Usuario("oswald","root"));
    }
    
    @Override
    public Usuario get(String id) {
		PreparedStatement stmt = null;
        Connection conn = null;
		Usuario usuario = null;
		try {
			conn = Conne.Conexion();
            //TODO: cambiarlo porque es propenso a SQL injection
			stmt = conn.prepareStatement("SELECT * FROM usuario where id =\"" + id + "\"");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
                usuario = new Usuario();
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			Conne.cerrarConexion(conn);
            Conne.cerrarStatement(stmt);
		}
		
		return usuario;
    }
    
    @Override
    public List<Usuario> getAll() {
		PreparedStatement stmt = null;
        Connection conn = null;
		List<Usuario> list = new ArrayList<Usuario>();
		try {
			conn = Conne.Conexion();
			stmt = conn.prepareStatement("SELECT username FROM usuario");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
                String username = rs.getString("username");
				Usuario user = new Usuario(username, "");				
				list.add(user);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			Conne.cerrarConexion(conn);
            Conne.cerrarStatement(stmt);
		}
		
		return list;
    }
    
    @Override
    public void save(Usuario user) {
        usuarios.add(user);
    }
    
    @Override
    public void update(Usuario user, String[] params) {
        user.setUsername(Objects.requireNonNull(
          params[0], "Name cannot be null"));
        user.setPassword(Objects.requireNonNull(
          params[1], "Email cannot be null"));
        
        usuarios.add(user);
    }
    
    @Override
    public void delete(Usuario user) {
        usuarios.remove(user);
    }
}