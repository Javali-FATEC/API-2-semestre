package javalee.com.entities;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javalee.com.bd_connection.DbConnection;

public class Stations {

    private List<Station> listStations;

    public Stations() {
        this.listStations = new LinkedList<Station>();
    }

    public Station searchStation(String code, int idCity) {

        DbConnection db = new DbConnection();
        ResultSet resultStation = db.executeWithReturn("SELECT * FROM estacao WHERE codigo = '" + code + "'");
        Station station = null;

        try {
            if (resultStation.next()) {
                int idReturn = resultStation.getInt("id_estacao");
                String codeReturn = resultStation.getString("codigo");

                station = new Station(idReturn,codeReturn); 
            }else{
                db.executeNotReturn("INSERT INTO estacao (id_cidade, codigo) VALUES ("+idCity+", '"+code+"');");
                return searchStation(code, idCity);
            }

        } catch (Exception e) {

        }
        db.Desconnect();

        return station;
    }

    public List<Station> buscarEstacoesCidade (String nomeCidade) {
        List<Station> retorno = new LinkedList<Station>();
        
        DbConnection db = new DbConnection();
        ResultSet resultStation = db.executeWithReturn("select * FROM estacao inner join cidade on cidade.id_cidade = estacao.id_cidade where cidade.nome_cidade = '"+nomeCidade+"'");

        try {
            while (resultStation.next()) {
                int idEstacao = resultStation.getInt("id_estacao");
                String codigo = resultStation.getString("codigo");

                retorno.add(new Station(idEstacao, codigo));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            db.Desconnect();
        }

        return retorno;
    }

    public void loadStation() {

        DbConnection db = new DbConnection();
        ResultSet resultStation = db.executeWithReturn("SELECT * FROM estacao");

        try {
            while (resultStation.next()) {
                int idReturn = resultStation.getInt("id_estacao");
                String codeReturn = resultStation.getString("codigo");
                this.listStations.add(new Station(idReturn, codeReturn));

            }

        } catch (Exception e) {
        }
        db.Desconnect();
    }

    public List<String> getAllCodStations(String nomeCidade) {
        List<Station> stations = buscarEstacoesCidade(nomeCidade);

        List<String> nomeEstacoes = new LinkedList<>();
    
        for (Station station : stations) {
          nomeEstacoes.add(station.getCodigo());
        }
        return nomeEstacoes;
    }
}
