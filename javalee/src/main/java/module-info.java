module javalee.com {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;

    opens javalee.com to javafx.fxml;

    exports javalee.com;
}
