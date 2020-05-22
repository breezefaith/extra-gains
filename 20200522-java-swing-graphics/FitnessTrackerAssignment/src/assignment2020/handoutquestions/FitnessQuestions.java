package assignment2020.handoutquestions;

import assignment2020.codeprovided.fitnesstracker.Participant;
import assignment2020.codeprovided.fitnesstracker.Tracker;
import assignment2020.codeprovided.fitnesstracker.measurements.Measurement;
import assignment2020.codeprovided.fitnesstracker.measurements.MeasurementType;
import assignment2020.codeprovided.handoutquestions.AbstractFitnessQuestions;

import java.util.*;


public class FitnessQuestions extends AbstractFitnessQuestions {

    public FitnessQuestions(Collection<Participant> participants) {
        super(participants);
    }

    @Override
    public String toString() {
        // TODO Implement
        int qa1 = getTotalParticipants();
        int qa2 = getParticipantsNumberWithHRM();
        int qa3 = getParticipantsNumberWithStepsM();
        int qa4 = getParticipantsNumberWithDistanceM();
        int qa5 = getParticipantsNumberWithEEM();
        int qa6 = getTotalHRMCount();
        int qa7 = getTotalStepsCount();
        int qa8 = getTotalDistanceCount();
        int qa9 = getTotalEECount();
        List<Integer> qa10 = getHRMCountPerFT();
        int qa11 = getEEMCountForFT1();
        List<Integer> qa12 = getStepsMCountForFT234();
        int qa13 = getDistanceMCountForFT5();
        Set<String> qa14 = getHighestNumberOfStepsParticipants();
        Set<String> qa15 = getLowestNumberOfStepsParticipants();
        Set<String> qa16 = getHighestWalkedDistanceParticipants();
        Set<String> qa17 = getLowestWalkedDistanceParticipants();
        double qa18 = getGlobalAverageHR();
        List<String> qa19 = getAvgHRAboveGlobalParticipants();
        List<String> qa20 = getAvgHRBelowGlobalParticipants();

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Q1. Total number of participants: " + qa1 + "\n");
        stringBuffer.append("Q2. Number of participants with heart rate measurements:" + qa2 + "out of" + qa1 + "\n");
        stringBuffer.append("Q3. Number of participants with steps measurements:" + qa3 + "out of" + qa1 + "\n");
        stringBuffer.append("Q4. Number of participants with distance measurements:" + qa4 + "out of" + qa1 + "\n");
        stringBuffer.append("Q5. Number of participants with energy expenditure measurements:" + qa5 + "out of" + qa1 + "\n");
        stringBuffer.append("Q6. Total count of heart rate measurements: " + qa6 + "\n");
        stringBuffer.append("Q7. Total count of steps measurements: " + qa7 + "\n");
        stringBuffer.append("Q8. Total count of distance measurements: " + qa8 + "\n");
        stringBuffer.append("Q9. Total count of energy expenditure measurements: " + qa9 + "\n");
        stringBuffer.append("Q10. ");
        stringBuffer.append("Total count of heart rate measurements for FT1: " + qa10.get(0) + "\n");
        stringBuffer.append("\tTotal count of heart rate measurements for FT2: " + qa10.get(1) + "\n");
        stringBuffer.append("\tTotal count of heart rate measurements for FT3: " + qa10.get(2) + "\n");
        stringBuffer.append("\tTotal count of heart rate measurements for FT4: " + qa10.get(3) + "\n");
        stringBuffer.append("\tTotal count of heart rate measurements for FT5: " + qa10.get(4) + "\n");
        stringBuffer.append("Q11. Total count of energy expenditure measurements for FT1: " + qa11 + "\n");
        stringBuffer.append("Q12. ");
        stringBuffer.append("Total count of energy expenditure measurements for FT2: " + qa12.get(0) + "\n");
        stringBuffer.append("\tTotal count of energy expenditure measurements for FT3: " + qa12.get(1) + "\n");
        stringBuffer.append("\tTotal count of energy expenditure measurements for FT4: " + qa12.get(2) + "\n");
        stringBuffer.append("Q13. Total count of distance measurements for FT5: " + qa13 + "\n");
        stringBuffer.append("Q14. " + qa14.size() + " participant/s with the highest number of steps: " + "\n");
        for (String s : qa14) {
            String[] ss = s.split(" ");
            stringBuffer.append("\t* Participant ID" + ss[0] + " with number of steps: " + ss[1] + "\n");
        }
        stringBuffer.append("Q15. " + qa15.size() + " participant/s with the lowest number of steps: " + "\n");
        for (String s : qa15) {
            String[] ss = s.split(" ");
            stringBuffer.append("\t* Participant ID" + ss[0] + " with number of steps: " + ss[1] + "\n");
        }
        stringBuffer.append("Q16. " + qa16.size() + " participant/s with the highest number of distance: " + "\n");
        for (String s : qa16) {
            String[] ss = s.split(" ");
            stringBuffer.append("\t* Participant ID" + ss[0] + " with number of distance: " + ss[1] + "\n");
        }
        stringBuffer.append("Q17. " + qa17.size() + " participant/s with the lowest number of distance: " + "\n");
        for (String s : qa17) {
            String[] ss = s.split(" ");
            stringBuffer.append("\t* Participant ID" + ss[0] + " with number of distance: " + ss[1] + "\n");
        }
        stringBuffer.append("Q18. Global average heart rate: " + qa18 + "\n");
        stringBuffer.append("Q19. " + qa19.size() + " participant/s with heart rate above global average heart rate (" + qa18 + "):\n");
        for (String s : qa19) {
            String[] ss = s.split(" ");
            stringBuffer.append("\t* Participant ID" + ss[0] + " with individual average heart rate " + ss[1] + "\n");
        }
        stringBuffer.append("Q20. " + qa20.size() + " participant/s with heart rate below global average heart rate (" + qa18 + "):\n");
        for (String s : qa20) {
            String[] ss = s.split(" ");
            stringBuffer.append("\t* Participant ID" + ss[0] + " with individual average heart rate " + ss[1] + "\n");
        }
        return stringBuffer.toString();
    }

