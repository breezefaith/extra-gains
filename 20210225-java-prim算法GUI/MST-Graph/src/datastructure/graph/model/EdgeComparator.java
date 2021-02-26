package datastructure.graph.model;

import java.util.Comparator;

/**
 * Custom comparator for Edge
 */
public class EdgeComparator implements Comparator<Edge> {

    public int compare(Edge o1, Edge o2) {
        return (int) (o1.getWeight() - o2.getWeight());
    }

}
