package ch.chang.thread.learn.pattern.observe;

public class BinaryObserver extends Observe {
    public BinaryObserver(Subject subject) {
        this.subject = subject;
        subject.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("subject update!");
    }
}
