package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.general.IDao;
import config.Connection.Conne;
import modelos.Fundacion;

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

    public FundacionDao() {
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
                    + " FROM fundacion f WHERE f.deleted_at IS NULL";
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

}
