package ChanceCard;

import ChanceCard.ChanceCard;

public class Move extends ChanceCard {
    int move;

    public Move (String text, int ID, int move){
        super(text, ID);
        this.move = move;
    }
    public int getSpecific(int pos){
        return pos+=this.move;
    }
}
