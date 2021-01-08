package ChanceCard;

import ChanceCard.ChanceCard;

public class Move extends ChanceCard {
    public Move(int id, String text) {
        super(text, id);

    }
    public int getSpecific(int pos){
        return pos+=this.move;
    }
}
