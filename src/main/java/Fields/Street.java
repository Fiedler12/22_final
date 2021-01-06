package Fields;

import java.awt.*;

public class Street extends Ownable {
    public int currentRent;
    String name;
    String subText;
    String description;
    String rent;
    Color mainColor;
    int pawnValue;
    int houseCount;
    int housePrice;
    int firstHouse;
    int secondHouse;
    int thirdHouse;
    int fourthHouse;
    int hotel;

    public Street(int price, String name, String subText, String description, String rent, Color mainColor, int currentRent, int pawnValue, int housePrice, int firstHouse, int secondHouse, int thirdHouse, int fourthHouse, int hotel) {
        super(price);
        this.name = name;
        this.subText = subText;
        this. description = description;
        this.rent = rent;
        this.mainColor = mainColor;
        houseCount = 0;
        this.currentRent = currentRent;
        this.pawnValue = pawnValue;
        this.housePrice = housePrice;
        this.firstHouse = firstHouse;
        this.secondHouse = secondHouse;
        this.thirdHouse = thirdHouse;
        this.fourthHouse = fourthHouse;
        this.hotel = hotel;
    }

    public int getCurrentRent() {
        return currentRent;
    }

    public String getName() {
        return name;
    }

    public Color getMainColor() {
        return mainColor;
    }

    public int getPawnValue() {
        return pawnValue;
    }
    public void build (int houseAmount) {
        houseCount = houseCount + houseAmount;
        if (houseCount == 1) {
            currentRent = firstHouse;
        }
        if (houseCount == 2) {
            currentRent = secondHouse;
        }
        if (houseCount == 3) {
            currentRent = thirdHouse;
        }
        if (houseCount == 4) {
            currentRent = fourthHouse;
        }
        if (houseCount == 5) {
            currentRent = hotel;
        }
    }


}


