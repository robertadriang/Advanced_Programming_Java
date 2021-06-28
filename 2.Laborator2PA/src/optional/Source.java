package optional;

import java.util.Objects;

public abstract class Source {
    private String name;
    private int supply;
    private String type;  // Enum type

    public Source(String name, int supply, String type) {
        this.name = name;
        this.supply = supply;
        this.type = new String(type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = new String(name);
    }

    public int getSupply() {
        return supply;
    }

    public void setSupply(int supply) {
        this.supply = supply;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /* Overriden .toString method for Source objects*/
    public String toString(){
        String answer="{\n";
        answer+="name: "+this.getName()+"\n";
        answer+="type: "+this.getType()+"\n";
        answer+="supply: "+this.getSupply()+"\n}\n";
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Source)) return false;
        Source source = (Source) o;
        return  name.equals(source.name) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, supply, type);
    }
}
