package javalee.com;

public class DataMeasurement {
    private String typeMeasurement;
    private String unidade;
    private Float value;
    private String hour;
    private String date;

    public DataMeasurement( String typeMeasurement, String unidade, String date, String hour, String value){
        this.typeMeasurement = typeMeasurement;
        try
        {
            this.value = Float.parseFloat(value.replace(',', '.'));
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
            this.value = this.value + 273;
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
        return String.valueOf(value);
    }
    public String getHour(){
        return hour;
    }
    public String getDate(){
        return date;
    }
}
