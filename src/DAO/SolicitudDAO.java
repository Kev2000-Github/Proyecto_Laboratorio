
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.List;
import DAO.general.IDao;
import config.Connection.Conne;
import modelos.Servicio;//?
import modelos.Solicitud;

public abstract class SolicitudDAO implements IDao<Solicitud> {
    
    private Conne con;
    
    @Override
    public Solicitud setEntity(ResultSet rs) {
        try {
            
            Solicitud solicitud = new Solicitud();
            
            //strings
            solicitud.setId(rs.getString("id"));
            solicitud.setFundacionDestino(rs.getString("fundacion_id"));
            solicitud.setCosto_total(rs.getFloat("costo_total"));
           
            //objetos
            Servicio servicio = new Servicio();
            String id_servicio = servicio.getId();
            
            //solicitud.setServicio();
            //solicitud.setBeneficiario();
            //solicitud.setEmpleado();
            
            //enums
            solicitud.setPrioridad();
            solicitud.setTipoAyuda();
            solicitud.setEstado();
            
            return solicitud;
        } catch (SQLException e) {
            String msg = "Error asignando los datos obtenidos\n" + e.getMessage();
            System.out.println(msg);
            return null;
        }
    }

    public void SolicitudDao() {
    }

    @Override
    public Solicitud get(String id) {
        try {
            con = new Conne();
            con.open();
            String sql = "SELECT id, cedula, empleado_id, fundacion_id, prioridad, status, costo_total"
                    + " FROM solicitud s WHERE id = ? AND s.deleted_at IS NULL";
            String[] params = { id };
            ResultSet rs = con.execQuery(sql, params);

            if (con.isResultSetEmpty(rs))
                return null;
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
            String sql = "SELECT id, cedula, empleado_id, fundacion_id, prioridad, status, costo_total"
                    + " FROM solicitud s WHERE s.deleted_at IS NULL";
            ResultSet rs = con.execQuery(sql);
            if (con.isResultSetEmpty(rs))
                return list;
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
            String sql = "INSERT INTO solicitud(id, cedula, empleado_id,fundacion_id,prioridad,status, costo_total?, created_at?) VALUES(?,?,?,?,?,?,?,?)";
            String[] params = {
                    solicitud.getId(),
                
                
                    solicitud.getFundacionDestino(),

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
    public void update(Solicitud solicitud) {
        try {
            con = new Conne();
            con.open();
            String sql = "UPDATE solicitud SET"
                    + " tema=?, direccion=?, organismo=?, fecha=?"
                    + " WHERE id = ? AND deleted_at IS NULL";
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

    
}

