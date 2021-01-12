package ChanceCard;

public class GetOutOfJailCard extends ChanceCard{
    int sellValue;

    public GetOutOfJailCard (String Text, int ID,int sellValue){
        super(Text,ID);
        this.sellValue = sellValue;
    }
    public int getSellValue() {
        return sellValue;
    }
}

