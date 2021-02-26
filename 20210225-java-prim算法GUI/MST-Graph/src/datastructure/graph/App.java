package datastructure.graph;

import datastructure.graph.gui.MainPanel;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Application entry
 */
public class App {
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("origin.Graph");
                MainPanel panel = new MainPanel();
                frame.setContentPane(panel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                panel.display();
            }
        });
    }
}
