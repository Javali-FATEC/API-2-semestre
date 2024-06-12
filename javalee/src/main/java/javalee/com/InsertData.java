package javalee.com;

import java.time.LocalDate;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javalee.com.entities.Cities;
import javalee.com.entities.City;
import javalee.com.entities.Metrics;
import javalee.com.entities.Stations;
import javalee.com.entities.Units;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javalee.com.entities.Metric;

public class InsertData {

    ObservableList<String> hours = FXCollections.observableArrayList();

    @FXML
    private DatePicker datePickerInitial;

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
    }

    @FXML
    private void selectedCity(ActionEvent event) {
        System.err.println("Insert Station By Cities");
        Stations st = new Stations();
        List<String> stations = st.getAllCodStations(cityData.getValue());
        ObservableList<String> stationList = FXCollections.observableArrayList(stations);
        stationData.setItems(stationList);
    }

    @FXML
    void insertData() {
        LocalDate dataInicial = datePickerInitial.getValue();

    }

    @FXML
    public void btRegisterData(ActionEvent event) {
        Metrics metrics = new Metrics();
        metrics.loadMetrics();
        Metric metricSelected = metrics.searchMetrics(cbDataType.getValue());
        Cities cities = new Cities();
        City citie = cities.searchCity(cityData.getValue());
        Stations st = new Stations();
        st.searchStation(code , cityData.getValue());

    
    }

    private void loadHoursComboBox() {
        for (int i = 0; i < 24; i++) {
            hours.add(Integer.toString(i));
        }
    }

}
