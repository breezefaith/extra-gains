package problem1;

public final class Event {
    private EventType type;
    private IEventHandler source;
    private IEventHandler target;

    public Event(EventType type, IEventHandler source, IEventHandler target) {
        this.type = type;
        this.source = source;
        this.target = target;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public IEventHandler getSource() {
        return source;
    }

    public void setSource(IEventHandler source) {
        this.source = source;
    }

    public IEventHandler getTarget() {
        return target;
    }

    public void setTarget(IEventHandler target) {
        this.target = target;
    }
}
