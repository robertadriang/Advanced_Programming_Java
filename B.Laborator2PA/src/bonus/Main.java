package bonus;
import java.util.Random;
/*The Transportation Problem
An instance of the Transportation Problem consists of source and destinations.

Each source has a given capacity, i.e. how many units of a commodity it is able to supply to the destinations.
Each destination demands a certain amount of commodities.
The cost of transporting a unit of commodity from each source to each destination is given by a cost matrix (or function).
We consider the problem of determining the quantities to be transported from sources to destinations, in order to minimize the total transportation cost. The supply and demand constraints must be satisfied. (We may assume that all the values are integer).

Consider the following example.
D1	    D2	D3	Supply
S1	    2	3	1	10
S2	    5	4	8	35
S3	    5	6	8	25
Demand	20	25	25

Optional (2p)
Override the equals method form the Object class for the Source, Destination classes. The problem should not allow adding the same source or destination twice.
Instead of using an enum, create dedicated classes for warehouses and factories. Source will become abstract.
Create a class to describe the solution.
Implement a simple algorithm for creating a feasible solution to the problem (one that satisfies the supply and demand constraints).
Write doc comments in your source code and generate the class documentation using javadoc.

Bonus (2p)
Implement an algorithm in order to minimize the total cost (Vogel's approximation method)
Generate large random instances and analyze the performance of your algorithm (running times, memory consumption). Identify the hot-spots in your code.
*/
public class Main {

    public static void checkSourceEquality(Source s1,Source s2){
        if(s1.equals(s2)){
            System.out.println("Equal");
        } else {
            System.out.println("Not equal");
        }
    }

    public static void checkDestinationEquality(Destination d1,Destination d2){
        if(d1.equals(d2)){
            System.out.println("Equal");
        } else {
            System.out.println("Not equal");
        }
    }

    public static Source randomSourceGenerator(int index){
        Random rand = new Random();
        String name="S"+index;
        int supply=10+rand.nextInt(100);
        return (new Source(name,supply,SourceType.FACTORY));
    }

    public static Destination randomDestinationGenerator(int index){
        Random rand = new Random();
        String name="D"+index;
        int demand=10+rand.nextInt(100);
        return (new Destination(name,demand));
    }

    public static void main(String[] args) {
        /*Bonus main*/
        /* Create three source objects and put them in an array */
        Source source1 = new Source("S1", 10, SourceType.FACTORY);
        Source source2 = new Source("S2", 35, SourceType.WAREHOUSE);
        Source source3 = new Source("S3", 25, SourceType.WAREHOUSE);
        Source[] sources = new Source[]{source1, source2, source3};

        /* Create three destination objects and put them in an array */
        Destination d1 = new Destination("D1", 20);
        Destination d2 = new Destination("D2", 25);
        Destination d3 = new Destination("D3", 25);
        Destination[] destinations = new Destination[]{d1, d2, d3};

        /* Generate a vector of costs of length= nrOfSources*nrOfDestinations the cost-value of going from source i to destination j is found on:
         * cosValues[i*(1+j)] */
        int[] costValues = {2, 3, 1, 5, 4, 8, 5, 6, 8};

        int sourceSize = 3, destinationSize = 3;

        /* Use the constructor that takes only the nr of sources and destinations and use setters for the arrays*/
        Problem problem = new Problem(sourceSize, destinationSize);
        problem.setSources(sources);
        problem.setDestinations(destinations);
        problem.setCostMatrix(costValues);

        /* sout(problem) calls for the toString method of the object problem */
        System.out.println(problem);

        /* Method used for printing the problem instance in a table manner */
        problem.printProblemInstance();

        Solution s=new Solution(problem);
        System.out.printf("Result with normal algorithm: %d\n",s.solveProblem(problem));
        int result=s.solveProblemVogel(problem);
        System.out.printf("Result with Vogel aproximation: %d\n",result);

        bigInstanceTest(10);
        /***********************************************************************/
//      /* OPTIONAL MAIN */
//        Source source4 = new Source("S1", 10, SourceType.FACTORY);
//        Source source1 = new Source("S1", 10, SourceType.FACTORY);
//        Source source2 = new Source("S1", 10, SourceType.FACTORY);
//        Source source3 = new Source("S3", 10, SourceType.FACTORY);
//        Destination d1 = new Destination("D1", 20);
//        Destination d2 = new Destination("D1", 20);
//        Destination d3 = new Destination("D3", 25);
//        Destination d4 = new Destination("D4", 25);
//        checkSourceEquality(source1,source2);
//        checkSourceEquality(source1,source3);
//        if(source1.equals(d1)){
//            System.out.println("Equal");
//        } else {
//            System.out.println("Not equal");
//        }
//
//        checkDestinationEquality(d1,d2);
//        checkDestinationEquality(d1,d3);
//        if(d1.equals(source1)){
//            System.out.println("Equal");
//        } else {
//            System.out.println("Not equal");
//        }
//
//
//        Source[] sources = new Source[]{source1, source3, source1};
//        Destination[] destinations = new Destination[]{d1, d2, d3};
//        Destination[] destinations2=new Destination[]{d1,d3,d4};
//
//        int sourceSize = 3, destinationSize = 3;
//
//        Problem problem = new Problem(sourceSize, destinationSize);
//        problem.setSources(sources); // not working
//        problem.setDestinations(destinations); // not working
//        problem.setDestinations(destinations2); // ok
    }

    private static void bigInstanceTest(int bound) {
        Source[] sources;

        Destination[] destinations;
        Random rand = new Random();
        int sourcesNumber=bound+rand.nextInt(bound);
        int destinationsNumber=bound+rand.nextInt(bound);
        sources = new Source[sourcesNumber];
        destinations=new Destination[destinationsNumber];

        int sourceTotal=0;
        for(int i=0;i<sources.length;++i)
        {
            sources[i]=randomSourceGenerator(i);
            sourceTotal+=sources[i].getSupply();
        }

        int destinationTotal=0;
        for(int i=0;i<destinations.length;++i)
        {
            destinations[i]=randomDestinationGenerator(i);
            destinationTotal+=destinations[i].getDemand();
        }

        if(sourceTotal>destinationTotal)
        {
            int difference=sourceTotal-destinationTotal;
            int iterator=0;
            while(difference>10)
            {
                destinations[iterator].setDemand(destinations[iterator].getDemand()+10);
                iterator=(iterator+1)%destinations.length;
                if(iterator==0)
                    System.out.println("check");
                difference-=10;
            }
            destinations[iterator].setDemand(destinations[iterator].getDemand()+difference);
        }
        else
        {
            int difference=destinationTotal-sourceTotal;
            int iterator=0;
            while(difference>10)
            {
                sources[iterator].setSupply( sources[iterator].getSupply()+10);
                iterator=(iterator+1)%sources.length;
                difference-=10;
            }
            sources[iterator].setSupply( sources[iterator].getSupply()+difference);
        }

        int randomCost[]=new int[sources.length*destinations.length];
        for(int i=0;i<randomCost.length;++i)
        {
            randomCost[i]=1+rand.nextInt(30);
        }

        Problem bigProblem = new Problem(sourcesNumber, destinationsNumber);
        bigProblem.setSources(sources);
        bigProblem.setDestinations(destinations);
        bigProblem.setCostMatrix(randomCost);

        Solution bigSolution=new Solution(bigProblem);
        System.out.println("Final generated");
        //System.out.printf("Result with normal algorithm: %d\n",s.solveProblem(bigProblem));
        int bigResult=bigSolution.solveProblemVogel(bigProblem);
        System.out.printf("Result with Vogel aproximation: %d\n",bigResult);
    }
}
