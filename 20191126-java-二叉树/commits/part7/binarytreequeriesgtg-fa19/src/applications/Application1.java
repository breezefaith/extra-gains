package applications;

import net.datastructures.BinaryTree;
import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;

public class Application1 {
    public static void main(String[] args) {
        LinkedBinaryTree<Integer> lbt = new LinkedBinaryTree<>();

        // create this tree
        //       10
        //      /  \
        //     20  30
        Position<Integer> myRoot = lbt.addRoot(10);
        Position<Integer> leftOfRoot = lbt.addLeft(myRoot, 20);
        Position<Integer> rightOfRoot = lbt.addRight(myRoot, 30);

        // two ways to print the root
        System.out.println("data at root is " + lbt.root().getElement()); // using the root() method
        System.out.println("data at root is " + myRoot.getElement()); // using the Position we got from addRoot above

        // two ways to print left of root
        System.out.println("data at left of root is " + lbt.left(lbt.root()).getElement()); // using the root() and left() methods
        System.out.println("data at left of root is " + leftOfRoot.getElement()); // using the Position we got from addLeft above

        // two ways to print right of root
        System.out.println("data at right of root is " + lbt.right(lbt.root()).getElement()); // using the root() and right() methods
        System.out.println("data at right of root is " + rightOfRoot.getElement());       // using the Position we got from addRight above



        // BinaryTree actually has right, root, and left methods, so we could also read the tree
        // using a BinaryTree reference to our LinkedBinaryTree
        BinaryTree<Integer> bt = lbt;

        // two ways to print the root
        System.out.println("data at root is " + bt.root().getElement()); // using the root() method
        System.out.println("data at root is " + myRoot.getElement()); // using the Position we got from addRoot above

        // two ways to print left of root
        System.out.println("data at left of root is " + bt.left(bt.root()).getElement()); // using the root() and left() methods
        System.out.println("data at left of root is " + leftOfRoot.getElement()); // using the Position we got from addLeft above

        // two ways to print right of root
        System.out.println("data at right of root is " + bt.right(bt.root()).getElement()); // using the root() and right() methods
        System.out.println("data at right of root is " + rightOfRoot.getElement());       // using the Position we got from addRight above
    }
}
