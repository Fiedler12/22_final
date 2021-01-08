package ChanceCard;

public class GoToJailCard extends ChanceCard {
    int moveToJail;

    public GoToJailCard(String text, int ID, int moveToJail){
        super(text, ID);
        this.moveToJail = moveToJail;
    }
}
