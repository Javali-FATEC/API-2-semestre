package javalee.com.services;

import javalee.com.bd_connection.DbConnection;

public class InsertMaxMinValues {
    private String maximoValor;
    private String minimoValor;

    public void updateMaxValue(String metrica){
        DbConnection db = new DbConnection();

        try{
            Float changeToFloat = Float.parseFloat(maximoValor);

            db.executeNotReturn("UPDATE metrica SET maximo_risco = '"+changeToFloat+"' WHERE nome = '"+metrica+"'");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("teste erro");
        } finally {
            db.Desconnect();
        }
    }

    public void updateMinValue(String metrica){
        DbConnection db = new DbConnection();
        Float changeToFloat = Float.parseFloat(minimoValor);

        try {
            db.executeNotReturn("UPDATE metrica SET minimo_risco = '"+changeToFloat+"' WHERE nome = '"+metrica+"' ");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            db.Desconnect();
        }
    }

    public static void main(String[] args) {
        InsertMaxMinValues valor = new InsertMaxMinValues();
        valor.maximoValor = "11.7";
        valor.minimoValor = "5";
        valor.updateMaxValue("Chuva");
        valor.updateMinValue("Chuva");
    }
}
