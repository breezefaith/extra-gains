package assignment2020.codeprovided.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import assignment2020.codeprovided.fitnesstracker.Participant;
import assignment2020.codeprovided.fitnesstracker.measurements.MeasurementType;
import assignment2020.gui.GUIPlotPanel;

/*
 * AbstractGUIPanel.java  	1.0  10/04/2020
 *
 * Copyright (c) University of Sheffield 2020
 */

/**
 * AbstractGUIPanel.java
 *
 * Abstract class designed to be extended. It represents the main panel that
 * will be visualised. This panel contains: 
 * - At the top: the GUI controls that
 * will determine which curves should be displayed in the central panel of the
 * main GUI 
 * - At the centre: the panel where the relevant curves should be drawn
 * using Java2D 
 * - At the bottom: the panel where general details about the
 * dataset and the curves plotted shown in the central panel.
 * 
 * @version 1.0 10/04/2020
 *
 * @author Ben Clegg / Islam Elgendy / Maria-Cruz Villa-Uriol
 */
public abstract class AbstractGUIPanel extends JPanel {

	/**
	 * generated serial version UID
	 */
	private static final long serialVersionUID = -2674518387270317168L;

	// defining all the labels to facilitate the what goes where in the GUI
	protected JLabel listParticipantsLabel = new JLabel("Participant:", SwingConstants.LEFT);
	protected JLabel measurementTypeLabel = new JLabel("Measurement Type:", SwingConstants.LEFT);
	protected JLabel trackerLabel = new JLabel("Tracker/s:", SwingConstants.LEFT);
	protected JLabel plotResultsLabel = new JLabel("Curve/s:", SwingConstants.LEFT);
	protected JLabel viewDetailsLabel = new JLabel("Displayed curve/s details:", SwingConstants.LEFT);
	protected JLabel datasetSummaryLabel = new JLabel("Dataset general summary:", SwingConstants.LEFT);

	// defining all the comboboxes that will be used to select the data that will be
	// visualised
	protected String[] participantsArray;
	protected String[] trackersArray;
	protected String[] measurementTypeArray;

	// defining the comboboxes used to select the data to be visualised
	protected JComboBox<String> comboListParticipants;
	protected JComboBox<String> comboListTrackers;
	protected JComboBox<String> comboListMeasurementType;
	protected JCheckBox checkboxGoldStandard = new JCheckBox("Gold Standard");

	// defining the button that will force an update of the GUI based on the
	// selected settings (topPanel)
	protected JButton displayButton = new JButton("Display");

	// defining the two JTextAreas that will show details about the dataset
	protected JTextArea datasetGeneralDetailsTextArea = new JTextArea(10, 30);
	protected JTextArea visualisedDataDetailsTextArea = new JTextArea(10, 30);

	// defining the panel were the curves will be displayed
	protected GUIPlotPanel curvesPanel;

	protected Collection<Participant> participants;

	/**
	 * loadParticipantsNames method - This method load the participant names into an
	 * array of String to be used in the participant list comboBox.
	 */
	protected void loadParticipantsNames() {
		int i = 0;
		participantsArray = new String[participants.size()];
		// TODO Please override this method in GUIPanel.java and delete this piece of
		// code
		if (participants.contains(null)) {
			for (Participant p : participants) {
				participantsArray[i] = "P" + i;
				i++;
			}
			return;
		}
		i = 0;
		// TODO When you override this method in GUIPanel.Java, keep the remaining of
		// this method
		for (Participant p : participants) {
			participantsArray[i] = p.getName();
			i++;
		}
		System.out.println(Arrays.toString(participantsArray));
	}

