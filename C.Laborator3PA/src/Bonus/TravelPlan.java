package Bonus;

import java.util.List;

public class TravelPlan {
    City city;
    List<Integer> prefferences;

    public TravelPlan(City city, List<Integer> prefferences) {
        this.city = city;
        this.prefferences = prefferences;
    }
    public int getPrefferencePosition(Integer value){
        for(int i=0;i<prefferences.size();++i){
            if(prefferences.get(i)==value)
                return i;
        }
        return -1;
    }
}
