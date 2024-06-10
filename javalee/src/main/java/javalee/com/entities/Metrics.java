package javalee.com.entities;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javalee.com.bd_connection.DbConnection;

public class Metrics {

    private List<Metric> listMetrics; 

    private ObservableList<String> typeMetrics;

    public Metrics() {
        this.listMetrics = new LinkedList<Metric>();
    }

    public Metric searchMetrics(String nome) {
        for (Metric metric : this.listMetrics) {
            if (metric.getNome().equals(nome)) {
                return metric;
            }
        }

        return null;
    }

    public void loadMetrics() {
        DbConnection db = new DbConnection();

        ResultSet resultMetric = db.executeWithReturn("SELECT * FROM metrica");
        Metric metric = null;

        try {
            while (resultMetric.next()) {
                int idReturn = resultMetric.getInt("id_metrica");
                String nameReturn = resultMetric.getString("nome");
                metric = new Metric(idReturn, nameReturn);
                this.listMetrics.add(metric);
            }

        } catch (Exception e) {
        }

        db.Desconnect();
    }

    public ObservableList<String> getMetrics() {
        typeMetrics = FXCollections.observableArrayList();
        for (Metric metric : this.listMetrics) {
            typeMetrics.add(metric.getNome());
        }
        return typeMetrics;
    }

}
