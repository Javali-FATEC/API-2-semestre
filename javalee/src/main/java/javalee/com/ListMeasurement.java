package javalee.com;

import java.util.LinkedList;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

public class ListMeasurement {
    private LinkedList<DataMeasurement> listDataFile;
    private boolean isPatternA;
    private String interator;
    private Map<String, String> lineErros;

    public ListMeasurement(FileReader selecteFile, String pattern){
        isPatternA = pattern == "Padrão A";
        listDataFile = new LinkedList<DataMeasurement>();
        extractList(new BufferedReader(selecteFile));
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
                    String jo = line.replace("\"", "");
            
                    String[] parts = jo.split(";");
                    if(this.interator=="virgula ( , )")
                    {
                        line = line.replace(",,", ", ,");
                        parts = line.split(";");
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
                        if( this.isPatternA ){
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

    public void constructListPatterA(String[] parts){
        DataMeasurement temperatura = new DataMeasurement("Temperatura","ºC",parts[0],parts[1],parts[2]);
        temperatura.convertTemp();
        listDataFile.add(temperatura);
        listDataFile.add(new DataMeasurement("Umidade", "Umidade Relativa (%)",parts[0],parts[1], parts[3]));
        listDataFile.add(new DataMeasurement("Pressão", "hPa",parts[0],parts[1], parts[4]));
        listDataFile.add(new DataMeasurement("Velocidade do Vento", "m/s",parts[0],parts[1], parts[5]));
        listDataFile.add(new DataMeasurement("Direção do Vento", "m/s",parts[0],parts[1], parts[6]));
        listDataFile.add(new DataMeasurement("Nebulosidade", "Decimos",parts[0],parts[1], parts[7]));
        listDataFile.add(new DataMeasurement("Insolação", "h",parts[0],parts[1], parts[8]));
        listDataFile.add(new DataMeasurement("Temperatura Máxima", "ºC",parts[0],parts[1], parts[9]));
        listDataFile.add(new DataMeasurement("Temperatura Mínima", "ºC",parts[0],parts[1], parts[10]));
        String lastValue = "";
        boolean lastValueIsPresent = parts.length > 11;
        if(lastValueIsPresent) {
            lastValue = parts[11];
        }
        listDataFile.add(new DataMeasurement("Chuva", "mm",parts[0],parts[1], lastValue));
    }

    public void constructListPatterB(String[] parts){
        listDataFile.add(new DataMeasurement("Temperatura", "°C", parts[0], parts[1],checkedNull(parts,2) ));
        listDataFile.add(new DataMeasurement("Temperatura Máxima", "°C", parts[0], parts[1],checkedNull(parts,3) ));
        listDataFile.add(new DataMeasurement("Temperatura Mínima", "°C", parts[0], parts[1], checkedNull(parts,4)));
        listDataFile.add(new DataMeasurement("Umidade Relativa Instantânea", "%", parts[0], parts[1], checkedNull(parts,5)));
        listDataFile.add(new DataMeasurement("Umidade Relativa Máxima", "%", parts[0], parts[1], checkedNull(parts,6)));
        listDataFile.add(new DataMeasurement("Umidade Relativa Mínima", "%", parts[0], parts[1], checkedNull(parts,7)));
        listDataFile.add(new DataMeasurement("Ponto de Orvalho Instantâneo", "°C", parts[0], parts[1], checkedNull(parts,8)));
        listDataFile.add(new DataMeasurement("Ponto de Orvalho Máximo", "°C", parts[0], parts[1], checkedNull(parts,9)));
        listDataFile.add(new DataMeasurement("Ponto de Orvalho Mínimo", "°C", parts[0], parts[1], checkedNull(parts,10)));
        listDataFile.add(new DataMeasurement("Pressão Atmosférica Instantânea", "hPa", parts[0], parts[1], checkedNull(parts,11)));
        listDataFile.add(new DataMeasurement("Pressão Atmosférica Máxima", "hPa", parts[0], parts[1], checkedNull(parts,12)));
        listDataFile.add(new DataMeasurement("Pressão Atmosférica Mínima", "hPa", parts[0], parts[1], checkedNull(parts,13)));
        listDataFile.add(new DataMeasurement("Velocidade do Vento", "m/s", parts[0], parts[1], checkedNull(parts,14)));
        listDataFile.add(new DataMeasurement("Direção do Vento", "m/s", parts[0], parts[1], checkedNull(parts,15)));
        listDataFile.add(new DataMeasurement("Rajada de Vento", "m/s", parts[0], parts[1], checkedNull(parts,16)));
        listDataFile.add(new DataMeasurement("Radiação Solar", "KJ/m²", parts[0], parts[1], checkedNull(parts,17)));
        listDataFile.add(new DataMeasurement("Chuva", "mm",parts[0],parts[1], checkedNull(parts,17)));
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
