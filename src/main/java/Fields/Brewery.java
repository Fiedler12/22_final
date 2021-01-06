package Fields;

public class Brewery extends Ownable {
    int multiplier;

    public Brewery (int price, int multiplier) {
        super(price);
        this.multiplier = multiplier;
    }

}
