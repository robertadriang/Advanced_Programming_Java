package optional;

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

Optional:
CInstead of using an enum, create dedicated classes for warehouses and factories. Source will become abstract.
*/
public class Main {

    public static void checkSourceEquality(Source s1, Source s2){
        if(s1.equals(s2)){
            System.out.println("Equal");
        } else {
            System.out.println("Not equal");
        }
    }

    public static void checkDestinationEquality(ro.uaic.info.Destination d1, Destination d2){
        if(d1.equals(d2)){
            System.out.println("Equal");
        } else {
            System.out.println("Not equal");
        }
    }

    public static void main(String[] args) {
        /* Create three source objects and put them in an array */
        Source source1 = new Factory("S1", 10);
        Source source2 = new Warehouse("S2", 35);
        Source source3 = new Warehouse("S3", 25);
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
    }
}
