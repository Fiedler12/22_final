package ChanceCard;

public class IncreasePrice extends ChanceCard{
    int prHouse;
    int prHotel;

    public IncreasePrice(int ID, String text, int prHotel, int prHouse) {
        super(text, ID);
        this.prHotel = prHotel;
        this.prHouse = prHouse;
    }


}
