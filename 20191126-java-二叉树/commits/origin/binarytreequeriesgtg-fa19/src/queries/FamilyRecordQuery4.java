
package queries;

import net.datastructures.LinkedBinaryTree;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;


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

        System.out.println(under50);

    }


    // define a class here
    
}
