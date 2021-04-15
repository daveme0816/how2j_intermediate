package exception;

public class OverdraftException extends Exception{
    double deficit = 0;

    public OverdraftException(String message, double deficit){
        super(message);
        this.deficit = deficit;

    }

    public double getDeficit(){
        return deficit;
    }
}
