package javalee.com.services;

import java.util.LinkedList;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javalee.com.patters.*;
import javalee.com.entities.LeituraSuspeita;
import javalee.com.entities.ValorRisco;
import javalee.com.entities.ValoresRisco;
import javalee.com.exceptions.ExceptionEmptyLine;

public class ListMeasurement {
    private LinkedList<DataMeasurement> listDataFile;
    private String pattern;
    private String interator;
    private boolean isPatternAut;
    private List<LeituraSuspeita> lineErros;
    private ValoresRisco calculaValoresRisco;

    public ListMeasurement(FileReader selecteFile, String pattern, boolean isPatternAut){
        this.calculaValoresRisco = new ValoresRisco();

        this.pattern = pattern;
        this.isPatternAut = isPatternAut;
        listDataFile = new LinkedList<DataMeasurement>();
        extractList(new BufferedReader(selecteFile));
    }

    public void setIsPatternAut(boolean isPatternAut){
        this.isPatternAut = isPatternAut;
    }

    public void setInterator( String interator){
        this.interator = interator;
    }

    public LinkedList<DataMeasurement> getListDataFile(){
        return listDataFile;
    }

    public List<LeituraSuspeita> getLineErros(){
        return this.lineErros;
    }

    private void extractList(BufferedReader reader){
        int lineNumber = 0;
        
        this.lineErros = new LinkedList<LeituraSuspeita>();
        String line;
        try{
            while ((line = reader.readLine()) != null) {
                try{
                    lineNumber++;
                    if(line == "")
                    {
                        throw new ExceptionEmptyLine(lineNumber);
                    }
                    line = line.replace(";;", "; ;");
                    String lineAspas = line.replace("\"", "");
            
                    String[] parts = lineAspas.split(";");
                    if(this.interator=="virgula ( , )")
                    {
                        line = line.replace(",,", ", ,");
                        parts = line.split(",");
                    }
                    if(lineNumber>0)
                    {
                        try{
                        if( parts[0] == " " || parts[1] == " " )
                        {
                            throw new ExceptionEmptyLine(lineNumber);
                        }}catch( ArrayIndexOutOfBoundsException e){
                            throw new ExceptionEmptyLine(lineNumber);
                        }
                        if( !this.isPatternAut ){
                            constructListPatterA(parts, lineNumber);
                        }
                        else{
                            constructListPatterB(parts, lineNumber);
                        }
                    }
                }catch( ExceptionEmptyLine e){
                    //LeituraSuspeita leituraSuspeita = new LeituraSuspeita(e.getMessage(), "Linha vazia", e.getLine());
                    //lineErros.put(e.getLine(), e.getMessage());
                }
        }
        reader.close();
        }catch(IOException e){

        }
    }

    private boolean isPatternA(int numeberOfColuns){
        if(this.pattern == "Automático") {
            return isPatternAut;
        }
        return this.pattern == "Padrão A";
    }

    public void constructListPatterA(String[] parts, int lineNumber){
        //System.out.println("AAAAAAAAAAA");
        for(PatterA enumPatter : PatterA.values()){
            Patter patter = enumPatter.getPatter();
            DataMeasurement dataMeasurement = new DataMeasurement(patter.getName(), patter.getUnidade(),parts[0],parts[1], checkedNull(parts,patter.getColuna()),lineNumber);
            if(patter.isTemp()){
                dataMeasurement.convertTemp();
            }
            listDataFile.add(dataMeasurement);
            verificaValoresRisco(dataMeasurement, lineNumber);
        } 
    }

    private void verificaValoresRisco(DataMeasurement dataMeasurement , int lineNumber){
        ValorRisco maxMinValues = calculaValoresRisco.buscaValoreRiscoMetrica(dataMeasurement.getTypeMeasurament());
        if(dataMeasurement.getValueBigDecimal() != null){
            boolean isMax = false;
            boolean isMin = false;
            if(maxMinValues.getValorMaximo() != null)
            {
                isMax = dataMeasurement.getValueBigDecimal().compareTo(maxMinValues.getValorMaximo()) > 0;
            }
            if(maxMinValues.getValorMinimo() != null)
            {
                isMin = dataMeasurement.getValueBigDecimal().compareTo(maxMinValues.getValorMinimo()) < 0;
            }

            if( isMax ){
                LeituraSuspeita leituraSuspeita = new LeituraSuspeita( String.valueOf(lineNumber), "Valor acima do máximo recomendado", dataMeasurement);
                lineErros.add(leituraSuspeita);
            }

            if( isMin ){
                LeituraSuspeita leituraSuspeita = new LeituraSuspeita( String.valueOf(lineNumber), "Valor abaixo do mínimo recomendado", dataMeasurement);
                lineErros.add(leituraSuspeita);
            }
        }
    }

    public void constructListPatterB(String[] parts, int lineNumber){
        for(PatterB enumPatter : PatterB.values()){
            Patter patter = enumPatter.getPatter();
            DataMeasurement dataMeasurement = new DataMeasurement(patter.getName(), patter.getUnidade(),parts[0],parts[1], checkedNull(parts,patter.getColuna()),lineNumber);
            listDataFile.add(dataMeasurement);
            verificaValoresRisco(dataMeasurement, lineNumber);
        }
    }

    public String checkedNull(String[] parts, int index){
        String result = "";
        if(parts.length > index)
        {
            result = parts[index];
        }

        return result;
    }
}
