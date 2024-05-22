package javalee.com;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javalee.com.services.DataFile;
import javalee.com.services.DataMeasurement;

public class PreviewRiskValuesController implements Initializable {

    private DataFile dataFile;

    ObservableList<String> rowsTypeMeasurementRiskValues = FXCollections.observableArrayList(
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
    private AnchorPane previewData;

    @FXML
    private ChoiceBox<String> typeMeasurementRiskValue;

    @FXML
    private Label typeMeasurementRiskValueLabel;

    @FXML
    private TableView<String[]> tableDataMeasurementRiksValue;

    public void handleTypeMeasurementChangeRiskValue(ActionEvent event) {
        String selectedItem = typeMeasurementRiskValue.getValue();
        listaDados(selectedItem);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeMeasurementRiskValue.setItems(rowsTypeMeasurementRiskValues);
    }

    public void setDataFile(DataFile dataFile) {
        this.dataFile = dataFile;
    }

    private void listaDados(String tipoMedida) {
        tableDataMeasurementRiksValue.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableDataMeasurementRiksValue.getItems().clear();
        tableDataMeasurementRiksValue.getColumns().clear();

        if (tableDataMeasurementRiksValue.getColumns().isEmpty()) {

            TableColumn<String[], String> coluna1 = new TableColumn<>("Data");
            TableColumn<String[], String> coluna2 = new TableColumn<>("Hora");
            TableColumn<String[], String> coluna3 = new TableColumn<>("Valor");

            coluna1.setCellValueFactory(cellData -> {
                String[] rowData = cellData.getValue();
                return new SimpleStringProperty(rowData[0]);
            });
            coluna2.setCellValueFactory(cellData -> {
                String[] rowData = cellData.getValue();
                return new SimpleStringProperty(rowData[1]);
            });

            coluna3.setCellValueFactory(cellData -> {
                String[] rowData = cellData.getValue();
                return new SimpleStringProperty(rowData[2]);
            });

            tableDataMeasurementRiksValue.getColumns().addAll(coluna1, coluna2, coluna3);
        }

        ObservableList<String[]> dataTable = FXCollections.observableArrayList();

        String messageAboutData = "Não há registros para esse tipo de medição";
        for (DataMeasurement dataLine : this.dataFile.getDataMeasurements()) {
            boolean lineIncompleted = dataLine.getDate() == null || dataLine.getHour() == null
                    || dataLine.getValue() == null || dataLine.getTypeMeasurament() == null;
            if (!lineIncompleted && dataLine.getTypeMeasurament() == tipoMedida) {
                messageAboutData = "Unidade de medida: " + dataLine.getUnidade();
                String[] rowData = new String[] { dataLine.getDate(), dataLine.getHour() + ":00", dataLine.getValue() };
                dataTable.add(rowData);
            }
        }

        typeMeasurementRiskValueLabel.setText(messageAboutData);
        tableDataMeasurementRiksValue.setItems(dataTable);
    }

}
