module javalee.com {
    requires javafx.controls;
    requires javafx.fxml;

    opens javalee.com to javafx.fxml;
    exports javalee.com;
}
