package ChanceCard;

public class CardTest {
    /*tester vores Chancekortdæk ved at trække 100 kort efter blanding og ser om vores blandingsmetode virker
  og om det giver os kortene i en bestemt rækkefølge*/
    public static void main(String[] args) {
        CardDeck testDeck = new CardDeck();
        testDeck.mix();
        int t = 0;
        while(t < 100) {
            testDeck.draw();
            testDeck.recieveID();
            testDeck.recieveT();
            System.out.println(testDeck.recieveID());
            System.out.println(testDeck.recieveT());
            t++;
        }

    }
}
