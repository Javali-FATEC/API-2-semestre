package javalee.com.entities;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javalee.com.bd_connection.DbConnection;

public class Units {
    
    private List<UnitMeasure> listOfUnits;
    
    private ObservableList<String> listNamesOfUnits;

    public ObservableList<String> getUnitsNames() {
        listNamesOfUnits = FXCollections.observableArrayList();
        for (UnitMeasure unitMeasure : this.listOfUnits) {
            listNamesOfUnits.add(unitMeasure.getName());
        }
        return listNamesOfUnits;
    }

    public void loadUnits() {
        DbConnection db = new DbConnection();

        ResultSet result = db.executeWithReturn("SELECT * FROM unidade_medida");
        listOfUnits = new LinkedList<UnitMeasure>();

        UnitMeasure unitMeasure;

        try {
            while (result.next()) {
                int idReturn = result.getInt("id_unidade_medida");
                String nameReturn = result.getString("nome");
                String descriptionUnit = result.getString("descricao");
                unitMeasure = new UnitMeasure(idReturn, nameReturn, descriptionUnit);
                listOfUnits.add(unitMeasure);
            }

        } catch (Exception e) {
        }

        db.Desconnect();
    }

    
}
