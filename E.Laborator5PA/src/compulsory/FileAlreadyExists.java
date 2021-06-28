package compulsory;

public class FileAlreadyExists extends RuntimeException{
    public FileAlreadyExists(String name){
        super("A file with the same name/extension already exists: "+name);
    }
}