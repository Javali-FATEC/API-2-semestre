package javalee.com.services;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DataMeasurement {
    private String typeMeasurement;
    private String unidade;
    private BigDecimal value;
    private String hour;
    private String date;

    public DataMeasurement( String typeMeasurement, String unidade, String date, String hour, String value){
        this.typeMeasurement = typeMeasurement;
        try
        {
            if(value == "")
            {
                this.value = null;
            }
            else
            {
                Double valueDouble = Double.parseDouble(value.replace(',', '.'));
                this.value = BigDecimal.valueOf(valueDouble);
            }
        }catch( NumberFormatException e)
        {
            this.value = null;
        }
        if(hour != null)
        {
            hour = hour.substring(0, hour.length() - 2);
            this.hour = hour;
        }
        this.date = date;
        this.unidade = unidade;
    }

    public void convertTemp(){
        if(this.value != null)
        {
            this.value = this.value.subtract(BigDecimal.valueOf(273.15));
        }
    }

    public String getTypeMeasurament(){
        return typeMeasurement;
    }
    public String getUnidade(){
        return unidade;
    }
    public String getValue(){
        if(value == null){
            return null;
        }
        DecimalFormat formatUsed = new DecimalFormat("#.##########");
        String numberFormated = formatUsed.format(value);
        return numberFormated.replace('.', ',');
    }
    public String getHour(){
        return hour;
    }
    public String getDate(){
        return date;
    }
}
