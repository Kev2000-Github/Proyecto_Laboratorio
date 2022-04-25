package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.general.IDao;
import config.Connection.Conne;
import modelos.Beneficiario;

public class BeneficiarioDao implements IDao<Beneficiario> {
    private Conne con;

    @Override
    public Beneficiario setEntity(ResultSet rs){
        try{
            Beneficiario beneficiario = new Beneficiario();
            beneficiario.setCedula(rs.getString("cedula"));	
            beneficiario.setNombre(rs.getString("nombre"));	
            beneficiario.setApellido(rs.getString("apellido"));	
            beneficiario.setDireccion(rs.getString("direccion"));	
            beneficiario.setTelefono(rs.getString("telefono"));
            beneficiario.setId(rs.getString("id"));
            return beneficiario;
        }
        catch(SQLException e){
            String msg = "Error asignando los datos obtenidos\n" + e.getMessage();
            System.out.println(msg);
            return null;
        }
    }

    public BeneficiarioDao() {}
    
    @Override
    public Beneficiario get(String id) {
		try {
			con = new Conne();
            con.open();
            String sql = "SELECT id, nombre, apellido, p.cedula, direccion, telefono"
                + " FROM beneficiario e JOIN persona p ON e.cedula = p.cedula where id =?"
                + " AND e.deleted_at IS NULL AND p.deleted_at IS NULL";
            String[] params = {id};
            ResultSet rs = con.execQuery(sql, params);
            
            if(con.isResultSetEmpty(rs)) return null;
            Beneficiario beneficiario = setEntity(rs);
            return beneficiario;	
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
    public List<Beneficiario> getAll() {
		try {
            List<Beneficiario> list = new ArrayList<Beneficiario>();
			con = new Conne();
            con.open();
            String sql = "SELECT id, nombre, apellido, p.cedula, direccion, telefono"
                + " FROM beneficiario e JOIN persona p ON e.cedula = p.cedula AND e.deleted_at IS NULL AND p.deleted_at IS NULL";			
            ResultSet rs = con.execQuery(sql);
            if(con.isResultSetEmpty(rs)) return list;
			do {
                Beneficiario beneficiario = setEntity(rs);
				list.add(beneficiario);
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
    public void save(Beneficiario beneficiario) {
		try {
			con = new Conne();
            con.open();
			String sql = "INSERT INTO Persona(cedula, nombre, apellido, direccion, telefono) VALUES(?, ?,?,?,?)";
            String[] params = {
                beneficiario.getCedula(),
                beneficiario.getNombre(),
                beneficiario.getApellido(),
                beneficiario.getDireccion(),
                beneficiario.getTelefono()
            };
            con.execMutation(sql, params);

            String sqlRel = "INSERT INTO Beneficiario(cedula) VALUES(?)";
            String[] paramsRel = {
                beneficiario.getCedula(),
            };
            con.execMutation(sqlRel, paramsRel);

            
            //falta insertar en beneficiario
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			con.close();
		}
    }
    
    @Override
    public void update(Beneficiario beneficiario) {
		try {
			con = new Conne();
            con.open();
            String sql = "UPDATE persona SET"
                + " nombre=?, apellido=?, telefono=?, direccion=?"
                + " WHERE cedula = ? AND deleted_at IS NULL";
            String[] params = {
                beneficiario.getNombre(),
                beneficiario.getApellido(),
                beneficiario.getTelefono(),
                beneficiario.getDireccion(),
                beneficiario.getCedula()
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
    public void delete(Beneficiario beneficiario) {
		try {
			con = new Conne();
            con.open();
            Timestamp now = new Timestamp(new Date().getTime());
			String sql = "UPDATE beneficiario SET deleted_at = '" + now.toString() + "' WHERE id = ? AND deleted_at IS NULL";
            String[] params = {
                beneficiario.getId()
            };
            con.execMutation(sql, params);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			con.close();
		}
    }

    public List<Beneficiario> getAllFromFundacion(String fundacionId) {
		try {
            List<Beneficiario> list = new ArrayList<Beneficiario>();
			con = new Conne();
            con.open();
            String sql = "SELECT id, nombre, apellido, p.cedula, direccion, telefono"
                + " FROM beneficiario e JOIN persona p ON e.cedula = p.cedula"
                + " WHERE e.fundacion_id = ? AND e.deleted_at IS NULL AND p.deleted_at IS NULL";
            String[] params = {fundacionId};
            ResultSet rs = con.execQuery(sql, params);
            if(con.isResultSetEmpty(rs)) return list;
			do {
                Beneficiario beneficiario = setEntity(rs);
				list.add(beneficiario);
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

    public Beneficiario getByCedula(String cedula) {
		try {
			con = new Conne();
            con.open();
            String sql = "SELECT id, nombre, apellido, p.cedula, direccion, telefono"
                + " FROM beneficiario e JOIN persona p ON e.cedula = p.cedula where p.cedula =?"
                + " AND e.deleted_at IS NULL AND p.deleted_at IS NULL";
            String[] params = {cedula};
            ResultSet rs = con.execQuery(sql, params);
            
            if(con.isResultSetEmpty(rs)) return null;
            Beneficiario beneficiario = setEntity(rs);
            return beneficiario;	
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
}