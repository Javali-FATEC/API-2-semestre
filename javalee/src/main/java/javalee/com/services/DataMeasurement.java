package javalee.com.services;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javalee.com.entities.Metric;
import javalee.com.entities.Metrics;

public class DataMeasurement {
    private String typeMeasurement;
    private String unidade;
    private BigDecimal value;
    private String hour;
    private String date;

    public DataMeasurement(String typeMeasurement, String unidade, String date, String hour, String value) {
        this.typeMeasurement = typeMeasurement;
        try {
            if (value == "") {
                this.value = null;
            } else {
                Double valueDouble = Double.parseDouble(value.replace(',', '.'));
                this.value = BigDecimal.valueOf(valueDouble);
            }
        } catch (NumberFormatException e) {
            this.value = null;
        }
        if (hour != null) {
            hour = hour.substring(0, hour.length() - 2);
            this.hour = hour;
        }
        this.date = date;
        this.unidade = unidade;
    }

    public void convertTemp() {
        if (this.value != null) {
            this.value = this.value.subtract(BigDecimal.valueOf(273.15));
        }
    }

    public String getTypeMeasurament() {
        return typeMeasurement;
    }

    public String getUnidade() {
        return unidade;
    }

    public String getValue() {
        if (value == null) {
            return null;
        }
        DecimalFormat formatUsed = new DecimalFormat("#.##########");
        String numberFormated = formatUsed.format(value);
        return numberFormated.replace('.', ',');
    }

    public String getHour() {
        return hour;
    }

    public String getDate() {
        return date;
    }

    public String getHourFormatSql() {
        SimpleDateFormat originFormat = new SimpleDateFormat("HH");
        SimpleDateFormat formatHour = new SimpleDateFormat("hh:mm:ss");
        try {
            Date hour = originFormat.parse(this.hour);
            return formatHour.format(hour);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getDateFormatSql() {
        SimpleDateFormat formatoOrigem = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoDesejado = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date data = formatoOrigem.parse(this.date);
            return formatoDesejado.format(data);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getValueFormatComma() {
        if (value == null) {
            return null;
        }

        DecimalFormat formatUsed = new DecimalFormat("#.##########");
        String numberFormated = formatUsed.format(value);

        return numberFormated.replace(',', '.');
    }

    public String toInsertSql(String idEstacao, Metrics metrics) {
        Metric metric = metrics.searchMetrics(typeMeasurement);
        
        if (metric == null) {
            return "";
        }
        
        if (this.getValue() == null) {
            return "";
        }
        
        String sql = "INSERT INTO db_javalee.registro (id_metrica, id_estacao, valor, data_hora) VALUES ('" + metric.getIdMetrica() + "','" + idEstacao + "','" + this.getValueFormatComma() + "', '" + this.getDateFormatSql() + " " + getHourFormatSql() + "');";
        System.out.println("to aqui capeta" + sql);
        return sql;
    }
}
