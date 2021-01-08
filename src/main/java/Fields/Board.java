package Fields;
import java.awt.*;
public class Board {
    Field[] fields;

    public Board() {
        fields = new Field[40];
            fields[0] = new Start(0);
            fields[1] = new Street(1,1200, "Rødovrevej", "1.200 kr", "Leje af grund: 50 kr. \n m/ 1 hus: 250 \n 2 huse: 750 \n 3 huse: 2.225 \n 4 huse: 4.000 \n hotel: 6.000", "", new Color(13,90,184), Color.black, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            fields[2] = new Chancefield(2);
            fields[3] = new Street(3,1200, "Hvidovrevej", "1.200 kr", "Leje af grund: 50 kr. \n m/ 1 hus: 250 \n 2 huse: 750 \n 3 huse: 2.225 \n 4 huse: 4.000 \n hotel: 6000", "", new Color(13,90,184), Color.black, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            fields[4] = new TaxField(4, true, 4000);
            fields[5] = new Shipping(5, 4000,"Scandlines Helsingør","4.000", "her skal betales","");
            fields[6] = new Street(6,2000, "Roskildevej", "2.000 kr", "Leje af grund: 100 kr. \n m/ 1 hus: 600 \n 2 huse: 1.800 \n 3 huse: 5.400 \n 4 huse: 8.000 \n hotel: 11.000", "", new Color(250,145,10), Color.black, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            fields[7] = new Chancefield(7);
            fields[8] = new Street(8,2000, "Valby Langgade", "2.000 kr", "Leje af grund: 100 kr. \n m/ 1 hus: 600 \n 2 huse: 1.800 \n 3 huse: 5.400 \n 4 huse: 8.000 \n hotel: 11.000", "", new Color(250,145,10), Color.black, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            fields[9] = new Street(9,2400, "Allégade", "2.400 kr", "Leje af grund: 150 kr. \n m/ 1 hus: 800 \n 2 huse: 2.000 \n 3 huse: 6.000 \n 4 huse: 9.000 \n hotel: 12.000", "", new Color(250,145,10), Color.black, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            fields[10] = new Jail(10);
            fields[11] = new Street(11,2800, "Frederiksberg Allé", "2.800 kr", "Leje af grund: 200 kr. \n m/ 1 hus: 1.000 \n 2 huse: 3.000 \n 3 huse: 9.000 \n 4 huse: 12.500 \n hotel: 15.000", "", new Color(181,249,47), Color.black, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            fields[12] = new Brewery(12, 3000, 10);
            fields[13] = new Street(13,2800, "Bülowsvej", "2.800 kr", "Leje af grund: 200 kr. \n m/ 1 hus: 1.000 \n 2 huse: 3.000 \n 3 huse: 9.000 \n 4 huse: 12.500 \n hotel: 15.000", "", new Color(181,249,47), Color.black, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            fields[14] = new Street(14,3200, "Gl. Kongevej", "3.200 kr", "Leje af grund: 250 kr. \n m/ 1 hus: 1.250 \n 2 huse: 3.750 \n 3 huse: 10.000 \n 4 huse: 14.000 \n hotel: 18.000", "", new Color(181,249,47), Color.black, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            fields[15] = new Shipping(15, 4000,"Molslinjen","4.000", "her skal betales","");
            fields[16] = new Street(16,3600, "Bernstorffsvej", "3.600 kr", "Leje af grund: 300 kr. \n m/ 1 hus: 1.400 \n 2 huse: 4.000 \n 3 huse: 11.000 \n 4 huse: 15.000 \n hotel: 19.000", "", new Color(189,186,183), Color.black, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            fields[17] = new Chancefield(17);
            fields[18] = new Street(18,3600, "Hellerupvej", "3.600 kr", "Leje af grund: 300 kr. \n m/ 1 hus: 1.400 \n 2 huse: 4.000 \n 3 huse: 11.000 \n 4 huse: 15.000 \n hotel: 19.000", "", new Color(189,186,183), Color.black, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            fields[19] = new Street(19,4000, "Strandvejen", "4.000 kr", "Leje af grund: 350 kr. \n m/ 1 hus: 1.600 \n 2 huse: 4.400 \n 3 huse: 12.000 \n 4 huse: 16.000 \n hotel: 20.000", "", new Color(189,186,183), Color.black, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            fields[20] = new Parking(20);
            fields[21] = new Street(21,4400, "Trianglen", "4.400 kr", "Leje af grund: 350 kr. \n m/ 1 hus: 1.800 \n 2 huse: 5.000 \n 3 huse: 14.000 \n 4 huse: 17.500 \n hotel: 21.000", "", Color.red, Color.black, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            fields[22] = new Chancefield(22);
            fields[23] = new Street(23,4400, "Østerbrogade", "4.400 kr", "Leje af grund: 350 kr. \n m/ 1 hus: 1.800 \n 2 huse: 5.000 \n 3 huse: 14.000 \n 4 huse: 17.500 \n hotel: 21.000", "", Color.red, Color.black, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            fields[24] = new Street(24,4800, "Grønningen", "4.800 kr", "Leje af grund: 400kr. \n m/ 1 hus: 2.000 \n 2 huse: 6.000 \n 3 huse: 15.000 \n 4 huse: 18.500 \n hotel: 22.000", "", Color.red, Color.black, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            fields[25] = new Shipping(25, 4000,"Scandlines2","4.000", "her skal betales","");
            fields[26] = new Street(26,5200, "Bredgade", "5.200 kr", "Leje af grund: 450kr. \n m/ 1 hus: 2.200 \n 2 huse: 6.600 \n 3 huse: 16.000 \n 4 huse: 19.500 \n hotel: 23.000", "", Color.white, Color.black, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            fields[27] = new Street(27,5200, "Kgs. Nytorv", "5.200 kr", "Leje af grund: 450kr. \n m/ 1 hus: 2.200 \n 2 huse: 6.600 \n 3 huse: 16.000 \n 4 huse: 19.500 \n hotel: 23.000", "", Color.white, Color.black, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            fields[28] = new Brewery(28, 3000, 10);
            fields[29] = new Street(29,5800, "Østergade", "5.600 kr", "Leje af grund: 500kr. \n m/ 1 hus: 2.400 \n 2 huse: 7.200 \n 3 huse: 17.000 \n 4 huse: 20.500 \n hotel: 24.000", "", Color.white, Color.black, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            fields[30] = new GoToJail(30);
            fields[31] = new Street(31,6000, "Amagertorv", "6.000 kr", "Leje af grund: 550kr. \n m/ 1 hus: 2.600 \n 2 huse: 7.800 \n 3 huse: 18.000 \n 4 huse: 22.000 \n hotel: 25.000", "", Color.yellow, Color.black, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            fields[32] = new Street(32,6000, "Vimmelskaftet", "6.000 kr", "Leje af grund: 550kr. \n m/ 1 hus: 2.600 \n 2 huse: 7.800 \n 3 huse: 18.000 \n 4 huse: 22.000 \n hotel: 25.000", "", Color.yellow, Color.black, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            fields[33] = new Chancefield(33);
            fields[34] = new Street(34,6400, "Nygade", "6.400 kr", "Leje af grund: 600kr. \n m/ 1 hus: 3.000 \n 2 huse: 9.000 \n 3 huse: 20.000 \n 4 huse: 24.000 \n hotel: 28.000", "", Color.yellow, Color.black, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            fields[35] = new Shipping(35, 4000,"Scandlines3","4.000", "her skal betales","");
            fields[36] = new Chancefield(36);
            fields[37] = new Street(37,7000, "Frederiksberggade", "7.000 kr", "Leje af grund: 700kr. \n m/ 1 hus: 3.500 \n 2 huse: 10.000 \n 3 huse: 22.000 \n 4 huse: 26.000 \n hotel: 30.000", "", new Color(161,1,236), Color.black, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
            fields[38] = new TaxField(38, false, 2000);
            fields[39] = new Street(39,8000, "Rådhuspladsen", "8.000 kr", "Leje af grund: 1.000kr. \n m/ 1 hus: 4.000 \n 2 huse: 12.000 \n 3 huse: 28.000 \n 4 huse: 34.000 \n hotel: 40.000", "", new Color(161,1,236), Color.black, 0, 600, 1000, 250, 750, 2250, 4000, 6000);
    }
    public Field[] getFields() {
        return fields;
    }


}
