package ChanceCard;

public class JailCard extends ChanceCard {
    int moveToJail;

    public JailCard(String text, int ID, int moveToJail){
        super(text, ID);
        this.moveToJail = moveToJail;
    }
}
