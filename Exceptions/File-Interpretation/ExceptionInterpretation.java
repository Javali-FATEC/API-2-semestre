public class ExceptionInterpretation extends Exception {
    
    private int line;

    public int getLine() {
        return line;
    }

    public ExceptionInterpretation(String message) {
        super(message);
    }

}