	/**
	 * loadTrackers method - This method load the tracker names into an array of
	 * String to be used in the trackers list comboBox.
	 */
	protected void loadTrackers() {
		// TODO Please override this method in GUIPanel.java and delete this piece of
		// code
		if (participants.contains(null)) {
			trackersArray = new String[6];
			trackersArray[0] = "all";
			trackersArray[1] = "FT1";
			trackersArray[2] = "FT2";
			trackersArray[3] = "FT3";
			trackersArray[4] = "FT4";
			trackersArray[5] = "FT5";
			return;
		}
		// TODO When you override this method in GUIPanel.Java, keep the remaining of
		// this method as it will properly populate the combobox used to select which
		// tracker to be visualised
		Collection<String> trackerNames = ((Participant) participants.toArray()[0]).getAllTrackerNames();
		trackerNames.remove("GS");
		trackersArray = new String[trackerNames.size() + 1];
		trackersArray[0] = "all";
		int i = 1;
		for (String trackerName : trackerNames) {
			trackersArray[i] = trackerName;
			i++;
		}
		System.out.println(Arrays.toString(trackersArray));
	}

	/**
	 * loadMeasurementTypes method - This method load the measurement types into an
	 * array of String to be used in the measurement types list comboBox.
	 */
	private void loadMeasurementTypes() {
		measurementTypeArray = new String[MeasurementType.values().length];
		int i = 0;
		for (MeasurementType t : MeasurementType.values()) {
			measurementTypeArray[i] = t.getMeasurementName();
			i++;
		}
		System.out.println(Arrays.toString(measurementTypeArray));
	}

	/**
	 * setup method - This method initializes some of the GUI components, and sets
	 * out the layout of AbstractGUIPanel.
	 */
	private void setup() {
		// loading data to set up combo boxes (participants, trackers and measurement
		// types) and combo boxes set up
		loadParticipantsNames();
		comboListParticipants = new JComboBox<>(participantsArray);
		comboListParticipants.setName("participants");

		loadTrackers();
		comboListTrackers = new JComboBox<>(trackersArray);
		comboListTrackers.setName("trackers");

		loadMeasurementTypes();
		comboListMeasurementType = new JComboBox<>(measurementTypeArray);
		comboListMeasurementType.setName("measurementTypes");

		// basic set up of remaining panels (curves, dataset details, and visualised
		// data details)
		curvesPanel = new GUIPlotPanel(this);
		curvesPanel.setName("curvesPanel");
		datasetGeneralDetailsTextArea.setName("datasetGeneralDetails");
		visualisedDataDetailsTextArea.setName("visualisedDataDetails");

		// building the GUI using a combination of JPanels and a range of LayoutManagers
		// to get a compelling GUI
		this.setLayout(new BorderLayout());
	}

	/**
	 * topPanelCreation method - This method creates the top part of the GUI, which
	 * contains the type and filter selectors that will control the data that will
	 * be visualised.
	 * 
	 * @param topControlPanel the panel to add the GUI components in it and then
	 *                        added to the main frame.
	 */
	private void topPanelCreation(JPanel topControlPanel) {
		JPanel typeSelectorPanel = new JPanel();
		typeSelectorPanel.setLayout(new FlowLayout());
		typeSelectorPanel.add(listParticipantsLabel);
		typeSelectorPanel.add(comboListParticipants);
		typeSelectorPanel.add(trackerLabel);
		typeSelectorPanel.add(comboListTrackers);

		JPanel filterBuilderPanel = new JPanel();
		filterBuilderPanel.setLayout(new FlowLayout());
		filterBuilderPanel.add(measurementTypeLabel);
		filterBuilderPanel.add(comboListMeasurementType);
		filterBuilderPanel.add(checkboxGoldStandard);
		filterBuilderPanel.add(displayButton);

		topControlPanel.add(typeSelectorPanel);
		topControlPanel.add(filterBuilderPanel);

		this.add(topControlPanel, BorderLayout.NORTH);
	}

	/**
	 * centrePanelCreation method - This method creates the central part of the GUI,
	 * which contains the curve plot to be drawn displaying the results in a graph.
	 * 
	 * @param fitnessDataPlotPanel the panel to add the GUI components in it and
	 *                             then added to the main frame.
	 */
	private void centrePanelCreation(JPanel fitnessDataPlotPanel) {
		fitnessDataPlotPanel.add(plotResultsLabel, BorderLayout.NORTH);
		fitnessDataPlotPanel.add(curvesPanel, BorderLayout.CENTER);

		this.add(fitnessDataPlotPanel, BorderLayout.CENTER);

	}

