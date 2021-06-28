package bonus;

/**
 * This is the problem class that modelates The Transportation Problem
 * An instance of the Transportation Problem consists of source and destinations.
 *
 * Each source has a given capacity, i.e. how many units of a commodity it is able to supply to the destinations.
 * Each destination demands a certain amount of commodities.
 * The cost of transporting a unit of commodity from each source to each destination is given by a cost matrix (or function).
 * We consider the problem of determining the quantities to be transported from sources to destinations, in order to minimize the total transportation cost. The supply and demand constraints must be satisfied. (We may assume that all the values are integer).
 *
 * Consider the following example.
 * D1	    D2	D3	Supply
 * S1	    2	3	1	10
 * S2	    5	4	8	35
 * S3	    5	6	8	25
 * Demand	20	25	25
 */
public class Problem {

    private final int sourceSize, destinationsSize;
    private Source[] sources;
    private Destination[] destinations;
    public int[][] costMatrix;

    /**
     * Default constructor that coppies the parameters to the fields of the Object
     * @param sources array of Source objects
     * @param destinations array of Destination objects
     * @param costMatrix matrix of Costs. position i,j marks the cost of transferring an unit from i to j
     * @param sourceSize the number of sources
     * @param destinationsSize the number of destinations
     */
    public Problem(Source[] sources, Destination[] destinations, int[][] costMatrix, int sourceSize, int destinationsSize) {
        this.sources = sources;
        this.destinations = destinations;
        this.costMatrix = costMatrix;
        this.sourceSize = sourceSize;
        this.destinationsSize = destinationsSize;
    }  // Generated constructor that requires parsing all the objects

    /**
     * Constructor that allocates memory for the arrays based on the number of sources and destinations passed
     * @param sourceSize
     * @param destinationsSize
     */
    public Problem(int sourceSize, int destinationsSize) {
        this.sourceSize = sourceSize;
        this.destinationsSize = destinationsSize;
        this.sources = new Source[sourceSize];
        this.destinations = new Destination[destinationsSize];
        this.costMatrix = new int[sourceSize + 1][destinationsSize + 1];

    } // Constructor that allocated the arrays sizes based on the source/destinations given and sets the sizes accordingly

    /**
     * Set function that sets the list of sources. The sources are added only if all of the sources are different (by name)
     * @param sources array of sources
     */
    public void setSources(Source[] sources) {

        for(int i=0;i<sources.length-1;++i) {
            for(int j=i+1;j<sources.length;++j)
                if(sources[i].equals(sources[j])){
                    System.out.printf("Cannot add duplicate sources: %s,%s",sources[i].getName(),sources[j].getName());
                    return;
                }
        }

        for (int i = 0; i < sourceSize; ++i) {
            for(int j=0;j<sources.length;++j)

                this.sources[i] = new Source(sources[i].getName(), sources[i].getSupply(), sources[i].getType());
        }

    } //Setter for sources array that takes an array of sources

    /**
     * Set function that sets the list of destinations. The destinations are added only if all of the destinations are different (by name)
     * @param destinations array of destinations
     */
    public void setDestinations(Destination[] destinations) {

        for(int i=0;i<destinations.length-1;++i) {
            for(int j=i+1;j<destinations.length;++j)
                if(destinations[i].equals(destinations[j])){
                    System.out.printf("Cannot add duplicate destinations: %s,%s",destinations[i].getName(),destinations[j].getName());
                    return;
                }
        }

        for (int i = 0; i < destinationsSize; ++i) {
            this.destinations[i] = new Destination(destinations[i].getName(), destinations[i].getDemand());
        }
    } //Setter for destinations array that takes an array of destinations

    /**
     * Set function that sets the matrix of costs and adds the total supply/demand of the problem
     * @param costValues of costs of length= nrOfSources*nrOfDestinations the cost-value of going from source i to destination j is found on: costValues[i*(1+j)]
     */
    public void setCostMatrix(int[] costValues) {
        int it = 0;
        for (int i = 0; i < sourceSize; ++i)
            for (int j = 0; j <= destinationsSize; ++j) {
                if (j != destinationsSize) {
                    this.costMatrix[i][j] = costValues[it++];
                } else {
                    this.costMatrix[i][j] = this.sources[i].getSupply();
                }
            }

        for (int i = 0; i < destinationsSize; ++i)
            this.costMatrix[sourceSize][i] = this.destinations[i].getDemand();
    } //Initiate the cost matrix based on the values of the cost vector given and adds the supply/demand fields;

