package javalee.com;

import java.io.IOException;
import java.util.LinkedList;
import javafx.beans.property.SimpleStringProperty;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import java.util.List;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.Map;

public class openToolTipController implements Initializable{

    @FXML
    private TableView<String[]> tableMap;

    @FXML
    private TableView<String[]> tableMapB;

    @FXML
    private Label typeMeasurementLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaDados();
        listaDadosB();
    }
    
    private void listaDados(){
        tableMap.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableMap.getItems().clear();
        tableMap.getColumns().clear();

        if(tableMap.getColumns().isEmpty()) {

            TableColumn<String[], String> coluna1 = new TableColumn<>("Coluna");
            TableColumn<String[], String> coluna2 = new TableColumn<>("Medição");
            TableColumn<String[], String> coluna3 = new TableColumn<>("Unidade");

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

            tableMap.getColumns().addAll(coluna1, coluna2, coluna3);
        }

        ObservableList<String[]> dataTable = FXCollections.observableArrayList();

        dataTable.add(new String[]{"1 ou A","Data","Data(DD/MM/YYYY)"});
        dataTable.add(new String[]{"2 ou B","Hora","Inteiro"});
        dataTable.add(new String[]{"3 ou C","Temperatura","Kelvin ( K )"});
        dataTable.add(new String[]{"4 ou D","Umidade","% Porcentagem"});
        dataTable.add(new String[]{"5 ou E","Pressão","Hectapascal(hPa)"});
        dataTable.add(new String[]{"6 ou F","Vel. do Vento","Metros por Segundo(m/s)"});
        dataTable.add(new String[]{"7 ou G","Direção do Vento","Metros por Segundo(m/s)"});
        dataTable.add(new String[]{"8 ou H","Nebulosidade","Decimos"});
        dataTable.add(new String[]{"9 ou I","Insolação","horas"});
        dataTable.add(new String[]{"10 ou J","Temp. Máxima","Kelvin ( K )"});
        dataTable.add(new String[]{"11 ou K","Temp. Minima","Kelvin ( K )"});
        dataTable.add(new String[]{"12 ou L","Chuva","Milimetros( mm )"});

        tableMap.setItems(dataTable);
    }

    private void listaDadosB(){
        tableMapB.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableMapB.getItems().clear();
        tableMapB.getColumns().clear();

        if(tableMapB.getColumns().isEmpty()) {

            TableColumn<String[], String> coluna1 = new TableColumn<>("Coluna");
            TableColumn<String[], String> coluna2 = new TableColumn<>("Medição");
            TableColumn<String[], String> coluna3 = new TableColumn<>("Unidade");

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

            tableMapB.getColumns().addAll(coluna1, coluna2, coluna3);
        }

        ObservableList<String[]> dataTable = FXCollections.observableArrayList();

        dataTable.add(new String[]{"1 ou A","Data","Data(DD/MM/YYYY)"});
        dataTable.add(new String[]{"2 ou B","Hora","Hora"});
        dataTable.add(new String[]{"3 ou C","Temperatura","Celsius ( ºC )"});
        dataTable.add(new String[]{"4 ou D","Temp. Máxima","Celsius (ºC)"});
        dataTable.add(new String[]{"5 ou E","Temp. Mínima","Celsius (ºC )"});
        dataTable.add(new String[]{"6 ou F","Umidade","% Porcentagem"});
        dataTable.add(new String[]{"7 ou G","Umidade Máxima","% Porcentagem"});
        dataTable.add(new String[]{"8 ou H","Umidade Mínima","% Porcentagem"});
        dataTable.add(new String[]{"9 ou I","Ponto de Orvalho","Celsius ( ºC )"});
        dataTable.add(new String[]{"10 ou J","Ponto de Orvalho Máxima","Celsius ( ºC)"});
        dataTable.add(new String[]{"11 ou K","Ponto de Orvalho Mínimo","Celsius ( ºC)"});
        dataTable.add(new String[]{"12 ou L","Pressão","Hectapascal(hPa)"});
        dataTable.add(new String[]{"13 ou M","Pressão Máxima","Hectapascal(hPa)"});
        dataTable.add(new String[]{"14 ou N","Pressão Mínima","Hectapascal(hPa)"});
        dataTable.add(new String[]{"15 ou O","Velocidade do Vento","Metros por Segundo (m/s)"});
        dataTable.add(new String[]{"16 ou P","Direção do Vento","Metros por Segundo (m/s)"});
        dataTable.add(new String[]{"17 ou Q","Rajada de Vento","Metros por Segundo (m/s)"});
        dataTable.add(new String[]{"18 ou R","Radiação","Kilojoule por metro²(KJ/m²)"});
        dataTable.add(new String[]{"17 ou S","Chuva","Milimetros(mm)"});

        tableMapB.setItems(dataTable);
    }

}

