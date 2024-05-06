package javalee.com;

import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javalee.com.entities.RelatorioMedia;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javalee.com.entities.RelatorioMedia;
import javalee.com.entities.RelatoriosMedia;

import java.net.URL;

public class ReportDataController implements Initializable {

    private List<RelatorioMedia> relatorio;

    @FXML
    private TableView<String[]> tableDataRelatorio;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setRelatorio(List<RelatorioMedia> relatorio) {
        this.relatorio = relatorio;
        listaDados(relatorio);
    }

    public void listaDados(List<RelatorioMedia> relatorio) {
        tableDataRelatorio.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableDataRelatorio.getItems().clear();
        tableDataRelatorio.getColumns().clear();

        if (tableDataRelatorio.getColumns().isEmpty()) {

            TableColumn<String[], String> coluna1 = new TableColumn<>("Data e hora");
            TableColumn<String[], String> coluna2 = new TableColumn<>("Valor");
            TableColumn<String[], String> coluna3 = new TableColumn<>("Unidade");
            TableColumn<String[], String> coluna4 = new TableColumn<>("Métrica");

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
            coluna4.setCellValueFactory(cellData -> {
                String[] rowData = cellData.getValue();
                return new SimpleStringProperty(rowData[3]);
            });

            tableDataRelatorio.getColumns().addAll(coluna1, coluna2, coluna3, coluna4);

            ObservableList<String[]> dataTable = FXCollections.observableArrayList();
            for (RelatorioMedia linhaRelatorio : relatorio) {
                String[] rowData = new String[] {
                        linhaRelatorio.getHoraMedia(), String.valueOf(linhaRelatorio.getValor()),
                        linhaRelatorio.getDado(), linhaRelatorio.getUnidade()
                };
                dataTable.add(rowData);
            }
            tableDataRelatorio.setItems(dataTable);

        }
    }
}
