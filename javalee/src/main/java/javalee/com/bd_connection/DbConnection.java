package javalee.com.bd_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javalee.com.configs.*;

public class DbConnection {

    private Connection conn;

    private static final String URL = "jdbc:postgresql://localhost:5432/db_javali?currentSchema=db_javalee";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "admin";

    public DbConnection() {
        ConfigBdReader config = new ConfigBdReader();
        this.conn = null;

        try {
            Class.forName("org.postgresql.Driver");

            this.conn = DriverManager.getConnection(config.getUrlBd() + config.getNameBd(), config.getUserBd(),
                    config.getPasswordBd());

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Desconnect() {

        try {
            if (this.conn != null) {
                this.conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeNotReturn(String comando) {

        ResultSet resultSet = null;

        try {
            PreparedStatement stm = this.conn.prepareStatement(comando);
            stm.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet executeWithReturn(String comando) {

        ResultSet resultSet = null;

        try {
            PreparedStatement stm = this.conn.prepareStatement(comando);


            resultSet = stm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;

    }

}