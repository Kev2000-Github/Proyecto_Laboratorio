package config.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import config.Config;

import java.sql.*;

public class Conne {

    // Variables de control.
    private String url;
    private String user;
    private String password;
    public Connection con;

    public Conne(){
        Config config = Config.getConfig();
        this.con = null;
        this.url = config.get("DATABASE_CONNECTION");
        this.user = config.get("DATABASE_USER");
        this.password = config.get("DATABASE_PASS");
    }

    public Connection open(){
        try {
            this.con = DriverManager.getConnection(url, user, password);
        } 
        catch (SQLException e) {
            String errorMsg = "Error al conectar con la base de datos.\n" + e.getMessage().toString();
            System.out.println(errorMsg);
        }
        return this.con;
    }

    public void close() {
        try {
            this.con.close();
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage().toString());
        }
    }

    public ResultSet execQuery(String sql){
        try{
            Statement queryExecutor = con.createStatement();
            ResultSet result = queryExecutor.executeQuery(sql);
            return result;
        }
        catch(Exception e){
            String msg = "Error obteniendo los datos\n" + e.getMessage();
            System.out.println(msg);
            return null;
        }
    }

    public ResultSet execQuery(String sql,String[] args){
        try{
            PreparedStatement queryExecutor = con.prepareStatement(sql);
            for(int i = 1; i <= args.length; i++){
                queryExecutor.setObject(i, args[i - 1]);
            }
            ResultSet result = queryExecutor.executeQuery();
            return result;
        }
        catch(Exception e){
            String msg = "Error obteniendo los datos\n" + e.getMessage();
            System.out.println(msg);
            return null;
        }
    }

    public void execMutation(String sql,String[] args){
        try{
            PreparedStatement queryExecutor = con.prepareStatement(sql);
            for(int i = 1; i <= args.length; i++){
                queryExecutor.setObject(i, args[i - 1]);
            }
            queryExecutor.executeUpdate();
        }
        catch(Exception e){
            String msg = "Error obteniendo los datos\n" + e.getMessage();
            System.out.println(msg);
        }
    }

    //beware this, it moves cursor one step forward
    public boolean isResultSetEmpty(ResultSet rs){
        try{
            boolean isEmpty = false;
            if(rs == null || rs.next() == false ){
                isEmpty = true;
            }
            return isEmpty;
        }
        catch(Exception e){
            String msg = "Error revisando si el resultSet esta vacio\n" + e.getMessage();
            System.out.println(msg);
            return false;
        }
    }
}
