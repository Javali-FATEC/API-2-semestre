package javalee.com;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javalee.com.bd_connection.DbConnection;
import javalee.com.entities.Cities;
import javalee.com.entities.City;
import javalee.com.entities.Station;
import javalee.com.services.utilInterno;
import javafx.util.StringConverter;


public class ManageCitiesController implements Initializable {

    @FXML
    private Button btnSaveCities;

    @FXML
    private Button btnDeleteCities;

    @FXML
    private ComboBox<City> cBoxCity;

    @FXML
    private ObservableList<City> listCity = FXCollections.observableArrayList();

    @FXML
    private TextField txtnome;

    @FXML
    private TextField txtsigla;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cBoxCity.setConverter(new StringConverter<City>() {
            @Override
            public String toString(City city) {
                return city != null ? city.getNome() : "";
            }

            @Override
            public City fromString(String string) {
                return cBoxCity.getItems().stream()
                        .filter(city -> city.getNome().equals(string))
                        .findFirst()
                        .orElse(null);
            }
        });

        btnSaveCities.setText("Cadastrar");
        btnDeleteCities.setDisable(true);

        loadCities();

    cBoxCity.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            txtnome.setText(newValue.getNome());
            txtsigla.setText(newValue.getSigla());
            txtnome.setEditable(true); 
            txtsigla.setEditable(true);
        }
    });
}

private void loadCities() {
    cBoxCity.getItems().clear();
    Cities cities = new Cities();
    List<City> cityList = Cities.getAllCity(); 
    listCity.addAll(cityList);
    cBoxCity.setItems(listCity);
}


@FXML
public void selecionaCidade(){
    if(cBoxCity.getValue() != null)
    {
        btnSaveCities.setText("Atualizar");
    }
    else
    {
        btnSaveCities.setText("Cadastrar");
    }
    btnDeleteCities.setDisable(cBoxCity.getValue() == null);
}

@FXML
public void btnSaveCities() {
    String sql = "";
    if( cBoxCity.getValue() != null )
    {
        City cityUpdate = new City( cBoxCity.getValue().getIdCidade() );
        sql = cityUpdate.toUpdateSql(txtnome.getText(), txtsigla.getText());
    }
    else
    {
        City cidadeSalvar = new City(txtsigla.getText(),txtnome.getText());
        sql = cidadeSalvar.toInsertSql();
    }
    
    DbConnection db = new DbConnection();
    db.executeNotReturn(sql);
    db.Desconnect();

    utilInterno.alertSucesso("Concluído com Sucesso", "Sucesso");
    clear();
    
}
private void clear(){
    txtnome.setText("");
    txtsigla.setText("");
    this.initialize(null, null);
}

@FXML
public void btnDeleteCities(){
    City cityDelete = new City( cBoxCity.getValue().getIdCidade() );
    List<Station> listaEstacoes = cityDelete.getStations();
    if(listaEstacoes.size() != 0)
    {
        utilInterno.alertError("Há estações cadastradas nessa cidade", "Nâo é possível excluir essa cidade");
    }
    else
    {
        cityDelete.detele();
        utilInterno.alertSucesso("Excluído com sucesso","Sucesso");
        clear();
    }
}
}