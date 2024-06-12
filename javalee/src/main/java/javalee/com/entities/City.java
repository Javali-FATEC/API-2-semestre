package javalee.com.entities;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javalee.com.bd_connection.DbConnection;

public class City {
    private String sigla;
    private String nome;
    private int idCidade;

    public City(String sigla, String nome, int idCidade) {
        this.sigla = sigla;
        this.nome = nome;
        this.idCidade = idCidade;
    } 

    public City(String sigla, String nome) {
        this.sigla = sigla;
        this.nome = nome;
    }

    public City( int id){
        this.idCidade =  id;
    }

    public int getIdCidade() {
        return idCidade;
    }

    public String getNome() {
        return this.nome;
    }

    public String getSigla(){
        return this.sigla;
    }
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toInsertSql(){
        return "INSERT INTO cidade (sigla_cidade, nome_cidade) VALUES ('"+sigla+"', '"+nome+"');";
    }

    public String toUpdateSql( String nomeAtualizado, String siglaNova ){
        return "UPDATE cidade SET sigla_cidade='" + siglaNova + "', nome_cidade='" + nomeAtualizado + "' WHERE id_cidade ='" + String.valueOf( this.idCidade ) + "'";
    }

    public List<Station> getStations() {
        DbConnection db = new DbConnection();
        List<Station> retorno = new LinkedList<>();
        ResultSet resultStation = db.executeWithReturn("SELECT * FROM estacao WHERE id_cidade = '" + String.valueOf( this.idCidade ) + "'");

        try {
            if (resultStation.next()) {
                String codigoEstacao = resultStation.getString("codigo");

                Station station = new Station(codigoEstacao);
                retorno.add(station);
            }

        } catch (Exception e) {
        }
        db.Desconnect();

        return retorno;
    }

    public void detele()
    {
        DbConnection db = new DbConnection();
        String sql = "DELETE FROM cidade WHERE id_cidade = '" + String.valueOf( this.idCidade ) + "'";

        db.executeNotReturn(sql);
    }
}