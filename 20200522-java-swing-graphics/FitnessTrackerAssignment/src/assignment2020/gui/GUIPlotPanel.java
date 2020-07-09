package assignment2020.gui;

import assignment2020.codeprovided.fitnesstracker.Participant;
import assignment2020.codeprovided.fitnesstracker.Tracker;
import assignment2020.codeprovided.fitnesstracker.measurements.Measurement;
import assignment2020.codeprovided.fitnesstracker.measurements.MeasurementType;
import assignment2020.codeprovided.gui.AbstractGUIPanel;
import assignment2020.codeprovided.gui.BasicGUIPlotPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A class to represent the GUI panel where the selected curves will be plot
 * using Java 2D. You are expected to write this class.
 */

public class GUIPlotPanel extends BasicGUIPlotPanel {

    /**
     * Generated Serial version UID
     */
    private static final long serialVersionUID = -1482643158587603732L;

    public GUIPlotPanel(AbstractGUIPanel parentGUIPanel) {
        // TODO
        super(parentGUIPanel);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // TODO
        // Tip 1: Here you will need to access the list of participants,
        // and the GUI selections (combo boxes and checkbox)
        // See a practical example about how you could obtain the state of the gold
        // standard checkbox
        String pname = this.parentGUIPanel.getSelectedParticipantName();
        String tname = this.parentGUIPanel.getSelectedTrackersNames();
        MeasurementType type = this.parentGUIPanel.getSelectedMeasurementType();
        boolean goldStandardSelected = this.parentGUIPanel.isGoldStandardSelected();

        // Tip 2: The classes Participant and Tracker have a wide range of methods
        // that will be vital to get the data points of the curves that you need to plot
        // in this panel
        Participant p = getParticipant(pname);
        Map<String, Collection<Measurement>> map = getData(p, tname, type, goldStandardSelected);
        printDetail(p, map);

        // Tip 3: See how you can draw a line. Please, replace.
        drawLine(g, map, type);
        g.setColor(Color.BLACK);
    }

    private Participant getParticipant(String pname) {
        for (Participant p : ((GUIPanel) parentGUIPanel).getParticipants()) {
            if (pname.equals(p.getName())) {
                return p;
            }
        }
        return null;
    }

    private void printDetail(Participant p, Map<String, Collection<Measurement>> map) {
        JTextArea textArea = ((GUIPanel) parentGUIPanel).getVisualisedDetailArea();
        textArea.setText("Name: " + p.getName() + "\n");
        textArea.append("Gender: " + p.getGender() + "\n");
        textArea.append("Age: " + p.getAge() + "\n");
        textArea.append("Visualised trackers: ");

        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        int ci = 0;
        for (Map.Entry<String, Collection<Measurement>> entry : map.entrySet()) {
            if (entry.getValue().size() > 0) {
                sb1.append(entry.getKey() + ", ");

                sb2.append(entry.getKey() + "(" + colorsText[ci++] + ", " + entry.getValue().size() + "): ");
                for (Measurement m : entry.getValue()) {
                    sb2.append(String.format("(%d, %.2f), ", m.getCount(), m.getValue().doubleValue()));
                }
                sb2.replace(sb2.length() - 2, sb2.length(), "\n\n");
            }
        }
        textArea.append(sb1.substring(0, sb1.length() - 2) + "\n");
        textArea.append(sb2.toString());
    }

    private Map<String, Collection<Measurement>> getData(Participant p, String tname, MeasurementType type, boolean goldStandardSelected) {
        Map<String, Collection<Measurement>> map = new HashMap<>();

        if (tname.equals("all")) {
            for (Tracker t : p.getAllTrackers()) {
                if ("GS".equals(t.getName()) && goldStandardSelected == false) {
                    continue;
                }
                map.put(t.getName(), t.getMeasurementsForType(type));
            }
        } else {
            if (goldStandardSelected) {
                map.put("GS", p.getTracker("GS").getMeasurementsForType(type));
            }
            map.put(tname, p.getTracker(tname).getMeasurementsForType(type));
        }

        return map;
    }

    private Color[] colors = {Color.BLACK, Color.BLUE, Color.GREEN, Color.RED, Color.ORANGE, Color.PINK};
    private String[] colorsText = {"Black", "Blue", "Green", "Red", "Orange", "Pink"};

    public void drawLine(Graphics g, Map<String, Collection<Measurement>> map, MeasurementType type) {
        switch (type) {
            case HEART_RATE:
                drawHR(g, map);
                break;
            case STEPS:
                drawSteps(g, map);
                break;
            case DISTANCE:
                drawDistance(g, map);
                break;
            case ENERGY_EXPENDITURE:
                drawEE(g, map);
                break;
        }
    }

