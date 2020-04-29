
package queries;

import net.datastructures.AbstractTree;
import net.datastructures.LinkedBinaryTree;

import java.io.IOException;
import java.util.Iterator;
import java.util.function.BiFunction;


public class FamilyRecordQuery2 {

    public static void main(String[] args) throws IOException {
        Iterator<FamilyRecord> records = FamilyRecordTools.records("familyrecord.csv");

        LinkedBinaryTree<String> treeOfNames = new LinkedBinaryTree<>();
        while (records.hasNext()) {
            FamilyRecord record = records.next();
            treeOfNames.insertNode(record.name);
        }

        System.out.println("Family Tree:\n" + AbstractTree.toList(treeOfNames.breadthfirst()));

    /* UNCOMMENT TO FINISH QUERY
        int generationNumberA = 3;
        String ageGroupB = treeOfNames.reduceAtDepthRecursive(generationNumberA, new ConcatentateNames(), "");
        System.out.println("Generation " + generationNumberA + ": " + ageGroupB);

        int generationNumberB = 4;
        String ageGroupA = treeOfNames.reduceAtDepthIterative(generationNumberB, new ConcatentateNames(), "");
        System.out.println("Generation " + generationNumberB + ": " + ageGroupA);

        int generationNumberC = 2;
        String ageGroupC = treeOfNames.reduceAtDepthRecursive(generationNumberC, new ConcatentateNames(), "");
        System.out.println("Generation " + generationNumberC + ": " + ageGroupC);
        */


    }

    /* UNCOMMENT TO FINISH QUERY
    private static class ConcatentateNames implements BiFunction<String, String, String> {

    }
    */

}


