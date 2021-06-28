package Compulsory;

import java.util.List;
import java.util.Locale;

public class School implements Comparable {
    private String name;
    private int capacity;
    private List<String> preferredStudents;

    public School(String name, int capacity,  List<String> preferredStudents) {
        this.name = name;
        this.capacity = capacity;
        this.preferredStudents = preferredStudents;
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
