package applications;

import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;

import java.util.Iterator;

public class Application3 {
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


        // The following traversals use the Iterable returned by preorder,postorder,inorder,breadfirst by getting their iterator
        // then using a while loop to ask for items from the iterator

        // Print elements of tree in preorder
        System.out.println("preorder");
        Iterator<Position<Integer>> preorderIterator = lbt.preorder().iterator();
        while(preorderIterator.hasNext()) {
            System.out.println(preorderIterator.next().getElement());
        }

        // Print elements of tree in postorder
        System.out.println("postorder");
        Iterator<Position<Integer>> postorderIterator = lbt.postorder().iterator();
        while(postorderIterator.hasNext()) {
            System.out.println(postorderIterator.next().getElement());
        }

        // Print elements of tree in inorder
        System.out.println("inorder");
        Iterator<Position<Integer>> inorderIterator = lbt.inorder().iterator();
        while(inorderIterator.hasNext()) {
            System.out.println(inorderIterator.next().getElement());
        }

        // Print elements of tree in breadth first
        System.out.println("breadth first");
        Iterator<Position<Integer>> breadthfirstIterator = lbt.breadthfirst().iterator();
        while(breadthfirstIterator.hasNext()) {
            System.out.println(breadthfirstIterator.next().getElement());
        }



        // Another way to use the Iterable returned by preorder, postorder, inorder, breadthfirst
        // is in a Java foreach loop (colon notation)

        // Print elements of tree in preorder
        System.out.println("preorder");
        for (Position<Integer> p : lbt.preorder()) {
            System.out.println(p.getElement());
        }

        // Print elements of tree in postorder
        System.out.println("postorder");
        for (Position<Integer> p : lbt.postorder()) {
            System.out.println(p.getElement());
        }

        // Print elements of tree in inorder
        System.out.println("inorder");
        for (Position<Integer> p : lbt.inorder()) {
            System.out.println(p.getElement());
        }

        // Print elements of tree in breadth first
        System.out.println("breadth first");
        for (Position<Integer> p : lbt.breadthfirst()) {
            System.out.println(p.getElement());
        }
    }
}