    @Override
    public int getTotalParticipants() {
        // TODO Auto-generated method stub
        return getParticipants().size();
    }

    @Override
    public int getParticipantsNumberWithHRM() {
        // TODO Auto-generated method stub
        int c = 0;
        for (Participant p : getParticipants()) {
            for (Measurement m : p.getAllMeasurements()) {
                if (MeasurementType.HEART_RATE.equals(m.getMeasurementType())) {
                    c++;
                    break;
                }
            }
        }
        return c;
    }

    @Override
    public int getParticipantsNumberWithStepsM() {
        // TODO Auto-generated method stub
        int c = 0;
        for (Participant p : getParticipants()) {
            for (Measurement m : p.getAllMeasurements()) {
                if (MeasurementType.STEPS.equals(m.getMeasurementType())) {
                    c++;
                    break;
                }
            }
        }
        return c;
    }

    @Override
    public int getParticipantsNumberWithDistanceM() {
        // TODO Auto-generated method stub
        int c = 0;
        for (Participant p : getParticipants()) {
            for (Measurement m : p.getAllMeasurements()) {
                if (MeasurementType.DISTANCE.equals(m.getMeasurementType())) {
                    c++;
                    break;
                }
            }
        }
        return c;
    }

    @Override
    public int getParticipantsNumberWithEEM() {
        // TODO Auto-generated method stub
        int c = 0;
        for (Participant p : getParticipants()) {
            for (Measurement m : p.getAllMeasurements()) {
                if (MeasurementType.ENERGY_EXPENDITURE.equals(m.getMeasurementType())) {
                    c++;
                    break;
                }
            }
        }
        return c;
    }

    @Override
    public int getTotalHRMCount() {
        // TODO Auto-generated method stub
        int c = 0;
        for (Participant p : getParticipants()) {
            for (Measurement m : p.getAllMeasurements()) {
                if (MeasurementType.HEART_RATE.equals(m.getMeasurementType())) {
                    c++;
                }
            }
        }
        return c;
    }

    @Override
    public int getTotalStepsCount() {
        // TODO Auto-generated method stub
        int c = 0;
        for (Participant p : getParticipants()) {
            for (Measurement m : p.getAllMeasurements()) {
                if (MeasurementType.STEPS.equals(m.getMeasurementType())) {
                    c++;
                }
            }
        }
        return c;
    }

