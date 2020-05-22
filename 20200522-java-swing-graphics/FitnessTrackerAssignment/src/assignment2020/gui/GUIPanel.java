package assignment2020.gui;

import assignment2020.codeprovided.fitnesstracker.Participant;
import assignment2020.codeprovided.fitnesstracker.measurements.MeasurementType;
import assignment2020.codeprovided.gui.AbstractGUIPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class GUIPanel extends AbstractGUIPanel {

    private static final long serialVersionUID = -1257936627636425453L;

    public GUIPanel(Collection<Participant> participants) {
        super(participants);
        // TODO Auto-generated constructor stub
        datasetGeneralDetailsTextArea.setText("Number of participants: " + participants.size() + "\n");
        int fc = 0, mc = 0;
        int minAge = Integer.MAX_VALUE, maxAge = Integer.MIN_VALUE;
        for (Participant p : participants) {
            if ("M".equals(p.getGender().toUpperCase())) {
                mc++;
            } else if ("F".equals(p.getGender().toUpperCase())) {
                fc++;
            }
            if (minAge > p.getAge()) {
                minAge = p.getAge();
            }
            if (maxAge < p.getAge()) {
                maxAge = p.getAge();
            }
        }
        datasetGeneralDetailsTextArea.append("Number of male: " + mc + "\n");
        datasetGeneralDetailsTextArea.append("Number of female: " + fc + "\n");
        datasetGeneralDetailsTextArea.append("Range of age: " + minAge + "~" + maxAge + "\n");
    }

    @Override
    public void addListeners() {
        // TODO add the event handlers here
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Graphics2D g = (Graphics2D) curvesPanel.getGraphics();
                Dimension d = curvesPanel.getSize();
                g.clearRect(0, 0, d.width, d.height);
                curvesPanel.paintComponent(g);
            }
        });
    }

    @Override
    public String getSelectedParticipantName() {
        // TODO Auto-generated method stub
        return (String) comboListParticipants.getSelectedItem();
    }

    @Override
    public String getSelectedTrackersNames() {
        // TODO Auto-generated method stub
        return (String) comboListTrackers.getSelectedItem();
    }

    @Override
    public MeasurementType getSelectedMeasurementType() {
        // TODO Auto-generated method stub
        return MeasurementType.fromMeasurementName((String) comboListMeasurementType.getSelectedItem());
    }

    @Override
    public boolean isGoldStandardSelected() {
        // TODO Auto-generated method stub
        return checkboxGoldStandard.isSelected();
    }

    public Collection<Participant> getParticipants() {
        return participants;
    }

    public JTextArea getVisualisedDetailArea(){
        return visualisedDataDetailsTextArea;
    }

}
