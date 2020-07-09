package assignment2020;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import assignment2020.codeprovided.fitnesstracker.Participant;
import assignment2020.codeprovided.gui.GUIFrame;
import assignment2020.dataloading.DataLoader;
import assignment2020.handoutquestions.FitnessQuestions;

//import assignment2020.modelsolution.dataloading.DataLoader;
//import assignment2020.modelsolution.handoutquestions.FitnessQuestions;
//import assignment2020.modelsolution.gui.GUIFrame;

/*
 * Assignment.java  	1.0  10/04/2020
 *
 * Copyright (c) University of Sheffield 2020
 */

/**
 * Assignment.java
 *
 * Main assignment class that provides all the required functionality to run this
 * assignment. This class should NOT be modified and should be kept as it is.
 * 
 * @version 1.0 10/04/2020
 *
 * @author Ben Clegg / Islam Elgendy / Maria-Cruz Villa-Uriol
 */
public class Assignment {

	/**
	 * Main method
	 * 
	 * @param args should have one argument, the path to the directory containing
	 *             the data files. In Eclipse, you can run with arguments by
	 *             choosing "Run" -> "Run Configurations..." then selecting the
	 *             "Arguments" tab.
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("The path to the data folder (e.g. resources/data) is not provided.");
		}
		Path dataFolder = Paths.get(args[0]);
		try {
			Assignment assignment = new Assignment(dataFolder);
		} catch (IOException ioEx) {
			System.err.println("Could not list files in " + dataFolder);
			System.err.println("The provided path may be incorrect.");
		}
	}

	/**
	 * Run the assignment.
	 * 
	 * @param dataFolder the path the directory containing the data files.
	 */
	public Assignment(Path dataFolder) throws IOException {
		// Load participants
		DataLoader dataLoader = new DataLoader();
		Collection<Participant> participants = dataLoader.loadAllParticipants(dataFolder);

		// Questions
		FitnessQuestions questions = new FitnessQuestions(participants);
		System.out.println(questions.toString());

		// GUI
		JFrame explorerGUI = new GUIFrame(participants);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				explorerGUI.setVisible(true);
			}
		});
	}
}
