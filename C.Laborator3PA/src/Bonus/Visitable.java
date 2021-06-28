package Bonus;

public interface Visitable {
    void setTime(double openingHour,double closingHour);

    default double getOpeningHour(){
        return 9.3;
    };

    default double getClosingHour(){
        return 20;
    }

    static Duration getVisitingDuration(double openingHour, double closingHour){
         Duration totalOpened= new Duration(closingHour-openingHour);
         return totalOpened;
    }
}
