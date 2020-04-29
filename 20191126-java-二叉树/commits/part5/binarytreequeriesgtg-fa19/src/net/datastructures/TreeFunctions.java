package net.datastructures;

public class TreeFunctions {

  /* Returns the result of summing all elements at the given depth
    NOTE: must use recursion, no looping
     */
    public static Integer sumAtDepthRecursive(BinaryTree<Integer> tree, int depth) {
        return sumAtDepthRecursiveHelper((LinkedBinaryTree.Node<Integer>) tree.root(), depth, 0);
    }

    private static Integer sumAtDepthRecursiveHelper(LinkedBinaryTree.Node<Integer> root, int targetDepth, int curDepth) {
        if (root == null || curDepth > targetDepth) {
            return 0;
        }
        if (targetDepth == curDepth) {
            return root.getElement();
        }
        return sumAtDepthRecursiveHelper(root.getLeft(), targetDepth, curDepth + 1) + sumAtDepthRecursiveHelper(root.getRight(), targetDepth, curDepth + 1);
    }

    /* Returns the result of summing all elements at the given depth
    NOTE: must use looping, no recursion
     */
    public static Integer sumAtDepthIterative(BinaryTree<Integer> tree, int depth) {
        LinkedList<LinkedBinaryTree.Node<Integer>> list = new LinkedList<>();
        Integer sum = 0;
        int curDepth = 0;
        list.push((LinkedBinaryTree.Node<Integer>) tree.root());

        while (curDepth < depth && list.isEmpty() == false) {
            LinkedList<LinkedBinaryTree.Node<Integer>> tmp = new LinkedList<>();

            for (LinkedBinaryTree.Node<Integer> node : list) {
                if (node.getLeft() != null) {
                    tmp.push(node.getLeft());
                }
                if (node.getRight() != null) {
                    tmp.push(node.getRight());
                }
            }

            curDepth++;
            list = tmp;
        }
        for (LinkedBinaryTree.Node<Integer> node : list) {
            sum += node.getElement();
        }

        return sum;
    }


}
