package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DAO.general.IDao;
import config.Connection.Conne;
import modelos.Servicio;

public class ServicioDao implements IDao<Servicio> {

    private Conne con;

    @Override
    public Servicio setEntity(ResultSet rs) {
        try {
            Servicio servicio = new Servicio();
            servicio.setId(rs.getString("id"));
            servicio.setNombre(rs.getString("nombre"));
            servicio.setTipo(rs.getString("tipo"));
            servicio.setCosto(rs.getFloat("costo"));
            return servicio;
        } catch (SQLException e) {
            String msg = "Error asignando los datos obtenidos\n" + e.getMessage();
            System.out.println(msg);
            return null;
        }
    }

    @Override
    public Servicio get(String id) {
        try {
            con = new Conne();
            con.open();
            String sql = "SELECT s.id, s.nombre, s.tipo FROM servicio s"
            + " WHERE s.id = ? AND s.deleted_at IS NULL";
            String[] params = {id};
            ResultSet rs = con.execQuery(sql, params);

            if (con.isResultSetEmpty(rs)) {
                return null;
            }

            Servicio servicio = new Servicio();
            servicio.setId(rs.getString("id"));
            servicio.setNombre(rs.getString("nombre"));
            servicio.setTipo("tipo");
            return servicio;
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
    public List<Servicio> getAll() {
        try {
            List<Servicio> list = new ArrayList<Servicio>();
            con = new Conne();
            con.open();
            String sql = "SELECT id, nombre, tipo"
                    + " FROM servicio WHERE deleted_at IS NULL";
            ResultSet rs = con.execQuery(sql);
            if (con.isResultSetEmpty(rs)) {
                return list;
            }
            do {
                Servicio servicio = setEntity(rs);
                list.add(servicio);
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
    
    
    public List<Map<String, String>> getAllWithCosto(String fundacionId) {
        try {
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            con = new Conne();
            con.open();
            String sql = "select s.id, fs.fundacion_id, s.nombre, s.tipo, fs.costo from servicio s "
                        +"join fundacion_servicio fs on s.id = fs.servicio_id "
                        +"where fs.fundacion_id = ? AND s.deleted_at IS NULL and fs.deleted_at IS NULL ";
            String[] params = {fundacionId};
            ResultSet rs = con.execQuery(sql, params);
            if (con.isResultSetEmpty(rs)) {
                return list;
            }
            do {
                Map<String, String> map = new HashMap<>();
                map.put("id", rs.getString("id"));
                map.put("fundacionId", rs.getString("fundacion_id"));
                map.put("nombre", rs.getString("nombre"));
                map.put("tipo", rs.getString("tipo"));
                map.put("costo", rs.getString("costo"));
                list.add(map);
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
    public void save(Servicio servicio) {
        try {
            con = new Conne();
            con.open();
            String sql = "INSERT INTO servicio(id, nombre, tipo) VALUES(?,?,?)";
            String[] params = {
                servicio.getId(),
                servicio.getNombre(),
                String.valueOf(servicio.getTipo()),};
            con.execMutation(sql, params);
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            con.close();
        }
    }

    @Override
    public void update(Servicio servicio) {
        try {
            con = new Conne();
            con.open();
            String sql = "UPDATE servicio SET"
                    + " nombre=?, tipo=?"
                    + " WHERE id = ? AND deleted_at IS NULL";
            String[] params = {
                servicio.getNombre(),
                String.valueOf(servicio.getTipo()),
                servicio.getId()

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
    public void delete(Servicio servicio) {
        try {
            con = new Conne();
            con.open();
            Timestamp now = new Timestamp(new Date().getTime());
            String sql = "UPDATE servicio SET deleted_at = '" + now.toString()
                    + "' WHERE id = ? AND deleted_at IS NULL";
            String[] params = {
                servicio.getId()
            };
            con.execMutation(sql, params);
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            con.close();
        }
    }

    public List<Servicio> getAllFromFundacion(String fundacionId) {
        try {
            List<Servicio> list = new ArrayList<Servicio>();
            con = new Conne();
            con.open();
            String sql = "SELECT * FROM servicio s"
                    + " JOIN fundacion_servicio fs"
                    + " ON s.id = fs.servicio_id"
                    + " WHERE fundacion_id = ? AND fs.deleted_at IS NULL AND s.deleted_at IS NULL";
            String[] params = {fundacionId};
            ResultSet rs = con.execQuery(sql, params);
            if (con.isResultSetEmpty(rs)) {
                return list;
            }
            do {
                Servicio servicio = setEntity(rs);
                list.add(servicio);
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

    public List<Servicio> getAllFromSolicitud(String solicitudId) {
        try {
            List<Servicio> list = new ArrayList<Servicio>();
            con = new Conne();
            con.open();
            String sql = "SELECT s.id, s.nombre, s.tipo, sp.costo_generado AS costo FROM servicio s"
                    + " JOIN solicitud_presupuesto sp"
                    + " ON s.id = sp.servicio_id"
                    + " WHERE sp.solicitud_id = ? AND sp.deleted_at IS NULL AND s.deleted_at IS NULL";
            String[] params = {solicitudId};
            ResultSet rs = con.execQuery(sql, params);
            if (con.isResultSetEmpty(rs)) {
                return list;
            }
            do {
                Servicio servicio = setEntity(rs);
                list.add(servicio);
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

    public Servicio getWithCosto(String servicioId, String fundacionId) {
        try {
            con = new Conne();
            con.open();
            String sql = "SELECT s.id, s.nombre, s.tipo, fs.costo FROM servicio s"
            + " JOIN fundacion_servicio fs"
            + " ON s.id = fs.servicio_id"
            + " WHERE s.id = ? AND fs.fundacion_id = ? AND fs.deleted_at IS NULL AND s.deleted_at IS NULL";
            String[] params = {servicioId, fundacionId};
            ResultSet rs = con.execQuery(sql, params);

            if (con.isResultSetEmpty(rs)) {
                return null;
            }

            Servicio servicio = setEntity(rs);
            return servicio;
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
    public Servicio getHistoric(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
