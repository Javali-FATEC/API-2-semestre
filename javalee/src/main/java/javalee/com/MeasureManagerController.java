package javalee.com;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javalee.com.bd_connection.DbConnection;
import javalee.com.entities.Metric;
import javalee.com.entities.MetricUnity;
import javalee.com.entities.MetricUnitys;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

// import org.postgresql.translation.messages_bg;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class MeasureManagerController {

    @FXML
    private ObservableList<String> rowsTipeMetricUnity;

    @FXML
    private TableView<String[]> tblUnitMeasurements;

    @FXML
    private ComboBox<String> cbUnity;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtDescription;

    @FXML
    private void initialize() {
        txtDescription.setDisable(true);
        MetricUnitys metricUnitys = new MetricUnitys();
        List<MetricUnity> listMetricUnity = metricUnitys.getAllMetricUnity();
        List<String> nameMetricUnitys = new LinkedList<>();

        for (MetricUnity metricUnity : listMetricUnity) {
            nameMetricUnitys.add(metricUnity.getNameMetricUnity());
        }

        rowsTipeMetricUnity = FXCollections.observableArrayList(nameMetricUnitys);
        cbUnity.setItems(rowsTipeMetricUnity);
    }

    @FXML
    public void handleMetricUnitySelection(ActionEvent event) {
        String selectedMetricUnity = cbUnity.getValue();
        if (selectedMetricUnity != null) {
            MetricUnitys metricUnitys = new MetricUnitys();
            List<Metric> listMetricUnities = metricUnitys.searchMetricUnityMeasure(selectedMetricUnity);
            this.listaDados(listMetricUnities);

        }
        txtDescription.setDisable(false);

    }

    private void listaDados(List<Metric> lista) {
        tblUnitMeasurements.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tblUnitMeasurements.getItems().clear();
        tblUnitMeasurements.getColumns().clear();

        if (tblUnitMeasurements.getColumns().isEmpty()) {

            TableColumn<String[], String> coluna1 = new TableColumn<>("Variável climática");

            coluna1.setCellValueFactory(cellData -> {
                String[] rowData = cellData.getValue();
                return new SimpleStringProperty(rowData[0]);
            });

            tblUnitMeasurements.getColumns().addAll(coluna1);
        }

        ObservableList<String[]> dataTable = FXCollections.observableArrayList();

        for (Metric metric : lista) {
            String[] rowData = new String[] { metric.getNome() };
            dataTable.add(rowData);
        }

        tblUnitMeasurements.setItems(dataTable);
    }

    @FXML
    void editMeasure(ActionEvent event) {
        DbConnection db = new DbConnection();
        String metricUnity = cbUnity.getValue();
        String changeDescription = txtDescription.getText();
        MetricUnitys metricUnitys = new MetricUnitys();
        List<MetricUnity> listDecription = metricUnitys.getAllMetricUnity();
        List<String> nameDescription = new LinkedList<>();

        for (MetricUnity metric : listDecription){

            nameDescription.add(metric.getDescription());
        }

        System.out.println("SELECT id_unidade_medida FROM unidade_medida WHERE descricao = '" +metricUnity+"'");
        

        ResultSet resultIdMetric = db.executeWithReturn("SELECT id_unidade_medida FROM unidade_medida WHERE nome = '" +metricUnity+"'");

        try {
            boolean ifCodeExist = false;
            for (MetricUnity metricLoop : listDecription){
                if (metricLoop.getDescription().equals(changeDescription)) {
                    ifCodeExist = true;
                    break;
                    
                }
            }

            if (ifCodeExist) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Falha ao Atualizar");
                alert.setHeaderText(null);
                alert.setContentText("Descrição  já relacionado a " + metricUnity + ".");
                alert.showAndWait();

                
            }else{
                if (resultIdMetric.next()) {
                    if (listDecription != null) {
                        db.executeNotReturn("UPDATE unidade_medida SET descricao = '"+changeDescription+"' WHERE nome =  '"+metricUnity+"'");

                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Sucesso ao Atulizar!");
                        alert.setHeaderText(null);
                        alert.setContentText("Descrição alterado com sucesso!");

                        alert.showAndWait();

                        txtDescription.setText("");
                        
                    }
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            db.Desconnect();
        }

    }
}
