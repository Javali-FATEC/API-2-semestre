import java.time.LocalDateTime;

public class ExceptionHourNotFound extends Exception {
    private LocalDateTime hour;
    private LocalDateTime day;

    public ExceptionHourNotFound(LocalDateTime hour, LocalDateTime day) {
        super("Hora inexistente");
        this.hour = hour;
        this.day = day;
    }
}