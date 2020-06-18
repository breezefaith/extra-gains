package problem1;

public abstract class AbstractObserver {
    protected AbstractGameSubject subject;

    public AbstractObserver(AbstractGameSubject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }
}
