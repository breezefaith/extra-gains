package problem4.refactor;

public abstract class ElementDisplayer<T extends Element> implements IDisplayer<T> {
    protected T element;

    public ElementDisplayer(T element) {
        this.element = element;
    }

    public void display() {

    }

    public T getElement() {
        return element;
    }
}
