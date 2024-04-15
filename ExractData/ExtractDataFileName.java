import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractDataFileName {
    private String fileName;
    private String city;
    private String station;

    public ExtractDataFileName(String fileName) throws ExceptionFileNameInvalid {
        this.fileName = fileName;
        extractData();
    }

    private void extractData() throws ExceptionFileNameInvalid {
        String regex = "([A-Za-z0-9]+)_([A-Za-z]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fileName);

        if (matcher.find()) {
            station = matcher.group(1);
            city = matcher.group(2);
        } else {
            throw new ExceptionFileNameInvalid("Nome do arquivo inválido"); 
        }
    }

    public String getCity() {
        return city;
    }

    public String getStation() {
        return station;
    }
}
