package javalee.com;

import java.time.LocalDate;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javalee.com.entities.Metrics;
import javalee.com.entities.Units;
import javafx.fxml.FXML;

public class InsertData {

    @FXML
    private DatePicker datePickerInitial;

    @FXML
    private ComboBox<String> cbDataType;

    @FXML
    private ComboBox<String> unitData;

    @FXML
    private void initialize() {
        Metrics metrics = new Metrics();
        metrics.loadMetrics();
        cbDataType.setItems(metrics.getMetrics());
        Units units = new Units();
        units.loadUnits();
        unitData.setItems(units.getUnitsNames());
    }


    @FXML
    void insertData() {
        LocalDate dataInicial = datePickerInitial.getValue();
    }
}
