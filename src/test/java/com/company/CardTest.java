package com.company;

import ChanceCard.CardDeck;

public class CardTest {
    /*tester vores Chancekortdæk ved at trække 100 kort efter blanding og ser om vores blandingsmetode virker
  og om det giver os kortene i en bestemt rækkefølge*/
    public static void main(String[] args) {
        CardDeck testDeck = new CardDeck();
        testDeck.mix();
        int t = 0;
        while(t < 100) {
            testDeck.draw();
            testDeck.receiveID();
            testDeck.receiveT();
            System.out.println(testDeck.receiveID());
            System.out.println(testDeck.receiveT());
            t++;
        }

    }
}
