
package queries;

import net.datastructures.BinaryTree;
import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;

import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;


public class FamilyRecordQuery4 {

    public static void main(String[] args) throws IOException{
        Iterator<FamilyRecord> records = FamilyRecordTools.records("familyrecord.csv");

        LinkedBinaryTree<FamilyRecord> treeOfRecords = new LinkedBinaryTree<>();
        while (records.hasNext()) {
            FamilyRecord record = records.next();
            treeOfRecords.insertNode(record);
        }

        List<FamilyRecord> under50 = null;

        // insert query here to compute under50
        under50 = treeOfRecords.suchThatIterative(new IsUnder50());

        System.out.println(under50);

    }


    // define a class here
    private static class IsUnder50 implements BiFunction<BinaryTree<FamilyRecord>, Position<FamilyRecord>, Boolean> {
        private Calendar calendar = Calendar.getInstance();

        @Override
        public Boolean apply(BinaryTree<FamilyRecord> familyRecords, Position<FamilyRecord> familyRecordPosition) {
            return calendar.get(Calendar.YEAR) - familyRecordPosition.getElement().birthYear < 50;
        }
    }
}
