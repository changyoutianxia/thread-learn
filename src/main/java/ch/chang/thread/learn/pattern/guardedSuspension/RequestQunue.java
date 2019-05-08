package ch.chang.thread.learn.pattern.guardedSuspension;

import java.util.LinkedList;

public class RequestQunue {
    private final LinkedList<Request> requestQunue = new LinkedList();

    public Request getRequest() {
        synchronized (this) {
            while (requestQunue.size() <= 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    return null;
                }
            }
            return requestQunue.removeFirst();
        }
    }
    public void addRequest(Request request){
        synchronized (this){
            requestQunue.addLast(request);
            this.notifyAll();
        }
    }


}
