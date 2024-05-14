package javalee.com.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import javalee.com.bd_connection.DbConnection;

public class ReturnMaxMinValues {
    private Float valorMaximo;
    private Float valorMinimo;

    public ReturnMaxMinValues(String metrica){
        DbConnection db = new DbConnection();
        ResultSet resultMax = db.executeWithReturn("SELECT maximo_risco, minimo_risco FROM metrica WHERE nome = '" + metrica +"'");
    
        try {
            if (resultMax.next()){
                this.valorMaximo = resultMax.getFloat("maximo_risco");
                this.valorMinimo = resultMax.getFloat("minimo_risco");
            }
        } catch (SQLException e){
            e.getSQLState();
        } finally {
            db.Desconnect();
        }
    }

    public Float getValorMaximo() {
        return valorMaximo;
    }

    public Float getValorMinimo() {
        return valorMinimo;
    }
}
