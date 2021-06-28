package Bonus;

public class Museum extends Location implements Classifiable, Visitable, Payable {
    double openingHour,closingHour;
    double entryFee;
    String typeOfMuseum;

    public void setTime(double openingHour,double closingHour){
        this.openingHour=openingHour;
        this.closingHour=closingHour;
    }

    public void setEntryFee(double entryFee){
        this.entryFee=entryFee;
    }

    public void setRank(String typeOfMuseum){
        this.typeOfMuseum=typeOfMuseum;
    }

    public Museum(String name, String description, Pair<Integer, Integer> coordinates, double openingHour, double closingHour, double entryFee, String typeOfMuseum) {
        super(name, description, coordinates);
        setTime(openingHour, closingHour);
        setEntryFee(entryFee);
        setRank(typeOfMuseum);
    }
}
