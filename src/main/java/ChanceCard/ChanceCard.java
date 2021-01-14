package ChanceCard;

public abstract class ChanceCard {
    public int CardID;
    public String CardText;
    public  ChanceCard(String CardText, int CardID){
        this.CardID = CardID;
        this.CardText = CardText;
    }
}