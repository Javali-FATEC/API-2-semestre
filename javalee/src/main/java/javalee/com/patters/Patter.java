package javalee.com.patters;

public class Patter {
    private String name;
    private String unidade;
    private int coluna;

    public Patter(String name, String unidade, int coluna){
        this.name = name;
        this.unidade = unidade;
        this.coluna = coluna;
    }

    public boolean isTemp(){
        return this.name == "Temperatura" || this.name == "Temperatura Mínima" || this.name == "Temperatura Máxima";
    }

    public String getName(){
        return name;
    }

    public String getUnidade(){
        return unidade;
    }

    public int getColuna(){
        return coluna;
    }
}
