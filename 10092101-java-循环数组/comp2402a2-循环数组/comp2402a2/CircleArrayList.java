package comp2402a2;

public class CircleArrayList<T> {
    int head;
    int tail;
    Object[] elements;
    int capacity;
    int size;

    public CircleArrayList() {
        head = tail = 0;
        capacity = 10;
        elements = new Object[capacity];
    }


    public int size() {
        return size;
    }

    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        return (T) elements[(head + index) % capacity];
    }


    public T set(int index, T x) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        T old = (T) elements[(head + index) % capacity];
        elements[(head + index) % capacity] = x;
        return old;
    }


    public boolean add(T x) {
        if (size == capacity) {
            grow();
        }
        elements[tail] = x;
        tail = (tail + 1) % capacity;
        if (tail < 0) tail += capacity;
        size++;

        return true;
    }


    public void add(int index, T x) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (size == capacity) {
            grow();
        }
        //index is closer to head than tail
        if (index < size / 2.0) {
            for (int i = 0; i < index; i++) {
                elements[(head + i - 1) % capacity] = elements[(head + i) % capacity];
            }
            if (index == 0) {
                elements[(head - 1) % capacity] = x;
            } else {
                elements[(head + index - 1) % capacity] = x;
            }
            head = (head - 1) % capacity;
            if (head < 0) head += capacity;
        } else {
            for (int i = 0; i < (size - index); i++) {
                elements[(tail - i) % capacity] = elements[(tail - i - 1) % capacity];
            }
            elements[(tail - (size - index)) % capacity] = x;
            tail = (tail + 1) % capacity;
            if (tail < 0) tail += capacity;
        }
        size++;
    }


    public T remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        T old = (T) elements[(head + index) % capacity];
        //index is closer to head than tail
        if (index < size / 2.0) {
            for (int i = index; i > 0; i--) {
                elements[(head + i) % capacity] = elements[(head + i - 1) % capacity];
            }
            elements[(head) % capacity] = null;
            head = (head + 1) % 10;
            if (head < 0) head += capacity;
        } else {
            for (int i = index; i < tail - 1; i++) {
                elements[(head + i) % capacity] = elements[(head + i + 1) % capacity];
            }
            elements[(tail - 1) % capacity < 0 ? (tail - 1) % capacity + capacity : (tail - 1) % capacity] = null;
            tail = (tail - 1) % capacity;
            if (tail < 0) tail += capacity;
        }
        size--;
        return old;
    }

    private void grow() {
        int newCap = 2 * capacity;
        Object[] newElts = new Object[newCap];
        for (int i = 0; i < size; i++) {
            newElts[i] = elements[(head + i) % capacity];
        }
        elements = newElts;
        capacity = newCap;
        head = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        for (int i = 0; i < elements.length; i++) {
            sb.append(elements[i] + ", ");
        }
        sb.append("\b\b ]");
        sb.append(", head = " + head);
        sb.append(", tail = " + tail);
        sb.append(", size = " + size);
        sb.append(", capacity = " + capacity);
        return sb.toString();
    }
}
