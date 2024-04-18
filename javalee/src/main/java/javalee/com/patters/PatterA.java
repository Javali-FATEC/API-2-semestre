package javalee.com.patters;

public enum PatterA {
    TEMPERATURA(new Patter("Temperatura","ºC",2)),
    UMIDADE(new Patter("Umidade","%",3)),
    PRESSAO(new Patter("Pressão", "hPa",4)),
    VELOCIDADEVENTO(new Patter("Velocidade do Vento", "m/s",5)),
    DIRECAOVENTO(new Patter("Direção do Vento", "m/s",6)),
    NEBULOSIDADE(new Patter("Nebulosidade", "Decimos",7)),
    INSOLACAO(new Patter("Insolação", "h",8)),
    TEMPMAXIMA(new Patter("Temperatura Máxima", "ºC",9)),
    TEMPMINIMA(new Patter("Temperatura Mínima", "ºC",10)),
    CHUVA(new Patter("Chuva", "mm",11));
    
    private Patter patter;

    PatterA(Patter patter) {
        this.patter = patter;
    }

    public Patter getPatter() {
        return patter;
    }
}

