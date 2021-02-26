package datastructure.graph.algorithm;

import datastructure.graph.model.Edge;

import java.util.List;

/**
 * a structure for the result of prim
 */
public class MSTResult {
    List<Integer> vertices;
    List<Edge> edges;

    public List<Integer> getVertices() {
        return vertices;
    }

    public void setVertices(List<Integer> vertices) {
        this.vertices = vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}
