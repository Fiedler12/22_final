package ChanceCard;

import ChanceCard.ChanceCard;

public class MoneyFromPlayer extends ChanceCard {
    int recieveFromPlayer;
    public MoneyFromPlayer(String text, int ID, int recieveFromPlayer) {
        super(text, ID);
        this.recieveFromPlayer = recieveFromPlayer;
    }

    public int getRecieveFromPlayer() {
        return recieveFromPlayer;
    }
}
