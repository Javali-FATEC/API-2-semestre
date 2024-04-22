
public class ExceptionDuplicateLine extends ExceptionInterpretation {
    private int line;
    private int firstIncidentLine;

    public ExceptionDuplicateLine(int firstIncidentLine) {
        super("Esta linha está duplicada");
        this.firstIncidentLine = firstIncidentLine;
    }

}
