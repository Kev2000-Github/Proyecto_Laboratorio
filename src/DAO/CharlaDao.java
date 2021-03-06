package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import DAO.general.IDao;
import config.Connection.Conne;
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
            e.printStackTrace();
            return null;
        } finally {
            con.close();
        }
    }
    
    public boolean charlaIsRegistered(String id){
        try {
                con = new Conne();
                con.open();
                String sql = "SELECT DISTINCT id, tema, lugar, organismo, fecha"
                             + " FROM charla c JOIN asistencia_charla ac ON c.id = ac.charla_id"
                             + " WHERE c.id = ? AND c.deleted_at IS NULL";
                String[] params = { id };
                ResultSet rs = con.execQuery(sql, params);

                if (con.isResultSetEmpty(rs))
                    return false;
                return true;
            } catch (Exception e) {
                String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
                System.out.println(msg);
                e.printStackTrace();
                //REVISAR: Dejo este codigo? es necesario? --> return null;
            } finally {
                con.close();
            }
        return true;
    }
    
    public List<Charla> getCharlasByDate(String fecha_f, String fecha_t) {
        try {
            List<Charla> list = new ArrayList<Charla>();
            con = new Conne();
            con.open();
            String sql = "SELECT id, tema, lugar,organismo,fecha"
                    + " FROM charla c WHERE c.fecha BETWEEN " 
                    + "? AND ? AND c.deleted_at IS NULL;";
            
            String[] params = { fecha_f, fecha_t };
            ResultSet rs = con.execQuery(sql,params);
            
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
            e.printStackTrace();
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
            String sql = "SELECT id, tema, lugar, organismo, fecha"
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
            e.printStackTrace();
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
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String sql = "INSERT INTO charla(id, tema, lugar, organismo, fecha) VALUES(?,?,?,?,?)";
            String[] params = {
                    charla.getId(),
                    charla.getTema(),
                    charla.getDireccion(),
                    charla.getOrganismo(),
                    simpleDateFormat.format(charla.getFecha())
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
            String sql = "SELECT c.id, c.tema, c.direccion, c.organismo, c.fecha"
                    + " FROM charla c JOIN asistencia_charla ac ON c.id = ac.charlaId"
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
            e.printStackTrace();
            return null;
        } finally {
            con.close();
        }
    }

    public int countCharlasAsistidas(String cedula) {
        try {
            con = new Conne();
            con.open();
            String sql = "SELECT COUNT(ac.cedula) as count"
                    + " FROM charla c JOIN asistencia_charla ac ON c.id = ac.charla_id"
                    + " WHERE ac.cedula = ? AND c.deleted_at IS NULL AND ac.deleted_at IS NULL";
            String[] params = { cedula };
            ResultSet rs = con.execQuery(sql, params);

            if (con.isResultSetEmpty(rs))
                return 0;
            int count = rs.getInt("count");         
            return count;
        } catch (Exception e) {
            String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            e.printStackTrace();
            return 0;
        } finally {
            con.close();
        }
    }
    
    public boolean existingAsistente(String ci, String id){
         try {
            con = new Conne();
            con.open();
            String sql = "SELECT * FROM asistencia_charla " 
                         + "WHERE cedula = ? AND charla_id = ? AND deleted_at IS NULL";
            String[] params = { ci, id };
            ResultSet rs = con.execQuery(sql, params);

            if (con.isResultSetEmpty(rs))
                return false;
            return true;
        } catch (Exception e) {
            String msg = "Error obteniendo los datos de la bd\n" + e.getMessage();
            System.out.println(msg);
            e.printStackTrace();
            return false;
        } finally {
            con.close();
        }
    }
    
    
    public void saveAsistente(String ciAsistente, String idcharla) {
        try {
            con = new Conne();
            con.open();
            String sql = "INSERT INTO asistencia_charla(cedula, charla_id) VALUES (?,?)";
            String[] params = { ciAsistente, idcharla };
            con.execMutation(sql, params);
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            con.close();
        }
    }

    @Override
    public Charla getHistoric(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
