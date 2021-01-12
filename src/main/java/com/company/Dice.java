package com.company;

import java.lang.annotation.Native;

public class Dice {
        int die1;  //Første terning
        int die2;


        public Dice() {   //roll funktion
            roll();
        }

        public void roll() {  /*Vi får roll funktionen til at sørge for at hver terning giver os et tilfældigt
        tal mellem 1-6 for hver gang vi roller. Det er også nemt at ændre værdien af terningen
        og det er også nemt at sørge for at hver terning har et vidst antal øjne*/
            die1 = (2);
            die2 = (2);
        }

        public int getTotal() { //Giv et resultat for de 2 terninger lagt sammen
            int total;
            total = die1 + die2;
            return total;
        }
}
