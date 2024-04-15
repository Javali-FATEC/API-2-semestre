package javalee.com.exceptions;

public class ExceptionEmptyLine extends Exception {
    private int line;
    public ExceptionEmptyLine(int line){
        super("Linha não possui as colunas iniciais");
        this.line = line;
    }

    public String getLine(){
        String result = String.valueOf(this.line);
        if(result ==null)
        {
            return "SL";
        }
        return result;
    }
    
}
