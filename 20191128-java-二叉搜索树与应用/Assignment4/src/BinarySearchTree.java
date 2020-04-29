import java.util.Iterator;

public class BinarySearchTree<T> implements BinarySearchTreeADT<T>, Iterable<T> {
    private BinaryTreeNode<T> root;
    private int size;

    public BinarySearchTree() {
        size = 0;
        root = null;
    }


    public BinaryTreeNode<T> find(BinaryTreeNode<T> node, Comparable<T> element) throws NonExistentKeyException {
        BinaryTreeNode<T> res = node;
        if (node == null) {
            throw new NonExistentKeyException(element.toString());
        }
        int comp = element.compareTo(res.getElement());
        while (comp != 0) {
            if (comp > 0) {
                res = res.getRight();
            } else {
                res = res.getLeft();
            }
            if (res == null) {
                throw new NonExistentKeyException(element.toString());
            }
            comp = element.compareTo(res.getElement());
        }
        return res;
    }

    public void add(BinaryTreeNode<T> node, T element) throws DuplicatedKeyException {
        BinaryTreeNode<T> p = new BinaryTreeNode<T>(element);
        Comparable<T> comparableElement = (Comparable<T>) element;

        if (isEmpty())
            root = p;
        else {
            BinaryTreeNode<T> parent;
            BinaryTreeNode<T> current = node;
            while (true) {
                parent = current;
                int comp = comparableElement.compareTo(current.getElement());
                if (comp > 0) {
                    current = current.getRight();
                    if (current == null) {
                        parent.setRight(p);
                        break;
                    }
                } else if (comp < 0) {
                    current = current.getLeft();
                    if (current == null) {
                        parent.setLeft(p);
                        break;
                    }
                } else {
                    throw new DuplicatedKeyException(element.toString());
                }
            }
        }
        size++;
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null && size == 0;
    }

    public int size() {
        return size;
    }

    public boolean contains(T element) {
        return contains(root, (Comparable<T>) element) != null;
    }

    private BinaryTreeNode<T> contains(BinaryTreeNode<T> node, Comparable<T> element) {
        BinaryTreeNode<T> res = node;
        if (node == null) {
            return null;
        }
        int comp = element.compareTo(res.getElement());
        while (comp != 0) {
            if (comp > 0) {
                res = res.getRight();
            } else {
                res = res.getLeft();
            }
            if (res == null) {
                return null;
            }
            comp = element.compareTo(res.getElement());
        }
        return res;
    }

    public Iterator<T> iterator() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        inorder(root, tempList);
        return tempList.iterator();
    }


    private void inorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            inorder(node.getLeft(), tempList);
            tempList.addToRear(node.getElement());
            inorder(node.getRight(), tempList);
        }
    }


    public String toString() {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        inorder(root, templist);
        return templist.toString();
    }
}
