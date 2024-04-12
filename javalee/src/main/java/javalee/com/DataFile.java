package javalee.com;

import java.util.List;

public class DataFile {
    private String siglaCidade;
    private String idEstacao;
    private List<DataMeasurement> dataMeasurements;

    public DataFile( String siglaCidade, String idEstacao, List<DataMeasurement> dataMeasurements){
        this.siglaCidade = siglaCidade;
        this.idEstacao = idEstacao;
        this.dataMeasurements = dataMeasurements;
    }

    public String getSiglaCidade(){
        return siglaCidade;
    }

    public String getIdEstacao(){
        return idEstacao;
    }

    public List<DataMeasurement> getDataMeasurements(){
        return this.dataMeasurements;
    }
    
}
