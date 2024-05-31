package javalee.com;

import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javalee.com.entities.Cities;
import javalee.com.entities.City;
import javalee.com.entities.Station;
import javalee.com.entities.Stations;

public class ManageStationsController {
    @FXML
    private ComboBox<String> typeMeasurementCity;
    
    @FXML
    private ComboBox<String> typeMeasurementStation;

    @FXML
    ObservableList<String> rowsTypeMeasurementStation;

    @FXML
    private void initialize(){
        typeMeasurementStation.setDisable(true);
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

    public void selecionarCidade(){
        typeMeasurementStation.setDisable(false);
    }
}