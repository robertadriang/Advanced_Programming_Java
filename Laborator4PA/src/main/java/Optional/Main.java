package Optional;

import com.github.javafaker.Faker;
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
Create a class that describes the problem and one that describes a solution (a matching) to this problem.
Using Java Stream API, write queries that display the students who find acceptable a given list of schools, and the schools that have a given student as their top preference.
Use a third-party library in order to generate random fake names for students and schools.
Implement an algorithm for creating a matching, considering that each student has a score obtained at the evaluation exam and the schools rank students based on this score.
Test your algorithm.
* */
import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static List<String> schoolInitializer(int studentNumber) {
        List<String> answer = new ArrayList<String>();
        answer.add("H0");
        if (studentNumber < 2) {
            for (int i = 1; i <= 2; ++i) {
                answer.add("H" + i);
            }
        } else if (studentNumber == 2) {
            answer.add("H" + 1);
        } else answer.add("H" + 2);
        return answer;
    }

    public static int capacityInitializer(int schoolNumber) {
        if (schoolNumber == 0)
            return 1;
        else return 2;
    }

    /* Students are distributed from the best student (highest mark) to the worst. Each student goes to the most preffered school available
    * EX: s1 prefferences in order are {h0,h1,h2}.
    * h0 still has one place available so s1 will be distributed to h0.
    * s2 prefferences in order are {h0,h1,h2}.
    * h0 does not have a place available so s2 will be distributed to h1. if h1 doesn't have a place it will be distributed to h2.
    * If no preffered school is available for distribution the student will not be assigned to any school.*/
    public static Solution distributeStudents(Student[] students, School[] schools) {
        Solution s = new Solution();
        ArrayList<Student> notDistributed = new ArrayList<Student>();
        ArrayList<School> availableSchools = new ArrayList<School>();
        for (Student student : students) {
            int distributed = 0;
            for (School school : schools) {
                if (school.getPreferredStudents().size() < school.getCapacity()) {
                    school.addStudent(student.getName());
                    s.addMatch(student.getName(), school.getName());
                    distributed = 1;
                    break;
                }
            }
            if (distributed == 0) {
                notDistributed.add(student);
            }
        }

        if (notDistributed.size() != 0) {
            System.out.printf("%d students did not get to a preffered school!\n",notDistributed.size());
            for (School school : schools) {
                if (school.getPreferredStudents().size() < school.getCapacity()) {
                    availableSchools.add(school);
                }
            }
            System.out.printf("There are %d available schools for 2nd distribution round!\n",availableSchools.size());

        }
        else{
            System.out.println("All students were distributed!");
            System.out.printf("%s",s.toString());
            return s;
        }

        return s;
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
        /* Create the objects from the examples */
        var students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i, schoolInitializer(i)))
                .toArray(Student[]::new);

        var schools = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School("H" + i, capacityInitializer(i), studentInitializer(i)))
                .toArray(School[]::new);

        /* Create a linked list of students and sort them using a stream by the number of prefferences */
        List<Student> studentList = new LinkedList<Student>();
        Stream<Student> stream = Arrays.stream(students);
        studentList = stream.collect(Collectors.toCollection(LinkedList::new));
        Collections.sort(studentList, (Student s1, Student s2) -> {
            return s1.getNrOfPrefferences() - s2.getNrOfPrefferences();
        });

        /* Create a set of schools*/
        Set<School> schoolSet = new TreeSet<School>();
        Stream<School> stream2 = Arrays.stream(schools);
        schoolSet = stream2.collect(Collectors.toCollection(TreeSet::new));

        /* Create different implementations of maps and print them*/
        Map<String, LinkedList<School>> studentPreferencesHash = new HashMap<String, LinkedList<School>>();
        Map<String, LinkedList<School>> studentPreferencesTree = new TreeMap<String, LinkedList<School>>();

        for (int i = 0; i < students.length; ++i) {
            Stream<School> stream3 = Arrays.stream(schools);
            String key = students[i].getName();
            var aux = stream3
                    .filter(x -> x.getPreferredStudents().contains(key)).collect(Collectors.toCollection(LinkedList::new));
            studentPreferencesHash.put(key, aux);
            studentPreferencesTree.put(key, aux);
        }

        System.out.println("Hashmap:");
        studentPreferencesHash.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("\n\nTreeMap:");
        studentPreferencesTree.forEach((key, value) -> System.out.println(key + ": " + value));

        /* Create the input for the matching problem (ArrayList of students and Arraylist of schools) */
        var studList = new ArrayList<Student>();
        var problemStreamStud = Arrays.stream(students);
        studList = problemStreamStud.collect(Collectors.toCollection(ArrayList::new));

        var schList = new ArrayList<School>();
        var problemStreamSch = Arrays.stream(schools);
        schList = problemStreamSch.collect(Collectors.toCollection(ArrayList::new));

        Problem p = new Problem(studList, schList);
        Solution s = new Solution();
        System.out.println("\n\n\n");

        ArrayList<School> schoolCriteria = new ArrayList<School>();
        schoolCriteria.add(schools[1]);
        schoolCriteria.add(schools[2]);

        /* Filter the arrays with certain criteria*/
        System.out.println("Students that opted for H1 and H2");
        p.studentsPrefferingSchool(schoolCriteria);
        System.out.println("\n\n");
        System.out.println("Schools that have S0 as a top prefference");
        p.schoolsPrefferingStudent(students[0]);

        /* Implement the Faker library*/
        Faker faker = new Faker();
        Student testStudent = new Student(faker);

        /**Generate random data for the problem input**/
        var studentsToBeDistributed = IntStream.rangeClosed(0, 250)
                .mapToObj((x) -> new Student(faker))
                .toArray(Student[]::new);

        Arrays.sort(studentsToBeDistributed, (Student s1, Student s2) -> {
            return ((s2.getScore() - s1.getScore()) > 0) ? 1 : -1;
        });

        var schoolsToBeFilled = IntStream.rangeClosed(0, 20)
                .mapToObj(i -> new School("H" + i))
                .toArray(School[]::new);

        /**Distribute the students**/
        distributeStudents(studentsToBeDistributed, schoolsToBeFilled);


    }

}
