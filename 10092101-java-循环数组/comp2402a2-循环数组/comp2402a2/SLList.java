package comp2402a2;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * An implementation of a FIFO Queue as a singly-linked list.
 * This also includes the stack operations push and pop, which
 * operate on the head of the queue
 *
 * @param <T> the class of objects stored in the queue
 * @author morin
 */
public class SLList<T> extends AbstractList<T> implements Queue<T> {
    class Node {
        T x;
        Node next;
    }

    /**
     * Front of the queue
     */
    Node head;

    /**
     * Tail of the queue
     */
    Node tail;

    /**
     * The number of elements in the queue
     */
    int n;

    public T get(int i) {
        // TODO: Implement this
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        Node p = head;
        for (int j = 0; j < i; j++) {
            p = p.next;
        }
        return p.x;
    }

    public T set(int i, T x) {
        // TODO: Implement this
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        Node p = head;
        for (int j = 0; j < i; j++) {
            p = p.next;
        }
        T oldVal = p.x;
        p.x = x;
        return oldVal;
    }

    public void add(int i, T x) {
        // TODO: Implement this
        if (i < 0 || i > n) throw new IndexOutOfBoundsException();

        //create new node
        Node node = new Node();
        node.x = x;
        node.next = null;

        if (i == 0) {
            node.next = head;
            head = node;
        } else if (i == n) {
            node.next = null;
            tail.next = node;
            tail = node;
        } else {
            Node p = head;
            for (int j = 0; j < i - 1; j++) {
                p = p.next;
            }

            node.next = p.next;
            p.next = node;
        }

        n++;
        if (n == 1) {
            tail = head;
        }
    }

    public T remove(int i) {
        // TODO: Implement this
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        T oldVal = null;

        if (i == 0) {
            oldVal = head.x;
            head = head.next;
        } else {
            Node p = head;
            for (int j = 0; j < i - 1; j++) {
                p = p.next;
            }
            oldVal = p.next.x;
            p.next = p.next.next;
            if (i == n - 1) {
                tail = p;
            }
        }

        n--;
        return oldVal;
    }

    public void reverse() {
        // TODO: Implement this
        Node p = head;
        Node newHead = null;
        Node newTail = null;
        while (p != null) {
            Node next = p.next;
            if (p == head) {
                p.next = null;
                newHead = newTail = p;
            } else {
                p.next = newHead;
                newHead = p;
            }
            p = next;
        }

        head = newHead;
        tail = newTail;
    }

    public Iterator<T> iterator() {
        class SLIterator implements Iterator<T> {
            protected Node p;

            public SLIterator() {
                p = head;
            }

            public boolean hasNext() {
                return p != null;
            }

            public T next() {
                T x = p.x;
                p = p.next;
                return x;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
        return new SLIterator();
    }

    public int size() {
        return n;
    }

    public boolean add(T x) {
        Node u = new Node();
        u.x = x;
        if (n == 0) {
            head = u;
        } else {
            tail.next = u;
        }
        tail = u;
        n++;
        return true;
    }

    public boolean offer(T x) {
        return add(x);
    }

    public T peek() {
        if (n == 0) return null;
        return head.x;
    }

    public T element() {
        if (n == 0) throw new NoSuchElementException();
        return head.x;
    }

    public T poll() {
        if (n == 0)
            return null;
        T x = head.x;
        head = head.next;
        if (--n == 0)
            tail = null;
        return x;
    }

    /**
     * Stack push operation - push x onto the head of the list
     *
     * @param x the element to push onto the stack
     * @return x
     */
    public T push(T x) {
        Node u = new Node();
        u.x = x;
        u.next = head;
        head = u;
        if (n == 0)
            tail = u;
        n++;
        return x;
    }

    protected void deleteNext(Node u) {
        if (u.next == tail)
            tail = u;
        u.next = u.next.next;
    }

    protected void addAfter(Node u, Node v) {
        v.next = u.next;
        u.next = v;
        if (u == tail)
            tail = v;
    }

    protected Node getNode(int i) {
        Node u = head;
        for (int j = 0; j < i; j++)
            u = u.next;
        return u;
    }

    /**
     * Stack pop operation - pop off the head of the list
     *
     * @return the element popped off
     */
    public T remove() {
        if (n == 0) return null;
        T x = head.x;
        head = head.next;
        if (--n == 0) tail = null;
        return x;
    }

    public T pop() {
        if (n == 0) return null;
        T x = head.x;
        head = head.next;
        if (--n == 0) tail = null;
        return x;
    }

    public static void main(String[] args) {

    }
}
