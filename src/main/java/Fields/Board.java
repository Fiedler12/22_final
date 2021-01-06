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
            board[i++] = new Street(i,1200, "Rødovrevej", ".", ".", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Chancefield(i);
            board[i++] = new Street(i,1200, "Hvidovrevej", ".", ".", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new TaxField(i);
            board[i++] = new Shipping(4000,"Scandlines");
            board[i++] = new Street(i,2000, "Hvidovrevej", ".", ".", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Chancefield(i);
            board[i++] = new Street(i,2000, "Hvidovrevej", ".", ".", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Street(i,2400, "Hvidovrevej", ".", ".", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Jail(i);
            board[i++] = new Street(i,2800, "Hvidovrevej", ".", ".", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Brewery(3000);
            board[i++] = new Street(i,2800, "Hvidovrevej", ".", ".", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Street(i,3200, "Hvidovrevej", ".", ".", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Shipping(4000,"Mols");
            board[i++] = new Street(i,3600, "Hvidovrevej", ".", ".", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Chancefield();
            board[i++] = new Street(i,3600, "Hvidovrevej", ".", ".", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Street(i,4000, "Hvidovrevej", ".", ".", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Parking(i);
            board[i++] = new Street(i,4400, "Hvidovrevej", ".", ".", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Chancefield(i);
            board[i++] = new Street(i,4400, "Hvidovrevej", ".", ".", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Street(i,4800, "Hvidovrevej", ".", ".", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Shipping(,"Scandlines2");
            board[i++] = new Street(i,5200, "Hvidovrevej", ".", ".", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Street(i,5200, "Hvidovrevej", ".", ".", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Brewery(3000);
            board[i++] = new Street(i,5800, "Hvidovrevej", ".", ".", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new GoToJail(i);
            board[i++] = new Street(i,6000, "Hvidovrevej", ".", ".", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Street(i,6000, "Hvidovrevej", ".", ".", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Chancefield(i);
            board[i++] = new Street(i,6400, "Hvidovrevej", ".", ".", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new Shipping(4000,"Scandlines3");
            board[i++] = new Chancefield(i);
            board[i++] = new Street(i,7000, "Hvidovrevej", ".", ".", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            board[i++] = new TaxField(i);
            board[i++] = new Street(i,8000, "Hvidovrevej", ".", ".", "", Color.BLUE, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
    }

}
