package datastructure.graph.gui;

import datastructure.graph.algorithm.PrimExecutor;
import datastructure.graph.algorithm.MSTResult;
import datastructure.graph.model.Edge;
import datastructure.graph.model.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainPanel extends JPanel {
    public static final int PANEL_WIDTH = 800;
    public static final int PANEL_HEIGHT = 600;
    private static final int POINT_SIZE = 8;

    private Graph graph;
    private final PrimExecutor primExecutor = new PrimExecutor();

    private JTextField textStart;
    private JButton btnPrim;
    private JButton btnReset;
    private JCheckBox chkShowWeight;
    private JPanel panelToolbar;
    private GraphPanel panelGraph;

    private final Random random = new Random();

    public MainPanel() {
        // initialize components
        this.initComponents();
    }

    /**
     * generate a graph and draw it
     */
    public void display() {
        //set a random number for vertices（2~20）
        int vertices = this.random.nextInt(19) + 2;

        //creat graph
        this.graph = this.generateGraph(vertices);
        this.panelGraph.setGraph(this.graph);

        //set a random start point
        this.textStart.setText(String.valueOf(this.random.nextInt(this.graph.getNumberOfVertices())));

        List<Integer> vs = new ArrayList<>();
        for (int i = 0; i < this.graph.getNumberOfVertices(); i++) {
            vs.add(i);
        }
        this.panelGraph.drawVertices(vs, false);
        List<Edge> edges = this.graph.getEdges();
        this.panelGraph.drawEdges(edges, false);
    }

    /**
     * initialize components
     */
    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        this.textStart = new JTextField();
        this.textStart.setColumns(10);

        this.btnPrim = new JButton("Prim");
        btnPrim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                clickPrim();
            }
        });

        this.btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                clickReset();
            }
        });

        this.chkShowWeight = new JCheckBox("Show Weight", true);
        chkShowWeight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                showWeightChanged();
            }
        });

        this.panelToolbar = new JPanel();
        this.panelToolbar.add(new JLabel(new String("Start")));
        this.panelToolbar.add(textStart);
        this.panelToolbar.add(btnPrim);
        this.panelToolbar.add(btnReset);
        this.panelToolbar.add(chkShowWeight);

        this.panelGraph = new GraphPanel();

        this.add("North", this.panelToolbar);
        this.add("Center", this.panelGraph);
    }

    /**
     * action for btnPrim
     */
    private void clickPrim() {
        int start = Integer.valueOf(this.textStart.getText());
        if (start < 0 || start >= this.graph.getNumberOfVertices()) {
            JOptionPane.showMessageDialog(this, "Invalid Start Point");
            return;
        }
        MSTResult result = this.primExecutor.execute(this.graph, start);
        this.panelGraph.drawEdges(result.getEdges(), true);
    }

    /**
     * action for btnReset
     */
    private void clickReset() {
        this.display();
    }

    /**
     * action for changing the value of chkShowWeight
     */
    private void showWeightChanged() {
        //update the showWeight value
        this.panelGraph.setShowLineWeight(!this.panelGraph.isShowLineWeight());
        //redraw the graph
        this.panelGraph.draw();
    }

    /**
     * generate a random graph
     *
     * @param vertices the number of vertices
     * @return
     */
    private Graph generateGraph(int vertices) {
        Graph graph = new Graph();

        for (int i = 0; i < vertices; i++) {
            graph.addVertex(this.random.nextInt(this.panelGraph.getWidth() + 1), this.random.nextInt(this.panelGraph.getHeight() + 1));
        }

        for (int i = 0; i < vertices; i++) {
            for (int j = i + 1; j < vertices; j++) {
                graph.connectVertices(i, j);
            }
        }

        return graph;
    }
}
