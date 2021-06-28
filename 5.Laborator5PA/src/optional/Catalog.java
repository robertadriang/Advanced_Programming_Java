package optional;

import optional.errors.FileAlreadyExists;
import optional.errors.InvalidTypeException;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    String name;
    List<Item> itemList;

    Catalog(String name) {
        this.name=name;
        itemList = new ArrayList<Item>();
    }

    public int add(String type) {
        try {
            if (type.compareTo("Movie") == 0) {
                Item a = new Movie();
                itemList.add(a);
            } else if (type.compareTo("Song") == 0) {
                Item a = new Song();
                itemList.add(a);
            } else if(type.compareTo("Picture")==0)
            {
                Item a=new Picture();
                itemList.add(a);
            } else throw new InvalidTypeException(type);
        }catch(InvalidTypeException e){
            System.out.println(e);
            return 0;
        }
        return 1;
    }

    public int add(String type,String name){
        try {
            Item a;
            if (type.compareTo("Movie") == 0) {
                a = new Movie(name);
            } else if (type.compareTo("Song") == 0) {
                a = new Song(name);
            } else if(type.compareTo("Picture")==0)
            {
                a=new Picture(name);
            } else throw new InvalidTypeException(type);

            for(int i=0;i<itemList.size();++i){
                if(itemList.get(i).path.compareTo(a.path)==0)
                    throw new optional.errors.FileAlreadyExists(name);
            }

        }catch(InvalidTypeException e){
            System.out.println(e);
            return 0;
        } catch (FileAlreadyExists e){
            System.out.println(e);
        }
        return 1;
    }

    public int list(){
        itemList.forEach((n)-> System.out.println(n));
        return 1;
    }

    public int play(int position){
        try{
            File u=new File(itemList.get(position).path);
            Desktop d= Desktop.getDesktop();
            d.open(u);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public int play(String name){
        try{
            boolean found=false;
            for(int i=0;i<itemList.size();++i){
                if(itemList.get(i).name.compareTo(name)==0){
                    File u=new File(itemList.get(i).path);
                    Desktop d = Desktop.getDesktop();
                    d.open(u);
                    found=true;
                    return 1;
                }
            }
            throw new FileNotFound(name);
        }catch (FileNotFound e){
            System.out.println(e);
        }catch (IOException e){
            e.printStackTrace();
        }
        return 0;
    }

    public int save(String path){
        try{
            FileOutputStream fos = new FileOutputStream(path+"\\catalog.ser");
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(this);
            out.close();
            fos.close();
        } catch (IOException e){
            System.out.println("IOException caught");
        }
      return 1;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "name: " + name +
                " | itemList=" + itemList +
                '}';
    }
}
