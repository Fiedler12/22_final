package ChanceCard;


public class CardDeck {
    ChanceCard[] cardDeck;
    String T;
    int ID;
    int TopCard;


    public CardDeck() {
        setTopCard(0);
        cardDeck = new ChanceCard[46];
        cardDeck[0] = new IncreasePrice("Oliepriserne er steget, og de skal betale 500 kr pr hus og 2000kr pr hotel", 0, 500,2000);
        cardDeck[1] = new IncreasePrice("Ejendomsskatten er steget. Ekstraudgifterne er 800kr pr hus, 2300kr pr hotel",1,800,2300);
        cardDeck[2] = new PayMoney("De har kørt frem for fuldt stop, betal 1000 kr i bøde",2,1000);
        cardDeck[3] = new PayMoney("Betal for vognvask og smøring 300kr", 3,300);
        cardDeck[4] = new PayMoney("Betal 200 kr for levering af 2 kasser øl",4,200);
        cardDeck[5] = new PayMoney("Betal 3000 for reparation af deres vogn",5,3000);
        cardDeck[6] = new PayMoney("Betal 3000 for reparation af deres vogn",6,3000);
        cardDeck[7] = new PayMoney("De har købt 4 nye dæk til deres vogn",7,1000);
        cardDeck[8] = new PayMoney("De har fået en parkeringskøbe, betal 200 kr i bøde",8,200 );
        cardDeck[9] = new PayMoney("Betal deres bilforsikring",9,1000);
        cardDeck[10] = new PayMoney("De har været udenlands og købt for manger smønger, betal 200kr i told",10,200);
        cardDeck[11] = new PayMoney("Tandlægeregning betal 2000kr",11,2000);
        cardDeck[12] = new RecieveMoney("De har vundet i klasselotteriet. Modtag 500kr",12,500);
        cardDeck[13] = new RecieveMoney("De har vundet i klasselotteriet. Modtag 500kr",13,500);
        cardDeck[14] = new RecieveMoney("De modtager deres aktieudbytte. Motag 1000 kr af banken",14,1000);
        cardDeck[15] = new RecieveMoney("De modtager deres aktieudbytte. Motag 1000 kr af banken",15,1000);
        cardDeck[16] = new RecieveMoney("De modtager deres aktieudbytte. Motag 1000 kr af banken",16,1000);
        cardDeck[17] = new RecieveMoney("Kommunen har eftergivet et kvartals skat. Hæv i banken 3000kr",17,3000);
        cardDeck[18] = new RecieveMoney("De havde en række med elleve rigtige i tipning modtag 1000kr",18,1000);
        cardDeck[19] = new RecieveMoney("Grundet dyrtiden har de fået gageforhøjelse, modtag 1000kr",19,1000);
        cardDeck[20] = new RecieveMoney("Deres præmieobligation er udtrykket, de modtager 1000kr",20,1000);
        cardDeck[21] = new RecieveMoney("Deres præmieobligation er udtrykket, de modtager 1000kr",21,1000);
        cardDeck[22] = new RecieveMoney("De har solgt nogle gamle møbler på auktion, modtag 1000kr",22,1000);
        cardDeck[23] = new RecieveMoney("Værdien af egen avl fra nyttehaven udgør 200kr som de modtager",23,200);
        cardDeck[24] = new RecieveMoney("De modtager Matador-legatet på 40000kr, men kun hvis dine værdier ikke overstiger 15000",24,40000);
        cardDeck[25] = new MoneyFromPlayer("Det er deres fødselsdag, motag 200 kr fra hver medspiller",25,200);
        cardDeck[26] = new MoneyFromPlayer("De har lagt penge ud til et sammenskudsgilde.Mærkværdigvis betaler alle straks, modtag 500kr fra hver medspiller",26,500);
        cardDeck[27] = new MoneyFromPlayer("De skal holde familiefest og får et tilskud fra hver medspiller på 500kr",27,500);
        cardDeck[28] = new MovetoSpecific("Ryk frem til START", 28,39 );//placement for start når vi engang for lavet brattet
        cardDeck[29] = new MovetoSpecific("Ryk frem til START", 29,39 );//placement for start når vi engang for lavet brattet
        cardDeck[30] = new Move("Ryk tre felter frem",30,+3);
        cardDeck[31] = new Move("Ryk tre felter tilbage",31,-3);
        cardDeck[32] = new Move("Ryk tre felter tilbage",32,-3);
        cardDeck[33] = new MovetoSpecific("Ryk frem til Frederiksberg Alle. Hvis De passere START, indkasser da 4000kr.",33,11);
        cardDeck[34] = new MoveToShipping("Ryk frem til det nærmeste rederi og betal ejeren to gange den leje han ellers er berettiget til, hvis selskabet ikke ejes af nogen kan De købe det af banken",34,3);
        cardDeck[35] = new MoveToShipping("Ryk frem til det nærmeste rederi og betal ejeren to gange den leje han ellers er berettiget til, hvis selskabet ikke ejes af nogen kan De købe det af banken",35,3);
        cardDeck[36] = new Move("Tag Mols-Linien, flyt brikken frem og hvis De passerer START indkasser da 4000kr",36,25);
        cardDeck[37] = new MovetoSpecific("Ryk frem til Grønningen, hvis De passere start indkasser da 4000kr.",37,24);
        cardDeck[38] = new MovetoSpecific("Ryk frem til Vimmelskaftet, hvis de passerer start indkasser da 4000kr.",38,32);
        cardDeck[39] = new MoveToShipping("Tag med den nærmeste færge, hvis de passerer start indkasser da 4000kr.",39,3);
        cardDeck[40] = new MovetoSpecific("Ryk frem til Strandvejen. Hvis De passere START, indkasser da 4000kr.",40,19);
        cardDeck[41] = new MovetoSpecific("Tag til Rådhuspladsen",41,39);
        cardDeck[42] = new GetOutOfJailCard("I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det, eller det kan sælges",42,600,true);
        cardDeck[43] = new GetOutOfJailCard("I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det, eller det kan sælges",43,600,true);
        cardDeck[44] = new GoToJailCard("Gå i fængsel, De indkasserer ikke 4000kr for at passere start.", 44,10);
        cardDeck[45] = new GoToJailCard("Gå i fængsel, De indkasserer ikke 4000kr for at passere start.", 45,10);

        draw();
    }
    public void draw() { //returnerer kort tekst og kort ID og vælger det første kort i bunken og sætter et nyt topkort
        this.T = cardDeck[getTopCard()].CardText;
        this.ID = cardDeck[getTopCard()].CardID;
        setTopCard(getTopCard() + 1);
    }

    public String recieveT() {return T; }
    public int recieveID() {return ID; }

    public void mix() {
        int t = 0;
        while (t < 1000) { //blander alle kortene 1000 gange
            int i = (int) (Math.random() * 45);
            int j = (int) (Math.random() * 45);

            ChanceCard e = cardDeck[i];
            ChanceCard f = cardDeck[j];

            cardDeck[i] = f;
            cardDeck[j] = e;
            t++;
        }
    }
    public int getTopCard() {
        return TopCard;
    }

    public void setTopCard(int topKort) {
        this.TopCard = topKort;
        if(topKort > 45) {
            setTopCard(getTopCard() - 45);
        }
    }

    public ChanceCard[] getCardDeck() {
        return cardDeck;
    }
}