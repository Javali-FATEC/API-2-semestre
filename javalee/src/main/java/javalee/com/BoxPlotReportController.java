package javalee.com;

import javafx.beans.property.SimpleStringProperty;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javalee.com.services.DataFile;
import javalee.com.services.DataMeasurement;
import javalee.com.services.RelatorioBoxPlot;

public class BoxPlotReportController implements Initializable{

    private String estacao;
    private String data;
    
    @FXML
    private Label quartil1;

    @FXML
    private Label quartil2;

    @FXML
    private Label quartil3;

    @FXML
    private Label limiteSuperior;

    @FXML
    private Label limiteInferior;

    ObservableList<String> rowsTypeMeasurement = FXCollections.observableArrayList(
    "Chuva",
    "Direção do Vento",
    "Direção do Vento",
    "Insolação",
    "Nebulosidade",
    "Ponto de Orvalho Instantâneo",
    "Ponto de Orvalho Máximo",
    "Ponto de Orvalho Mínimo",
    "Pressão",
    "Pressão Máxima",
    "Pressão Mínima",
    "Rajada de Vento",
    "Radiação Solar",
    "Temperatura",
    "Temperatura Máxima",
    "Temperatura Mínima",
    "Umidade",
    "Umidade Relativa Máxima",
    "Umidade Relativa Mínima",
    "Velocidade do Vento",
    "Velocidade do Vento"
);

    @FXML
    private ChoiceBox<String> typeMeasurement;

    @FXML
    private TableView<String[]> tableDataMeasurement;

    @FXML
    private Label typeMeasurementLabel;

    @FXML
    private Label messageNull;

    @FXML
    private Pane paneLane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
         typeMeasurement.setItems(rowsTypeMeasurement);
    }

    public void setBoxPlotReport(String estacao, String dataFormatada) {
        this.estacao = estacao;
        this.data = dataFormatada;
    }
    
    public void handleTypeMeasurementChange(){
        RelatorioBoxPlot relatorioBoxPlot = new RelatorioBoxPlot(estacao, typeMeasurement.getValue(), this.data);
        if (relatorioBoxPlot.getprimeiroQuartil() == null) {
            messageNull.setText("Sem dados disponíveis");
        } else {
            messageNull.setText("");
            paneLane.setVisible(true);
            quartil1.setText(relatorioBoxPlot.getprimeiroQuartil());
            quartil2.setText(relatorioBoxPlot.getSegundoQuartil());
            quartil3.setText(relatorioBoxPlot.getterceiroQuartil());
            limiteSuperior.setText(String.valueOf(relatorioBoxPlot.getLimiteSuperior()));
            limiteInferior.setText(String.valueOf(relatorioBoxPlot.getLimiteInferior()));
        }
    }
}

