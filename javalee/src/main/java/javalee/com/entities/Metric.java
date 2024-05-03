package javalee.com.entities;

public class Metric {

    private int idMetrica;
    private String nome;

    public Metric(int idMetrica, String nome) {
        this.idMetrica = idMetrica;
        this.nome = nome;
    }

    public Metric(String codigo){
    }
    
    public String getIdMetrica() {
        return String.valueOf(idMetrica);
    }

    public void setIdMetrica(int idMetrica) {
        this.idMetrica = idMetrica;
    }

    public String getNome() {
        return nome;
    }    
}
