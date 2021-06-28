package Compulsory;
import java.util.*;

public class Student {
    private String name;
    private List<String> prefferedSchools;

    Student(String name, List<String> prefferedSchools){
        this.name=name;
        this.prefferedSchools=prefferedSchools;
    }
    public Student(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public List<String> getPrefferedSchools() {
        return prefferedSchools;
    }
    public String getNPrefferedSchool(int n){
        return prefferedSchools.get(n);
    }

    public int getNrOfPrefferences(){
        return prefferedSchools.size();
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", prefferedSchools=" + prefferedSchools +
                '}';
    }
}
