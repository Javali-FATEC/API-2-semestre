import java.time.LocalDateTime;

public class ExceptionDayNotFound extends Exception {
    private LocalDateTime day;

    public ExceptionDayNotFound(LocalDateTime day) {
        super("Dia inexistente");
        this.day = day;
    }
}
