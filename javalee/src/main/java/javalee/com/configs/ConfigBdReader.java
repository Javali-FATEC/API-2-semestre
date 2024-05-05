package javalee.com.configs;

public class ConfigBdReader {

    private String urlBd;
    private String userBd;
    private String passwordBd;
    private String nameBd;

    public ConfigBdReader() {
        try {
            this.urlBd = "jdbc:postgresql://localhost:5432/";
            this.userBd = "postgres";
            this.passwordBd = "admin";
            this.nameBd = "db_javali";
            
        } catch (Exception e) {
            System.out.println("erro " + e.getMessage());
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