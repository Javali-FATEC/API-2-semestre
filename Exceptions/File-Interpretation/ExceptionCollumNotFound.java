import java.util.List;
import java.util.ArrayList;

public class ExceptionCollumNotFound extends ExceptionInterpretation {
    private int line;
    private List<String> collumNotFound;

    public ExceptionCollumNotFound(List<String> collumNotFound) {
        super("Coluna inexistente");
        this.collumNotFound = collumNotFound;
    }
}
