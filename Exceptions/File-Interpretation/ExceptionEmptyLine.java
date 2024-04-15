
public class ExceptionEmptyLine extends ExceptionInterpretation {

    private int line;

    public ExceptionEmptyLine(int line) {
        super("Esta linha está vazia");
        this.line = line;

    }

}
