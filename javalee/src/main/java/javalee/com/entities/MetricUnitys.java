package javalee.com.entities;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javalee.com.bd_connection.DbConnection;

public class MetricUnitys {

    private List<MetricUnity> listMetrics;

    public MetricUnitys() {
        this.listMetrics = new LinkedList<MetricUnity>();
    }

    public MetricUnity searchMetricUnity(String code, int idMetricUnity) {

        DbConnection db = new DbConnection();
        ResultSet resultMetricUnity = db.executeWithReturn("SELECT * FROM unidade_medida WHERE codigo '" + code + "'");
        MetricUnity metricUnity = null;

        try {
            if (resultMetricUnity.next()) {
                int idReturn = resultMetricUnity.getInt("id_unidade_medida");
                String codeReturn = resultMetricUnity.getString("nome");
                String description = resultMetricUnity.getString("descricao");

                metricUnity = new MetricUnity(idReturn, codeReturn, description);

            } else {
                db.executeNotReturn("INSERT INTO unidade_medida (id_uniadade_medida, nome) VALUES (" + idMetricUnity
                        + ", '" + code + "');");
                return searchMetricUnity(code, idMetricUnity);

            }
        } catch (Exception e) {

        }

        db.Desconnect();
        return metricUnity;

    }

    public List<Metric> searchMetricUnityMeasure(String nameMeasure) {
        List<Metric> returnList = new LinkedList<Metric>();

        DbConnection db = new DbConnection();
        ResultSet resultMetricUnity = db.executeWithReturn("SELECT * FROM metrica " +
                "INNER JOIN unidade_medida ON metrica.id_unidade_medida = unidade_medida.id_unidade_medida " +
                "WHERE unidade_medida.nome = '" + nameMeasure + "'");

        try {
            while (resultMetricUnity.next()) {

                int idMetric = resultMetricUnity.getInt("id_metrica");
                String code = resultMetricUnity.getString("nome");

                Metric metric = new Metric(idMetric, code);
                returnList.add(metric);

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            db.Desconnect();
        }

        return returnList;
    }


    public List<MetricUnity> getAllMetricUnity() {
        DbConnection db = new DbConnection();
        ResultSet resultMetricUnity = db.executeWithReturn("SELECT * FROM unidade_medida");
        List<MetricUnity> result = new LinkedList<>();

        try {
            while (resultMetricUnity.next()) {
                int idUnidadeMedida = resultMetricUnity.getInt("id_unidade_medida");
                String nome = resultMetricUnity.getString("nome");
                String description = resultMetricUnity.getString("descricao");

                result.add(new MetricUnity(idUnidadeMedida, nome, description));
            }
        } catch (Exception e) {

        }
        db.Desconnect();

        return result;
    }



}
