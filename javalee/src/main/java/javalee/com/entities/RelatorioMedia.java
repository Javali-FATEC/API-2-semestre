package javalee.com.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RelatorioMedia {
    private final SimpleIntegerProperty valor;
    private final SimpleStringProperty unidade;
    private final SimpleStringProperty dado;
    private final SimpleStringProperty horaMedia;

    public RelatorioMedia(String horaMedia, int valor, String unidade, String dado) {
        this.valor = new SimpleIntegerProperty(valor);
        this.unidade = new SimpleStringProperty(unidade);
        this.dado = new SimpleStringProperty(dado);
        this.horaMedia = new SimpleStringProperty(horaMedia);
    }

    public int getValor() {
        return valor.get();
    }

    public void setValor(int valor) {
        this.valor.set(valor);
    }

    public SimpleIntegerProperty valorProperty() {
        return valor;
    }

    public String getUnidade() {
        return unidade.get();
    }

    public void setUnidade(String unidade) {
        this.unidade.set(unidade);
    }

    public SimpleStringProperty unidadeProperty() {
        return unidade;
    }

    public String getDado() {
        return dado.get();
    }

    public void setDado(String dado) {
        this.dado.set(dado);
    }

    public SimpleStringProperty dadoProperty() {
        return dado;
    }

    public String getHoraMedia() {
        return horaMedia.get();
    }

    public void setHoraMedia(String horaMedia) {
        this.horaMedia.set(horaMedia);
    }

    public SimpleStringProperty horaMediaProperty() {
        return horaMedia;
    }
}
