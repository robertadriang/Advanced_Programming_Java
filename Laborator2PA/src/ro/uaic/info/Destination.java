package ro.uaic.info;

public class Destination {
    private String name;
    private int demand;

    public Destination(String name, int demand) {
        this.name = name;
        this.demand = demand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    /* Overriden .toString method for Destination objects*/
    public String toString(){
        String answer="{\n";
        answer+="name: "+this.getName()+"\n";
        answer+="demand: "+this.getDemand()+"\n}\n";
        return answer;
    }
}
