package javalee.com;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javalee.com.entities.*;
import javafx.fxml.FXML;

import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;



public class BoxPlotController {
    
  @FXML
  private DatePicker datePicker;

  @FXML
  private ChoiceBox<String> selectorCity;

  @FXML
  private ChoiceBox<ChoiceBoxItem> selectorMetric;

  @FXML
  private ChoiceBox<String> typeMeasurementCity;

  @FXML
  private ChoiceBox<String> typeMeasurementStation;

  ObservableList<String> rowsTypeMeasurement;
  ObservableList<String> rowsTypeMeasurementStation;

  @FXML
  private void initialize(){
    typeMeasurementStation.setDisable(true);
    Cities cities = new Cities();
    List<City> listaTodasCidades = cities.getAllCity();
    List<String> nomeCidades = new LinkedList<>();

    for (City city : listaTodasCidades) {
      nomeCidades.add(city.getNome());
    }
    rowsTypeMeasurement = FXCollections.observableArrayList(nomeCidades);
    typeMeasurementCity.setItems(rowsTypeMeasurement);
  }
  
  public void selecionarCidade() {
    typeMeasurementStation.setDisable(false);
    Stations stations = new Stations();
    List<Station> listaTodasEstacoes = stations.buscarEstacoesCidade(typeMeasurementCity.getValue());
    
    List<String> nomeEstacoes = new LinkedList<>();
    
    for (Station station : listaTodasEstacoes) {
      nomeEstacoes.add(station.getCodigo());
    }
    rowsTypeMeasurementStation = FXCollections.observableArrayList(nomeEstacoes);
    typeMeasurementStation.setItems(rowsTypeMeasurementStation);
  }
  
  public void openScreenBoxPlotReportData() {

    LocalDate dataInicial = datePicker.getValue();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String dataFormatada = dataInicial.format(formatter);
    
    try{
      App.openWindowReportBoxPlot("boxPlotReport", typeMeasurementStation.getValue(), dataFormatada);
    } catch (IOException e){
      e.printStackTrace();
    }
  }

  class ItemChoiceBox {
    private String display;
    private String value;
  
    public ItemChoiceBox(String display, String value) {
      this.display = display;
      this.value = value;
    }
  
    public String getDisplay() {
      return display;
    }
  
    public String getValue() {
      return value;
    }
  
    @Override
    public String toString() {
      return display;
    }
  }  
}
