package Optional;

import com.github.javafaker.Faker;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Student {
    private String name;
    private List<String> prefferedSchools;
    private double score;

    Student(String name, List<String> prefferedSchools){
        this.name=name;
        this.prefferedSchools=prefferedSchools;
    }

    private static DecimalFormat df = new DecimalFormat("0.00");

    List<String> randomSchoolInitializer(Random r){
        List<String> aux=new ArrayList<String>();
        int numberOfSchools=r.nextInt(10)+1;
        for(int i=0;i<numberOfSchools;++i){
            int schoolNumber=r.nextInt(20);
            while(aux.contains("H"+schoolNumber)){
                schoolNumber=r.nextInt(20);
            }
            aux.add("H"+schoolNumber);
        }
        return aux;
    }

    Student(Faker f){
        Random r = new Random();
        this.name=f.name().fullName();
        this.prefferedSchools=randomSchoolInitializer(r);
        this.score=Double.parseDouble(df.format(r.nextDouble()*10));
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

    public int checkPrefferences(ArrayList<School> s){
        int found=0;
       for(var names : prefferedSchools){
           for(var school : s){
               if(school.getName().compareTo(names)==0)
               {
                   ++found;
                   break;
               }
           }
       }
       if(found==s.size())
       return 1;
       else return 0;
    }

    public double getScore(){
        return score;
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

