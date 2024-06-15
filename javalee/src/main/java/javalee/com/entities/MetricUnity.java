package javalee.com.entities;

public class MetricUnity {

    private int idMetricUnity;
    private String nameMetricUnity;
    private String description;

    public MetricUnity(int idMetricUnity, String nameMetricUnity, String description) {
        this.idMetricUnity = idMetricUnity;
        this.nameMetricUnity = nameMetricUnity;
        this.description = description;
    }

    public int getIdMetricUnity() {
        return idMetricUnity;
    }

    public void setIdMetricUnity(int idMetricUnity) {
        this.idMetricUnity = idMetricUnity;
    }

    public String getNameMetricUnity() {
        return nameMetricUnity;
    }

    public void setNameMetricUnity(String nameMetricUnity) {
        this.nameMetricUnity = nameMetricUnity;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    


    
}
