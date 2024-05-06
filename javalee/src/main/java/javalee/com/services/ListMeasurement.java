package javalee.com.services;

import java.util.LinkedList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javalee.com.patters.*;
import javalee.com.exceptions.ExceptionEmptyLine;
import javalee.com.patters.PatterB;

public class ListMeasurement {
    private LinkedList<DataMeasurement> listDataFile;
    private String pattern;
    private String interator;
    private boolean isPatternAut;
    private Map<String, String> lineErros;

    public ListMeasurement(FileReader selecteFile, String pattern, boolean isPatternAut){
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

    public Map<String, String> getLineErros(){
        return this.lineErros;
    }

    private void extractList(BufferedReader reader){
        int lineNumber = 0;
        
        this.lineErros = new HashMap<>();
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
                            constructListPatterA(parts);
                        }
                        else{
                            constructListPatterB(parts);
                        }
                    }
                }catch( ExceptionEmptyLine e){
                    lineErros.put(e.getLine(), e.getMessage());
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

    public void constructListPatterA(String[] parts){
        //System.out.println("AAAAAAAAAAA");
        for(PatterA enumPatter : PatterA.values()){
            Patter patter = enumPatter.getPatter();
            DataMeasurement dataMeasurement = new DataMeasurement(patter.getName(), patter.getUnidade(),parts[0],parts[1], checkedNull(parts,patter.getColuna()));
            if(patter.isTemp()){
                System.out.println("TEMP");
                dataMeasurement.convertTemp();
            }
            listDataFile.add(dataMeasurement);
        } 
    }

    public void constructListPatterB(String[] parts){
        for(PatterB enumPatter : PatterB.values()){
            Patter patter = enumPatter.getPatter();
            DataMeasurement dataMeasurement = new DataMeasurement(patter.getName(), patter.getUnidade(),parts[0],parts[1], checkedNull(parts,patter.getColuna()));
            listDataFile.add(dataMeasurement);
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
