package Bonus;

public class Hotel extends Location implements Classifiable {
    String nrOfStars;

    public void setRank(String rank){
        nrOfStars=rank;
    }
    public Hotel(String name, String description, Pair<Integer, Integer> coordinates, String nrOfStars) {
        super(name, description, coordinates);
        setRank(nrOfStars);
    }

}