package comp2402a4;

import java.lang.reflect.Array;
import java.util.SortedSet;

/**
 * An unfinished implementation of an Countdown tree (for exercises)
 *
 * @param <T>
 * @author morin
 */
public class CountdownTree<T> extends
        BinarySearchTree<CountdownTree.Node<T>, T> implements SSet<T> {

    // countdown delay factor
    double d;

    public static class Node<T> extends BSTNode<Node<T>, T> {
        int timer;  // the height of the node
    }

    public CountdownTree(double d) {
        this.d = d;
        sampleNode = new Node<T>();
        c = new DefaultComparator<T>();
    }

    public boolean add(T x) {
        Node<T> u = new Node<T>();
        u.timer = (int) Math.ceil(d);
        u.x = x;
        if (super.add(u)) {
            // add some code here
            Node<T> cur = u.parent;

            while (cur != nil) {
                cur.timer--;
                if (cur.timer == 0) {
                    explode(cur);
                }
                cur = cur.parent;
            }
            return true;
        }
        return false;
    }

    public void splice(Node<T> u) {
        Node<T> w = u.parent;
        super.splice(u);
        // add some code here (we just removed u from the tree)
        Node<T> cur = w;
        while (cur != null) {
            cur.timer--;
            if (cur.timer == 0) {
                explode(cur);
            }
            cur = cur.parent;
        }
    }

    protected void explode(Node<T> u) {
        // Write this code to explode u
        // Make sure to update u.parent and/or r (the tree root) as appropriate
        rebuild(u);
    }

    private void rebuild(Node<T> u) {
        int ns = size(u);
        Node<T> p = u.parent;
        Node<T>[] a = (Node<T>[]) Array.newInstance(Node.class, ns);
        packIntoArray(u, a, 0);
        if (p == nil) {
            r = buildBalanced(a, 0, ns);
            r.parent = nil;
        } else if (p.right == u) {
            p.right = buildBalanced(a, 0, ns);
            p.right.parent = p;
        } else {
            p.left = buildBalanced(a, 0, ns);
            p.left.parent = p;
        }
    }

    int packIntoArray(Node<T> u, Node<T>[] a, int i) {
        if (u == nil) {
            return i;
        }
        i = packIntoArray(u.left, a, i);
        a[i++] = u;
        return packIntoArray(u.right, a, i);
    }

    Node<T> buildBalanced(Node<T>[] a, int i, int ns) {
        if (ns == 0)
            return nil;
        int m = ns / 2;
        a[i + m].left = buildBalanced(a, i, m);
        if (a[i + m].left != nil)
            a[i + m].left.parent = a[i + m];
        a[i + m].right = buildBalanced(a, i + m + 1, ns - m - 1);
        if (a[i + m].right != nil)
            a[i + m].right.parent = a[i + m];
        //reset timer
        a[i + m].timer = (int)Math.ceil(size(a[i + m]) * d);
        return a[i + m];
    }

    private int calTimer(Node<T> u) {
        int size = 1;
        if (u.left != null) {
            size += (int) (u.left.timer / d);
        }
        if (u.right != null) {
            size += (int) (u.right.timer / d);
        }
        return (int)Math.ceil(size * d);
    }

    // Here is some test code you can use
    public static void main(String[] args) {
        Testum.sortedSetSanityTests(new SortedSSet<Integer>(new CountdownTree<Integer>(1)), 1000);
        Testum.sortedSetSanityTests(new SortedSSet<Integer>(new CountdownTree<Integer>(2.5)), 1000);
        Testum.sortedSetSanityTests(new SortedSSet<Integer>(new CountdownTree<Integer>(0.5)), 1000);

        java.util.List<SortedSet<Integer>> ell = new java.util.ArrayList<SortedSet<Integer>>();
        ell.add(new java.util.TreeSet<Integer>());
        ell.add(new SortedSSet<Integer>(new CountdownTree<Integer>(1)));
        ell.add(new SortedSSet<Integer>(new CountdownTree<Integer>(2.5)));
        ell.add(new SortedSSet<Integer>(new CountdownTree<Integer>(0.5)));
        Testum.sortedSetSpeedTests(ell, 1000000);
//        Testum.sortedSetSpeedTests(ell, 1000);

//        Testum.sortedSetSanityTests(new SortedSSet<Integer>(new CountdownTree<Integer>(1)), 100);
//        Testum.sortedSetSanityTests(new SortedSSet<Integer>(new CountdownTree<Integer>(2.5)), 100);
//        Testum.sortedSetSanityTests(new SortedSSet<Integer>(new CountdownTree<Integer>(0.5)), 100);
//
//        java.util.List<SortedSet<Integer>> ell = new java.util.ArrayList<SortedSet<Integer>>();
//        ell.add(new java.util.TreeSet<Integer>());
//        ell.add(new SortedSSet<Integer>(new CountdownTree<Integer>(1)));
//        ell.add(new SortedSSet<Integer>(new CountdownTree<Integer>(2.5)));
//        ell.add(new SortedSSet<Integer>(new CountdownTree<Integer>(0.5)));
//        Testum.sortedSetSpeedTests(ell, 100000);

//        CountdownTree<Integer> tree1 = new CountdownTree<>(1);
//        tree1.add(10);
//        tree1.add(4);
//        tree1.add(0);
//        tree1.add(3);
//        tree1.add(2);
//        tree1.add(1);
//        tree1.add(15);
//        tree1.add(25);
//        tree1.add(13);
//        tree1.add(8);
//        tree1.add(14);
//
//		Node<Integer> n1 = tree1.findLast(10);
//		Node<Integer> n2 = tree1.findLast(20);
//		Node<Integer> n3 = tree1.findLast(70);
//		Node<Integer> n4 = tree1.findLast(15);
//		Node<Integer> n5 = tree1.findLast(4);
//		Node<Integer> n6 = tree1.findLast(25);
//		Node<Integer> n7 = tree1.findLast(13);
//		Node<Integer> n8 = tree1.findLast(8);
//		Node<Integer> n9 = tree1.findLast(1);
//		Node<Integer> n10 = tree1.findLast(14);
//
//		tree1.splice(n4);
//        tree1.show();
    }
}
