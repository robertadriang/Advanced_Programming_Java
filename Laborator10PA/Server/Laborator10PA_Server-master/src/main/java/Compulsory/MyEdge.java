package Compulsory;

import org.jgrapht.graph.DefaultWeightedEdge;

public class MyEdge extends DefaultWeightedEdge {
    @Override
    public String toString() {
        return Double.toString(getWeight());
    }
}