	/**
	 * bottomPanelCreation method - This method creates the bottom part of the GUI,
	 * which contains the dataset summary and details.
	 * 
	 * @param bottomControlPanel the panel to add the GUI components in it and then
	 *                           added to the main frame.
	 */
	private void bottomPanelCreation(JPanel bottomControlPanel) {
		// Setting up the left side panel
		JPanel leftTextPanel = new JPanel();
		leftTextPanel.setLayout(new BorderLayout());

		datasetGeneralDetailsTextArea.setLineWrap(true);
		// providing a default message that should be overwritten
		datasetGeneralDetailsTextArea.setText(
				"Default text:\nThis panel should display general details about the dataset, e.g. number of participants, number of female vs male, age range, number of trackers... It should never change its contents. Feel free to innovate!");
		JScrollPane leftTextScrollPane = new JScrollPane(datasetGeneralDetailsTextArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		leftTextPanel.add(datasetSummaryLabel, BorderLayout.NORTH);
		leftTextPanel.add(leftTextScrollPane, BorderLayout.CENTER);

		// Setting up the right side panel
		JPanel rightTextPanel = new JPanel(new FlowLayout());
		rightTextPanel.setLayout(new BorderLayout());
		visualisedDataDetailsTextArea.setLineWrap(true);
		// providing a default message that should be overwritten
		visualisedDataDetailsTextArea.setText(
				"Default text:\nThis panel should display the details about the curves visualised, e.g. participant ID, female or male, age, visualised tracker/s... The contents displayed will depend on the selected controls in the top panel. Feel free to innovate!");

		JScrollPane rightTextScrollPane = new JScrollPane(visualisedDataDetailsTextArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		rightTextPanel.add(viewDetailsLabel, BorderLayout.NORTH);
		rightTextPanel.add(rightTextScrollPane, BorderLayout.CENTER);

		// Adding the left and right panels to the main text area panel
		bottomControlPanel.add(leftTextPanel);
		bottomControlPanel.add(rightTextPanel);

		this.add(bottomControlPanel, BorderLayout.SOUTH);
	}

	/**
	 * constructor
	 */
	public AbstractGUIPanel(Collection<Participant> participants) {
		this.participants = participants;

		// setting up components in GUI, and the layout of the frame
		setup();

		// Creating the top panel area
		JPanel topVisualizationControlPanel = new JPanel();
		topVisualizationControlPanel.setLayout(new GridLayout(2, 1));
		topPanelCreation(topVisualizationControlPanel);

		// Creating the centre panel area
		JPanel centreCurvesPanel = new JPanel();
		centreCurvesPanel.setLayout(new BorderLayout());
		centrePanelCreation(centreCurvesPanel);

		// Creating the bottom panel area
		JPanel bottomDatasetDetailsPanel = new JPanel();
		bottomDatasetDetailsPanel.setLayout(new GridLayout(1, 2));
		bottomPanelCreation(bottomDatasetDetailsPanel);

		// adding the listeners, you will need to implement this method to register the
		// events generated
		// by the GUI components that will be expecting a change in the results being
		// displayed by the GUI
		this.addListeners();
	}

	/**
	 * addListeners method - adds relevant actionListeners to the GUI components (as
	 * required). You will need to listen (at least) to the Display button.
	 */
	public abstract void addListeners();

	/**
	 * getSelectedParticipant method - returns a String with name of the participant
	 * selected with the corresponding combo box.
	 */
	public abstract String getSelectedParticipantName();

	/**
	 * getSelectedTrackers method - returns a String indicating which trackers were
	 * selected with the corresponding combo box. Tip: The return is a String
	 * because if all trackers were to be visualised, you could use the
	 * Tracker.FILTER_ANY constant to specify that.
	 */
	public abstract String getSelectedTrackersNames();

	/**
	 * getSelectedMeasurementType method - returns the measurement type selected
	 * with the corresponding combo box.
	 */
	public abstract MeasurementType getSelectedMeasurementType();

	/**
	 * isGoldStandardSelected method - returns if the gold standard has been
	 * selected or not with the corresponding check box. button.
	 */
	public abstract boolean isGoldStandardSelected();

}
