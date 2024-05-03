package javalee.com.entities;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javalee.com.bd_connection.DbConnection;

public class Metrics {
    
    private List<Metric> listMetrics;

    public Metrics() {
        this.listMetrics = new LinkedList<Metric>();
    }

    public Metric searchMetrics(String nome){

    
        DbConnection db = new DbConnection();
        
        ResultSet resultMetric = db.executeWithReturn("SELECT * FROM metrica WHERE nome = '" + nome + "'");
        Metric metric = null;


        try {
            if (resultMetric.next()) {
                int idReturn = resultMetric.getInt("id_metrica");
                String nameReturn = resultMetric.getString("nome");
                metric = new Metric(idReturn,nameReturn); 
            }
            
        } catch (Exception e) {
            
        }
        db.Desconnect();
        
        return metric;
    }

}
