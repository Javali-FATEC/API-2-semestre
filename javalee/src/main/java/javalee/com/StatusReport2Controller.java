package javalee.com;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javalee.com.entities.RelatorioMedia;
import javalee.com.entities.RelatoriosMedia;
import javalee.com.entities.relatoriosMedia;

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
    void openReportData(ActionEvent event) {
        LocalDate dataInicial = datePickerInicial.getValue();
        LocalDate dataFinal = datePickerFinal.getValue();
    }

    @FXML
    public void initialize() {
        onComboBox();
    }

    private void onComboBox() {
        RelatoriosMedia relatorios = new RelatoriosMedia();
        LinkedList<String> listaCidades = relatorios.ListCidades();
        ObservableList<String> observableList = FXCollections.observableArrayList(listaCidades);
        cBoxCidade.setItems(observableList);
    }

    @FXML
    private void onBtnGerarRelorio() {
        try {
            RelatoriosMedia relatorio = new RelatoriosMedia();
            LocalDate dataInicial = datePickerInicial.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataFormatadaInicial = dataInicial.format(formatter);
            LocalDate dataFinal = datePickerFinal.getValue();
            String dataFormatadaFinal = dataFinal.format(formatter);
            List<RelatorioMedia> relatorioRetornado = relatorio.searchCidadeRelatorioMetric(cBoxCidade.getValue(),
                    dataFormatadaInicial, dataFormatadaFinal);
            App.openReportData(relatorioRetornado, cBoxCidade.getValue());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
