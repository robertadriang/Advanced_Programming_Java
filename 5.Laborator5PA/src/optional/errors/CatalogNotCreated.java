package optional.errors;

public class CatalogNotCreated extends RuntimeException{
    public CatalogNotCreated(String name){
        super("A catalog with this name can't be created:"+name);
    }
    public CatalogNotCreated(){
        super("The catalog doesn't exist");
    }
}
