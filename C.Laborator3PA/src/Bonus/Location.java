package Bonus;

import java.util.HashMap;
import java.util.Map;

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
    public int isVisitable(){
        if(this instanceof Museum || this instanceof Restaurant)
            return 1;
        else return 0;
    }

    public int isOpen(double time){
        int hours=0;
        while(time>60){
            ++hours;
            time-=60;
        }
        double auxTime=hours+time/100;
        double diff=auxTime-(int)(auxTime);
        if(diff>0.6){
            auxTime+=0.4;
        }
        if(this instanceof Museum){
            Museum aux=(Museum)this;
            if(auxTime>aux.openingHour && auxTime<aux.closingHour)
                return 1;
            else return  0;
        }
        else if(this instanceof Restaurant){
            Restaurant aux=(Restaurant)this;
            if(auxTime>aux.openingHour && auxTime<aux.closingHour)
                return 1;
            else return  0;
        }
        return 1;
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