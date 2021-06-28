package Bonus;

public class Church extends Location implements Classifiable {
    String religion;

    public Church(String name, String description, Pair<Integer, Integer> coordinates, String religion) {
        super(name, description, coordinates);
        setRank(religion);
    }

    public void setRank(String religion){
        this.religion=religion;
    }
}