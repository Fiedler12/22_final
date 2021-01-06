package com.company;

public class Chance {
    String kortTekst;
    int kortID;

    public Chancekort(String kortTekst, int kortID) {
        this.kortTekst = kortTekst;
        this.kortID = kortID;
    }

    public String getKortTekst() {
        return kortTekst;
    }

    public int getKortID() {
        return kortID;
    }
}



}