    @Override
    public int getTotalDistanceCount() {
        // TODO Auto-generated method stub
        int c = 0;
        for (Participant p : getParticipants()) {
            for (Measurement m : p.getAllMeasurements()) {
                if (MeasurementType.DISTANCE.equals(m.getMeasurementType())) {
                    c++;
                }
            }
        }
        return c;
    }

    @Override
    public int getTotalEECount() {
        // TODO Auto-generated method stub
        int c = 0;
        for (Participant p : getParticipants()) {
            for (Measurement m : p.getAllMeasurements()) {
                if (MeasurementType.ENERGY_EXPENDITURE.equals(m.getMeasurementType())) {
                    c++;
                }
            }
        }
        return c;
    }

    @Override
    public List<Integer> getHRMCountPerFT() {
        // TODO Auto-generated method stub
        List<Integer> res = new ArrayList<>();
        res.add(0);
        res.add(0);
        res.add(0);
        res.add(0);
        res.add(0);
        for (Participant p : getParticipants()) {
            for (Tracker t : p.getAllTrackers()) {
                if (t.getName().startsWith("FT1")) {
                    res.set(0, res.get(0) + t.getMeasurementsForType(MeasurementType.HEART_RATE).size());
                } else if (t.getName().startsWith("FT2")) {
                    res.set(1, res.get(2) + t.getMeasurementsForType(MeasurementType.HEART_RATE).size());
                } else if (t.getName().startsWith("FT3")) {
                    res.set(2, res.get(2) + t.getMeasurementsForType(MeasurementType.HEART_RATE).size());
                } else if (t.getName().startsWith("FT4")) {
                    res.set(3, res.get(3) + t.getMeasurementsForType(MeasurementType.HEART_RATE).size());
                } else if (t.getName().startsWith("FT5")) {
                    res.set(4, res.get(4) + t.getMeasurementsForType(MeasurementType.HEART_RATE).size());
                }
            }
        }
        return res;
    }

    @Override
    public int getEEMCountForFT1() {
        // TODO Auto-generated method stub
        int c = 0;
        for (Participant p : getParticipants()) {
            for (Tracker t : p.getAllTrackers()) {
                if (t.getName().startsWith("FT1")) {
                    c += t.getMeasurementsForType(MeasurementType.ENERGY_EXPENDITURE).size();
                }
            }
        }
        return c;
    }

    @Override
    public List<Integer> getStepsMCountForFT234() {
        // TODO Auto-generated method stub
        List<Integer> res = new ArrayList<>();
        res.add(0);
        res.add(0);
        res.add(0);
        for (Participant p : getParticipants()) {
            for (Tracker t : p.getAllTrackers()) {
                if (t.getName().startsWith("FT2")) {
                    res.set(0, res.get(0) + t.getMeasurementsForType(MeasurementType.STEPS).size());
                } else if (t.getName().startsWith("FT3")) {
                    res.set(1, res.get(2) + t.getMeasurementsForType(MeasurementType.STEPS).size());
                } else if (t.getName().startsWith("FT4")) {
                    res.set(2, res.get(2) + t.getMeasurementsForType(MeasurementType.STEPS).size());
                }
            }
        }
        return res;
    }

    @Override
    public int getDistanceMCountForFT5() {
        // TODO Auto-generated method stub
        int c = 0;
        for (Participant p : getParticipants()) {
            for (Tracker t : p.getAllTrackers()) {
                if (t.getName().startsWith("FT5")) {
                    c += t.getMeasurementsForType(MeasurementType.DISTANCE).size();
                }
            }
        }
        return c;
    }

    @Override
    public Set<String> getHighestNumberOfStepsParticipants() {
        // TODO Auto-generated method stub
        Number mv = Double.MIN_VALUE;
        Set<String> res = new HashSet<>();
        for (Participant p : getParticipants()) {
            for (Tracker t : p.getAllTrackers()) {
                for (Measurement m : t.getMeasurementsForType(MeasurementType.STEPS)) {
                    if (m.getValue().doubleValue() > mv.doubleValue()) {
                        res.clear();
                        mv = m.getValue();
                        res.add(p.getName() + " " + mv.toString());
                    } else if (m.getValue().doubleValue() == mv.doubleValue()) {
                        res.add(p.getName() + " " + mv.toString());
                    }
                }
            }
        }
        return res;
    }

