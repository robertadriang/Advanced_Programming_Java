package compulsory;

public class Restaurant extends Location implements Classifiable,Visitable{
    double openingHour,closingHour;
    String priceRange;
    String typeOfRestaurant;

    public Restaurant(String name, String description, Pair<Integer, Integer> coordinates,double openingHour,double closingHour,String typeOfRestaurant,String priceRange) {
        super(name, description, coordinates);
        setTime(openingHour,closingHour);
        setRank(typeOfRestaurant);
        setPriceRange(priceRange);
    }

    public void setTime(double openingHour,double closingHour){
        this.openingHour=openingHour;
        this.closingHour=closingHour;
    }

    public void setRank(String typeOfRestaurant){
        this.typeOfRestaurant=typeOfRestaurant;
    }

    public void setPriceRange(String priceRange){
        this.priceRange=priceRange;
    }
}
