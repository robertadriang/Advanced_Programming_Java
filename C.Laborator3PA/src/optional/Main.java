package optional;

import java.util.*;
/*
In the City class, create a method to display the locations that are visitable and are not payable, sorted by their opening hour. -Done
Create default methods in the interface Visitable, with the opening hour 09:30 and the closing hour 20:00. -Done
Create a static method getVisitingDuration,in the interface Visitable, that returns a Duration object, representing how long a location is opened during a day. -Done
Create the class TravelPlan. An instance of this class will contain a city and the preferences regarding the visiting order.
Implement an efficient agorithm to determine the shortest path between two given locations, conforming to the preferences.
 */
public class Main {
    public static void main(String[] args) {
        Location v1=new Hotel("International","Hotel International Iasi - decent",new Pair(15,25),"4");
        Location v2=new Museum("Muzeul Unirii","Muzeul Unirii - Monument istoric și de arhitectură", new Pair(25,20),10,16,5,"Muzeu de istorie");
        Location v3=new Museum("Palatul Culturii","clădire emblematică în perimetrul fostei curți domnești medievale moldovenești", new Pair(102,90),10,16,50,"Muzeu de istorie");
        Location v4=new Church("Catedrala Mitropolinata","biserica catedrală a Mitropoliei Moldovei și Bucovinei",new Pair(99,98),"Ortodoxa");
        Location v5=new Church("Catedrala \"Sfânta Fecioară Maria, Regină\"","catedrală episcopală",new Pair(99,97),"Catolica");
        Location v6=new Restaurant("Mamma mia","Restaurant din centrul orasului.",new Pair(70,75),9,23,"Pizzeria","$$");
        Location v7=new Restaurant("Dopo Poco","Restaurant langa Moldova Mall", new Pair(50,50),10,20,"FastFood","$$");
        Location v8=new Restaurant("Kiwan","Restaurant Stefan", new Pair(50,60),12,21,"FastFood","$");
        Location v9=new Restaurant("Alila","Pizza prin Cantemir",new Pair(20,15),"Pizzeria","$");
        Location[]mustVisit={v1,v2,v3,v4,v5,v6,v7,v8,v9};

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

        Iasi.showVisitableNotPayable();
        System.out.println("we added the paths");

        List<Integer> tripPrefferences=new ArrayList<>();
        tripPrefferences.add(1);
        tripPrefferences.add(0);

        int [][]graph = new int[mustVisit.length][mustVisit.length];
        for(int i=0;i< mustVisit.length;++i)
            for (Map.Entry<String,Integer> entry : Iasi.places.get(i).relatedPlaces.entrySet())
            {
                graph[i][Iasi.getPosition(entry.getKey())]=entry.getValue();
            }

        TravelPlan trip = new TravelPlan(Iasi,tripPrefferences);

            ShortestPath r=new ShortestPath();
            r.dijkstra(graph,4,6,trip);

    }
}
