package ChanceCard;

public class MoneyFromPlayer extends ChanceCard {
    int receiveFromPlayer;
    public MoneyFromPlayer(String text, int ID, int receiveFromPlayer) {
        super(text, ID);
        this.receiveFromPlayer = receiveFromPlayer;
    }

    public int getReceiveFromPlayer() {
        return receiveFromPlayer;
    }
}
