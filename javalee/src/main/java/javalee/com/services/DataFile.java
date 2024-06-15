package javalee.com.services;

import java.util.List;
import java.util.Map;

import javalee.com.entities.LeituraSuspeita;

public class DataFile {
    private String siglaCidade;
    private String idEstacao;
    private List<DataMeasurement> dataMeasurements;
    private List<LeituraSuspeita> lineErros;
    

    public DataFile( String siglaCidade, String idEstacao, List<DataMeasurement> dataMeasurements, 
    List<LeituraSuspeita> lineErros){
        this.siglaCidade = siglaCidade;
        this.idEstacao = idEstacao.replaceAll("[^0-9]", "");
        this.dataMeasurements = dataMeasurements;
        this.lineErros = lineErros;
    }

    public List<LeituraSuspeita> getLineErros(){
        return this.lineErros;
    }

    public String getCity(){
        return siglaCidade;
    }

    public String getStation(){
        return idEstacao;
    }

    public List<DataMeasurement> getDataMeasurements(){
        return this.dataMeasurements;
    }

    
}