    private void drawDistance(Graphics g, Map<String, Collection<Measurement>> map) {
        Dimension d = new Dimension((int) (getWidth() * 0.9), (int) (getHeight() * 0.9));
        Graphics2D g2 = (Graphics2D) g;
        int ci = 0;
        int maxY = 10000;

        for (Map.Entry<String, Collection<Measurement>> entry : map.entrySet()) {
            Double curX = d.getWidth() * 0.05;
            Double curY = d.getHeight();
            g2.setColor(colors[ci++]);

            for (Measurement m : entry.getValue()) {
                if (curX == null && curY == null) {
                    curX = 1.0 * m.getCount() / entry.getValue().size() * d.getWidth() + d.getWidth() * 0.05;
                    curY = d.getHeight() - m.getValue().doubleValue() / maxY * d.getHeight();
                    continue;
                }
                Double x = 1.0 * m.getCount() / entry.getValue().size() * d.getWidth() + d.getWidth() * 0.05;
                Double y = d.getHeight() - m.getValue().doubleValue() / maxY * d.getHeight();
                g2.fillOval(curX.intValue(), curY.intValue(), 5, 5);
                Line2D l = new Line2D.Double(curX, curY, x, y);
                g2.draw(l);
                curX = x;
                curY = y;
            }
            if (curX != null && curY != null)
                g2.fillOval(curX.intValue(), curY.intValue(), 5, 5);
        }
    }

    private void drawHR(Graphics g, Map<String, Collection<Measurement>> map) {
        Dimension d = new Dimension((int) (getWidth() * 0.9), (int) (getHeight() * 0.9));
        Graphics2D g2 = (Graphics2D) g;
        int ci = 0;
        int maxY = 300;

        for (Map.Entry<String, Collection<Measurement>> entry : map.entrySet()) {
            Double curX = null;
            Double curY = null;
            g2.setColor(colors[ci++]);

            for (Measurement m : entry.getValue()) {
                if (curX == null && curY == null) {
                    curX = 1.0 * m.getCount() / entry.getValue().size() * d.getWidth() + d.getWidth() * 0.05;
                    curY = d.getHeight() - m.getValue().doubleValue() / maxY * d.getHeight();
                    continue;
                }
                Double x = 1.0 * m.getCount() / entry.getValue().size() * d.getWidth() + d.getWidth() * 0.05;
                Double y = d.getHeight() - m.getValue().doubleValue() / maxY * d.getHeight();

                g2.fillOval(curX.intValue(), curY.intValue(), 5, 5);
                Line2D l = new Line2D.Double(curX, curY, x, y);
                g2.draw(l);
                curX = x;
                curY = y;
            }
            if (curX != null && curY != null)
                g2.fillOval(curX.intValue(), curY.intValue(), 5, 5);
        }
    }

    private void drawSteps(Graphics g, Map<String, Collection<Measurement>> map) {
        Dimension d = new Dimension((int) (getWidth() * 0.9), (int) (getHeight() * 0.9));
        Graphics2D g2 = (Graphics2D) g;
        int ci = 0;
        int maxY = 10000;

        for (Map.Entry<String, Collection<Measurement>> entry : map.entrySet()) {
            Double curX = null;
            Double curY = null;
            g2.setColor(colors[ci++]);

            for (Measurement m : entry.getValue()) {
                if (curX == null && curY == null) {
                    curX = 1.0 * m.getCount() / entry.getValue().size() * d.getWidth() + d.getWidth() * 0.05;
                    curY = d.getHeight() - m.getValue().doubleValue() / maxY * d.getHeight();
                    continue;
                }
                Double x = 1.0 * m.getCount() / entry.getValue().size() * d.getWidth() + d.getWidth() * 0.05;
                Double y = d.getHeight() - m.getValue().doubleValue() / maxY * d.getHeight();
                g2.fillOval(curX.intValue(), curY.intValue(), 5, 5);
                Line2D l = new Line2D.Double(curX, curY, x, y);
                g2.draw(l);
                curX = x;
                curY = y;
            }
            if (curX != null && curY != null)
                g2.fillOval(curX.intValue(), curY.intValue(), 5, 5);
        }
    }

    private void drawEE(Graphics g, Map<String, Collection<Measurement>> map) {
        Dimension d = new Dimension((int) (getWidth() * 0.9), (int) (getHeight() * 0.9));
        Graphics2D g2 = (Graphics2D) g;
        int maxY = 500;
        int ci = 0;

        for (Map.Entry<String, Collection<Measurement>> entry : map.entrySet()) {
            Double curX = null;
            Double curY = null;
            g2.setColor(colors[ci++]);

            for (Measurement m : entry.getValue()) {
                if (curX == null && curY == null) {
                    curX = 1.0 * m.getCount() / entry.getValue().size() * d.getWidth() + d.getWidth() * 0.05;
                    curY = d.getHeight() - m.getValue().doubleValue() / maxY * d.getHeight();
                    continue;
                }
                Double x = 1.0 * m.getCount() / entry.getValue().size() * d.getWidth() + d.getWidth() * 0.05;
                Double y = d.getHeight() - m.getValue().doubleValue() / maxY * d.getHeight();
                g2.fillOval(curX.intValue(), curY.intValue(), 5, 5);
                Line2D l = new Line2D.Double(curX, curY, x, y);
                g2.draw(l);
                curX = x;
                curY = y;
            }
            if (curX != null && curY != null)
                g2.fillOval(curX.intValue(), curY.intValue(), 5, 5);
        }
    }
}
