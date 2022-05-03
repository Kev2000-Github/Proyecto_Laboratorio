package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import DAO.general.IDao;
import config.Connection.Conne;
import javax.swing.table.DefaultTableModel;
import modelos.Charla;

public class CharlaDao implements IDao<Charla> {

    private Conne con;

    @Override
    public Charla setEntity(ResultSet rs) {
        try {
            Charla charla = new Charla();
            charla.setTema(rs.getString("tema"));
            charla.setDireccion(rs.getString("lugar"));
            charla.setOrganismo(rs.getString("organismo"));
            charla.setFecha(rs.getDate("fecha"));
            charla.setId(rs.getString("id"));
            return charla;
        } catch (SQLException e) {
            String msg = "Error asignando los datos obtenidos\n" + e.getMessage();
            System.out.println(msg);
            return null;
        }
    }

    public CharlaDao() {
    }
    
    @Override
    public Charla get(String id) {
        try {
            con = new Conne();
            con.open();
            String sql = "SELECT id, tema, lugar,organismo,fecha"
                    + " FROM charla c WHERE id = ? AND c.deleted_at IS NULL";
            String[] params = { id };
            ResultSet rs = con.execQuery(sql, params);

            if (con.isResultSetEmpty(rs))
                return null;
            Charla charla = setEntity(rs);
            return charla;
        } catch (Exception e) {
            String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            return null;
        } finally {
            con.close();
        }
    }

    public List<Charla> getCharlasByType(String selection) {
        try {
           
            List<Charla> list = new ArrayList<Charla>();
            con = new Conne();
            con.open();
            
            //Inicializando Consulta
            String sql = "";
            
            //Condicion para Seleccionar el tipo de Consulta a realizar
            if(selection=="Por empezar, sin Registrar"){
                
            sql = "SELECT id, tema, lugar, organismo, fecha"
                    + " FROM charla c WHERE status = pend c.deleted_at IS NULL";
            }
            else if (selection =="Finalizadas, Registradas"){
                
            sql = "SELECT c.id, c.tema, c.lugar, c.organismo, c.fecha"
                  + " FROM charla c JOIN asistenciacharla ac ON c.id = ac.charla_id"
                  + " WHERE c.status = fin AND c.deleted_at IS NULL";

            }
            else {//caso Finalizadas sin Registrar
            
                sql = "SELECT c.id, c.tema, c.lugar, c.organismo, c.fecha"
                      + "FROM  charla c LEFT JOIN asistenciacharla ac ON c.id = ac.charla_id"
                      + "WHERE c.status = 'fin' AND ac.charla_id IS NULL" ;         
            }
            
            ResultSet rs = con.execQuery(sql);
            
            if (con.isResultSetEmpty(rs))
                return list;
            
            do {                
                Charla charla = setEntity(rs);
                list.add(charla);               
            } while (rs.next());
            
            return list;
            
        } catch (SQLException e) {
            String msg = "Error #7105 obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            return null;
        } finally {
            con.close();
        }
    }
    
    @Override
    public List<Charla> getAll() {
        try {
            List<Charla> list = new ArrayList<Charla>();
            con = new Conne();
            con.open();
            String sql = "SELECT id, tema, lugar,organismo,fecha"
                    + " FROM charla c WHERE c.deleted_at IS NULL";
            ResultSet rs = con.execQuery(sql);
            if (con.isResultSetEmpty(rs))
                return list;
            do {
                Charla charla = setEntity(rs);
                list.add(charla);
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
    public void save(Charla charla) {
        try {
            con = new Conne();
            con.open();
            String sql = "INSERT INTO fundacion(id, tema, lugar,organismo,fecha) VALUES(?,?,?,?,?)";
            String[] params = {
                    charla.getId(),
                    charla.getTema(),
                    charla.getDireccion(),
                    charla.getOrganismo(),
                    String.valueOf(charla.getFecha())

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
    public void update(Charla charla) {
        try {
            con = new Conne();
            con.open();
            String sql = "UPDATE charla SET"
                    + " tema=?, lugar=?, organismo=?, fecha=?"
                    + " WHERE id = ? AND deleted_at IS NULL";
            String[] params = {
                    charla.getTema(),
                    charla.getDireccion(),
                    charla.getOrganismo(),
                    String.valueOf(charla.getFecha()),
                    charla.getId()

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
    public void delete(Charla charla) {
        try {
            con = new Conne();
            con.open();
            Timestamp now = new Timestamp(new Date().getTime());
            String sql = "UPDATE charla SET deleted_at = '" + now.toString()
                    + "' WHERE id = ? AND deleted_at IS NULL";
            String[] params = {
                    charla.getId()
            };
            con.execMutation(sql, params);
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            con.close();
        }
    }

    public List<Charla> getCharlasAsistidas(String cedula) {
        try {
            con = new Conne();
            con.open();
            List<Charla> list = new ArrayList<Charla>();
            String sql = "SELECT c.id, c.tema, c.lugar, c.organismo, c.fecha"
                    + " FROM charla c JOIN asistenciacharla ac ON c.id = ac.charlaId"
                    + " WHERE ac.cedula = ? AND c.deleted_at IS NULL";
            String[] params = { cedula };
            ResultSet rs = con.execQuery(sql, params);

            if (con.isResultSetEmpty(rs))
                return list;
            do {
                Charla charla = setEntity(rs);
                list.add(charla);
            } while (rs.next());            
            return list;
        } catch (Exception e) {
            String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            return null;
        } finally {
            con.close();
        }
    }
}
