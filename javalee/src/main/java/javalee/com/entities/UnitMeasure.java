package javalee.com.entities;

public class UnitMeasure {

    private int idUnitMeasure;

    private String nameMeasure;

    private String description;

    public UnitMeasure(int idUnitMeasure, String nameMeasure, String description) {
        this.idUnitMeasure = idUnitMeasure;
        this.nameMeasure = nameMeasure;
        this.description = description; 
    }

    public String getName() {
        return nameMeasure;
    }
    
}
