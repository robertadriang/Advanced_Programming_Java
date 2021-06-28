package bonus;
import java.util.Objects;

/**
 * This is the Destination class used for creating destination objects.
 */
public class Destination {
    private String name;
    private int demand;

    /**
     * Constructor that takes the following parameters:
     * @param name: the name of the destination
     * @param demand: the demand that must be fulfilled
     */
    public Destination(String name, int demand) {
        this.name = name;
        this.demand = demand;
    }

    /**
     * Gen the name of the destination
     * @return the name of the destination
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the destination with:
     * @param name that must be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the quantity that must be given to the destination
     * @return the demand of the destination
     */
    public int getDemand() {
        return demand;
    }

    /**
     * Set the quantity that must be given to the destination
     * @param demand that must be set
     */
    public void setDemand(int demand) {
        this.demand = demand;
    }

    /**
     * Overloaded Object toString function
     * @return the Destination object in a string format
     */
    public String toString(){
        String answer="{\n";
        answer+="name: "+this.getName()+"\n";
        answer+="demand: "+this.getDemand()+"\n}\n";
        return answer;
    }

    /**
     * Overloaded Object equals function
     * @param o - object to be compared with
     * @return true if the objects are equal false otherwise
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Destination)) return false;
        Destination that = (Destination) o;
        return name.equals(that.name);
    }

    /**
     * Overloaded Object hashCode function
     * @return the object value after the hash function is applied
     */
    public int hashCode() {
        return Objects.hash(name, demand);
    }
}
