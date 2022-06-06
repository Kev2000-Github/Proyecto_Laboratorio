package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.general.IDao;
import config.Connection.Conne;
import modelos.Gobernacion;

public class GobernacionDao implements IDao<Gobernacion> {
    private Conne con;

    @Override
    public Gobernacion setEntity(ResultSet rs) {
        try {
            Gobernacion gobernacion = new Gobernacion();
            gobernacion.setNombre(rs.getString("nombre"));
            gobernacion.setFondos(rs.getFloat("fondos"));
            gobernacion.setId(rs.getString("id"));
            return gobernacion;
        } catch (SQLException e) {
            String msg = "Error asignando los datos obtenidos\n" + e.getMessage();
            System.out.println(msg);
            return null;
        }
    }

    @Override
    public Gobernacion get(String id) {
        try {
            con = new Conne();
            con.open();
            String sql = "SELECT id, nombre, fondos"
                    + " FROM gobernacion f WHERE id = ? AND f.deleted_at IS NULL";
            String[] params = { id };
            ResultSet rs = con.execQuery(sql, params);

            if (con.isResultSetEmpty(rs))
                return null;
            Gobernacion gobernacion = setEntity(rs);
            return gobernacion;
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
    public List<Gobernacion> getAll() {
        try {
            List<Gobernacion> list = new ArrayList<Gobernacion>();
            con = new Conne();
            con.open();
            String sql = "SELECT id, nombre, fondos"
                    + " FROM gobernacion f WHERE f.deleted_at IS NULL";
            ResultSet rs = con.execQuery(sql);
            if (con.isResultSetEmpty(rs))
                return list;
            do {
                Gobernacion gobernacion = setEntity(rs);
                list.add(gobernacion);
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
    public void save(Gobernacion fundacion) {
        try {
            con = new Conne();
            con.open();
            String sql = "INSERT INTO gobernacion(id, nombre, fondos) VALUES(?,?,?)";
            String[] params = {
                    fundacion.getId(),
                    fundacion.getNombre(),
                    String.valueOf(fundacion.getFondos()),
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
    public void update(Gobernacion gobernacion) {
        try {
            con = new Conne();
            con.open();
            String sql = "UPDATE gobernacion SET"
                    + " nombre=?, fondos=?"
                    + " WHERE id = ? AND deleted_at IS NULL";
            String[] params = {
                    gobernacion.getNombre(),
                    String.valueOf(gobernacion.getNombre()),
                    String.valueOf(gobernacion.getFondos()),
                    gobernacion.getId()

            };
            con.execMutation(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            con.close();
        }
    }

    @Override
    public void delete(Gobernacion gobernacion) {
        try {
            con = new Conne();
            con.open();
            Timestamp now = new Timestamp(new Date().getTime());
            String sql = "UPDATE gobernacion SET deleted_at = '" + now.toString()
                    + "' WHERE id = ?";
            String[] params = {
                    gobernacion.getId()
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
    public Gobernacion getHistoric(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
