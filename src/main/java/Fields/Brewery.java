package Fields;

public class Brewery extends Ownable {
    int multiplier;

    public Brewery (String name, int id, int price, int multiplier) {
        super(name, id, price);
        this.multiplier = multiplier;
    }
}
