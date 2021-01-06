package Fields;


import jdk.tools.jaotc.binformat.GotSymbol;

import java.awt.*;
import java.io.StreamTokenizer;

public class Board {
    Field[] board;

    public Board() {
        board = new Field[40];

        int i = 0;
            board[i] = new Start(i);
            board[i++] = new Street(i,1200, "Rødovrevej", ".", "Leje af grund: 50 kr. \n m/ 1 hus: 250 \n 2 huse: 750 \n 3 huse: 2.225 \n 4 huse: 4.000 \n hotel: 6.000", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Chancefield(i);
            board[i++] = new Street(i,1200, "Hvidovrevej", ".", "Leje af grund: 50 kr. \n m/ 1 hus: 250 \n 2 huse: 750 \n 3 huse: 2.225 \n 4 huse: 4.000 \n hotel: 6000", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new TaxField(i, true, 4000);
            board[i++] = new Shipping(i, 4000,"Scandlines");
            board[i++] = new Street(i,2000, "Roskildevej", ".", "Leje af grund: 100 kr. \n m/ 1 hus: 600 \n 2 huse: 1.800 \n 3 huse: 5.400 \n 4 huse: 8.000 \n hotel: 11.000", "", Color.orange, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Chancefield(i);
            board[i++] = new Street(i,2000, "Valby Langgade", ".", "Leje af grund: 100 kr. \n m/ 1 hus: 600 \n 2 huse: 1.800 \n 3 huse: 5.400 \n 4 huse: 8.000 \n hotel: 11.000", "", Color.orange, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Street(i,2400, "Allégade", ".", "Leje af grund: 150 kr. \n m/ 1 hus: 800 \n 2 huse: 2.000 \n 3 huse: 6.000 \n 4 huse: 9.000 \n hotel: 12.000", "", Color.orange, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Jail(i);
            board[i++] = new Street(i,2800, "Frederiksberg Allé", ".", "Leje af grund: 200 kr. \n m/ 1 hus: 1.000 \n 2 huse: 3.000 \n 3 huse: 9.000 \n 4 huse: 12.500 \n hotel: 15.000", "", Color.yellow, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Brewery(i, 3000, 10);
            board[i++] = new Street(i,2800, "Bülowsvej", ".", "Leje af grund: 200 kr. \n m/ 1 hus: 1.000 \n 2 huse: 3.000 \n 3 huse: 9.000 \n 4 huse: 12.500 \n hotel: 15.000", "", Color.yellow, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Street(i,3200, "Gl. Kongevej", ".", "Leje af grund: 250 kr. \n m/ 1 hus: 1.250 \n 2 huse: 3.750 \n 3 huse: 10.000 \n 4 huse: 14.000 \n hotel: 18.000", "", Color.yellow, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Shipping(i, 4000,"Molslinjen");
            board[i++] = new Street(i,3600, "Bernstorffsvej", ".", "Leje af grund: 300 kr. \n m/ 1 hus: 1.400 \n 2 huse: 4.000 \n 3 huse: 11.000 \n 4 huse: 15.000 \n hotel: 19.000", "", Color.gray, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Chancefield(i);
            board[i++] = new Street(i,3600, "Hellerupvej", ".", "Leje af grund: 300 kr. \n m/ 1 hus: 1.400 \n 2 huse: 4.000 \n 3 huse: 11.000 \n 4 huse: 15.000 \n hotel: 19.000", "", Color.gray, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Street(i,4000, "Strandvejen", ".", "Leje af grund: 350 kr. \n m/ 1 hus: 1.600 \n 2 huse: 4.400 \n 3 huse: 12.000 \n 4 huse: 16.000 \n hotel: 20.000", "", Color.gray, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Parking(i);
            board[i++] = new Street(i,4400, "Trianglen", ".", "Leje af grund: 350 kr. \n m/ 1 hus: 1.800 \n 2 huse: 5.000 \n 3 huse: 14.000 \n 4 huse: 17.500 \n hotel: 21.000", "", Color.red, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Chancefield(i);
            board[i++] = new Street(i,4400, "Østerbrogade", ".", "Leje af grund: 350 kr. \n m/ 1 hus: 1.800 \n 2 huse: 5.000 \n 3 huse: 14.000 \n 4 huse: 17.500 \n hotel: 21.000", "", Color.red, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Street(i,4800, "Grønningen", ".", "Leje af grund: 400kr. \n m/ 1 hus: 2.000 \n 2 huse: 6.000 \n 3 huse: 15.000 \n 4 huse: 18.500 \n hotel: 22.000", "", Color.red, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Shipping(i, 4000,"Scandlines2");
            board[i++] = new Street(i,5200, "Bredgade", ".", "Leje af grund: 450kr. \n m/ 1 hus: 2.200 \n 2 huse: 6.600 \n 3 huse: 16.000 \n 4 huse: 19.500 \n hotel: 23.000", "", Color.white, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Street(i,5200, "Kgs. Nytorv", ".", "Leje af grund: 450kr. \n m/ 1 hus: 2.200 \n 2 huse: 6.600 \n 3 huse: 16.000 \n 4 huse: 19.500 \n hotel: 23.000", "", Color.white, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Brewery(i, 3000, 10);
            board[i++] = new Street(i,5800, "Østergade", ".", "Leje af grund: 500kr. \n m/ 1 hus: 2.400 \n 2 huse: 7.200 \n 3 huse: 17.000 \n 4 huse: 20.500 \n hotel: 24.000", "", Color.white, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new GoToJail(i);
            board[i++] = new Street(i,6000, "Amagertorv", ".", "Leje af grund: 550kr. \n m/ 1 hus: 2.600 \n 2 huse: 7.800 \n 3 huse: 18.000 \n 4 huse: 22.000 \n hotel: 25.000", "", Color.yellow, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Street(i,6000, "Vimmelskaftet", ".", "Leje af grund: 550kr. \n m/ 1 hus: 2.600 \n 2 huse: 7.800 \n 3 huse: 18.000 \n 4 huse: 22.000 \n hotel: 25.000", "", Color.yellow, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Chancefield(i);
            board[i++] = new Street(i,6400, "Nygade", ".", "Leje af grund: 600kr. \n m/ 1 hus: 3.000 \n 2 huse: 9.000 \n 3 huse: 20.000 \n 4 huse: 24.000 \n hotel: 28.000", "", Color.yellow, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Shipping(i, 4000,"Scandlines3");
            board[i++] = new Chancefield(i);
            board[i++] = new Street(i,7000, "Frederiksberggade", ".", "Leje af grund: 700kr. \n m/ 1 hus: 3.500 \n 2 huse: 10.000 \n 3 huse: 22.000 \n 4 huse: 26.000 \n hotel: 30.000", "", Color.PINK, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new TaxField(i, false, 4000);
            board[i++] = new Street(i,8000, "Rådhuspladsen", ".", "Leje af grund: 1.000kr. \n m/ 1 hus: 4.000 \n 2 huse: 12.000 \n 3 huse: 28.000 \n 4 huse: 34.000 \n hotel: 40.000", "", Color.PINK, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
    }

}
