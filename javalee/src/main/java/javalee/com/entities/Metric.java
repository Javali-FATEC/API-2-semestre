package javalee.com.entities;

public class Metric {

    private int idMetrica;
    private String codigo;

    public Metric(int idMetrica, String codigo) {
        this.idMetrica = idMetrica;
        this.codigo = codigo;
    }

    public Metric(String codigo){
        this.codigo = codigo;
    }
    
    public String getIdMetrica() {
        return String.valueOf(idMetrica);
    }

    public void setIdMetrica(int idMetrica) {
        this.idMetrica = idMetrica;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }






    
}
