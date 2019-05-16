package ch.chang.thread.pattern.guardedSuspension;

import java.util.Random;

public class ClientThread extends Thread {
    private final RequestQunue requestQunue;
    private final String sendValue;
    private final Random random;

    public ClientThread(RequestQunue requestQunue, String sendValue) {
        this.requestQunue = requestQunue;
        this.sendValue = sendValue;
        this.random = new Random(System.currentTimeMillis());
    }


    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            System.out.println("client add request");
            requestQunue.addRequest(new Request(sendValue));
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
