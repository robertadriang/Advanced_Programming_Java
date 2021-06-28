package ro.uaic.info;

public class Source {
   private String name;
   private int supply;
   private SourceType type;  // Enum type

    public Source(String name, int supply, SourceType type) {
        this.name = name;
        this.supply = supply;
        this.type = type;
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

    public SourceType getType() {
        return type;
    }

    public void setType(SourceType type) {
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
}
