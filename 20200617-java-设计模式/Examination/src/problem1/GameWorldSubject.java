package problem1;

import java.util.ArrayList;
import java.util.List;

public class GameWorldSubject implements ISubject {
    protected List<IEventHandler> handlers = new ArrayList<>();

    /**
     * register an handlers.
     *
     * @param handlers
     */
    public void attach(IEventHandler handlers) {
        this.handlers.add(handlers);
    }

    /**
     * remove an observer.
     *
     * @param handlers
     */
    public void detach(IEventHandler handlers) {
        this.handlers.remove(handlers);
    }

    /**
     * fire an event
     *
     * @param event
     */
    public void fireEvent(Event event) {
        this.notifyObservers(event);
    }

    /**
     * notify all observers
     *
     * @param event
     */
    private void notifyObservers(Event event) {
        for (IEventHandler handler : handlers) {
            handler.handle(event);
        }
    }
}
