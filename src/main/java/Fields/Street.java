package Fields;

import java.awt.*;

public class Street extends Ownable {
    public int currentRent;
    String subText;
    String description;
    String rent;
    Color mainColor;
    Color secondaryColor;
    int houseCount;
    int noHouse;
    int housePrice;
    int firstHouse;
    int secondHouse;
    int thirdHouse;
    int fourthHouse;
    int hotel;
    boolean canBuild;

    public Street(String name, int id, int price, String subText, String description, String rent, Color mainColor, Color secondaryColor, int currentRent, int noHouse, int housePrice, int firstHouse, int secondHouse, int thirdHouse, int fourthHouse, int hotel) {
        super(name, id, price);
        this.noHouse = noHouse;
        this.subText = subText;
        this. description = description;
        this.rent = rent;
        this.mainColor = mainColor;
        this.secondaryColor = secondaryColor;
        houseCount = 0;
        this.currentRent = currentRent;
        this.housePrice = housePrice;
        this.firstHouse = firstHouse;
        this.secondHouse = secondHouse;
        this.thirdHouse = thirdHouse;
        this.fourthHouse = fourthHouse;
        this.hotel = hotel;
        canBuild = false;
    }

    public void setCurrentRent(int currentRent) {
        this.currentRent = currentRent;
    }

    public int getHousePrice() { return housePrice; }

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

    public int getHouseCount() {
        return houseCount;
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
    public void setOwnedID(int ownedID) {
        this.ownedID = ownedID;
        currentRent = noHouse;
    }

    public boolean isCanBuild() {
        return canBuild;
    }

    public void setCanBuild(boolean canBuild) {
        this.canBuild = canBuild;
        if (canBuild) {
            setCurrentRent(noHouse * 2);
        }
        if (!canBuild) {
            currentRent = noHouse;
        }
    }
}


