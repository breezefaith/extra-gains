package datastructure.graph.algorithm;

import datastructure.graph.model.Edge;
import datastructure.graph.model.EdgeComparator;
import datastructure.graph.model.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The process of PRIM algorithm is as follows:
 * <p>
 * Assume that N= (V,{E}) is a connected graph, and Te is the set of edges in the minimum spanning tree on N. The algorithm
 * starts from U={u0} (u0∈V), TE={}.
 * <p>
 * Repeat the following operations:
 * <p>
 * (1) In all the edges (u, v) ∈E of u∈U, v∈V-U, find the least cost edge (u0, v0) to merge into the set Te, and v0 to
 * merge into u until u =V.
 * (2) In this case, there must be n-1 edges in TE, then T= (V, {TE}) is the minimum spanning tree of n.
 * <p>
 */
public class PrimExecutor {
    /**
     * Execute the prim algorithm and return the result
     *
     * @param vertice start point
     * @return
     */
    public MSTResult execute(Graph graph, int vertice) {
        if (graph.getNumberOfVertices() < 1) {
            return null;
        }

        // put the result edges into bestEdges in order
        List<Edge> bestEdges = new ArrayList<>();

        // put the result vertices into bestEdges in order
        List<Integer> usedVertices = new ArrayList<>();
        usedVertices.add(vertice);

        List<Edge> neighbours = new ArrayList<>();

        while (usedVertices.size() < graph.getNumberOfVertices()) {
            neighbours.addAll(graph.getEdgesConnectedTo(usedVertices.get(usedVertices.size() - 1)));
            Collections.sort(neighbours, new EdgeComparator());

            boolean hasFoundEdge = false;

            for (Edge edge : neighbours) {
                if (usedVertices.contains(edge.getFromVertex()) && !usedVertices.contains(edge.getToVertex())) {
                    bestEdges.add(edge);
                    usedVertices.add(edge.getToVertex());
                    hasFoundEdge = true;
                    break;
                } else if (usedVertices.contains(edge.getToVertex()) && !usedVertices.contains(edge.getFromVertex())) {
                    bestEdges.add(edge);
                    usedVertices.add(edge.getFromVertex());
                    hasFoundEdge = true;
                    break;
                }
            }

            if (!hasFoundEdge) {
                break;
            }
        }

        MSTResult res = new MSTResult();
        res.setVertices(usedVertices);
        res.setEdges(bestEdges);
        return res;
    }
}
