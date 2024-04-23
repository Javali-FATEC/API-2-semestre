package javalee.com;

import javafx.beans.property.SimpleStringProperty;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

// import javax.swing.Action;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javalee.com.services.DataFile;
import javalee.com.services.DataMeasurement;

public class PreviewDataController implements Initializable{

    private DataFile dataFile;
    private DbConnection dbConnection;

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

    private Connection connection;
    public void handleTypeMeasurementChange(){
        String selectedItem = typeMeasurement.getValue();
        listaDados(selectedItem);

        try {
            connection = connectToDb("jdbc://posgresql:/localhost/");
        } catch (Exception e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            // Exibir mensagem de erro ao usuário
            return;
        }

        ObservableList<String[]> dataTable = FXCollections.observableArrayList();
        String messageAboutData = "Não há registros para esse tipo de medição";

        for (DataMeasurement dataLine : this.dataFile.getDataMeasurements()) {
            boolean lineIncomplete = dataLine.getDate() == null || dataLine.getHour() == null || dataLine.getValue() == null || dataLine.getTypeMeasurament() == null;
            if (!lineIncomplete && dataLine.getTypeMeasurament().equals(tipoMedida)) {
                messageAboutData = "Unidade de medida: " + dataLine.getUnidade();
                String[] rowData = new String[]{dataLine.getDate(), dataLine.getHour() + ":00", dataLine.getValue()};
                dataTable.add(rowData);

                // Insert data into database
                try {
                    String sql = "INSERT INTO RelatorioClima (data, hora, valor, tipo_medicao) VALUES (?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, dataLine.getDate());
                    statement.setString(2, dataLine.getHour());
                    statement.setString(3, dataLine.getValue());
                    statement.setString(4, dataLine.getTypeMeasurament());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    System.err.println("Erro ao inserir dados: " + e.getMessage());
                    
                }
            }
        }

        typeMeasurementLabel.setText(messageAboutData);
        tableDataMeasurement.setItems(dataTable);

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

