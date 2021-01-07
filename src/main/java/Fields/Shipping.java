package Fields;

import java.awt.*;

public class Shipping extends Ownable {
    String name;
    String subText;
    String description;
    String rent;
    Color mainColor;
    Color secondaryColor;

    int currentPrice;
    int pawnValue;
    int priceOfOne;
    int priceOfTwo;
    int priceOfThree;
    int priceOfFour;

    public Shipping(int id, int price, String name, String subText, String description, String rent) {
        super(id, price);
        this.pawnValue = 2000;
        this.priceOfOne = 500;
        this.priceOfTwo = 1000;
        this.priceOfThree = 2000;
        this.priceOfFour = 4000;
        this.name = name;
        this.subText = subText;
        this.description = description;
        this.rent = rent;


    }

}
