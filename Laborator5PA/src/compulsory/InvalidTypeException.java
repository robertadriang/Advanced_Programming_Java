package compulsory;

public class InvalidTypeException extends RuntimeException{
    public InvalidTypeException(String type){
        super("Unknown type: "+type);
    }
}
