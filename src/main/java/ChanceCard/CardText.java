package ChanceCard;

import org.w3c.dom.Text;

public class CardText extends ChanceCard {
    ChanceCard[] CardPile;
    String T;
    int ID;
    int TopCard;


    public ChanceCard() {
        CardPile = new ChanceCard[35];
        int i = 0;
        CardPile[i] = new IncreasePrice()
        CardPile[i++] = new ReceiveMoney("Modtag 500 kr.", i, 500);
        }
}



}