package javalee.com.bd_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javalee.com.configs.ConfigBdReader;

public class DbConnection {

    private Connection conn;

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