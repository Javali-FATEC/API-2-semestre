package javalee.com.entities;

import javalee.com.services.DataMeasurement;

public class LeituraSuspeita {
    String linha;
    String erro;
    DataMeasurement dataMeasurement;

    public LeituraSuspeita(String linha, String erro, DataMeasurement data) {
        this.linha = linha;
        this.erro = erro;
        this.dataMeasurement = data;
    }

    public String getLinha() {
        return linha;
    }

    public String getErro() {
        return erro;
    }

    public DataMeasurement getData() {
        return dataMeasurement;
    }
}
