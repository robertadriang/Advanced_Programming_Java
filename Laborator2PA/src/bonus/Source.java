package bonus;

import java.util.Objects;

/**
 * This is the Source class used for creating source objects.
 */
public class Source {
    private String name;
    private int supply;
    private SourceType type;  // Enum type

    /**
     * Constructor for initializing the Source object
     * @param name of the object
     * @param supply value that can be send from the Source object
     * @param type of the Source object
     */
    public Source(String name, int supply, SourceType type) {
        this.name = name;
        this.supply = supply;
        this.type = type;
    }

    /**
     * Get function that
     * @return the name of the Source object
     */
    public String getName() {
        return name;
    }

    /**
     *Set function that sets the name of the Source object with the
     * @param name value passed
     */
    public void setName(String name) {
        this.name = new String(name);
    }

    /**
     * Get function that
     * @return the supply that can be send from the source object
     */
    public int getSupply() {
        return supply;
    }

    /**
     * Set function that sets the supply of the Source object with the
     * @param supply value passed
     */
    public void setSupply(int supply) {
        this.supply = supply;
    }

    /**
     * Get function that
     * @return the type of the source
     */
    public SourceType getType() {
        return type;
    }

    /**
     * Set functin that sets the type of the Source object with the
     * @param type value passed
     */
    public void setType(SourceType type) {
        this.type = type;
    }

    /**
     * Overloaded Object toString function
     * @return the Source object in a string format
     */
    public String toString(){
        String answer="{\n";
        answer+="name: "+this.getName()+"\n";
        answer+="type: "+this.getType()+"\n";
        answer+="supply: "+this.getSupply()+"\n}\n";
        return answer;
    }

    /**
     * Overloaded Object equals function
     * @param o - object to be compared with
     * @return true if the objects are equal false otherwise
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Source)) return false;
        Source source = (Source) o;
        return  name.equals(source.name) ;
    }

    /**
     * Overloaded Object hashCode function
     * @return the object value after the hash function is applied
     */
    public int hashCode() {
        return Objects.hash(name, supply, type);
    }
}
