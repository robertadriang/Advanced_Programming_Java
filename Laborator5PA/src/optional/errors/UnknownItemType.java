package optional.errors;

public class UnknownItemType extends RuntimeException{
    public UnknownItemType(String type){
        super("Unknown type:"+type);
    }
}
