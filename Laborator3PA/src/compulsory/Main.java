package compulsory;

/**
 * The Tourist Trip Planning Problem
 * A tourist is about to visit a city. In this city, there are various locations such as tourism sites, hotels and restaurants, parks, etc. Locations have names and may have other common properties (such as a description, an image, coordinates, etc). Depending on its type, each location has various specific properties. However, if the location is a visitable, it must have opening hours. If the location is payable, it must have the entry fee (the price of the ticket). If the location is classifiable, it must have a classification (a rank).
 * The time (in minutes) required to go from one location to another is known. The tourist has also preferences regarding the order in which he (or she) would like to visit some locations.
 * Example: locations are: v1 (Hotel) v2 (Museum A) v3 (Museum B) v4 (Church A) v5 (Church B) v6 (Restaurant).
 * From-To	Cost
 * v1→v2	10
 * v1→v3	50
 * v2↔v3	20
 * v2→v4	20
 * v2→v5	10
 * v3→v4	20
 * v4↔v5	30
 * v4→v6	10
 * v5→v6	20
 * The main specifications of the application are:
 *
 * Compulsory (1p)
 *
 * Create an object-oriented model of the problem. You should have at least the following classes City, Hotel, Museum, Church, Restaurant. The natural ordering of their objects is given by their names.
 * Create the interfaces Visitable, Payable, Classifiable. The classes above must implement these interfaces accordingly.
 * The City class will contain a List of locations.
 * Each location will contain a Map representing the times required to go from this location to others.
 * Create all the objects given in the example. ---done
 */
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Location v1=new Hotel("International","Hotel International Iasi - decent",new Pair(15,25),"4");
        Location v2=new Museum("Muzeul Unirii","Muzeul Unirii - Monument istoric și de arhitectură", new Pair(25,20),10,16,5,"Muzeu de istorie");
        Location v3=new Museum("Palatul Culturii","clădire emblematică în perimetrul fostei curți domnești medievale moldovenești", new Pair(102,90),10,16,50,"Muzeu de istorie");
        Location v4=new Church("Catedrala Mitropolinata","biserica catedrală a Mitropoliei Moldovei și Bucovinei",new Pair(99,98),"Ortodoxa");
        Location v5=new Church("Catedrala \"Sfânta Fecioară Maria, Regină\"","catedrală episcopală",new Pair(99,97),"Catolica");
        Location v6=new Restaurant("Mamma mia","Restaurant din centrul orasului.",new Pair(70,75),9,23,"Pizzeria","$$");

        Location[]mustVisit={v1,v2,v3,v4,v5,v6};

        City Iasi=new City();

        for(int i=0;i<mustVisit.length;++i)
            Iasi.places.add(mustVisit[i]);

        Collections.sort(Iasi.places);
        System.out.println("Come visit Iasi");

        Iasi.addTripDuration(v1,v2,10);
        Iasi.addTripDuration(v1,v3,50);
        Iasi.addTripDuration(v2,v3,20,1);
        Iasi.addTripDuration(v2,v4,20);
        Iasi.addTripDuration(v2,v5,10);
        Iasi.addTripDuration(v3,v4,20);
        Iasi.addTripDuration(v4,v5,30,1);
        Iasi.addTripDuration(v4,v6,10);
        Iasi.addTripDuration(v5,v6,20);

        System.out.println("we added the paths");
    }
}
