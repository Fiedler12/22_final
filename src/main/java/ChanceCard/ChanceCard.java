package ChanceCard;

abstract class ChanceCard {
    int CardID;
    String CardText;
    public  ChanceCard(String CardText, int CardID){
        this.CardID = CardID;
        this.CardText = CardText;

    }
}