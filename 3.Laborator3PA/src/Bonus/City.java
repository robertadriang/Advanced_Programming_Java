package Bonus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class City {
    List<Location> places;

    public City() {
        places= new ArrayList<Location>();
    }

    public void addTripDuration(Location start, Location end, int time){
        start.relatedPlaces.put(end.name,time);
    }

    public void addTripDuration(Location start, Location end, int time, int bothWays){
        start.relatedPlaces.put(end.name,time);
        end.relatedPlaces.put(start.name,time);
    }

    public int getPosition(String name){
        for(int i=0;i<places.size();++i)
        {
            if(places.get(i).name.compareTo(name)==0){
                return i;
            }
        }
        return -1;
    }


    public void showVisitableNotPayable(){
        List<Pair<Location,Double>> sortByOpening=new ArrayList<Pair<Location,Double>>();
        for(int i=0;i<places.size();++i){
            Location aux=places.get(i);
            if(aux instanceof Visitable && !(aux instanceof Payable))
            {
                if(aux instanceof Restaurant)
                {
                    sortByOpening.add(new Pair((Restaurant)aux,((Restaurant)aux).openingHour));
                }
            }
        }
        Collections.sort(sortByOpening, new Comparator<Pair<Location, Double>>() {
            @Override
            public int compare(Pair<Location, Double> o1, Pair<Location, Double> o2) {
                return (int)(o1.right*100-o2.right*100);
            }
        });
        System.out.println("Locations visitable but not payable:");
        for(int i=0;i<sortByOpening.size();++i){
            double duration=((Restaurant)(sortByOpening.get(i).left)).getTimeOpen();
            System.out.printf("Name:%s | OpeningHour:%.2f | TotalOpen: %.2f \n",sortByOpening.get(i).left.name,sortByOpening.get(i).right,duration);
        }

    }
}