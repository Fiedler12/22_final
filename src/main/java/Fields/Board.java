package Fields;
import java.awt.*;
public class Board {
    Field[] fields;
    public Board() {
        fields = new Field[40];
            fields[0] = new Start(0);
            fields[1] = new Street("Rødovrevej", 1,1200, "1.200 kr", "Leje af grund: 50 kr. \n m/ 1 hus: 250 \n 2 huse: 750 \n 3 huse: 2.225 \n 4 huse: 4.000 \n hotel: 6.000", "", new Color(13,90,184), Color.black, 0, 50, 1000, 250, 750, 2250, 4000, 6000);
            fields[2] = new Chancefield(2);
            fields[3] = new Street("Hvidovrevej", 3,1200, "1.200 kr", "Leje af grund: 50 kr. \n m/ 1 hus: 250 \n 2 huse: 750 \n 3 huse: 2.225 \n 4 huse: 4.000 \n hotel: 6000", "", new Color(13,90,184), Color.black, 0, 50, 1000, 250, 750, 2250, 4000, 6000);
            fields[4] = new TaxField(4, true, 4000);
            fields[5] = new Shipping("Scandlines Helsingør-Helsingborg", 5, 4000,"4.000", "her skal betales","");
            fields[6] = new Street("Roskildevej", 6,2000, "2.000 kr", "Leje af grund: 100 kr. \n m/ 1 hus: 600 \n 2 huse: 1.800 \n 3 huse: 5.400 \n 4 huse: 8.000 \n hotel: 11.000", "", new Color(250,145,10), Color.black, 0, 100, 2000, 1000, 3000, 9000, 12500, 15000);
            fields[7] = new Chancefield(7);
            fields[8] = new Street("Valby Langgade", 8,2000, "2.000 kr", "Leje af grund: 100 kr. \n m/ 1 hus: 600 \n 2 huse: 1.800 \n 3 huse: 5.400 \n 4 huse: 8.000 \n hotel: 11.000", "", new Color(250,145,10), Color.black, 0, 100, 1000, 600, 3000, 9000, 12500, 15000);
            fields[9] = new Street("Allégade", 9,2400, "2.400 kr", "Leje af grund: 150 kr. \n m/ 1 hus: 800 \n 2 huse: 2.000 \n 3 huse: 6.000 \n 4 huse: 9.000 \n hotel: 12.000", "", new Color(250,145,10), Color.black, 0, 150, 2000, 1250, 3750, 10000, 14000, 18000);
            fields[10] = new Jail(10);
            fields[11] = new Street("Frederiksberg Allé",11,2800,  "2.800 kr", "Leje af grund: 200 kr. \n m/ 1 hus: 1.000 \n 2 huse: 3.000 \n 3 huse: 9.000 \n 4 huse: 12.500 \n hotel: 15.000", "", new Color(181,249,47), Color.black, 0, 200, 2000, 250, 750, 2250, 4000, 6000);
            fields[12] = new Brewery("Tuborg", 12, 3000, 10);
            fields[13] = new Street("Bülowsvej",13,2800,  "2.800 kr", "Leje af grund: 200 kr. \n m/ 1 hus: 1.000 \n 2 huse: 3.000 \n 3 huse: 9.000 \n 4 huse: 12.500 \n hotel: 15.000", "", new Color(181,249,47), Color.black, 0, 200, 2000, 250, 750, 2250, 4000, 6000);
            fields[14] = new Street("Gl. Kongevej",14,3200,  "3.200 kr", "Leje af grund: 250 kr. \n m/ 1 hus: 1.250 \n 2 huse: 3.750 \n 3 huse: 10.000 \n 4 huse: 14.000 \n hotel: 18.000", "", new Color(181,249,47), Color.black, 0, 250, 2000, 250, 750, 2250, 4000, 6000);
            fields[15] = new Shipping("Molslinjen", 15, 4000,"4.000", "her skal betales","");
            fields[16] = new Street("Bernstorffsvej",16,3600,  "3.600 kr", "Leje af grund: 300 kr. \n m/ 1 hus: 1.400 \n 2 huse: 4.000 \n 3 huse: 11.000 \n 4 huse: 15.000 \n hotel: 19.000", "", new Color(189,186,183), Color.black, 0, 300, 2000, 1400, 4000, 11000, 15000, 19000);
            fields[17] = new Chancefield(17);
            fields[18] = new Street("Hellerupvej",18,3600,  "3.600 kr", "Leje af grund: 300 kr. \n m/ 1 hus: 1.400 \n 2 huse: 4.000 \n 3 huse: 11.000 \n 4 huse: 15.000 \n hotel: 19.000", "", new Color(189,186,183), Color.black, 0, 300, 2000, 1400, 4000, 11000, 15000, 19000);
            fields[19] = new Street("Strandvejen", 19,4000, "4.000 kr", "Leje af grund: 350 kr. \n m/ 1 hus: 1.600 \n 2 huse: 4.400 \n 3 huse: 12.000 \n 4 huse: 16.000 \n hotel: 20.000", "", new Color(189,186,183), Color.black, 0, 350, 2000, 1600, 4400, 12000, 16000, 20000);
            fields[20] = new Parking(20);
            fields[21] = new Street("Trianglen",21,4400,  "4.400 kr", "Leje af grund: 350 kr. \n m/ 1 hus: 1.800 \n 2 huse: 5.000 \n 3 huse: 14.000 \n 4 huse: 17.500 \n hotel: 21.000", "", Color.red, Color.black, 0, 350, 3000, 1800, 5000, 14000, 17500, 21000);
            fields[22] = new Chancefield(22);
            fields[23] = new Street("Østerbrogade",23,4400,  "4.400 kr", "Leje af grund: 350 kr. \n m/ 1 hus: 1.800 \n 2 huse: 5.000 \n 3 huse: 14.000 \n 4 huse: 17.500 \n hotel: 21.000", "", Color.red, Color.black, 0, 350, 3000, 1800, 5000, 14000, 17500, 21000);
            fields[24] = new Street("Grønningen", 24,4800, "4.800 kr", "Leje af grund: 400kr. \n m/ 1 hus: 2.000 \n 2 huse: 6.000 \n 3 huse: 15.000 \n 4 huse: 18.500 \n hotel: 22.000", "", Color.red, Color.black, 0, 400, 3000, 2000, 6000, 15000, 18500, 22000);
            fields[25] = new Shipping("Scandlines Gedser-Rostock",25, 4000,"4.000", "her skal betales","");
            fields[26] = new Street("Bredgade", 26,5200, "5.200 kr", "Leje af grund: 450kr. \n m/ 1 hus: 2.200 \n 2 huse: 6.600 \n 3 huse: 16.000 \n 4 huse: 19.500 \n hotel: 23.000", "", Color.white, Color.black, 0, 450,  3000, 2200, 6600, 16000, 19500, 23000);
            fields[27] = new Street("Kgs. Nytorv", 27,5200, "5.200 kr", "Leje af grund: 450kr. \n m/ 1 hus: 2.200 \n 2 huse: 6.600 \n 3 huse: 16.000 \n 4 huse: 19.500 \n hotel: 23.000", "", Color.white, Color.black, 0, 450, 3000, 2200, 6600, 16000, 19500, 23000);
            fields[28] = new Brewery("Carlsberg", 28, 3000, 10);
            fields[29] = new Street("Østergade", 29,5800, "5.600 kr", "Leje af grund: 500kr. \n m/ 1 hus: 2.400 \n 2 huse: 7.200 \n 3 huse: 17.000 \n 4 huse: 20.500 \n hotel: 24.000", "", Color.white, Color.black, 0, 500, 3000, 2400, 7200, 17000, 20500, 24000);
            fields[30] = new GoToJail(30,10);
            fields[31] = new Street("Amagertorv", 31,6000, "6.000 kr", "Leje af grund: 550kr. \n m/ 1 hus: 2.600 \n 2 huse: 7.800 \n 3 huse: 18.000 \n 4 huse: 22.000 \n hotel: 25.000", "", Color.yellow, Color.black, 0, 550, 4000, 2600, 7800, 18000, 22000, 25000);
            fields[32] = new Street("Vimmelskaftet", 32,6000, "6.000 kr", "Leje af grund: 550kr. \n m/ 1 hus: 2.600 \n 2 huse: 7.800 \n 3 huse: 18.000 \n 4 huse: 22.000 \n hotel: 25.000", "", Color.yellow, Color.black, 0, 550, 4000, 2600, 7800, 18000, 22000, 25000);
            fields[33] = new Chancefield(33);
            fields[34] = new Street("Nygade", 34,6400, "6.400 kr", "Leje af grund: 600kr. \n m/ 1 hus: 3.000 \n 2 huse: 9.000 \n 3 huse: 20.000 \n 4 huse: 24.000 \n hotel: 28.000", "", Color.yellow, Color.black, 0, 600, 4000, 3000, 9000, 20000, 24000, 28000);
            fields[35] = new Shipping("Scandlines Rødby-Puttgarden",35, 4000,"4.000", "her skal betales","");
            fields[36] = new Chancefield(36);
            fields[37] = new Street("Frederiksberggade", 37,7000, "7.000 kr", "Leje af grund: 700kr. \n m/ 1 hus: 3.500 \n 2 huse: 10.000 \n 3 huse: 22.000 \n 4 huse: 26.000 \n hotel: 30.000", "", new Color(161,1,236), Color.black, 0, 700, 4000, 3500, 10000, 22000, 26000, 30000);
            fields[38] = new TaxField(38, false, 2000);
            fields[39] = new Street("Rådhuspladsen", 39,8000, "8.000 kr", "Leje af grund: 1.000kr. \n m/ 1 hus: 4.000 \n 2 huse: 12.000 \n 3 huse: 28.000 \n 4 huse: 34.000 \n hotel: 40.000", "", new Color(161,1,236), Color.black, 0, 1000, 4000,4000 , 12000, 28000, 34000, 40000);
    }
    public Field[] getFields() {
        return fields;
    }


}
