package javalee.com;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javalee.com.bd_connection.DbConnection;
import javalee.com.entities.Cities;
import javalee.com.entities.City;
import javalee.com.entities.LeituraAjustada;
import javalee.com.entities.Metrics;
import javalee.com.entities.Station;
import javalee.com.entities.Stations;
import javalee.com.services.DataFile;
import javalee.com.services.DataMeasurement;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AnalysisController implements Initializable {

    @FXML
    private Button cityNameLabel;

    @FXML
    private TextField cityNameTextField;

    @FXML
    private Button salvarDados;

    @FXML
    private Label cityLabel;

    @FXML
    private Label stationLabel;

    private DataFile dataFile;

    private boolean cityIsExist;

    private City cityUsed;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setDataFile(DataFile dataFile) {
        this.dataFile = dataFile;
        cityLabel.setText(this.dataFile.getCity());
        stationLabel.setText(this.dataFile.getStation());
        Cities cities = new Cities();
        cityIsExist = cities.isExist(dataFile.getCity());
        if(cityIsExist){
            cityNameTextField.setDisable(true);
            cityUsed = cities.searchCity(dataFile.getCity());
            cityNameTextField.setText(cityUsed.getNome());
        }
        else{
            cityNameTextField.setDisable(false);
        }
    }

    @FXML
    private void saveData(ActionEvent event) {
        
        salvarDados.setDisable(true);
        DbConnection db = new DbConnection();
        
        if(!cityIsExist){
            String nameCity = dataFile.getCity();
            if( cityNameTextField.getText() != null )
            {
                nameCity = cityNameTextField.getText();
            }
            Cities cities = new Cities();
            cities.createCity(dataFile.getCity(), nameCity);
            cityUsed = cities.searchCity(dataFile.getCity());
        }
        Stations stations = new Stations();
        Station station = stations.searchStation(this.dataFile.getStation(),cityUsed.getIdCidade());
    
        String sql = "";
        Metrics metrics = new Metrics();
        metrics.loadMetrics();

        for (DataMeasurement dataLine : this.dataFile.getDataMeasurements()) {
            if (dataLine.getValue() != null) {
                sql += dataLine.toInsertSql(station.getIdEstacao(), metrics);
            }
        }
        db.executeNotReturn(sql);
        db.Desconnect();
    }

    @FXML
    private void previweData(ActionEvent event) {
        try {
            App.openPreviewData("preview-data", this.dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void seeInconsistencies(ActionEvent event) {
        try {
            List<LeituraAjustada> listaCorrecoes = App.openSeeInconsistencies("see-inconsistencies", this.dataFile.getLineErros());
            if( listaCorrecoes != null ){
                this.ajustarValoresDataFile(listaCorrecoes);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ajustarValoresDataFile( List<LeituraAjustada> listaCorrcoes){
        for( LeituraAjustada correcao : listaCorrcoes){
            for( DataMeasurement data : this.dataFile.getDataMeasurements()){
                if( data.verificaIgualdade(correcao.getLinha(), correcao.getVariavelClimatica()))
                {
                    System.out.println("Ajustando valor");
                    Double valueDouble = Double.parseDouble(correcao.getValorNovo());
                    data.setValue(BigDecimal.valueOf(valueDouble));
                }
            }
        }

    }
}
