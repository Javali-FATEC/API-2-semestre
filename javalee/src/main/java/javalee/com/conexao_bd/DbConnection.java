package javalee.com.conexao_bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javalee.com.services.DataMeasurement;

public class DbConnection {

    public Connection connect_to_db(String dbname, String user, String password) {

        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, password);
            if (conn != null) {
                System.out.println("Conexão realizada com sucesso");
            } else {
                System.out.println("Falha na conexão");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    public void Desconnect(Connection conn) {

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}