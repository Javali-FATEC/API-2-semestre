package javalee.com;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javalee.com.bd_connection.DbConnection;
import javalee.com.entities.Metric;
import javalee.com.entities.Metrics;
import javalee.com.entities.Station;
import javalee.com.entities.Stations;
import javalee.com.services.DataFile;
import javalee.com.services.DataMeasurement;
import javafx.event.ActionEvent;
import java.io.IOException;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AnalysisController implements Initializable {

    @FXML
    private Button cityNameLabel;

    @FXML
    private Button salvarDados;

    @FXML
    private Label cityLabel;

    @FXML
    private Label stationLabel;

    private DataFile dataFile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setDataFile(DataFile dataFile) {
        this.dataFile = dataFile;
        // cityLabel.setText("Cidade: " + this.dataFile.getCity());
        cityLabel.setText(this.dataFile.getCity());
        stationLabel.setText(this.dataFile.getStation());
    }

    @FXML
    private void saveData(ActionEvent event) {
        salvarDados.setDisable(true);
        DbConnection db = new DbConnection();
        Stations stations = new Stations();
        Station station = stations.searchStation(this.dataFile.getStation());

        for (DataMeasurement dataLine : this.dataFile.getDataMeasurements()) {
            if (dataLine.getValue() != null) {
                String sql = "";
                sql = dataLine.toInsertSql(station.getIdEstacao());
                db.executeNotReturn(sql);
            }
        }
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
            App.openSeeInconsistencies("see-inconsistencies", this.dataFile.getLineErros());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
