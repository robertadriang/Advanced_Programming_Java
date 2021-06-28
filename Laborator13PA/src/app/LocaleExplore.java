package app;

import com.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {

    public static void main(String[] args) throws IOException {
        controller c=new controller();
        Properties props=new Properties();
        String path_en="C:\\Users\\Robert\\OneDrive\\Documents\\GitHub\\Laborator13PA\\src\\res\\Messages.properties";
        var fr_en=new FileReader(path_en);

        String path_ro="C:\\Users\\Robert\\OneDrive\\Documents\\GitHub\\Laborator13PA\\src\\res\\Messages_ro.properties";
        var fr_ro=new FileReader(path_ro);

        props.load(fr_en);
        var currentLocale=Locale.getDefault();
        var locale_en=new Locale("en","US");
        var locale_ro=new Locale("ro","RO");

        Scanner keyboard = new Scanner(System.in);
        String request="";
        while(true){
            System.out.printf(props.getProperty("prompt"));
            request=keyboard.nextLine();
            if(request.startsWith("locales")) {
                System.out.println(props.getProperty("locales"));
                c.displayLocales();
            }
            else if(request.startsWith("locale.set")){
                if(request.substring(11).equals("en")){
                    fr_en=new FileReader(path_en);
                    props.load(fr_en);
                    currentLocale=c.setLocale(locale_en);
                    System.out.println(MessageFormat.format(props.getProperty("locale.set"),currentLocale.getDisplayCountry()));
                }else if(request.substring(11).equals("ro")){
                    fr_ro=new FileReader(path_ro);
                    props.load(fr_ro);
                    currentLocale=c.setLocale(locale_ro);
                    System.out.println(MessageFormat.format(props.getProperty("locale.set"),currentLocale.getDisplayCountry()));
                }else{
                    System.out.println("Locale unknown");
                }
            }
            else if(request.startsWith("info")){
                if(request.length()>4){
                    String language=request.substring(5);
                    var localeGiven=new Locale(language,language.toUpperCase(Locale.ROOT));
                    System.out.println(MessageFormat.format(props.getProperty("info"),localeGiven.getDisplayCountry()));
                    c.getInfo(localeGiven,props);
                }else{
                    System.out.println(MessageFormat.format(props.getProperty("info"),currentLocale.getDisplayCountry()));
                    c.getInfo(currentLocale,props);
                }
            }
            else{
                System.out.println(props.getProperty("invalid"));
            }
        }
    }
}
