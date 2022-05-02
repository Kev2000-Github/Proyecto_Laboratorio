package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.general.IDao;
import config.Connection.Conne;
import modelos.Fundacion;
import utils.Utils;

public class FundacionDao implements IDao<Fundacion> {
    private Conne con;

    @Override
    public Fundacion setEntity(ResultSet rs) {
        try {
            Fundacion fundacion = new Fundacion();
            fundacion.setNombre(rs.getString("nombre"));
            fundacion.setPresupuesto(rs.getFloat("presupuesto"));
            fundacion.setPorcentajePartidoAnual(rs.getFloat("porcentaje_partido_anual"));
            fundacion.setId(rs.getString("id"));
            return fundacion;
        } catch (SQLException e) {
            String msg = "Error asignando los datos obtenidos\n" + e.getMessage();
            System.out.println(msg);
            return null;
        }
    }

    @Override
    public Fundacion get(String id) {
        try {
            con = new Conne();
            con.open();
            String sql = "SELECT id, nombre, presupuesto, porcentaje_partido_anual"
                    + " FROM fundacion f WHERE id = ? AND f.deleted_at IS NULL";
            String[] params = { id };
            ResultSet rs = con.execQuery(sql, params);

            if (con.isResultSetEmpty(rs))
                return null;
            Fundacion fundacion = setEntity(rs);
            return fundacion;
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
    public List<Fundacion> getAll() {
        try {
            List<Fundacion> list = new ArrayList<Fundacion>();
            con = new Conne();
            con.open();
            String sql = "SELECT id, nombre, presupuesto, porcentaje_partido_anual"
                    + " FROM fundacion WHERE deleted_at IS NULL";
            ResultSet rs = con.execQuery(sql);
            if (con.isResultSetEmpty(rs))
                return list;
            do {
                Fundacion fundacion = setEntity(rs);
                list.add(fundacion);
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
    public void save(Fundacion fundacion) {
        try {
            con = new Conne();
            con.open();
            String sql = "INSERT INTO fundacion(id, nombre, presupuesto, porcentaje_partido_anual) VALUES(?,?,?,?)";
            String[] params = {
                    fundacion.getId(),
                    fundacion.getNombre(),
                    String.valueOf(fundacion.getPresupuesto()),
                    String.valueOf(fundacion.getPorcentajePartidoAnual())
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
    public void update(Fundacion fundacion) {
        try {
            con = new Conne();
            con.open();
            String sql = "UPDATE fundacion SET"
                    + " nombre=?, presupuesto=?, porcentaje_partido_anual=?"
                    + " WHERE id = ? AND deleted_at IS NULL";
            String[] params = {
                    fundacion.getNombre(),
                    String.valueOf(fundacion.getPresupuesto()),
                    String.valueOf(fundacion.getPorcentajePartidoAnual()),
                    fundacion.getId()

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
    public void delete(Fundacion fundacion) {
        try {
            con = new Conne();
            con.open();
            Timestamp now = new Timestamp(new Date().getTime());
            String sql = "UPDATE fundacion SET deleted_at = '" + now.toString()
                    + "' WHERE id = ? AND deleted_at IS NULL";
            String[] params = {
                    fundacion.getId()
            };
            con.execMutation(sql, params);
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            con.close();
        }
    }

    public Fundacion getFromEmpleado(String empleadoId) {
        try {
            con = new Conne();
            con.open();
            String sql = "SELECT id, nombre, presupuesto, porcentaje_partido_anual"
                    + " FROM fundacion f JOIN empleado e ON e.fundacion_id = f.id"
                    + " WHERE e.id = ? AND f.deleted_at IS NULL AND e.deleted_at IS NULL";
            String[] params = { empleadoId };
            ResultSet rs = con.execQuery(sql, params);
            if (con.isResultSetEmpty(rs))
                return null;
            Fundacion fundacion = setEntity(rs);
            return fundacion;
        } catch (Exception e) {
            String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            e.printStackTrace();
            return null;
        } finally {
            con.close();
        }
    } 

    public void updateFondos(Fundacion fundacion, float partidaAsignada) {
        try {
            con = new Conne();
            con.open();
            String currentYear = Integer.toString(Year.now().getValue());
            String sql = "UPDATE fundacion SET"
                    + " presupuesto=" + Float.toString(fundacion.getPresupuesto())
                    + " WHERE id = ? AND deleted_at IS NULL";
            String[] params = { fundacion.getId() };
            con.execMutation(sql, params);

            String sql2 = "INSERT INTO log_partidas_asignadas(id, fundacion_id, partida_asignada, annio)"
                        + " VALUES(?,?," + Float.toString(partidaAsignada) + ", " + currentYear + " )";
            String[] params2 = {
                Utils.genRandomSalt(),
                fundacion.getId()
            };
            con.execMutation(sql2, params2);
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            con.close();
        }
    } 

    public float getPartidaAsignada(String fundacionId, int annio) {
        try {
            con = new Conne();
            con.open();
            String sql = "SELECT partida_asignada"
                    + " FROM log_partidas_asignadas"
                    + " WHERE fundacion_id = ? AND annio = " + Integer.toString(annio) + " AND deleted_at IS NULL";
            String[] params = { fundacionId };
            ResultSet rs = con.execQuery(sql, params);
            if (con.isResultSetEmpty(rs))
                return 0;
            float partida = rs.getFloat("partida_asignada");
            return partida;
        } catch (Exception e) {
            String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            e.printStackTrace();
            return 0;
        } finally {
            con.close();
        }
    }

    public float getPartidaAnnio(int annio) {
        try {
            con = new Conne();
            con.open();
            String sql = "SELECT partida_asignada"
                    + " FROM log_partidas_asignadas"
                    + " WHERE annio = " + Integer.toString(annio) + " AND deleted_at IS NULL";
            ResultSet rs = con.execQuery(sql);
            if (con.isResultSetEmpty(rs))
                return 0;
            float partida = 0;
            do {
                float partidaFundacion = rs.getFloat("partida_asignada");
                partida += partidaFundacion;
            } while (rs.next());
            return partida;
        } catch (Exception e) {
            String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            e.printStackTrace();
            return 0;
        } finally {
            con.close();
        }
    }

    public Fundacion getFromSolicitud(String solicitudId) {
        try {
            con = new Conne();
            con.open();
            String sql = "SELECT s.id, nombre, presupuesto, porcentaje_partido_anual"
                    + " FROM fundacion f JOIN solicitud s ON s.fundacion_id = f.id"
                    + " WHERE s.id = ? AND f.deleted_at IS NULL AND s.deleted_at IS NULL";
            String[] params = { solicitudId };
            ResultSet rs = con.execQuery(sql, params);
            if (con.isResultSetEmpty(rs))
                return null;
            Fundacion fundacion = setEntity(rs);
            return fundacion;
        } catch (Exception e) {
            String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            e.printStackTrace();
            return null;
        } finally {
            con.close();
        }
    }

    public float getTotalGastado(String fundacionId, int annio) {
        try {
            con = new Conne();
            con.open();
            String sql = "SELECT total_gastado FROM view_total_gastado_anual"
                    + " WHERE fundacion_id = ? AND annio = " + Integer.toString(annio);
            String[] params = { fundacionId };
            ResultSet rs = con.execQuery(sql, params);
            if (con.isResultSetEmpty(rs))
                return 0;
            float gasto = rs.getFloat("total_gastado");
            return gasto;
        } catch (Exception e) {
            String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            e.printStackTrace();
            return 0;
        } finally {
            con.close();
        }
    }
}