    /**
     * Get function that
     * @return the number of sources
     */
    public int getSourceSize() {
        return sourceSize;
    }

    /**
     * Get function that
     * @return the number of destinations
     */
    public int getDestinationsSize() {
        return destinationsSize;
    }

    /**
     * Get function that takes
     * @param position a position of an element in the source array
     * @return the source object if the position is valid null otherwise
     */
    public Source getSourceElement(int position){
        if(position>=0&&position<getSourceSize())
            return sources[position];
        else return null;
    }

    public Destination getDestinationElement(int position){
        if(position>=0&&position<getDestinationsSize())
            return destinations[position];
        else return null;
    }

    /**
     * Print the instance of a problem in a table like manner
     */
    public void printProblemInstance() {
        final String empty = "          "; /* const string used for formatting table */
        StringBuilder buffer = new StringBuilder(); /* Full answer*/
        StringBuilder tableHead = new StringBuilder(empty);

        /* calls like: string1.append(string2).append(string3.substring(string2.length)):
         * Add to string1 string2 then add string3 starting from string3+strlen(string2) position
         * Example: "12345678" can be replaced to "xxxx5678" by calling
         * out="xxxx"+"12345678".substring(4) */

        /* Generate the head of the table and add it to the answer body  (List of destination names + Supply column head*/
        for (int i = 0; i < destinationsSize; ++i) {
            String cell = this.destinations[i].getName();
            tableHead.append(cell).append(empty.substring(cell.length()));
        }
        tableHead.append("Supply").append(empty.substring(6));
        buffer.append(tableHead).append("\n");

        /*Generate the "source" rows of form sourceI---sourceItoDestination1---...---sourceItoDestinationJ---Supply[I] */
        for (int i = 0; i < sourceSize; ++i) {
            String cell = this.sources[i].getName();
            buffer.append(cell).append(empty.substring(cell.length()));
            for (int j = 0; j < destinationsSize; ++j)
                buffer.append(costMatrix[i][j]).append(empty.substring(String.valueOf(costMatrix[i][j]).length()));
            cell = String.valueOf(this.sources[i].getSupply());
            buffer.append(cell).append(empty.substring(cell.length())).append("\n");
        }

        /* Generate the demand row of form Demand---DemandOfDestination1---...---DemandOfDestinationJ*/
        buffer.append("Demand").append(empty.substring(6));
        for (int i = 0; i < destinationsSize; ++i) {
            String cell = String.valueOf(this.destinations[i].getDemand());
            buffer.append(cell).append(empty.substring(cell.length()));
        }

        System.out.println(buffer);
    }

    /**
     * Overriden toString method displaying the fields in a JSON simillar format
     * */
    public String toString() {
        StringBuilder response = new StringBuilder("{\nsourceSize: " + sourceSize + ",\n"); // add sourceSize to the response body
        response.append("destinationSize: ").append(destinationsSize).append(",\n\n");  // add destinationSize to the response body
        response.append("sources:[\n\n");
        for (int i = 0; i < destinationsSize; ++i) {
            response.append("sources[").append(i).append("]=");
            response.append(this.sources[i].toString());
            response.append("\n");
        } // Call the .toString for each Source object in the sources array and add it to the response body
        response.append("],\n\n");

        response.append("destinations:[\n\n");
        for (int i = 0; i < destinationsSize; ++i) {
            response.append("destinations[").append(i).append("]=");
            response.append(this.destinations[i].toString());
            response.append("\n");
        } // Call the .toString for each Destination object in the destinations array and add it to the response body
        response.append("],\n\n");

        response.append("costMatrix:\n");
        for (int i = 0; i <= sourceSize; ++i) {
            for (int j = 0; j <= destinationsSize; ++j) {
                response.append(costMatrix[i][j]).append(" ");
            } // Add the costMatrix to the response body
            response.append("\n");
        }
        response.append("}\n");
        return response.toString();
    }
}
