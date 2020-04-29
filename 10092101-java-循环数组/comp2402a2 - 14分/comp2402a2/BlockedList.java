package comp2402a2;

import java.util.*;

/**
 * @author morin
 *
 * @param <T> the type of objects stored in the List
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

	List<T> elements;

	/**
	 * Constructor
	 * @param t the type of objects that are stored in this list
	 * @param b the block size
	 */
	public BlockedList(Class<T> t, int b) {
		f = new Factory<T>(t);
		n = 0;
		// TODO: Implement this
		this.b = b;
		elements = new ArrayList<>(b);
	}

	public int size() {
		return n;
	}

	public T get(int i) {
		// TODO: Implement this
		if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();

		return elements.get(i);
	}

	public T set(int i, T x) {
		// TODO: Implement this
		if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
		return elements.set(i, x);
	}

	public void add(int i, T x) {
		// TODO: Implement this
		if (i < 0 || i > n) throw new IndexOutOfBoundsException();
		elements.add(i, x);
		n++;
	}

	public T remove(int i) {
		// TODO: Implement this
		if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
		T oldVal = elements.remove(i);
		n--;
		return oldVal;
	}
}
