module javalee.com {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens javalee.com to javafx.fxml;
    exports javalee.com;
}
