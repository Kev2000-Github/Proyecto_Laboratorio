package config.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class Conne {

    // Variables de control.
    private String driver = "com.postgresql.jdbc.Driver";
    private String url = "jdbc:postgresql://localhost:5432/lab";
    private String user = "root";
    private String password = "root";
    private Connection con;

    public Conne(){
        this.con = null;
    }

    public Connection open(){
        try {
            this.con = DriverManager.getConnection(url, user, password);
        } 
        catch (Exception e) {
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
            queryExecutor.close();
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
                queryExecutor.setString(i, args[i]);
            }
            ResultSet result = queryExecutor.executeQuery(sql);
            queryExecutor.close();
            return result;
        }
        catch(Exception e){
            String msg = "Error obteniendo los datos\n" + e.getMessage();
            System.out.println(msg);
            return null;
        }
    }
}
