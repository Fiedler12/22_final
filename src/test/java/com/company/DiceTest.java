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
    @Test
    void diceCount() {
        Dice dice = new Dice();
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        int five = 0;
        int six = 0;
        int seven = 0;
        int eight = 0;
        int nine = 0;
        int ten = 0;
        int eleven = 0;
        int twelve = 0;
        for (int i = 0; i < 1000; i++) {
            dice.roll();
            switch (dice.getTotal()) {
                case 1:
                    one++;
                    break;
                case 2:
                    two++;
                    break;
                case 3:
                    three++;
                    break;
                case 4:
                    four++;
                    break;
                case 5:
                    five++;
                    break;
                case 6:
                    six++;
                    break;
                case 7:
                    seven++;
                    break;
                case 8:
                    eight++;
                    break;
                case 9:
                    nine++;
                    break;
                case 10:
                    ten++;
                    break;
                case 11:
                    eleven++;
                    break;
                case 12:
                    twelve++;
                    break;
            }
        }
        System.out.println("1 slået: " + one);
        System.out.println("2 slået: " + two);
        System.out.println("3 slået: " + three);
        System.out.println("4 slået: " + four);
        System.out.println("5 slået: " + five);
        System.out.println("6 slået: " + six);
        System.out.println("7 slået: " + seven);
        System.out.println("8 slået: " + eight);
        System.out.println("9 slået: " + nine);
        System.out.println("10 slået: " + ten);
        System.out.println("11 slået: " + eleven);
        System.out.println("12 slået: " + twelve);
    }
}