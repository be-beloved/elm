package example.org.exception;

public class CircuitBreakerException extends BaseException{
    public CircuitBreakerException(String msg){
        super(msg);
    }
}
