package javalee.com.entities;

public class RelatorioMedia {
    private int valor;
    private String unidade;
    private String dado;
    private String hora_media;

    public RelatorioMedia(String hora_media, int valor, String unidade, String dado) {
        this.valor = valor;
        this.unidade = unidade;
        this.dado = dado;
        this.hora_media = hora_media;

    }
    public int getValor() {
        return valor;
    }

    public String getHoraMedia() {
        return hora_media;
    }

    public String getUnidade() {
        return unidade;
    }

    public String getDado() {
        return dado;
    }
}
