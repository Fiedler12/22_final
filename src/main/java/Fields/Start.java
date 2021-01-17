package Fields;

public class Start extends Field {
    boolean startField;
    public Start (int id) {
        super(id);
        setStartField(true);
    }

    public void setStartField(boolean startField) {
        this.startField = startField;
    }
}
