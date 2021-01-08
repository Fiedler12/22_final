package ChanceCard;

public class GetOutOfJailCard extends ChanceCard{
    int sellValue;
    boolean ownJailCard;

    public GetOutOfJailCard (String Text, int ID,int getSellValue, boolean isOwnJailCard){
        super(Text,ID);
        this.sellValue = getSellValue;
        this.ownJailCard = isOwnJailCard;
    }
}
