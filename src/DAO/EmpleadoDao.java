package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.general.IDao;
import config.Connection.Conne;
import modelos.Empleado;

public class EmpleadoDao implements IDao<Empleado> {

    private Conne con;

    @Override
    public Empleado setEntity(ResultSet rs) {
        try {
            Empleado empleado = new Empleado();
            empleado.setCedula(rs.getString("cedula"));
            empleado.setId(rs.getString("id"));
            empleado.setFundacionId(rs.getString("fundacion_id"));
            empleado.setNombre(rs.getString("nombre"));
            empleado.setCedula(rs.getString("cedula"));
            empleado.setApellido(rs.getString("apellido"));
            empleado.setDireccion(rs.getString("direccion"));
            empleado.setTelefono(rs.getString("telefono"));
            empleado.setCorreo(rs.getString("correo"));
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
            e.printStackTrace();
            return null;
        } finally {
            con.close();
        }
    }

        @Override
    public Empleado getHistoric(String cedula) {
        try {
            con = new Conne();
            con.open();
            String sql = "SELECT id, nombre, apellido, p.cedula, direccion, telefono, p.correo, e.fundacion_id"
                    + " FROM empleado e JOIN persona p ON e.cedula = p.cedula where e.cedula =?";
            String[] params = {cedula};
            ResultSet rs = con.execQuery(sql, params);

            if (con.isResultSetEmpty(rs)) {
                return null;
            }
            Empleado empleado = setEntity(rs);
            return empleado;
        } catch (Exception e) {
            String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            e.printStackTrace();
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
            e.printStackTrace();
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
                empleado.getCedula(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getDireccion(),
                empleado.getTelefono(),
                empleado.getCorreo(),};
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
                    + " nombre=?, apellido=?, telefono=?, direccion=?, correo=?, deleted_at=NULL"
                    + " WHERE cedula = ?";
            String[] params = {
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getTelefono(),
                empleado.getDireccion(),
                empleado.getCorreo(),
                empleado.getCedula()
            };
            con.execMutation(sql, params);

            String sqlFundacion = "UPDATE empleado SET"
                    + " fundacion_id=?, deleted_at=NULL"
                    + " WHERE cedula = ?";
            String[] paramsFundacion = {
                empleado.getFundacionId(),
                empleado.getCedula()
            };
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
            String sql2 = "UPDATE persona SET deleted_at = '" + now.toString() + "' WHERE cedula = ? AND deleted_at IS NULL";
            String[] params2 = {
              empleado.getCedula()
            };
            con.execMutation(sql2, params2);
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
            e.printStackTrace();
            return null;
        } finally {
            con.close();
        }
    }

    public Empleado getByCedula(String cedula) {
        try {
            con = new Conne();
            con.open();
            String sql = "SELECT id, nombre, apellido, p.cedula, direccion, telefono, p.correo, e.fundacion_id"
                    + " FROM empleado e JOIN persona p ON e.cedula = p.cedula where e.cedula =?"
                    + " AND e.deleted_at IS NULL AND p.deleted_at IS NULL";
            String[] params = {cedula};
            ResultSet rs = con.execQuery(sql, params);

            if (con.isResultSetEmpty(rs)) {
                return null;
            }
            Empleado empleado = setEntity(rs);
            return empleado;
        } catch (Exception e) {
            String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            e.printStackTrace();
            return null;
        } finally {
            con.close();
        }
    }
}
