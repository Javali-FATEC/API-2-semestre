package javalee.com.entities;

import java.math.BigDecimal;

public class ValorRisco {
    private BigDecimal valorMaximo;
    private BigDecimal valorMinimo;
    private String metrica;

    public ValorRisco(String metrica, BigDecimal valorMaximo, BigDecimal valorMinimo){
        this.metrica = metrica;
        this.valorMaximo = valorMaximo;
        this.valorMinimo = valorMinimo;
    }

    public BigDecimal getValorMaximo() {
        return valorMaximo;
    }

    public BigDecimal getValorMinimo() {
        return valorMinimo;
    }

    public String getMetrica() {
        return metrica;
    }
}
