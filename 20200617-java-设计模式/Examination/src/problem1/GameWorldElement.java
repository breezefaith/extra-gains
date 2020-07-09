package problem1;

public abstract class GameWorldElement implements IEventHandler {
    protected ISubject subject;

    public GameWorldElement(ISubject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    public void handle(Event event) {
        switch (event.getType()) {
            case LevelUp:
                this.handleLevelUp(event);
                break;
            case Arriving:
                this.handleArriving(event);
                break;
            case Collision:
                this.handleCollision(event);
                break;
        }
    }

    /**
     * handle the event level up
     *
     * @param event
     */
    protected abstract void handleLevelUp(Event event);

    /**
     * handle the event arriving
     *
     * @param event
     */
    protected abstract void handleArriving(Event event);

    /**
     * handle the event collision
     *
     * @param event
     */
    protected abstract void handleCollision(Event event);
}
