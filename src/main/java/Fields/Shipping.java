package Fields;

public class Shipping extends Ownable {
    String name;
    int currentprice;
    int pawnValue;
    int priceOfOne;
    int priceOfTwo;
    int priceOfThree;
    int priceOfFour;

    public Shipping(int price, String name) {
        super(price);
        this.pawnValue = 2000;
        this.priceOfOne = 500;
        this.priceOfTwo = 1000;
        this.priceOfThree = 2000;
        this.priceOfFour = 4000;
        this.name = name;
    }

}
