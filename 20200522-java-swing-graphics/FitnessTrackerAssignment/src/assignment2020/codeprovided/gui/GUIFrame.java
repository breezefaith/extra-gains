package assignment2020.codeprovided.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Collection;

import javax.swing.JFrame;

import assignment2020.codeprovided.fitnesstracker.Participant;
import assignment2020.gui.GUIPanel;

/*
 * GUIFrame.java  	1.0  10/04/2020
 *
 * Copyright (c) University of Sheffield 2020
 */

/**
 * GUIFrame.java
 *
 * A class to represent the main JFrame the Fitness activity tracker data will
 * be shown.
 * 
 * @version 1.0 10/04/2020
 *
 * @author Ben Clegg / Islam Elgendy / Maria-Cruz Villa-Uriol
 */
public class GUIFrame extends JFrame {

	/**
	 * Generated Serial version UID
	 */
	private static final long serialVersionUID = -7326186207066804135L;

	public GUIFrame(Collection<Participant> participants) {
		AbstractGUIPanel explorerPanel = (AbstractGUIPanel) new GUIPanel(participants);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenDimensions = toolkit.getScreenSize();

		setTitle("Fitness Activity Tracker Data Explorer");
		add(explorerPanel);
		setPreferredSize(screenDimensions);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		pack();

	}

}
