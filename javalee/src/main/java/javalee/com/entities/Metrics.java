package javalee.com.entities;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javalee.com.bd_connection.DbConnection;
import javalee.com.services.DataMeasurement;

public class Metrics {
    
    private List<Metric> listMetrics;

    public Metrics() {
        this.listMetrics = new LinkedList<Metric>();
    }
    public Metric searchMetrics(String nome){
        for (Metric metric : this.listMetrics) {
            if(metric.getNome().equals( nome)){
                return metric;
            }
        }

        return null;
    }
    public void loadMetrics(){
        DbConnection db = new DbConnection();
        //List<Metric> listMetricsLoad = List.LinkedList();
        
        ResultSet resultMetric = db.executeWithReturn("SELECT * FROM db_javalee.metrica");
        Metric metric = null;

        try {
            while (resultMetric.next()) {
                int idReturn = resultMetric.getInt("id_metrica");
                String nameReturn = resultMetric.getString("nome");
                metric = new Metric(idReturn,nameReturn); 
                this.listMetrics.add(metric);
            }
            
        } catch (Exception e) {}

        db.Desconnect();
    }

}
