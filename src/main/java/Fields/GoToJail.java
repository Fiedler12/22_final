package Fields;

public class GoToJail extends Field {
    int prison;
    public GoToJail(int id, int prison){
        super(id);
        this.prison = prison;
        //setGoToJail(true);
    }

    public int getPrison() {
        return prison;
    }
/*  boolean goToJail;
    public boolean isGoToJail(){
        return goToJail;
    }
    public void setGoToJail(boolean goToJail){
        this.goToJail=goToJail;
    }*/
}
