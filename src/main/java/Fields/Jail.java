package Fields;

public class Jail extends Field{
    public Jail (int id){
        super(id);
        setInJail(true);
    }

    boolean isInJail;

    public boolean Prisonor(){
        return isInJail;
    }
    public void setInJail(boolean jail){
        this.isInJail=jail;
    }
}
