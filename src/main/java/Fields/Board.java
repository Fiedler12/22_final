package Fields;


import jdk.tools.jaotc.binformat.GotSymbol;

import java.io.StreamTokenizer;

public class Board extends Field {
    Field[] Board;

    public Board() {
        Board = new Field[40];
        int i = 0;
            Board[i] = new Start();
            Board[i++] = new Street(1200,"Rødovrevej",);
            Board[i++] = new Chancefield();
            Board[i++] = new Street(1200,"Hvidovrevej",);
            Board[i++] = new TaxField();
            Board[i++] = new Shipping(4000,"Scandlines");
            Board[i++] = new Street(2000,"Roskildevej");
            Board[i++] = new Chancefield();
            Board[i++] = new Street(2000,"Valbylanggade");
            Board[i++] = new Street(2400,"Allegade");
            Board[i++] = new Jail();
            Board[i++] = new Street(2800,"Frederiksbergalle");
            Board[i++] = new Brewery(3000);
            Board[i++] = new Street(2800,"Bulowsvej");
            Board[i++] = new Street(3200,"Gl.Kongevej");
            Board[i++] = new Shipping(4000,"Mols");
            Board[i++] = new Street(3600,"Berlstoffsvej");
            Board[i++] = new Chancefield();
            Board[i++] = new Street(3600,"Hellerupvej");
            Board[i++] = new Street(4000,"Strandvejen");
            Board[i++] = new Parking();
            Board[i++] = new Street(4400,"Trianglen");
            Board[i++] = new Chancefield();
            Board[i++] = new Street(4400,"Østerbrogade");
            Board[i++] = new Street(4800,"Grønningen");
            Board[i++] = new Shipping(4000,"Scandlines2");
            Board[i++] = new Street(5200,"Bredgade");
            Board[i++] = new Street(5200,"Kgs.Nytorv");
            Board[i++] = new Brewery(3000);
            Board[i++] = new Street(5600,"Østergade");
            Board[i++] = new GoToJail();
            Board[i++] = new Street(6000,"Amagertorv");
            Board[i++] = new Street(6000,"Vimmelskaftet");
            Board[i++] = new Chancefield();
            Board[i++] = new Street(6400,"Nygade");
            Board[i++] = new Shipping(4000,"Scandlines3");
            Board[i++] = new Chancefield();
            Board[i++] = new Street(7000,"Frederiksberggade");
            Board[i++] = new TaxField();
            Board[i++] = new Street(8000,"Rådhuspladsen");





    }

}
