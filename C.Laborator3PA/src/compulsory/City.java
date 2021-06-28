package compulsory;
import java.util.*;
public class City{
    List<Location> places;

    public City() {
        places= new ArrayList<Location>();
    }

    public void addTripDuration(Location start,Location end,int time){
        start.relatedPlaces.put(end.name,time);
    }

    public void addTripDuration(Location start,Location end,int time,int bothWays){
    start.relatedPlaces.put(end.name,time);
    end.relatedPlaces.put(start.name,time);
    }

}
