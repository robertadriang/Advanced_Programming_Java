package com;

import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.*;

public class controller {
    public static void displayLocales(){
        Locale available[]=Locale.getAvailableLocales();
        for(Locale locale:available){
            System.out.println(locale.getDisplayCountry() + "\t" + locale.getDisplayLanguage(locale));
        }
    }

    public static Locale setLocale(Locale givenLocale){
        Locale.setDefault(givenLocale);
        Locale setLocale=Locale.getDefault();
        return setLocale;
    }

    public static void getInfo(Locale givenLocale,Properties props){
        String country=givenLocale.getDisplayCountry();
        System.out.printf(MessageFormat.format(props.getProperty("country"),givenLocale.getDisplayCountry())+country+"\n");

        String language=givenLocale.getDisplayLanguage();
        System.out.printf(MessageFormat.format(props.getProperty("language"),givenLocale.getDisplayCountry())+language+"\n");

        var currencyObject=Currency.getInstance(givenLocale);
        String currencyCode=currencyObject.getCurrencyCode();
        System.out.printf(MessageFormat.format(props.getProperty("currency"),givenLocale.getDisplayCountry())+currencyCode+"\n");

        WeekFields weekFields=WeekFields.of(givenLocale);
        DayOfWeek day=weekFields.getFirstDayOfWeek();
        List<String> daysOfTheWeek=new ArrayList<>();
        for(int i=0;i<DayOfWeek.values().length;++i){
            daysOfTheWeek.add(day.getDisplayName(TextStyle.FULL,givenLocale));
            day=day.plus(1);
        }
        System.out.printf(MessageFormat.format(props.getProperty("week-days"),givenLocale.getDisplayCountry()));
        for(var singleDay:daysOfTheWeek){
            if(singleDay!=daysOfTheWeek.get(daysOfTheWeek.size()-1)){
                System.out.printf("%s, ",singleDay);
            }
            else{
                System.out.printf("%s\n",singleDay);
            }
        }

        DateFormatSymbols symbols=new DateFormatSymbols(givenLocale); /* Aici cumva ultimul simbol a mereu null deci trebuie eliminat ultimu element*/
        System.out.printf(MessageFormat.format(props.getProperty("months"),givenLocale.getDisplayCountry()));
        String[] monthNames=symbols.getMonths();
        monthNames=Arrays.copyOf(monthNames,monthNames.length-1);
        for(var month:monthNames){
            if(month!=monthNames[monthNames.length-1]){
                System.out.printf("%s, ",month);
            }else{
                System.out.printf("%s\n",month);
            }
        }

        System.out.printf(MessageFormat.format(props.getProperty("today"),givenLocale.getDisplayCountry()));
        DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.DEFAULT,givenLocale);
        Date today=new Date();
        System.out.printf(dateFormat.format(today)+"\n");

    }

    public static void main(String args[]) {
    }

}
