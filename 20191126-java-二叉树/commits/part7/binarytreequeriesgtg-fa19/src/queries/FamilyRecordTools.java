package queries;

import iterators.Transform;
import readers.LineFileReader;

import java.io.IOException;
import java.util.Iterator;
import java.util.function.Function;

public class FamilyRecordTools {
    // This is a namespace class. Cannot instantiate this class
    private FamilyRecordTools() {}

    public static Iterator<FamilyRecord> records(String filename) throws IOException {
        Iterator<String> lines = new LineFileReader(filename);
        Iterator<Object[]> recordsGeneric = new Transform<>(new FamilyRecordTools.ParseCSVLine(), lines);
        Iterator<FamilyRecord> records = new Transform<>(new FamilyRecordTools.ConvertToRecord(), recordsGeneric);
        return records;
    }


    // Converts a CSV record from an Object[] to a FamilyRecord
    public static class ConvertToRecord implements Function<Object[], FamilyRecord> {
        @Override
        public FamilyRecord apply(Object[] r) {
            return new FamilyRecord((String)r[0],
                    (int)r[1],
                    (String)r[2],
                    (String)r[3],
                    (String)r[4]);
        }
    }

    public static class ParseCSVLine implements Function<String, Object[]> {
        @Override
        public Object[] apply(String x) {
            String[] fields = x.split(",");
            Object[] r = new Object[fields.length];
            for (int i=0; i<fields.length; i++) {
                // try to convert to integer
                try {
                    r[i] = Integer.parseInt(fields[i]);
                } catch (NumberFormatException ex) {
                    // if it fails, then leave a string
                    r[i] = fields[i];
                }
            }
            return r;
        }
    }

}
