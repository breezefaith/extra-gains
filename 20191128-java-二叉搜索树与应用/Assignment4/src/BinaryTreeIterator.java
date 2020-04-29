import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryTreeIterator<T> implements Iterator<T> {
    private BinaryTreeNode<T> root;
    private T[] items;
    private int current;
    private int size;

    public BinaryTreeIterator(BinaryTreeNode<T> root, int size) {
        this.root = root;
        this.size = size;
        current = 0;
    }

    public boolean hasNext() {
        return (current < size);
    }

    public T next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        current++;
        return items[current - 1];
    }

}