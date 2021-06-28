package compulsory;

import java.util.*;

abstract class Location implements Comparable {
    String name;
    String description;
    Pair<Integer,Integer> coordinates;
    Map<String, Integer> relatedPlaces;

    public Location(String name, String description, Pair<Integer, Integer> coordinates) {
        this.name = name;
        this.description = description;
        this.coordinates = coordinates;
        this.relatedPlaces=new HashMap<String, Integer>();
    }

    public int compareTo(Object o){
        if(o == null)
            throw new NullPointerException();
        if(!(o instanceof Location))
            throw new ClassCastException("Uncomparable objects");
        Location place=(Location) o;
        return this.name.toLowerCase().compareTo(place.name.toLowerCase());
    }
}
