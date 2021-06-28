package optional;

import optional.errors.CatalogNotCreated;
import optional.errors.IllegalCharacter;
import optional.errors.UnknownCommand;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

/*
* Media Catalog
Write an application that can manage a catalog of multimedia items. An entry in this catalog might be a song, a movie, a book, an image or any item that
has at least a name and a path in the local file system. (We may also consider specifying a release year, a rating and other additional data,
for example the author of the book, etc.)

The main specifications of the application are:
*
* Compulsory (1p)

Create an object-oriented model of the problem. You should have at least the following classes: Catalog and two item classes at your choice. Consider using an interface or an abstract class in order to describe the items in a catalog.
Implement the following methods representing commands that will manage the content of the catalog:
add: adds a new entry into the catalog;
list: prints the content of the catalog on the screen;
play: playback using the native operating system application (see the Desktop class);
save: saves the catalog to an external file (either as a text or binary, using object serialization);
load: loads the catalog from an external file.
The application will signal invalid data (year, path, etc.) using a custom exception.
*
* */
public class Main {
    public static Catalog load(String path){
        Catalog aux = null;
        try{
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fis);
            aux=(Catalog)in.readObject();
            aux.name="Imported Catalog";
            in.close();
            fis.close();
        } catch (IOException e){
            System.out.println("IOEXception caught");
        } catch (ClassNotFoundException e){
            System.out.println("ClassNotFoundException caught");
        }
        finally{
            return aux;
        }
    }

    public static Catalog create_catalog(String name){
        Catalog ans = null;
        try {
            if (name.contains(" ")){
                throw new IllegalCharacter();
            }
            ans = new Catalog(name);
        }catch (IllegalCharacter e){
            System.out.println(e);
        }
        finally {
            return ans;
        }
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Catalog obj=null;
        AddCommand addHandler=new AddCommand();
        while(true){
            try {
                System.out.println("@Enter a command:");
                String command = keyboard.nextLine();
                if(command.contains("create_catalog")){
                    obj=create_catalog(command.substring("create_catalog ".length()));
                    if(obj==null){
                        throw new CatalogNotCreated(command.substring("create_catalog ".length()));
                    }
                    System.out.printf("Catalog %s created!\n",command.substring("create_catalog ".length()));
                }
                else if(command.substring(0,3).compareTo("add")==0){
                    addHandler.addItem(obj,command.substring("add".length()));
                }
                else{
                    throw new UnknownCommand(command);
                }

            }catch(CatalogNotCreated e){
                System.out.println(e);
            }catch(UnknownCommand e){
                System.out.println(e);
            }

        }
    }
}
