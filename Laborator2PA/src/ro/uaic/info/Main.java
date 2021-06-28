package ro.uaic.info;

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

Compulsory (1p)

Create an object-oriented model of the problem. You should have (at least) the following classes: Source, Destination, Problem.
The sources and the destinations have names. The sources will also have the property type. The available types will be implemented as an enum . For example:
public enum SourceType {
    WAREHOUSE, FACTORY;
}
Assume S1 is a factory and S2, S3 are warehouses.
Each class should have appropriate constructors, getters and setters.
Use the IDE features for code generation, such as generating getters and setters.
The toString method form the Object class must be properly overridden for all the classes.
Use the IDE features for code generation, for example (in NetBeans) press Alt+Ins or invoke the context menu, select "Insert Code" and then "toString()" (or simply start typing "toString" and then press Ctrl+Space).
Create and print on the screen the instance of the problem described in the example.
*/
public class Main {
    public static void main(String[] args) {
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
    }
}
