package datastructure.graph.model;

/**
 * Edge
 */
public class Edge {

    private int fromVertex;
    private int toVertex;
    private double weight;

    public Edge(int fromVertex, int toVertex, double weight) {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        this.weight = weight;
    }

    public int getFromVertex() {
        return fromVertex;
    }

    public int getToVertex() {
        return toVertex;
    }

    public double getWeight() {
        return weight;
    }

}