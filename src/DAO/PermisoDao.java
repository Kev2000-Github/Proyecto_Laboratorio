package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.general.IDao;
import config.Connection.Conne;
import modelos.Permiso;

public class PermisoDao implements IDao<Permiso> {
    private Conne con;

    @Override
    public Permiso setEntity(ResultSet rs){
        try{
            Permiso permiso = new Permiso();
            permiso.setId(rs.getString("id"));	
            permiso.setDescripcion(rs.getString("descripcion"));	
            return permiso;
        }
        catch(SQLException e){
            String msg = "Error asignando los datos obtenidos\n" + e.getMessage();
            System.out.println(msg);
            return null;
        }
    }

    @Override
    public Permiso get(String id) {
		try {
			con = new Conne();
            con.open();
            String sql = "SELECT * FROM permiso where id =?";
            String[] params = {id};
            ResultSet rs = con.execQuery(sql, params);
            
            if(con.isResultSetEmpty(rs)) return null;
            Permiso permiso = setEntity(rs);
            return permiso;	
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
    public List<Permiso> getAll() {
		try {
            List<Permiso> list = new ArrayList<Permiso>();
			con = new Conne();
            con.open();
			String sql = "SELECT * FROM permiso";
			ResultSet rs = con.execQuery(sql);
            if(con.isResultSetEmpty(rs)) return null;
			do {
                Permiso permiso = setEntity(rs);
				list.add(permiso);
			}while (rs.next());
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

    public List<Permiso> getPermisoDeRol(String rolId) {
		try {
            List<Permiso> list = new ArrayList<Permiso>();
			con = new Conne();
            con.open();
			String sql = "SELECT p.id, p.descripcion FROM rol_permiso rp"
                + " JOIN permiso p ON rp.permiso_id = p.id"
                + " WHERE rol_id = ?";
            String[] params = {
                rolId
            };
			ResultSet rs = con.execQuery(sql, params);
            if(con.isResultSetEmpty(rs)) return null;
			do {
                Permiso permiso = setEntity(rs);
				list.add(permiso);
			}while (rs.next());
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
    public void save(Permiso permiso) {
		try {
			con = new Conne();
            con.open();
			String sql = "INSERT INTO Permiso(id, descripcion) VALUE(?,?)";
            String[] params = {
                permiso.getId(),
                permiso.getDescripcion()
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
    public void update(Permiso permiso) {
		try {
			con = new Conne();
            con.open();
            String sql = "UPDATE permiso SET descripcion=?";
            String[] params = {
                permiso.getDescripcion()
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
    public void delete(Permiso permiso) {
		try {
			con = new Conne();
            con.open();
			String sql = "DELETE FROM permiso WHERE id = ?";
            String[] params = {permiso.getId()};
            con.execQuery(sql, params);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			con.close();
		}    
    }

}
