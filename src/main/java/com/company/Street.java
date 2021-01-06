package com.company;

import java.awt.*;

public class Street {
    public int currentRent;
    String name;
    Color mainColor;
    int pawnValue;
    int houseCount;
    int housePrice;
    int firstHouse;
    int secondHouse;
    int thirdHouse;
    int fourthHouse;
    int hotel;

    public Street(String name, Color mainColor, int currentRent, int pawnValue, int housePrice, int firstHouse, int secondHouse, int thirdHouse, int fourthHouse, int hotel) {
        super();
        this.name = name;
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
    public void build(int houseAmount) {
        houseCount = houseCount + houseAmount;
    }


}


