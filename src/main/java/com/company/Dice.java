package com.company;

public class Dice {
        int dye1;  //Første terning
        int dye2;


        public Dice() {   //roll funktion
            roll();
        }

        public void roll() {  /*Vi får roll funktionen til at sørge for at hver terning giver os et tilfældigt
        tal mellem 1-6 for hver gang vi roller. Det er også nemt at ændre værdien af terningen
        og det er også nemt at sørge for at hver terning har et vidst antal øjne*/
            dye1 = (int) (Math.random() * 6 + 1);
            dye2 = (int) (Math.random() * 6 + 1);

        }

        public int getTotal() { //Giv et resultat for de 2 terninger lagt sammen
            int total;
            total = dye1 + dye2;
            return total;
        }
}
