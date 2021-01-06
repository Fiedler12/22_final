package Fields;


import jdk.tools.jaotc.binformat.GotSymbol;

import java.io.StreamTokenizer;

public class Board {
    Field[] board;

    public Board() {
        board = new Field[40];

        int i = 0;
            board[i] = new Start();
            board[i++] = new Street(1200,"Rødovrevej",);
            board[i++] = new Chancefield();
            board[i++] = new Street(1200,"Hvidovrevej",);
            board[i++] = new TaxField();
            board[i++] = new Shipping(4000,"Scandlines");
            board[i++] = new Street(2000,"Roskildevej");
            board[i++] = new Chancefield();
            board[i++] = new Street(2000,"Valbylanggade");
            board[i++] = new Street(2400,"Allegade");
            board[i++] = new Jail();
            board[i++] = new Street(2800,"Frederiksbergalle");
            board[i++] = new Brewery(3000);
            board[i++] = new Street(2800,"Bulowsvej");
            board[i++] = new Street(3200,"Gl.Kongevej");
            board[i++] = new Shipping(4000,"Mols");
            board[i++] = new Street(3600,"Berlstoffsvej");
            board[i++] = new Chancefield();
            board[i++] = new Street(3600,"Hellerupvej");
            board[i++] = new Street(4000,"Strandvejen");
            board[i++] = new Parking();
            board[i++] = new Street(4400,"Trianglen");
            board[i++] = new Chancefield();
            board[i++] = new Street(4400,"Østerbrogade");
            board[i++] = new Street(4800,"Grønningen");
            board[i++] = new Shipping(4000,"Scandlines2");
            board[i++] = new Street(5200,"Bredgade");
            board[i++] = new Street(5200,"Kgs.Nytorv");
            board[i++] = new Brewery(3000);
            board[i++] = new Street(5600,"Østergade");
            board[i++] = new GoToJail();
            board[i++] = new Street(6000,"Amagertorv");
            board[i++] = new Street(6000,"Vimmelskaftet");
            board[i++] = new Chancefield();
            board[i++] = new Street(6400,"Nygade");
            board[i++] = new Shipping(4000,"Scandlines3");
            board[i++] = new Chancefield();
            board[i++] = new Street(7000,"Frederiksberggade");
            board[i++] = new TaxField();
            board[i++] = new Street(8000,"Rådhuspladsen");
    }

}
