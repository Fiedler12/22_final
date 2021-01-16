package com.company;
import org.junit.jupiter.api.Test;

class DiceTest {

    @Test
    void dice() {
        Dice dice = new Dice();
        int t = 0;
        while (t < 100){
            dice.roll();
            dice.getTotal();
            if (dice.getTotal() >= 2 && dice.getTotal() <= 12){
                System.out.println("Good Dice");
            }
            else {
                System.out.println("BAD DICE");
            }
            if (dice.die1 >=1 && dice.die1 <= 6) {
                System.out.println("Good Die1");
            }
            else {
                System.out.println("BAD DIE1");
            }
            if (dice.die2 >=1 && dice.die2 <= 6){
                System.out.println("Good Die2");
            }
            else {
                System.out.println("BAD DIE2");
            }
            t++;
        }
    }
}