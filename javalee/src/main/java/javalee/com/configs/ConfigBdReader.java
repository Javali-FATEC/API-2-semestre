package javalee.com.configs;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigBdReader {

    private String urlBd;
    private String userBd;
    private String passwordBd;
    private String nameBd;


    public ConfigBdReader() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("javalee\\src\\main\\java\\javalee\\com\\configs\\config.properties"));
            this.urlBd = prop.getProperty("db.url");
            this.userBd = prop.getProperty("db.user");
            this.passwordBd = prop.getProperty("db.password");
            this.nameBd = prop.getProperty("db.name");
            
        } catch (Exception e) {
           System.out.println("erro "+ e.getMessage());
        }



    }

    public String getUrlBd() {
        return urlBd;
    }



    public String getUserBd() {
        return userBd;
    }



    public String getPasswordBd() {
        return passwordBd;
    }



    public String getNameBd() {
        return nameBd;
    }



}