package ChanceCard;

public class GoToJailCard extends ChanceCard {
    int GoToJail;

    public GoToJailCard(String text, int ID, int GoToJail){
        super(text, ID);
        this.GoToJail = GoToJail;
    }

    public int getGoToJail() {
        return GoToJail;
    }
}
