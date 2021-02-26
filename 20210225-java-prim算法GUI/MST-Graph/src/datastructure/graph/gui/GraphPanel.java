package datastructure.graph.gui;

import datastructure.graph.model.Edge;
import datastructure.graph.model.Graph;
import datastructure.graph.model.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphPanel extends JPanel {
    private static final int POINT_SIZE = 8;

    private Graph graph;
    private List<Edge> edges;
    private List<Integer> vertices;

    private boolean showLineWeight = true;

    public GraphPanel() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    /**
     * draw the graph
     */
    public void draw() {
        this.paintComponent(this.getGraphics());
    }

    /**
     * only draw the vertices
     *
     * @param vertices the list of vertices
     * @param sleep    if enabling the Thread.sleep()
     */
    public void drawVertices(List<Integer> vertices, boolean sleep) {
        try {
            this.vertices.clear();
            this.vertices.addAll(vertices);
            this.paintComponent(this.getGraphics());
            if (sleep) {
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * only draw the edges
     *
     * @param edges the list of edges
     * @param sleep if enabling the Thread.sleep()
     */
    public void drawEdges(List<Edge> edges, boolean sleep) {
        try {
            this.edges.clear();
            if (sleep) {
                for (Edge edge : edges) {
                    this.edges.add(edge);
                    this.paintComponent(this.getGraphics());
                    Thread.sleep(300);
                }
            } else {
                this.edges.addAll(edges);
                this.paintComponent(this.getGraphics());
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        /**
         * draw vertices and edges
         */
        if (this.vertices == null || this.vertices.size() == 0 || this.edges == null || this.edges.size() == 0) {
            return;
        }
        for (Integer i : this.vertices) {
            Position vertexPosition = this.graph.getPosition(i);
            g.setColor(Color.BLACK);
            g.fillOval(vertexPosition.getX() - POINT_SIZE / 2, vertexPosition.getY() - POINT_SIZE / 2, POINT_SIZE, POINT_SIZE);
            g.setColor(Color.BLUE);
            g.drawString(Integer.toString(i), vertexPosition.getX(), vertexPosition.getY() - POINT_SIZE / 2);
        }
        g.setColor(Color.red);
        for (Edge edge : this.edges) {
            Position p1 = this.graph.getPosition(edge.getFromVertex());
            Position p2 = this.graph.getPosition(edge.getToVertex());
            int x1 = p1.getX();
            int y1 = p1.getY();
            int x2 = p2.getX();
            int y2 = p2.getY();
            g.drawLine(x1, y1, x2, y2);

            if (this.showLineWeight) {
                String weight = String.valueOf(edge.getWeight());
                int centreX = 0;
                int centreY = 0;
                if (x1 > x2) {
                    centreX = x2 + ((x1 - x2) / 2);
                } else {
                    centreX = x1 + ((x2 - x1) / 2);
                }
                if (y1 > y2) {
                    centreY = y2 + ((y1 - y2) / 2);
                } else {
                    centreY = y1 + ((y2 - y1) / 2);
                }
                g.drawChars(weight.toCharArray(), 0, weight.indexOf("."), centreX, centreY);
            }
        }
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
        this.edges.clear();
        this.vertices.clear();
    }

    public boolean isShowLineWeight() {
        return showLineWeight;
    }

    public void setShowLineWeight(boolean showLineWeight) {
        this.showLineWeight = showLineWeight;
    }
}
