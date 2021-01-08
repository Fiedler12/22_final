package Fields;

public class Brewery extends Ownable {
    int multiplier;
    int payThis;

    public Brewery (String name, int id, int price, int multiplier) {
        super(name, id, price);
        this.multiplier = multiplier;
    }
    public int landsOnBrewery(int roll) {
        payThis = multiplier * roll;
        return payThis;
    }
}
