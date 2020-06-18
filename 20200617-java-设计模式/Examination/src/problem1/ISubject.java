package problem1;

public interface ISubject {
    /**
     * register an observer.
     *
     * @param observer
     */
    void attach(AbstractObserver observer);

    /**
     * remove an observer.
     *
     * @param observer
     */
    void detach(AbstractObserver observer);
}
