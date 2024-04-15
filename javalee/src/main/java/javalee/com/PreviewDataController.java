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
import javalee.com.services.DataFile;
import javalee.com.services.DataMeasurement;

public class PreviewDataController implements Initializable{

    private DataFile dataFile;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeMeasurement.setItems(rowsTypeMeasurement);
    }

    public void setDataFile(DataFile dataFile) {
        this.dataFile = dataFile;
    }

    public void handleTypeMeasurementChange(){
        String selectedItem = typeMeasurement.getValue();
        listaDados(selectedItem);
    }

    private void listaDados(String tipoMedida){
        tableDataMeasurement.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableDataMeasurement.getItems().clear();
        tableDataMeasurement.getColumns().clear();

        if(tableDataMeasurement.getColumns().isEmpty()) {

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

            tableDataMeasurement.getColumns().addAll(coluna1, coluna2, coluna3);
        }

        ObservableList<String[]> dataTable = FXCollections.observableArrayList();

        String messageAboutData = "Não há registros para esse tipo de medição";
        for(DataMeasurement dataLine : this.dataFile.getDataMeasurements()){
            boolean lineIncompleted = dataLine.getDate()==null || dataLine.getHour() == null || dataLine.getValue() == null || dataLine.getTypeMeasurament() == null;
            if(!lineIncompleted && dataLine.getTypeMeasurament()==tipoMedida)
            {
                messageAboutData = "Unidade de medida: " + dataLine.getUnidade();
                String[] rowData = new String[]{dataLine.getDate(), dataLine.getHour()+":00", dataLine.getValue()};
                dataTable.add(rowData);
            }
        }

        typeMeasurementLabel.setText(messageAboutData);
        tableDataMeasurement.setItems(dataTable);
    }

}

