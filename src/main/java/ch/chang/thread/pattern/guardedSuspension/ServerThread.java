package ch.chang.thread.pattern.guardedSuspension;

import java.util.Random;

public class ServerThread extends Thread {
    private final RequestQunue requestQunue;
    private final Random random;
    private volatile boolean flag = false;

    public ServerThread(RequestQunue requestQunue) {
        this.requestQunue = requestQunue;
        this.random = new Random(System.currentTimeMillis());
    }


    @Override
    public void run() {
        while (!flag) {
            Request request = requestQunue.getRequest();
            if (null == request) {
                return;
            }
            System.out.println(request.getValue());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public void close() {
        this.flag = true;
        //打断
        this.interrupt();
    }
}
