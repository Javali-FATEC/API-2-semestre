import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DataModel {
    private final StringProperty property1 = new SimpleStringProperty();
    private final StringProperty property2 = new SimpleStringProperty();

    public DataModel(String property1, String property2) {
        setProperty1(property1);
        setProperty2(property2);
    }

    public StringProperty property1Property() {
        return property1;
    }

    public final String getProperty1() {
        return property1.get();
    }

    public final void setProperty1(String value) {
        property1.set(value);
    }

    public StringProperty property2Property() {
        return property2;
    }

    public final String getProperty2() {
        return property2.get();
    }

    public final void setProperty2(String value) {
        property2.set(value);
    }
}
