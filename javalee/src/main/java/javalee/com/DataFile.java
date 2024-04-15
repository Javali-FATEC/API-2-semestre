package javalee.com;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class DataFile {
    private String siglaCidade;
    private String idEstacao;
    private List<DataMeasurement> dataMeasurements;
    private Map<String, String> lineErros;

    public DataFile( String siglaCidade, String idEstacao, List<DataMeasurement> dataMeasurements, 
    Map<String, String> lineErros){
        this.siglaCidade = siglaCidade;
        this.idEstacao = idEstacao;
        this.dataMeasurements = dataMeasurements;
        this.lineErros = lineErros;
    }

    public Map<String, String> getLineErros(){
        return this.lineErros;
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
