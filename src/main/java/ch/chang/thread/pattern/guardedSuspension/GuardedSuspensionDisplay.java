package ch.chang.thread.pattern.guardedSuspension;

public class GuardedSuspensionDisplay {

    public static void main(String[] args) {
        RequestQunue requestQunue = new RequestQunue();
        ClientThread clientThread = new ClientThread(requestQunue, "aaaaaaaaaa");
        new ClientThread(requestQunue, "bbb").start();
        ServerThread serverThread = new ServerThread(requestQunue);
        clientThread.start();
        serverThread.start();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serverThread.close();

    }
}
