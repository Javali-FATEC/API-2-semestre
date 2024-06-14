package javalee.com;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javalee.com.entities.Cities;
import javalee.com.entities.City;
import javalee.com.entities.Metrics;
import javalee.com.entities.Stations;
import javalee.com.entities.Station;
import javalee.com.entities.Units;
import javalee.com.services.DataMeasurement;
import javalee.com.bd_connection.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class InsertData {

    ObservableList<String> hours = FXCollections.observableArrayList();

    String sql;

    @FXML
    private DatePicker datePickerInitial;

    @FXML
    private TextField valueResp;

    @FXML
    private ComboBox<String> cbDataType;

    @FXML
    private ComboBox<String> unitData;

    @FXML
    private ComboBox<String> cityData;

    @FXML
    private ComboBox<String> hourData;

    @FXML
    private ComboBox<String> stationData;

    @FXML
    private Button btRegister;

    @FXML
    private void initialize() {
        Metrics metrics = new Metrics();
        metrics.loadMetrics();
        cbDataType.setItems(metrics.getMetrics());
        Units units = new Units();
        units.loadUnits();
        unitData.setItems(units.getUnitsNames());
        Cities cities = new Cities();
        cityData.setItems(cities.getAllNamesCities());
        loadHoursComboBox();
        hourData.setItems(hours);

        datePickerInitial.setValue(LocalDate.now());
        cbDataType.setValue("Temperatura");
        unitData.setValue("ºC");
        hourData.setValue("00");
    }


    @FXML
    private void insertStationByCities(ActionEvent event) {
        Stations st = new Stations();
        List<String> stations = st.getAllCodStations(cityData.getValue());
        ObservableList<String> stationList = FXCollections.observableArrayList(stations);
        stationData.setItems(stationList);
    }

    @FXML
    public void btRegisterData(ActionEvent event) {
       
        if (cbDataType.getValue() == null || unitData.getValue() == null || cityData.getValue() == null || stationData.getValue() == null || datePickerInitial.getValue() == null || hourData.getValue() == null || valueResp.getText() == null){
            
            System.out.println("cbDataType: " + cbDataType.getValue());
            System.out.println("unitData: " + unitData.getValue());
            System.out.println("cityData: " + cityData.getValue());
            System.out.println("stationData: " + stationData.getValue());
            System.out.println("datePickerInitial: " + datePickerInitial.getValue());
            System.out.println("hourData: " + hourData.getValue());
            System.out.println("valueResp: " + valueResp.getText());
            
            System.err.println("Preencha todos os campos");
            return;
        }

        formatSqlInsert();
        saveSqlInsert();
        
        }
        
    public void formatSqlInsert() {

        LocalDate data = datePickerInitial.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = data.format(formatter);

        String hourFormat = hourData.getValue() + "00";

        DataMeasurement dm = new DataMeasurement(cbDataType.getValue(), unitData.getValue(), dataFormatada, hourFormat, valueResp.getText());

        Cities cities = new Cities();
        City city = cities.searchCity(cityData.getValue());

        Stations st = new Stations();
        Station station = st.searchStation(stationData.getValue(), city.getIdCidade());

        Metrics metrics = new Metrics();
        metrics.loadMetrics();
        
        sql = dm.toInsertSql(station.getIdEstacao(), metrics);

    }

    public void saveSqlInsert() {
        DbConnection db = new DbConnection();
        db.executeNotReturn(sql);
        db.Desconnect();
        System.out.println("Valor adicionado");
        Stage stage = (Stage) cityData.getScene().getWindow();
        stage.close();
    }

    private void loadHoursComboBox() {
        hours.add("00");
        for (int i = 1; i < 24; i++) {
            hours.add(Integer.toString(i));
        }
    }

}
