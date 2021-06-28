package compulsory;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

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

    public static void main(String[] args) {
        Catalog myCatalog=new Catalog("CreatedCatalog");
        myCatalog.add("Mov");
        myCatalog.add("Song");
        myCatalog.add("Movie");
        myCatalog.add("Picture");
        myCatalog.add("Picture","Name2");
        myCatalog.list();
        myCatalog.play("Name0.mp4");
        myCatalog.play("UnknownFile");
        myCatalog.play(2);
        myCatalog.save("C:\\Users\\Robert\\OneDrive\\Desktop\\javaCatalog");

        Catalog importedCatalog=null;
        importedCatalog=load("C:\\Users\\Robert\\OneDrive\\Desktop\\javaCatalog\\catalog.ser");
        System.out.println("Test");
        System.out.println(myCatalog);
        System.out.println(importedCatalog);
    }
}
