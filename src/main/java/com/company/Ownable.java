package com.company;

public class Ownable extends Field {
    int price;
    int ownedID;

    public Ownable(int price) {
        super();
        this.price = price;
        ownedID = -1;
    }
    public int getOwnedID() {
        return ownedID;
    }

    public void setOwnedID(int ownedID) {
        this.ownedID = ownedID;
    }
}
