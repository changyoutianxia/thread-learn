package ch.chang.thread.pattern.activeObject;


public class DisplayStringRequest extends MethodRequest {

    private final String text;

    public DisplayStringRequest(Servant servant, final String text) {
        super(servant, null);
        this.text = text;
    }

    @Override
    public void execute() {
        this.servant.displayString(text);
    }
}