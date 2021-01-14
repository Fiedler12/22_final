package com.company;

import Fields.Field;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    Account playerAccount = new Account();
    int pos;
    int playerID;
    int shippingOwned;
    int breweryOwned;
    boolean inJail;
    boolean hasJailCard;
    boolean bankrupt;
    List<Integer> owns = new ArrayList<Integer>();
    List<Integer> pawned = new ArrayList<Integer>();


    public boolean isHasJailCard() {
        return hasJailCard;
    }

    public void setHasJailCard(boolean hasJailCard) {
        this.hasJailCard = hasJailCard;
    }

    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
        if (pos > 39) {
            this.pos = getPos() - 40;
            playerAccount.setBalance(playerAccount.getBalance() + 4000);
        }
        if (pos < 0) {
            this.pos = getPos() + 40;
        }
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }
    /* Her sørger vi for at når "askname" bliver kaldt på i andre klasser, så bliver det næste input spilleren laver
    det navnet bliver sat til */

    //Her gør vi så når man skriver getNavn så får man det definerede Navn
    public String getName() {
        return name;
    }

    //Her gør vi så når man skriver setNavn så definerer vi navnet
    public void setName(String name) {
        this.name = name;
    }

    public void buys (int fieldID, int price) {
        owns.add(fieldID);
        playerAccount.setBalance(playerAccount.getBalance() - price);
    }

    public void sells (int fieldID,int price) {
        owns.remove((Integer)fieldID);
        playerAccount.setBalance(playerAccount.getBalance() + price);
    }

    public int[] getOwns(int index) {
        int i = 0;
        int[] ownedIDs;
        ownedIDs = new int[owns.size()];
        while(i < owns.size()) {
            ownedIDs[i] = owns.get(i);
            i++;
        }
        return ownedIDs;
    }

    public void pawns (int fieldID, int value) {
        pawned.add(fieldID);
        playerAccount.setBalance(playerAccount.getBalance() + value);
        owns.remove((Integer)fieldID);
    }

    public void buyBackPawn (int fieldID, int value) {
        owns.add(fieldID);
        playerAccount.setBalance(playerAccount.getBalance() - value);
        pawned.remove((Integer)fieldID);
    }
    public int getShippingOwned() {
        return shippingOwned;
    }

    public void setShippingOwned(int shippingOwned) {
        this.shippingOwned = shippingOwned;
    }

    public int getBreweryOwned() {
        return breweryOwned;
    }

    public void setBreweryOwned(int breweryOwned) {
        this.breweryOwned = breweryOwned;
    }

    public boolean isBankrupt() {
        return bankrupt;
    }

    public void setBankrupt(boolean bankrupt) {
        this.bankrupt = bankrupt;
    }
}


