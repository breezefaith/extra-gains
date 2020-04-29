
public class BinaryTreeNode<T> implements Comparable<BinaryTreeNode<T>>{
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;
    private T element;

    public BinaryTreeNode(T element) {
        this.element = element;
        left = null;
        right = null;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    @Override
    public int compareTo(BinaryTreeNode<T> o) {
        return ((Comparable<T>)element).compareTo(o.getElement());
    }
}
