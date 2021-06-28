package optional.errors;

public class UnknownCommand extends RuntimeException{
    public UnknownCommand(String name){
        super("Unknown command:"+name);
    }
}
