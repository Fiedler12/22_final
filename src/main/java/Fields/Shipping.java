package Fields;

import java.awt.*;

public class Shipping extends Ownable {
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

    public Shipping(String name, int id, int price, String subText, String description, String rent) {
        super(name, id, price);
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

    public int landOnowned(int amountOwned) {
        switch (amountOwned) {
            case 1:
                toPay = priceOfOne;
                break;
            case 2:
                toPay = priceOfTwo;
                break;
            case 3:
                toPay = priceOfThree;
                break;
            case 4:
                toPay = priceOfFour;
        }

        return toPay;
    }

}
