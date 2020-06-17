package problem1;

public class AbstractObserver {
    protected Subject subject;

    public AbstractObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }
}
