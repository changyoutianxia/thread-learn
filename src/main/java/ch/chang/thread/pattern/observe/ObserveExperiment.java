package ch.chang.thread.pattern.observe;

public class ObserveExperiment {
    public static void main(String[] args) {
        Subject subject = new Subject();
        BinaryObserver binaryObserver = new BinaryObserver(subject);
        subject.setState(2);
        subject.setState(3);
    }
}
