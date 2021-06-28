package Optional;

import java.util.ArrayList;
import java.util.List;

public class Problem {
    List<Student> stud;
    List<School> sch;

    public Problem(ArrayList<Student> stud, ArrayList<School> sch) {
        this.stud = stud;
        this.sch = sch;
    }

    public void studentsPrefferingSchool(ArrayList<School> s) {
//        stud.stream()
//                .filter(x -> x.getPrefferedSchools().contains(s.getName()))
//                .forEach( y -> System.out.println(y));
        stud.stream()
                .filter(x -> x.checkPrefferences(s) == 1)
                .forEach(y -> System.out.println(y));
    }

    public void schoolsPrefferingStudent(Student s){
        sch.stream()
                .filter(x -> x.getTopPrefference().compareTo(s.getName())==0)
                .forEach(y -> System.out.println(y));
    }

    @Override
    public String toString() {
        return "Problem{" +
                "stud=" + stud +
                ", sch=" + sch +
                '}';
    }
}
