package Compulsory;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
* The Student / High School Admission Problem (SAP)
An instance of SAP involves a set of students and a set of high schools, each student seeking admission to one school, and each school having a number of available places (its capacity). Each student ranks some (acceptable) schools in strict order, and each school ranks its applicants in some order. A matching is a set of pairs (student, school) such that each student is assigned to at most one school and the capacities of the schools are not exceeded. A matching is stable if there is no pair (s, h) such that s is assigned to h' but s prefers h better than h' and h prefers s better than some of its assigned students. We consider the problem of creating a stable matching between students and schools.

Example: 4 students S0,S1,S2,S3, 3 high schools H0,H1,H2, capacity(H0)=1, capacity(H1)=2, capacity(H2)=2.
students preferences
S0: (H0, H1, H2)
S1: (H0, H1, H2)
S2: (H0, H1)
S3: (H0, H2)
schools preferences
H0: (S3, S0, S1, S2)
H1: (S0, S2, S1)
H2: (S0, S1, S3)
A solution for this example might be: [(S0:H1),(S1:H2),(S2:H1),(S3:H0)]
*
* Create an object-oriented model of the problem. You should have at least the following classes: Student, School and the main class.
Create all the objects in the example using streams.
Create a list of students, using LinkedList implementation. Sort the students, using a comparator.
Create a set of schools, using a TreeSet implementation. Make sure that School objects are comparable.
Create two maps (having different implementations) describing the students and the school preferences and print them on the screen.

* */
import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static List<String> schoolInitializer(int studentNumber){
        List<String> answer = new ArrayList<String>();
        answer.add("H0");
        if(studentNumber<2){
            for(int i=1;i<=2;++i){
                answer.add("H"+i);
            }
        }
        else if(studentNumber==2)
        {
            answer.add("H"+1);
        }
        else answer.add("H"+2);
        return answer;
    }

    public static int capacityInitializer(int schoolNumber){
        if(schoolNumber==0)
            return 1;
        else return 2;
    }

    public static  List<String> studentInitializer(int schoolNumber){
        List<String> answer = new ArrayList<String>();
        if(schoolNumber==0){
            answer.add("S"+3);
            for(int i=0;i<3;++i) {
                answer.add("S" + i);
            }
        }
        else{
            answer.add("S"+0);
            if(schoolNumber==1)
            {
                for(int i=2;i>=1;--i){
                    answer.add("S"+i);
                }
            }
            else{
                for(int i=1;i<=3;i+=2){
                    answer.add("S"+i);
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        var students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S"+i, schoolInitializer(i)))
                .toArray(Student[]::new);


        var schools=IntStream.rangeClosed(0,2)
                .mapToObj(i->new School("H"+i,capacityInitializer(i),studentInitializer(i)))
                .toArray(School[]::new);

        List<Student> studentList=new LinkedList<Student>();
        Stream<Student> stream= Arrays.stream(students);
        studentList=stream.collect(Collectors.toCollection(LinkedList::new));
        Collections.sort(studentList,(Student s1, Student s2)->{
           return s1.getNrOfPrefferences()-s2.getNrOfPrefferences();
        });

        Set<School> schoolSet=new TreeSet<School>();
        Stream<School> stream2=Arrays.stream(schools);
        schoolSet=stream2.collect(Collectors.toCollection(TreeSet::new));

        Map<String,LinkedList<School>> studentPreferencesHash=new HashMap<String,LinkedList<School>>();
        Map<String,LinkedList<School>> studentPreferencesTree=new TreeMap<String,LinkedList<School>>();

        for(int i=0;i<students.length;++i){
            Stream<School> stream3=Arrays.stream(schools);
            String key=students[i].getName();
            var aux=stream3
                    .filter(x -> x.getPreferredStudents().contains(key)).collect(Collectors.toCollection(LinkedList::new));
            studentPreferencesHash.put(key,aux);
            studentPreferencesTree.put(key,aux);
        }

        System.out.println("Hashmap:");
        studentPreferencesHash.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("\n\nTreeMap:");
        studentPreferencesTree.forEach((key, value) -> System.out.println(key + ": " + value));
/*        Predicate<Integer> pred1 = x -> (x > 2);
        Predicate<Integer> pred2 = x -> (x < 5);
        System.out.println(pred1.and(pred2).negate().test(1));
        int sum=Arrays.asList(1,2,3,4,5).stream()
                .filter(x -> x >=3)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(sum);*/

    }

}