    @Override
    public Set<String> getLowestNumberOfStepsParticipants() {
        // TODO Auto-generated method stub
        Number mv = Double.MAX_VALUE;
        Set<String> res = new HashSet<>();
        for (Participant p : getParticipants()) {
            for (Tracker t : p.getAllTrackers()) {
                for (Measurement m : t.getMeasurementsForType(MeasurementType.STEPS)) {
                    if (m.getValue().doubleValue() < mv.doubleValue()) {
                        res.clear();
                        mv = m.getValue();
                        res.add(p.getName() + " " + mv.toString());
                    } else if (m.getValue().doubleValue() == mv.doubleValue()) {
                        res.add(p.getName() + " " + mv.toString());
                    }
                }
            }
        }
        return res;
    }

    @Override
    public Set<String> getHighestWalkedDistanceParticipants() {
        // TODO Auto-generated method stub
        Number mv = Double.MIN_VALUE;
        Set<String> res = new HashSet<>();
        for (Participant p : getParticipants()) {
            for (Tracker t : p.getAllTrackers()) {
                for (Measurement m : t.getMeasurementsForType(MeasurementType.DISTANCE)) {
                    if (m.getValue().doubleValue() > mv.doubleValue()) {
                        res.clear();
                        mv = m.getValue();
                        res.add(p.getName() + " " + mv.toString());
                    } else if (m.getValue().doubleValue() == mv.doubleValue()) {
                        res.add(p.getName() + " " + mv.toString());
                    }
                }
            }
        }
        return res;
    }

    @Override
    public Set<String> getLowestWalkedDistanceParticipants() {
        // TODO Auto-generated method stub
        Number mv = Double.MAX_VALUE;
        Set<String> res = new HashSet<>();
        for (Participant p : getParticipants()) {
            for (Tracker t : p.getAllTrackers()) {
                for (Measurement m : t.getMeasurementsForType(MeasurementType.DISTANCE)) {
                    if (m.getValue().doubleValue() < mv.doubleValue()) {
                        res.clear();
                        mv = m.getValue();
                        res.add(p.getName() + " " + mv.toString());
                    } else if (m.getValue().doubleValue() == mv.doubleValue()) {
                        res.add(p.getName() + " " + mv.toString());
                    }
                }
            }
        }
        return res;
    }

    @Override
    public double getGlobalAverageHR() {
        // TODO Auto-generated method stub
        double total = 0;
        int c = 0;
        for (Participant p : getParticipants()) {
            for (Tracker t : p.getAllTrackers()) {
                for (Measurement m : t.getMeasurementsForType(MeasurementType.HEART_RATE)) {
                    c++;
                    total += m.getValue().doubleValue();
                }
            }
        }
        return total / c;
    }

    @Override
    public List<String> getAvgHRAboveGlobalParticipants() {
        // TODO Auto-generated method stub
        double ga = getGlobalAverageHR();
        List<String> res = new ArrayList<>();
        for (Participant p : getParticipants()) {
            double total = 0;
            int c = 0;
            for (Tracker t : p.getAllTrackers()) {
                for (Measurement m : t.getMeasurementsForType(MeasurementType.HEART_RATE)) {
                    c++;
                    total += m.getValue().doubleValue();
                }
            }
            double ia = total / c;
            if (ia > ga) {
                res.add(p.getName() + " " + ia);
            }
        }
        return res;
    }

    @Override
    public List<String> getAvgHRBelowGlobalParticipants() {
        // TODO Auto-generated method stub
        double ga = getGlobalAverageHR();
        List<String> res = new ArrayList<>();
        for (Participant p : getParticipants()) {
            double total = 0;
            int c = 0;
            for (Tracker t : p.getAllTrackers()) {
                for (Measurement m : t.getMeasurementsForType(MeasurementType.HEART_RATE)) {
                    c++;
                    total += m.getValue().doubleValue();
                }
            }
            double ia = total / c;
            if (ia < ga) {
                res.add(p.getName() + " " + ia);
            }
        }
        return res;
    }
}
