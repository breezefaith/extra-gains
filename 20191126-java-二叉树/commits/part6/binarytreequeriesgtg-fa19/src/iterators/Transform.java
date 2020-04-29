package iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class Transform<InT, OutT> implements Iterator<OutT> {
    private final Function<InT, OutT> f;
    private final Iterator<InT> input;

    public Transform(Function<InT, OutT> f, Iterator<InT> input) {
        this.f = f;
        this.input = input;
    }

    @Override
    public boolean hasNext() {
        return input.hasNext();
    }

    @Override
    public OutT next() {
        if (!hasNext()) throw new NoSuchElementException();
        return f.apply(input.next());
    }
}
