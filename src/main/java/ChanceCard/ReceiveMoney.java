package ChanceCard;

public class ReceiveMoney extends ChanceCard{
    int receive;


    public ReceiveMoney (String text, int ID, int receive){
        super(text,ID);
        this.receive = receive;
    }
}
