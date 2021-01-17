package Fields;

public class Chancefield extends Field {

    public Chancefield(int id) {
        super(id);
        setChance(true);
    }

    boolean chance;

    public void setChance(boolean chance) {
        this.chance = chance;
    }
}
