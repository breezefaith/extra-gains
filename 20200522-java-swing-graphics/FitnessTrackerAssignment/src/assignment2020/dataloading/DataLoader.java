package assignment2020.dataloading;

import assignment2020.codeprovided.dataloading.AbstractDataLoader;
import assignment2020.codeprovided.dataloading.DataParsingException;
import assignment2020.codeprovided.fitnesstracker.Participant;
import assignment2020.codeprovided.fitnesstracker.measurements.MeasurementFactory;
import assignment2020.codeprovided.fitnesstracker.measurements.MeasurementType;

import java.util.List;

public class DataLoader extends AbstractDataLoader {
    @Override
    public Participant loadDataLines(List<String> lines) throws DataParsingException {
        int no = 0;
        String line = null;
        String[] trackerNames = null;
        String[] values = null;
        MeasurementType currentType = null;

        String name = lines.get(no++).substring("Participant: ".length());
        int age = Integer.parseInt(lines.get(no++).substring("Age: ".length()));
        String gender = lines.get(no++).substring("Gender: ".length());

        Participant participant = new Participant(name, age, gender);

        while (no < lines.size() && lines.get(no).startsWith("--------------------------------------------")) {
            no++;
            line = lines.get(no);
            if (line.startsWith("Heart Rate")) {
                currentType = MeasurementType.HEART_RATE;
            } else if (line.startsWith("Steps")) {
                currentType = MeasurementType.STEPS;
            } else if (line.startsWith("Energy expenditure")) {
                currentType = MeasurementType.ENERGY_EXPENDITURE;
            } else if (line.startsWith("Distance")) {
                currentType = MeasurementType.DISTANCE;
            } else {
                break;
            }

            line = lines.get(++no);
            trackerNames = line.split(";");

            line = lines.get(++no);
            while (no < lines.size() && line.startsWith("--------------------------------------------") == false) {
                values = line.split(";");
                int count = Integer.parseInt(values[0]);
                for (int i = 1; i < values.length; i++) {
                    participant.addMeasurementToTracker(trackerNames[i], MeasurementFactory.createMeasurement(currentType, count, values[i]));
                }
                no++;
                if (no >= lines.size()) {
                    break;
                }
                line = lines.get(no);
            }
        }

        return participant;
    }

}
