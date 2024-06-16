package javalee.com;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javalee.com.bd_connection.DbConnection;
import javalee.com.entities.Cities;
import javalee.com.entities.City;
import javalee.com.entities.Station;
import javalee.com.entities.Stations;

public class ManageStationsController {
    @FXML
    private TextField identificador;

    @FXML
    private TextField latitude;
    
    @FXML
    private TextField longitude;

    @FXML
    private ComboBox<String> typeMeasurementCity;
    
    @FXML
    private ComboBox<String> typeMeasurementStation;

    @FXML
    ObservableList<String> rowsTypeMeasurementStation;

    @FXML
    private Button btnSalvar;

    @FXML
    private void initialize(){
        typeMeasurementStation.setDisable(true);
        btnSalvar.setText("Cadastrar");
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

    public void selectStation()
    {
        if (typeMeasurementCity.getValue() != null || typeMeasurementStation.getValue() != null){
            btnSalvar.setText("Atualizar");
            Stations stations = new Stations();
            
            Station station = stations.searchStation(typeMeasurementCity.getValue(), typeMeasurementStation.getValue());
            
            if (station != null){
                latitude.setText(station.getLatitude());
                longitude.setText(station.getLongitude());
            }
        }
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
        String latitudeTxt = latitude.getText();
        String longitudeTxt = longitude.getText();

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
                    String mensagemRetorno = "";
                    int idCidade = resultIdCidade.getInt("id_cidade");
                    if (cidade != null && estacao != null) {
                        db.executeNotReturn("UPDATE estacao SET codigo = '" +
                        changeToStrIdentificador + "', longitude = '"+ longitudeTxt +"', latitude = '"+ latitudeTxt +"' " + 
                        "WHERE id_cidade = '" + idCidade + "' AND codigo = '" + estacao + "'");  
                        mensagemRetorno= "Código alterado com sucesso!";                      
                    }
                    else
                    {                
                        db.executeNotReturn("INSERT INTO estacao (id_cidade, latitude, longitude, codigo) VALUES ('" +
                            idCidade + "', '" + latitudeTxt + "', '" + longitudeTxt + "', '" + changeToStrIdentificador + "')");
                        mensagemRetorno = "Código cadastrado com sucesso!";
                    }

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Sucesso ao Atualizar!");
                    alert.setHeaderText(null);
                    alert.setContentText(mensagemRetorno);
                    alert.showAndWait();

                    identificador.setText("");
                    typeMeasurementCity.setValue(null);
                    typeMeasurementStation.setValue(null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.Desconnect();
        }
    }

    public void deleteStation() {
        DbConnection db = new DbConnection();
        String cidade = typeMeasurementCity.getValue();
        String estacao = typeMeasurementStation.getValue();
    
        ResultSet resultIdCidade = db.executeWithReturn("SELECT id_cidade FROM cidade WHERE nome_cidade = '" + cidade + "'");
    
        try {
            if (resultIdCidade.next()) {
                int idCidade = resultIdCidade.getInt("id_cidade");
                
                ResultSet registrosVinculados = db.executeWithReturn("SELECT COUNT(*) AS count FROM registro WHERE id_estacao = (SELECT id_estacao FROM estacao WHERE id_cidade = '" + idCidade + "' AND codigo = '" + estacao + "')");
                registrosVinculados.next();
                int count = registrosVinculados.getInt("count");
                
                if (count > 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro ao Excluir Estação");
                    alert.setHeaderText(null);
                    alert.setContentText("Impossível excluir estação pois há registros vinculados a ela.");
                    alert.showAndWait();
                } else {
                    db.executeNotReturn("DELETE FROM estacao WHERE id_cidade = '" + idCidade + "' AND codigo = '" + estacao + "'");
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Exclusão Bem-Sucedida!");
                    alert.setHeaderText(null);
                    alert.setContentText("Estação excluída com sucesso!");
                    alert.showAndWait();
    
                    identificador.setText("");
                    latitude.setText("");
                    longitude.setText("");
                    typeMeasurementCity.setValue(null);
                    typeMeasurementStation.setValue(null);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.Desconnect();
        }
    } 
}

