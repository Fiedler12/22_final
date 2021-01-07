package Fields;

public class GoToJail extends Field {
    public GoToJail(int id){
        super(id);
        setGoToJail(true);
    }

    boolean goToJail;
    public boolean isGoToJail(){
        return goToJail;
    }
    public void setGoToJail(boolean goToJail){
        this.goToJail=goToJail;
    }
}
