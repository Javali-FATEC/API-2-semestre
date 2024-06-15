package javalee.com.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class utilInterno {
    public static void alertError(String message, String title) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void alertSucesso(String message, String title){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean isValidDate(LocalDate date) {
        LocalDate dataMinima = LocalDate.of(1900, 1, 1);
        LocalDate dataMaxima = LocalDate.of(2100, 12, 31);

        return date.isAfter(dataMinima) && date.isBefore(dataMaxima);
    }

    public static boolean isValidDateFormat(LocalDate date) {
        final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateStr = date.format(DATE_FORMATTER);
        String regex = "\\d{2}/\\d{2}/\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateStr);
        return matcher.matches();
    }

    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!Character.isDigit(ch) && ch != ',' && ch != '.') {
                return false;
            }
        }
        return true;
    }
}
