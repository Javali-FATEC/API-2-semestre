package javalee.com.entities;

public class Station {

    private int idEstacao;
    private String codigo;
    private String latitude;
    private String longitude;

    public Station(int idEstacao, String codigo) {
        this.idEstacao = idEstacao;
        this.codigo = codigo;
    }

    public Station(int idEstacao, String codigo, String latitude, String longitude){
        this.idEstacao = idEstacao;
        this.codigo = codigo;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Station(String codigo) {
        this.codigo = codigo;
    }
    
    public String getIdEstacao() {
        return String.valueOf(idEstacao);
    }

    public void setIdEstacao(int idEstacao) {
        this.idEstacao = idEstacao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    


}
