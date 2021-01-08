package ChanceCard;

public class RecieveMoney extends ChanceCard{
    int receive;


    public RecieveMoney(String text, int ID, int receive){
        super(text,ID);
        this.receive = receive;
    }
}
