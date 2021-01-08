package ChanceCard;

public class MoveToShipping extends ChanceCard{
    int nearestShipping;

    public MoveToShipping(String Text, int ID, int nearestShipping) {
        super(Text,ID);
        this.nearestShipping = nearestShipping;

    }


}
