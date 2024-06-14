package javalee.com;

import javafx.beans.property.SimpleStringProperty;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javalee.com.entities.LeituraAjustada;
import javalee.com.entities.LeituraSuspeita;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class SeeInconsistenciesController implements Initializable{

    private List<LeituraSuspeita> lineErrors;
    private List<LeituraAjustada> listaCorrecoes;

    private Stage dialogStage;

    @FXML
    private TableView<String[]> tableLineErrors;

    @FXML
    private Label typeMeasurementLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setLineErrors(List<LeituraSuspeita> lineErrors) {
        this.lineErrors = lineErrors;
        listaDados();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public List<LeituraAjustada> getCorrecaoDados(){
        return this.listaCorrecoes;
    }

    @FXML
    public void salvarAlteracoes(){
        dialogStage.close();
    }

    @FXML
    public void cancelarAlteracoes(){
        this.listaCorrecoes = null;
        dialogStage.close();
    }

    private void listaDados(){
        this.listaCorrecoes = new LinkedList<LeituraAjustada>();
        tableLineErrors.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        tableLineErrors.getItems().clear();
        tableLineErrors.getColumns().clear();

        if(tableLineErrors.getColumns().isEmpty()) {

            TableColumn<String[], String> coluna1 = new TableColumn<>("Linha");
            TableColumn<String[], String> coluna2 = new TableColumn<>("Variável");
            TableColumn<String[], String> coluna3 = new TableColumn<>("Valor");
            TableColumn<String[], String> coluna4 = new TableColumn<>("");
            TableColumn<String[], String> coluna5 = new TableColumn<>("Editar");

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

            coluna5.setCellFactory(param -> new TableCell<String[], String>() {
                final Button btn = new Button("Editar");

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    }
                    else
                    {
                        btn.setOnAction(event -> {
                            String[] rowData = getTableView().getItems().get(getIndex());
                            TextInputDialog dialog = new TextInputDialog(rowData[2]);
                            dialog.setTitle("Editar Linha");
                            dialog.setHeaderText(null);
                            dialog.setContentText("Edite o valor do risco:");

                            TextField textField = dialog.getEditor();

                            textField.setTextFormatter(new TextFormatter<>(change -> {
                                if (change.getControlNewText().matches("\\d*|\\d+,\\d*")) {
                                    return change;
                                } else {
                                    return null;
                                }
                            }));

                            dialog.showAndWait().ifPresent(newValue -> {
                                rowData[3] = "Valor modificado para: " + newValue;
                                int linha = Integer.parseInt(rowData[0]);
                                LeituraAjustada leituraAjustada = new LeituraAjustada(linha, rowData[1],newValue.replace(",","."));
                                listaCorrecoes.add(leituraAjustada);
                                getTableView().refresh();
                            });
                        });
                        setGraphic(btn);
                        setText(null);
                    }
                }
            });
            
            coluna1.setPrefWidth(35);
            coluna1.setMaxWidth(500);

            coluna2.setPrefWidth(140);
            coluna2.setMaxWidth(500);

            coluna3.setPrefWidth(45);
            coluna3.setMaxWidth(500);

            coluna4.setPrefWidth(310);
            coluna4.setMaxWidth(500);

            coluna5.setPrefWidth(55);
            coluna5.setMaxWidth(500);

            tableLineErrors.getColumns().addAll(coluna1, coluna2, coluna3, coluna4,coluna5);
        }

        ObservableList<String[]> dataTable = FXCollections.observableArrayList();

        for (LeituraSuspeita leituraSuspeita : lineErrors) {
            String[] rowData = new String[]{leituraSuspeita.getLinha(),leituraSuspeita.getData().getTypeMeasurament(),leituraSuspeita.getData().getValue(),leituraSuspeita.getErro()};
            dataTable.add(rowData);
        }

        tableLineErrors.setItems(dataTable);
    }

}

