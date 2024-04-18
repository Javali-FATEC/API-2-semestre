package javalee.com.patters;

public enum PatterB {
    TEMPERATURA(new Patter("Temperatura","ºC",2)),
    TEMPERATURAMAXIMA(new Patter("Temperatura Máxima","ºC",3)),
    TEMPERATURAMINIMA(new Patter("Temperatura Mínima", "%",4)),
    UMIDADE(new Patter("Umidade","%",5)),
    UMIDADEMAXIMA(new Patter("Umidade Máxima","%",6)),
    UMIDADEMINIMA(new Patter("Umidade Minima","%",7)),
    PONTOORVALHO(new Patter("Ponto de Orvalho Instantâneo", "°C",8)),
    PONTOORVALHOMAX(new Patter("Ponto de Orvalho Máximo", "°C",9)),
    PONTOORVALHOMIN(new Patter("Ponto de Orvalho Mínimo", "°C",10)),
    PRESSAO(new Patter("Pressão", "hPa",11)),
    PRESSAOMAX(new Patter("Pressão Máxima", "hPa",12)),
    PRESSAOMIN(new Patter("Pressão Mínima", "hPa",13)),
    VELOCIDADEVENTO(new Patter("Velocidade do Vento", "m/s",14)),
    DIRECAOVENTO(new Patter("Direção do Vento", "m/s",15)),
    RAJADA(new Patter("Rajada de Vento", "m/s",16)),
    RADIACAO(new Patter("Radiação Solar", "KJ/m²",17)),
    CHUVA(new Patter("Chuva", "mm",18));

    private Patter patter;

    PatterB(Patter patter) {
        this.patter = patter;
    }

    public Patter getPatter() {
        return patter;
    }
}
