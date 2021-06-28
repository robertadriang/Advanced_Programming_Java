package Optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class School implements Comparable {
    private String name;
    private int capacity;
    private List<String> preferredStudents;

    public School(String name, int capacity, List<String> preferredStudents) {
        this.name = name;
        this.capacity = capacity;
        this.preferredStudents = preferredStudents;
    }

    public School(String name){
        this.name=name;
        Random r = new Random();
        this.capacity=r.nextInt(25)+1;
        this.preferredStudents=new ArrayList<String>();
    }

    public void addStudent(String name){
        preferredStudents.add(name);
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getPreferredStudents() {
        return preferredStudents;
    }

    public String getTopPrefference(){
       return preferredStudents.get(0);
    }


    @Override
    public int compareTo(Object o) {
        School place=(School) o;
        return this.name.toLowerCase().compareTo(place.name.toLowerCase());
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", preferredStudents=" + preferredStudents +
                '}';
    }
}
