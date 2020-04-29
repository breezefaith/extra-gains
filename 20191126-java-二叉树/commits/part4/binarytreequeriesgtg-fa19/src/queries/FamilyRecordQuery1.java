
package queries;

import net.datastructures.AbstractTree;
import net.datastructures.LinkedBinaryTree;

import java.io.IOException;
import java.util.Iterator;


public class FamilyRecordQuery1 {

    public static void main(String[] args) throws IOException{
        System.out.println(System.getProperty("user.dir"));
        Iterator<FamilyRecord> records = FamilyRecordTools.records("familyrecord.csv");

        LinkedBinaryTree<FamilyRecord> treeOfRecords = new LinkedBinaryTree<>();
        LinkedBinaryTree<String> treeOfNames = new LinkedBinaryTree<>();
        while(records.hasNext()){
            FamilyRecord record = records.next();
            treeOfNames.insertNode(record.name);
            treeOfRecords.insertNode(record);
        }

        System.out.println(AbstractTree.toList(treeOfNames.breadthfirst()));
        System.out.println(AbstractTree.toList(treeOfRecords.breadthfirst()));
    }
}
