package Fields;

public class Brewery extends Ownable {
    int multiplier;
    int payThis;

    public Brewery (int id, int price, int multiplier) {
        super(id, price);
        this.multiplier = multiplier;
    }
    public int landsOnBrewery(int roll) {
        payThis = multiplier * roll;
        return payThis;
    }
}
