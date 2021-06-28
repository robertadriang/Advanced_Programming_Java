package optional;

import optional.errors.*;

import java.util.Scanner;

public class AddCommand {
    private static String addISBN(){
        Scanner keyboard = new Scanner(System.in);
        boolean validISBN=false;
        String isbn=null;
        while(!validISBN){
            System.out.println("ISBN:");
            isbn=String.valueOf(keyboard.nextInt());
            if(Integer.parseInt(isbn)<2_000_000_000&&Integer.parseInt(isbn)>1_000_000_000){
            validISBN=true;
            }
            else{
                System.out.println("Invalid ISBN");
            }
        }
        return isbn;
    }

    private static double addPrice(){
        Scanner keyboard = new Scanner (System.in);
        boolean validPrice=false;
        double price=-1;
        while(price<0||price>100_000){
            System.out.println("Add price:");
            String aux=keyboard.nextLine();
            price=Double.parseDouble(aux);
        }
        return price;
    }

    private static double addDuration(){
        System.out.println("Add duration");
        Scanner keyboard = new Scanner (System.in);
        return keyboard.nextDouble();
    }

    private static String addName(){
        System.out.println("Add name");
        Scanner keyboard = new Scanner (System.in);
        String ans=" ";
        boolean valid=false;
        while(!valid){
            ans=keyboard.nextLine();
            if(!ans.contains(" "))
                valid=true;
            else
                System.out.println(new IllegalCharacter());
        }
        return ans;
    }

    private static boolean AddMovie(Catalog c,String name){
        Scanner keyboard = new Scanner(System.in);
        String movieName=addName();
        String path="C:\\Users\\Robert\\OneDrive\\Desktop\\"+c.name+"\\"+movieName+".mov";
        String isbn=addISBN();
        double price=addPrice();
        double rating=0;
        double duration=addDuration();
        String genre="Comedy";
        double releaseYear=2001;
        Movie aux=new Movie(movieName,path,isbn,price,rating,duration,genre,releaseYear);
        c.itemList.add(aux);
        System.out.println("Movie added to the catalog!");
        System.out.println(aux);
        return true;
    }

    public static boolean addItem(Catalog c,String name){
      try{
          Scanner keyboard = new Scanner(System.in);
          if(c==null) throw new CatalogNotCreated();
          if(name.contains(" "))  throw new IllegalCharacter();
          System.out.println("\tIntroduce the type of the file:");
          boolean validCommand=false;
          while(!validCommand){
              String type = keyboard.nextLine();
              if(type.equals("Movie")||type.equals("Song")||type.equals("Picture")){
                  validCommand=true;
                  switch(type){
                      case "Movie":
                          AddMovie(c,name);
                          break;
                  }
              }
              else{
                  System.out.println(new UnknownItemType(type));
              }
          }

      }
      finally {

      }
      return true;
}
}
