package optional;

public class FileNotFound extends RuntimeException{
    public FileNotFound(String name){
        super("A file with this name doesn't exist: "+name);
    }
}