package problem1;

public interface ISubject {
    /**
     * register an observer.
     *
     * @param handler
     */
    void attach(IEventHandler handler);

    /**
     * remove an observer.
     *
     * @param handler
     */
    void detach(IEventHandler handler);

    /**
     * fire an event.
     * @param event
     */
    void fireEvent(Event event);
}
