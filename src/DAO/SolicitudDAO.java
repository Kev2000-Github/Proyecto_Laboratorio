package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import DAO.general.IDao;
import config.Connection.Conne;
import modelos.Beneficiario;
import modelos.Empleado;
import modelos.Servicio;//?
import modelos.Solicitud;
import java.util.Date;
import utils.Constants;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            solicitud.setStatus(Constants.estadoEnum.valueOf(rs.getString("tipo")));
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

    @Override
    public void update(Solicitud t) {
        // TODO Auto-generated method stub

    }
}
