package javalee.com.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javalee.com.exceptions.*;

public class ExtractFileName {
    private String fileName;
    private String city;
    private String station;

    public ExtractFileName(String fileName) throws ExceptionInvalidFileName {
        this.fileName = fileName;
        extractData();
    }

    private void extractData() throws ExceptionInvalidFileName {
        String regex = "([A-Za-z0-9]+)_([A-Za-z]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fileName);

        if (matcher.find()) {
            station = matcher.group(1);
            city = matcher.group(2);
        } else {
            throw new ExceptionInvalidFileName("Nome do arquivo inválido"); 
        }
    }

    public String getCity() {
        return city;
    }

    public String getStation() {
        return station;
    }
}
