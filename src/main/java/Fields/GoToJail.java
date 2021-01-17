package Fields;

public class GoToJail extends Field {
    int prison;
    public GoToJail(int id, int prison){
        super(id);
        this.prison = prison;
    }

    public int getPrison() {
        return prison;
    }

}
