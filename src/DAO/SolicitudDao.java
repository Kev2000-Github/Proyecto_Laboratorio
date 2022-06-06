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

import modelos.Solicitud;
import utils.Constants;

public class SolicitudDao implements IDao<Solicitud> {

    private Conne con;

    @Override
    public Solicitud setEntity(ResultSet rs) {
        try {
            Solicitud solicitud = new Solicitud();
            solicitud.setId(rs.getString("id"));
            solicitud.setEmpleadoId(rs.getString("empleado_id"));
            solicitud.setFundacionId(rs.getString("fundacion_id"));
            solicitud.setBeneficiarioId(rs.getString("beneficiario_id"));
            solicitud.setPrioridad(Constants.prioridadEnum.valueOf(rs.getString("prioridad")));
            solicitud.setStatus(Constants.estadoEnum.valueOf(rs.getString("status")));
            return solicitud;
        } catch (SQLException e) {
            String msg = "Error asignando los datos obtenidos\n" + e.getMessage();
            System.out.println(msg);
            return null;
        }
    }

    @Override
    public Solicitud get(String id) {
        try {
            con = new Conne();
            con.open();
            String sql = "SELECT id, empleado_id, fundacion_id, beneficiario_id, prioridad, status"
                    + " FROM solicitud f WHERE id = ? AND f.deleted_at IS NULL";
            String[] params = {id};
            ResultSet rs = con.execQuery(sql, params);

            if (con.isResultSetEmpty(rs)) {
                return null;
            }
            Solicitud solicitud = setEntity(rs);
            return solicitud;
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
    public List<Solicitud> getAll() {
        try {
            List<Solicitud> list = new ArrayList<Solicitud>();
            con = new Conne();
            con.open();
            String sql = "SELECT id, empleado_id, fundacion_id, beneficiario_id, prioridad, status"
                    + " FROM solicitud WHERE deleted_at IS NULL";
            ResultSet rs = con.execQuery(sql);
            if (con.isResultSetEmpty(rs)) {
                return list;
            }
            do {
                Solicitud solicitud = setEntity(rs);
                list.add(solicitud);
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
    public void save(Solicitud solicitud) {
        try {
            con = new Conne();
            con.open();
            String now = "'" + (new Timestamp(new Date().getTime())).toString() + "'";
            String prioridad = "'" + solicitud.getPrioridad().toString() + "'";
            String status = "'" + solicitud.getStatus().toString() + "'";
            String values = String.join(",","?","?","?","?", prioridad, status, now, now);
            String sql = "INSERT INTO solicitud(id, empleado_id, fundacion_id, beneficiario_id, prioridad, status, created_at, updated_at)"
                       + " VALUES(" + values + ")";
            String[] params = {
                solicitud.getId(),
                solicitud.getEmpleadoId(),
                solicitud.getFundacionId(),
                solicitud.getBeneficiarioId()
            };
            con.execMutation(sql, params);
            // ?servicio_id
            (solicitud.getServicios()).forEach(servicio -> {
                String sql2 = "INSERT INTO solicitud_presupuesto(solicitud_id, servicio_id, costo_generado)"
                            + "VALUES(?,?," + servicio.getCosto() +")";
                String[] localParams = {
                    solicitud.getId(),
                    servicio.getId()
                };
                con.execMutation(sql2, localParams);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            con.close();
        }
    }

    @Override
    public void delete(Solicitud solicitud) {
        try {
            con = new Conne();
            con.open();
            Timestamp now = new Timestamp(new Date().getTime());
            String sql = "UPDATE solicitud SET deleted_at = '" + now.toString()
                    + "' WHERE id = ? AND deleted_at IS NULL";
            String[] params = {
                solicitud.getId()
            };
            con.execMutation(sql, params);
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            con.close();
        }
    }

    public List<Solicitud> getAllPending() {
        try {
            List<Solicitud> list = new ArrayList<Solicitud>();
            con = new Conne();
            con.open();
            String sql = "SELECT id, empleado_id, fundacion_id, beneficiario_id, prioridad, status"
                    + " FROM solicitud WHERE status = 'pendiente' AND deleted_at IS NULL";
            ResultSet rs = con.execQuery(sql);
            if (con.isResultSetEmpty(rs)) {
                return list;
            }
            do {
                Solicitud solicitud = setEntity(rs);
                list.add(solicitud);
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

        public List<Solicitud> getAllSolicitud() {
        try {
            List<Solicitud> list = new ArrayList<Solicitud>();
            con = new Conne();
            con.open();
            String sql = "SELECT id, empleado_id, fundacion_id, beneficiario_id, prioridad, status"
                    + " FROM solicitud WHERE (status = 'pendiente' OR status = 'rechazado' OR status = 'aprobado') AND deleted_at IS NULL";
            ResultSet rs = con.execQuery(sql);
            if (con.isResultSetEmpty(rs)) {
                return list;
            }
            do {
                Solicitud solicitud = setEntity(rs);
                list.add(solicitud);
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

               
         public List<Solicitud> getAllSolicitudFilter(String fundacionId) {
        try {
            List<Solicitud> list = new ArrayList<Solicitud>();
            con = new Conne();
            con.open();
            String sql = "SELECT * "
                    + " FROM solicitud WHERE fundacion_id = ? AND deleted_at IS NULL";
            String[] params = {fundacionId};
            ResultSet rs = con.execQuery(sql,params);
            if (con.isResultSetEmpty(rs)) {
                return list;
            }
            do {
                Solicitud solicitud = setEntity(rs);
                list.add(solicitud);
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

        
    public int countNumberSolicitudes(String beneficiarioId, String status, Date from, Date to) {
        try {
            con = new Conne();
            con.open();
            String fromFormatted = new Timestamp(from.getTime()).toString();
            String toFormatted = new Timestamp(to.getTime()).toString();
            String statusFormatted = Constants.estadoEnum.valueOf(status).toString();
            String sql = "SELECT COUNT(*) AS count FROM solicitud s"
                    + " JOIN beneficiario b"
                    + " ON s.beneficiario_id = b.id"
                    + " WHERE status = '" + statusFormatted + "' and created_at between '" + fromFormatted + "' and '"+ toFormatted +"'"
                    + " AND b.id = ?"
                    + " AND s.deleted_at IS NULL AND b.deleted_at IS NULL";
            String[] params = {
                beneficiarioId
            };
            ResultSet rs = con.execQuery(sql, params);
            if (con.isResultSetEmpty(rs)) {
                return 0;
            }
            int count = rs.getInt("count");
            return count;
        } catch (SQLException e) {
            String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            e.printStackTrace();
            return 0;
        } finally {
            con.close();
        }
    }

    public Solicitud getHighestPriority(String id) {
        try {
            con = new Conne();
            con.open();
            String sql = "SELECT id, empleado_id, fundacion_id, beneficiario_id, prioridad, status"
                    + " FROM solicitud f WHERE id = ? AND f.deleted_at IS NULL ORDER BY prioridad DESC LIMIT 1";
            String[] params = {id};
            ResultSet rs = con.execQuery(sql, params);

            if (con.isResultSetEmpty(rs)) {
                return null;
            }
            Solicitud solicitud = setEntity(rs);
            return solicitud;
        } catch (Exception e) {
            String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            e.printStackTrace();
            return null;
        } finally {
            con.close();
        }
    }

    public void updateStatus(String solicitudId, String newStatus) {
        try {
            con = new Conne();
            con.open();
            String sql = "UPDATE solicitud SET"
                    + " status='" + newStatus + "'"
                    + " WHERE id = ? AND deleted_at IS NULL";
            String[] params = { solicitudId };
            con.execMutation(sql, params);
            System.out.println("updateStatus-solicitud");
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            con.close();
        }
    }

    public List<Map<String, String>> getListaPresupuestos(String status) {
        try {
            List<Map<String, String>> result = new ArrayList<Map<String, String>>();
            con = new Conne();
            con.open();
            String sql = "SELECT * FROM view_lista_presupuestos WHERE status = '" + status +"'";
            ResultSet rs = con.execQuery(sql);
            if (con.isResultSetEmpty(rs)) {
                return result;
            }
            do{
                Map<String, String> map = new HashMap<>();
                map.put("solicitud_id",rs.getString("id"));
                map.put("fundacion",rs.getString("fundacion"));
                map.put("encargado",rs.getString("empleado"));
                map.put("beneficiario",rs.getString("beneficiario"));
                map.put("presupuesto_total",rs.getString("presupuesto_total"));
                map.put("status",rs.getString("status"));
                result.add(map);
            }while(rs.next());
            return result;
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
    public void update(Solicitud t) {
        // TODO Auto-generated method stub

    }

    @Override
    public Solicitud getHistoric(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
