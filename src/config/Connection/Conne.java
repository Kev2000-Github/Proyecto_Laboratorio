package config.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class Conne {

    // Variables de control.
    private static final String driver = "com.postgresql.jdbc.Driver";
    private static final String url = "jdbc:postgresql://172.20.0.3:5432/lab";
    private static final String user = "root";
    private static final String password = "root";

    public static Connection Conexion() {

        Connection conex = null;

        try {

            Class.forName(driver);
            // Se crea la conexion.
            conex = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos.\n"
                    + e.getMessage().toString());
        }

        return conex;
    }

    public static void cerrarConexion(Connection conex) {
        try {
            // Cerramos la conexión
            conex.close();
        } catch (SQLException e) {

            System.out.println(e.getMessage().toString());
        }
    }

    public static void cerrarStatement(Statement stmt) {
        try {
            // Cerramos la conexión
            stmt.close();
        } catch (SQLException e) {

            System.out.println(e.getMessage().toString());
        }
    }
}
