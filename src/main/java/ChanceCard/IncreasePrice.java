package ChanceCard;

public class IncreasePrice extends ChanceCard{
    int prHouse;
    int prHotel;

    public IncreasePrice(String text,int ID, int prHotel, int prHouse) {
        super(text, ID);
        this.prHotel = prHotel;
        this.prHouse = prHouse;
    }


}
