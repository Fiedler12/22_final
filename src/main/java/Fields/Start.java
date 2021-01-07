package Fields;

public class Start extends Field {
    boolean startField;
    public Start (int id) {
        super(id);
        setStartField(true);
    }


    public boolean isStartField() {
        return startField;
    }
    public void setStartField(boolean startField) {
        this.startField = startField;
    }
}
