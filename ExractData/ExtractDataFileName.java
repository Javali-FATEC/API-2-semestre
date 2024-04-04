import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ExtractDataFileName {
    private String fileName;
    private String city;
    private String station;

    public ExtractDataFileName(String fileName) {
        this.fileName = fileName;
        extractData();
    }

    private void extractData() {
        String regex = "([A-Za-z0-9]+)_([A-Za-z]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fileName);

        if (matcher.find()) {
            station = matcher.group(1);
            city = matcher.group(2);
        } else {
            System.out.println("Invalid file name format!");
        }
    }

    public String getCity() {
        return city;
    }

    public String getStation() {
        return station;
    }

    public static void main(String[] args) {
        ExtractDataFileName data1 = new ExtractDataFileName("83726_SC");
        System.out.println("Estação: " + data1.getStation() + " | Cidade: " + data1.getCity());

        ExtractDataFileName data2 = new ExtractDataFileName("A728_TBT");
        System.out.println("Estação: " + data2.getStation() + " | Cidade: " + data2.getCity());

        ExtractDataFileName data3 = new ExtractDataFileName("A1701_SP");
        System.out.println("Estação: " + data3.getStation() + " | Cidade: " + data3.getCity());
    }
}
