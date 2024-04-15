package javalee.com.services;

import java.io.FileReader;
import java.io.IOException;
import javafx.stage.FileChooser;
import javalee.com.exceptions.*;
import java.io.File;

public class OpenCSV {
    public static DataFile OpenFileCSV(String patterUsed, String separete) throws FileException,ExceptionFileNameInvalid,IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Arquivo CSV");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivo CSV", "*.csv"));
        File selecteFile = fileChooser.showOpenDialog(null);

        if(selecteFile == null){
            throw new FileException();
        }
        ExtractFileName dataFileName = new ExtractFileName(selecteFile.getName());

        ListMeasurement listMeasurement = new ListMeasurement(new FileReader(selecteFile),patterUsed);
        listMeasurement.setInterator(separete);         

        DataFile dataFile = new DataFile(dataFileName.getCity(),dataFileName.getStation(),listMeasurement.getListDataFile(),listMeasurement.getLineErros());
        return dataFile;
    }
}
