package javalee.com.services;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import javalee.com.bd_connection.DbConnection;

public class ReturnMaxMinValues {
    private BigDecimal valorMaximo;
    private BigDecimal valorMinimo;

    public ReturnMaxMinValues(String metrica){
        DbConnection db = new DbConnection();
        ResultSet resultMax = db.executeWithReturn("SELECT maximo_risco, minimo_risco FROM metrica WHERE nome = '" + metrica +"'");
    
        try {
            if (resultMax.next()){
                this.valorMaximo = resultMax.getBigDecimal("maximo_risco");
                this.valorMinimo = resultMax.getBigDecimal("minimo_risco");
            }
        } catch (SQLException e){
            e.getSQLState();
        } finally {
            db.Desconnect();
        }
    }

    public BigDecimal getValorMaximo() {
        return valorMaximo;
    }

    public BigDecimal getValorMinimo() {
        return valorMinimo;
    }
}
