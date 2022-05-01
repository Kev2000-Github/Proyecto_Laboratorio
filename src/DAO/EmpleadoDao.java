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
import modelos.Empleado;
import modelos.Persona;

public class EmpleadoDao implements IDao<Empleado> {

    private Conne con;

    @Override
    public Empleado setEntity(ResultSet rs) {
        try {
            Empleado empleado = new Empleado();
            empleado.setCedula(rs.getString("cedula"));
            empleado.setId(rs.getString("id"));
            empleado.setFundacionId(rs.getString("fundacion_id"));
            Persona persona = new Persona();
            persona.setNombre(rs.getString("nombre"));
            persona.setCedula(rs.getString("cedula"));
            persona.setApellido(rs.getString("apellido"));
            persona.setDireccion(rs.getString("direccion"));
            persona.setTelefono(rs.getString("telefono"));
            persona.setCorreo(rs.getString("correo"));
            empleado.setPersona(persona);
            return empleado;
        } catch (SQLException e) {
            String msg = "Error asignando los datos obtenidos\n" + e.getMessage();
            System.out.println(msg);
            return null;
        }
    }

    public EmpleadoDao() {
    }

    @Override
    public Empleado get(String id) {
        try {
            con = new Conne();
            con.open();
            String sql = "SELECT id, nombre, apellido, p.cedula, direccion, telefono, p.correo, e.fundacion_id"
                    + " FROM empleado e JOIN persona p ON e.cedula = p.cedula where id =?"
                    + " AND e.deleted_at IS NULL AND p.deleted_at IS NULL";
            String[] params = {id};
            ResultSet rs = con.execQuery(sql, params);

            if (con.isResultSetEmpty(rs)) {
                return null;
            }
            Empleado empleado = setEntity(rs);
            return empleado;
        } catch (Exception e) {
            String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            return null;
        } finally {
            con.close();
        }
    }

    @Override
    public List<Empleado> getAll() {
        try {
            List<Empleado> list = new ArrayList<Empleado>();
            con = new Conne();
            con.open();
            String sql = "SELECT id, nombre, apellido, p.cedula, direccion, telefono,p.correo, e.fundacion_id"
                    + " FROM empleado e JOIN persona p ON e.cedula = p.cedula AND e.deleted_at IS NULL AND p.deleted_at IS NULL";
            ResultSet rs = con.execQuery(sql);
            if (con.isResultSetEmpty(rs)) {
                return list;
            }
            do {
                Empleado empleado = setEntity(rs);
                list.add(empleado);
            } while (rs.next());
            return list;
        } catch (SQLException e) {
            String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            return null;
        } finally {
            con.close();
        }
    }
 @Override
    public void save(Empleado empleado) {
        try {
            con = new Conne();
            con.open();
            String sql = "INSERT INTO persona(cedula, nombre, apellido, direccion, telefono, correo) VALUES(?,?,?,?,?,?)";
            String[] params = {
                empleado.getPersona().getCedula(),
                empleado.getPersona().getNombre(),
                empleado.getPersona().getApellido(),
                empleado.getPersona().getDireccion(),
                empleado.getPersona().getTelefono(),
                empleado.getPersona().getCorreo(),};
            con.execMutation(sql, params);
            String sqlRel = "INSERT INTO empleado(id,cedula,fundacion_id) VALUES(?,?,?)";
            String[] paramsRel = {
                empleado.getId(), empleado.getCedula(), empleado.getFundacionId()};
            con.execMutation(sqlRel, paramsRel);
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
                    + " nombre=?, apellido=?, telefono=?, direccion=?, correo=?"
                    + " WHERE cedula = ? AND deleted_at IS NULL";
            String[] params = {
                empleado.getPersona().getNombre(),
                empleado.getPersona().getApellido(),
                empleado.getPersona().getTelefono(),
                empleado.getPersona().getDireccion(),
                empleado.getPersona().getCorreo(),
                empleado.getPersona().getCedula()
            };
            con.execMutation(sql, params);

            String sqlFundacion = "UPDATE empleado SET"
                    + " fundacion_id=?"
                    + " WHERE cedula = ? AND deleted_at IS NULL";
            String[] paramsFundacion = {
                empleado.getFundacionId()};
            con.execMutation(sqlFundacion, paramsFundacion);
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
            Timestamp now = new Timestamp(new Date().getTime());
            String sql = "UPDATE empleado SET deleted_at = '" + now.toString() + "' WHERE id = ? AND deleted_at IS NULL";
            String[] params = {
                empleado.getId()
            };
            con.execMutation(sql, params);
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            con.close();
        }
    }

    public List<Empleado> getAllFromFundacion(String fundacionId) {
        try {
            List<Empleado> list = new ArrayList<Empleado>();
            con = new Conne();
            con.open();
            String sql = "SELECT id, nombre, apellido, p.cedula, direccion, telefono"
                    + " FROM empleado e JOIN persona p ON e.cedula = p.cedula"
                    + " WHERE e.fundacion_id = ? AND e.deleted_at IS NULL AND p.deleted_at IS NULL";
            String[] params = {fundacionId};
            ResultSet rs = con.execQuery(sql, params);
            if (con.isResultSetEmpty(rs)) {
                return list;
            }
            do {
                Empleado empleado = setEntity(rs);
                list.add(empleado);
            } while (rs.next());
            return list;
        } catch (SQLException e) {
            String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            return null;
        } finally {
            con.close();
        }
    }
}
