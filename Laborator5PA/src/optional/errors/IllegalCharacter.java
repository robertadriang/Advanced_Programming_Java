package optional.errors;

public class IllegalCharacter extends RuntimeException{
    public IllegalCharacter(){
        super("Spaces are not allowed!");
    }
}
