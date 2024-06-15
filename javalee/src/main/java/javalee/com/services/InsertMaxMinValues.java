package javalee.com.services;

import javalee.com.bd_connection.DbConnection;

public class InsertMaxMinValues {
    private String maximoValor;
    private String minimoValor;

    public void updateMaxValue(String metrica, String maximoValor){
        DbConnection db = new DbConnection();
        String formattedMaxValue = maximoValor.replace(",", ".");

        try
        {
            db.executeNotReturn("UPDATE metrica SET maximo_risco = " + formattedMaxValue + " WHERE nome = '" + metrica + "'");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            db.Desconnect();
        }
    }

    public void updateMinValue(String metrica, String minimoValor){
        DbConnection db = new DbConnection();
        String formattedMinValue = minimoValor.replace(",", ".");

        try {
            db.executeNotReturn("UPDATE metrica SET minimo_risco = "+formattedMinValue+" WHERE nome = '"+metrica+"' ");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            db.Desconnect();
        }
    }
}
