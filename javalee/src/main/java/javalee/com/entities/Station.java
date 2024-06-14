package javalee.com.entities;

public class Station {

    private int idEstacao;
    private String codigo;

    public Station(int idEstacao, String codigo) {
        this.idEstacao = idEstacao;
        this.codigo = codigo;
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
}
