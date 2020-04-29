package applications;

import net.datastructures.BinaryTree;
import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;

import java.util.Iterator;

public class Application2 {
    public static void main(String[] args) {
        LinkedBinaryTree<Integer> lbt = new LinkedBinaryTree<>();

        // create this tree
        //       10
        //      /   \
        //     20   30
        //    / \   / \
        //  40  50 60  70
        Position<Integer> myRoot = lbt.addRoot(10);
        Position<Integer> leftOfRoot = lbt.addLeft(myRoot, 20);
        Position<Integer> rightOfRoot = lbt.addRight(myRoot, 30);
        Position<Integer> rightOfRightOfRoot = lbt.addRight(rightOfRoot, 70);
        Position<Integer> leftOfRightOfRoot = lbt.addLeft(rightOfRoot, 60);
        Position<Integer> leftOfLeftOfRoot = lbt.addLeft(leftOfRoot, 40);
        Position<Integer> rightOfLeftOfRoot = lbt.addRight(leftOfRoot, 50);

        BinaryTree<Integer> bt = lbt;

        System.out.println("Is 10 internal? " + lbt.isInternal(myRoot));
        System.out.println("Is 20 internal? " + lbt.isInternal(leftOfRoot));
        System.out.println("Is 40 internal? " + lbt.isInternal(leftOfLeftOfRoot));

        System.out.println();

        System.out.println("Is 10 external? " + lbt.isExternal(myRoot));
        System.out.println("Is 20 external? " + lbt.isExternal(leftOfRoot));
        System.out.println("Is 40 external? " + lbt.isExternal(leftOfLeftOfRoot));

        System.out.println();

        System.out.println("10 sibling? " + lbt.sibling(myRoot)); // not calling getElement because sibling() returns null
        System.out.println("20 sibling? " + lbt.sibling(leftOfRoot).getElement());
        System.out.println("40 sibling? " + lbt.sibling(leftOfLeftOfRoot).getElement());

        System.out.println();

        System.out.println("10 parent? " + lbt.parent(myRoot)); // not calling getElement because parent() returns null
        System.out.println("20 parent? " + lbt.parent(leftOfRoot).getElement());
        System.out.println("40 parent? " + lbt.parent(leftOfLeftOfRoot).getElement());

        System.out.println();

        System.out.println("tree size? " + lbt.size());
        System.out.println("10 depth? " + lbt.depth(myRoot));
        System.out.println("10 height? " + lbt.height(myRoot));
    }
}
