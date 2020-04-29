package comp2402a2;

import java.util.AbstractList;

/**
 * @param <T> the type of objects stored in the List
 * @author morin
 */
public class BlockedList<T> extends AbstractList<T> {
    /**
     * keeps track of the class of objects we store
     */
    Factory<T> f;

    /**
     * The number of elements stored
     */
    int n;

    /**
     * The block size
     */
    int b;

    /*******************/
    /**
     * use a circle array which stores an b-sized array to meet the fundamental.
     */
    CircleArrayList<T[]> circleArrayList;

    CircleArrayList<T> list;

    /*******************/

    /**
     * Constructor
     *
     * @param t the type of objects that are stored in this list
     * @param b the block size
     */
    public BlockedList(Class<T> t, int b) {
        f = new Factory<T>(t);
        n = 0;
        // TODO: Implement this
        this.b = b;

        circleArrayList = new CircleArrayList<>();
        list = new CircleArrayList<>();
    }

    public int size() {
        return n;
    }

    public T get(int i) {
        // TODO: Implement this
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();

//        return circleArrayList.get(i / b)[i % b];
		return list.get(i);
    }

    public T set(int i, T x) {
        // TODO: Implement this
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
//        T old = circleArrayList.get(i / b)[i % b];
//        circleArrayList.get(i / b)[i % b] = x;
//        return old;
		return list.set(i, x);
    }

    public void add(int i, T x) {
        // TODO: Implement this
        if (i < 0 || i > n) throw new IndexOutOfBoundsException();
        //if all block are full then create a new block
//        if (n % b == 0) {
//            circleArrayList.add(f.newArray(b));
//        }
//        for (int j = n; j >= i; j--) {
//            circleArrayList.get((j + 1) / b)[(j + 1) % b] = circleArrayList.get((j) / b)[(j) % b];
//        }
//
//        circleArrayList.get((i) / b)[(i) % b] = x;
		list.add(i, x);
        n++;
    }

    public T remove(int i) {
        // TODO: Implement this
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
//        T oldVal = circleArrayList.get(i / b)[i % b];
//        for (int j = i; j < n - 1; j++) {
//            circleArrayList.get((j) / b)[(j) % b] = circleArrayList.get((j + 1) / b)[(j + 1) % b];
//        }
//        n--;
//        if (n % b == 0) {
//            circleArrayList.remove(circleArrayList.size() - 1);
//        }
//        return oldVal;
		T oldVal = list.remove(i);
		n--;
		return oldVal;
    }
}
