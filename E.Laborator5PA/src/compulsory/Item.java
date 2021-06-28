package compulsory;

import java.text.DecimalFormat;
import java.util.Random;

public abstract class Item implements java.io.Serializable{
    protected String name;
    protected String path;
    protected String isbn;
    protected double price;
    protected double rating;
    static int value=0;

    private static DecimalFormat df = new DecimalFormat("0.00");

    public Item(String name, String path, String isbn, double price, double rating) {
        this.name = name;
        this.path = path;
        this.isbn = isbn;
        this.price = price;
        this.rating = rating;
    }
    public Item(){
        this.name="Name"+value;
        this.path="C:\\Users\\Robert\\OneDrive\\Desktop\\javaCatalog\\"+this.name;
        Random rand = new Random();
        this.isbn=String.valueOf(rand.nextInt(1000))+String.valueOf(rand.nextInt(1000));
        this.price=Double.parseDouble(df.format(100*rand.nextDouble()));
        this.rating=rand.nextInt(5);
        ++value;
    }

    public Item(String name,String extension){
        this.name=name+extension;
        this.path="C:\\Users\\Robert\\OneDrive\\Desktop\\javaCatalog\\"+this.name;
        Random rand = new Random();
        this.isbn=String.valueOf(rand.nextInt(1000))+String.valueOf(rand.nextInt(1000));
        this.price=Double.parseDouble(df.format(100*rand.nextDouble()));
        this.rating=rand.nextInt(5);
        ++value;
    }


    public Item(String extension){
        this.name="Name"+value+extension;
        this.path="C:\\Users\\Robert\\OneDrive\\Desktop\\javaCatalog\\"+this.name;
        Random rand = new Random();
        this.isbn=String.valueOf(rand.nextInt(1000))+String.valueOf(rand.nextInt(1000));
        this.price=Double.parseDouble(df.format(100*rand.nextDouble()));
        this.rating=rand.nextInt(5);
        ++value;
    }

}
