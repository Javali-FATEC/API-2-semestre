package javalee.com;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.DatePicker;

public class StatusReport2Controller {

    @FXML
    private DatePicker datePickerInicial;

    @FXML
    private DatePicker datePickerFinal;

    @FXML
    private ComboBox<String> cBoxCidade;

    @FXML
    private Button btnGerarRelatorio;

    @FXML
    private void openReportData(ActionEvent event) {
        try {
            App.openReportData();
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
