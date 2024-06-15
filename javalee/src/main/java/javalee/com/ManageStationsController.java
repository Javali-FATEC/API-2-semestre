package javalee.com;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javalee.com.bd_connection.DbConnection;
import javalee.com.entities.Cities;
import javalee.com.entities.City;
import javalee.com.entities.Station;
import javalee.com.entities.Stations;

public class ManageStationsController {
    @FXML
    private TextField identificador;

    @FXML
    private ComboBox<String> typeMeasurementCity;
    
    @FXML
    private ComboBox<String> typeMeasurementStation;

    @FXML
    ObservableList<String> rowsTypeMeasurementStation;

    @FXML
    private void initialize(){
        typeMeasurementStation.setDisable(true);
        identificador.setDisable(true);
        Cities cities = new Cities();
        List<City> listaDeCidades = cities.getAllCity();
        List<String> nomeCidades = new LinkedList<>();

        for (City city : listaDeCidades){
            nomeCidades.add(city.getNome());
        }
        
        rowsTypeMeasurementStation = FXCollections.observableArrayList(nomeCidades);
        typeMeasurementCity.setItems(rowsTypeMeasurementStation);
    }

    public void selecionarEstacao(){
        typeMeasurementStation.setDisable(false);
        Stations stations = new Stations();
        List<Station> listaDeEstacoes = stations.buscarEstacoesCidade(typeMeasurementCity.getValue());
        List<String> nomeEstacoes = new LinkedList<>();

        for (Station station : listaDeEstacoes){
            nomeEstacoes.add(station.getCodigo());
        }

        rowsTypeMeasurementStation = FXCollections.observableArrayList(nomeEstacoes);
        typeMeasurementStation.setItems(rowsTypeMeasurementStation);
    }

    public void selectStation(){
        identificador.setDisable(false);
    }

    public void updateStationCode(){
        DbConnection db = new DbConnection();
        Stations stations = new Stations();
        List<Station> listaDeEstacoes = stations.buscarEstacoesCidade(typeMeasurementCity.getValue());
        List<String> nomeEstacoes = new LinkedList<>();

        for (Station station : listaDeEstacoes){
            nomeEstacoes.add(station.getCodigo());
        }

        String cidade = typeMeasurementCity.getValue();
        String estacao = typeMeasurementStation.getValue();
        String changeToStrIdentificador = identificador.getText();

        ResultSet resultIdCidade = db.executeWithReturn("SELECT id_cidade FROM cidade WHERE nome_cidade = '"+ cidade +"'");

        try {
            boolean codigoJaExiste = false;
            
            for (Station estacaoLoop : listaDeEstacoes) {
                if (estacaoLoop.getCodigo().equals(changeToStrIdentificador)) {
                    codigoJaExiste = true;
                    break;
                }
            }
            
            if (codigoJaExiste) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Falha ao Atualizar!");
                alert.setHeaderText(null);
                alert.setContentText("Código já relacionado a " + cidade + ".");
                alert.showAndWait();
            } else {
                if (resultIdCidade.next()) {
                    if (cidade != null && estacao != null) {
                        int idCidade = resultIdCidade.getInt("id_cidade");
            
                        db.executeNotReturn("UPDATE estacao SET codigo = '" + changeToStrIdentificador + "' WHERE id_cidade = '" + idCidade + "' AND codigo = '" + estacao + "'");
                    
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Sucesso ao Atualizar!");
                        alert.setHeaderText(null);
                        alert.setContentText("Código alterado com sucesso!");
                        alert.showAndWait();
            
                        identificador.setText("");
                        typeMeasurementCity.setValue(null);
                        typeMeasurementStation.setValue(null);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.Desconnect();
        }
    }
}