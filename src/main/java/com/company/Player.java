package com.company;

import Fields.Field;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    Account playerAccount = new Account();
    int pos;
    int playerID;
    boolean inJail;
    boolean hasJailCard;
    List<Integer> owns = new ArrayList<Integer>();

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
        if(pos > 39) {
            this.pos = getPos() - 40;
            playerAccount.setBalance(+ 4000);
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
}

