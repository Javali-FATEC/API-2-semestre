package javalee.com.entities;

public class LeituraAjustada {
    private int linha;
    private String valorNovo;
    private String variavelClimatica;

    public LeituraAjustada(int linha,String variavelClimatica, String valorNovo) {
        this.linha = linha;
        this.valorNovo = valorNovo;
        this.variavelClimatica = variavelClimatica;
    }

    public String getVariavelClimatica() {
        return variavelClimatica;
    }

    public int getLinha() {
        return linha;
    }

    public String getValorNovo() {
        return valorNovo;
    }
}
