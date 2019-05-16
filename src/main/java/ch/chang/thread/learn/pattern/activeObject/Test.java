package ch.chang.thread.learn.pattern.activeObject;


public class Test {


    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakerClientThread(activeObject, "Alice").start();
        new MakerClientThread(activeObject, "Bobby").start();

        new DisplayClientThread("Chris", activeObject).start();
    }
}
