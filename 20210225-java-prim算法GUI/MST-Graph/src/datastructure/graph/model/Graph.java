package datastructure.graph.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Graph
 */
public class Graph {

    List<Position> positions = new ArrayList<>();
    List<Edge> edges = new ArrayList<>();

    /**
     * add a vertex
     * @param x
     * @param y
     */
    public void addVertex(int x, int y) {
        positions.add(new Position(x, y));
    }

    /**
     * connect two vertices
     * @param vertex1 the serial number of vertex1
     * @param vertex2 the serial number of vertex2
     */
    public void connectVertices(int vertex1, int vertex2) {
        if (this.getEdge(vertex1, vertex2) == null) {
            Position p1 = this.getPosition(vertex1);
            Position p2 = this.getPosition(vertex2);
            int x1 = p1.getX();
            int y1 = p1.getY();
            int x2 = p2.getX();
            int y2 = p2.getY();
            int xRange = x1 > x2 ? x1 - x2 : x2 - x1;
            int yRange = y1 > y2 ? y1 - y2 : y2 - y1;
            double weight = Math.sqrt(Math.pow(xRange, 2) + Math.pow(yRange, 2));
            edges.add(new Edge(vertex1, vertex2, weight));
        }
    }

    public Position getPosition(int vertex) {
        return positions.get(vertex);
    }

    public int getNumberOfVertices() {
        return positions.size();
    }

    public int getNumberOfEdges() {
        return edges.size();
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Edge getEdge(int vertex1, int vertex2) {
        for (Edge edge : edges) {
            if ((edge.getFromVertex() == vertex1 && edge.getToVertex() == vertex2)
                    || (edge.getToVertex() == vertex1 && edge.getFromVertex() == vertex2)) {
                return edge;
            }
        }
        return null;
    }

    public List<Integer> getNeighbours(int vertex) {
        List<Integer> connectedVertices = new ArrayList<>();

        for (Edge edge : edges) {
            if (edge.getFromVertex() == vertex) connectedVertices.add(edge.getToVertex());
            else if (edge.getToVertex() == vertex) connectedVertices.add(edge.getFromVertex());
        }

        return connectedVertices;
    }

    public List<Edge> getEdgesConnectedTo(int vertex) {
        List<Edge> edges = new ArrayList<>();

        for (Edge edge : this.edges) {
            if (edge.getFromVertex() == vertex) edges.add(edge);
            else if (edge.getToVertex() == vertex) edges.add(edge);
        }

        return edges;
    }
}
