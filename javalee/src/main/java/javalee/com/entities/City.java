package javalee.com.entities;

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

    public int getIdCidade(){
        return idCidade;
    }

    public String getNome(){
        return this.nome;
    }

    public String toInsertSql(){
        return "INSERT INTO cidade (sigla_cidade, nome_cidade) VALUES ('"+sigla+"', '"+nome+"');";
    }
}
