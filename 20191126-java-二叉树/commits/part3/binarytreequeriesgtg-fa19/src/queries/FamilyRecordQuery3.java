
package queries;

import net.datastructures.BinaryTree;
import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;


public class FamilyRecordQuery3 {

    public static void main(String[] args) throws IOException{
        Iterator<FamilyRecord> records = FamilyRecordTools.records("familyrecord.csv");

        LinkedBinaryTree<FamilyRecord> treeOfRecords = new LinkedBinaryTree<>();
        while (records.hasNext()) {
            FamilyRecord record = records.next();
            treeOfRecords.insertNode(record);
        }

        /* UNCOMMENT
        List<FamilyRecord> robertList = treeOfRecords.suchThatIterative(new NameIs("Roger"));
        System.out.println(robertList);

        List<FamilyRecord> engineerList = treeOfRecords.suchThatRecursive(new JobIs("Engineer"));
        System.out.println(engineerList);

        List<FamilyRecord> childrenOfARogerList = treeOfRecords.suchThatIterative(new IsChildOf("Roger"));
        System.out.println(childrenOfARogerList);
        */
    }


    /* UNCOMMENT
    private static class NameIs implements BiFunction<BinaryTree<FamilyRecord>, Position<FamilyRecord>, Boolean> {

    }
    
    private static class JobIs implements BiFunction<BinaryTree<FamilyRecord>, Position<FamilyRecord>, Boolean> {

    }

    private static class IsChildOf implements BiFunction<BinaryTree<FamilyRecord>, Position<FamilyRecord>, Boolean> {

    }
    */
}
