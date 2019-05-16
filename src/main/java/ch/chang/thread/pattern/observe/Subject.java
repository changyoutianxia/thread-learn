package ch.chang.thread.pattern.observe;

import java.util.LinkedList;
import java.util.List;

public class Subject {
    private List<Observe> observeList = new LinkedList<>();
    private static volatile int state = 0;

    public void setState(int i) {
        if (state == i) {
            return;
        }
        notice();
    }

    private void notice() {
        observeList.stream().forEach(Observe::update);
    }

    public void addObserver(Observe observe) {
        observeList.add(observe);
    }
}
