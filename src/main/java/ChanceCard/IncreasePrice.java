package ChanceCard;

public class IncreasePrice extends ChanceCard{
    int prHouse;
    int prHotel;

    public IncreasePrice(String text, int ID, int prHouse, int prHotel) {
        super(text, ID);
        this.prHotel = prHotel;
        this.prHouse = prHouse;
    }


}
