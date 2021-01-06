package Fields;

public class Brewery extends Ownable {
    int multiplier;
    int payThis;

    public Brewery (int price, int multiplier) {
        super(price);
        this.multiplier = multiplier;
    }
    public int landsOnBrewery(int roll) {
        payThis = multiplier * roll;
        return payThis;
    }
}
