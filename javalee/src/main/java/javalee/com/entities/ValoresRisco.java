package javalee.com.entities;

import java.util.LinkedList;
import java.util.List;

import javalee.com.patters.Patter;
import javalee.com.patters.PatterA;
import javalee.com.patters.PatterB;
import javalee.com.services.ReturnMaxMinValues;

public class ValoresRisco {
    private List<ValorRisco> valoresRisco;

    public ValoresRisco(){
        valoresRisco = new LinkedList<ValorRisco>();

        for(PatterA enumPatter : PatterA.values()){
            Patter patter = enumPatter.getPatter();
            ReturnMaxMinValues maxMinValues = new ReturnMaxMinValues(patter.getName());
            ValorRisco valorRisco = new ValorRisco(patter.getName(), maxMinValues.getValorMaximo(), maxMinValues.getValorMinimo());
            valoresRisco.add(valorRisco);
        }

        for(PatterB enumPatter : PatterB.values()){
            Patter patter = enumPatter.getPatter();
            ReturnMaxMinValues maxMinValues = new ReturnMaxMinValues(patter.getName());
            ValorRisco valorRisco = new ValorRisco(patter.getName(), maxMinValues.getValorMaximo(), maxMinValues.getValorMinimo());
            valoresRisco.add(valorRisco);
        }
    }

    public ValorRisco buscaValoreRiscoMetrica(String metrica){
        for(ValorRisco valorRisco : valoresRisco){
            if(valorRisco.getMetrica().equals(metrica)){
                return valorRisco;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String retorno = "";
        for(ValorRisco valorRisco : valoresRisco){
            retorno += "Metrica: " + valorRisco.getMetrica() + " Valor Máximo: " + valorRisco.getValorMaximo() + " Valor Mínimo: " + valorRisco.getValorMinimo() + "\n";
        }

        return retorno;
    }

}
