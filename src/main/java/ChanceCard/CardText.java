package ChanceCard;


public class CardText extends ChanceCard {
    ChanceCard[] CardPile;
    String T;
    int ID;
    int TopCard;


    public ChanceCard() {
        CardPile = new ChanceCard[45];
        int i = 0;
        CardPile[i] = new IncreasePrice("Oliepriserne er steget, og de skal betale 500 kr pr hus og 2000kr pr hotel", i, 500,2000);
        CardPile[i++] = new IncreasePrice("Ejendomsskatten er steget. Ekstraudgifterne er 800kr pr hus, 2300kr pr hotel",i++,800,2300);
        CardPile[i++] = new PayMoney("De har kørt frem for fuldt stop, betal 1000 kr i bøde",i++,1000);
        CardPile[i++] = new PayMoney("Betal for vognvask og smøring 300kr", i++,300);
        CardPile[i++] = new PayMoney("Betal 200 kr for levering af 2 kasser øl",i++,200);
        CardPile[i++] = new PayMoney("Betal 3000 for reparation af deres vogn",i++,3000);
        CardPile[i++] = new PayMoney("Betal 3000 for reparation af deres vogn",i++,3000);
        CardPile[i++] = new PayMoney("De har købt 4 nye dæk til deres vogn",i++,1000);
        CardPile[i++] = new PayMoney("De har fået en parkeringskøbe, betal 200 kr i bøde",i++,200 );
        CardPile[i++] = new PayMoney("Betal deres bilforsikring",i++,1000);
        CardPile[i++] = new PayMoney("De har været udenlands og købt for manger smønger, betal 200kr i told",i++,200);
        CardPile[i++] = new PayMoney("Tandlægeregning betal 2000kr",i++,2000);
        CardPile[i++] = new ReceiveMoney("De har vundet i klasselotteriet. Modtag 500kr",i++,500);
        CardPile[i++] = new ReceiveMoney("De har vundet i klasselotteriet. Modtag 500kr",i++,500);
        CardPile[i++] = new ReceiveMoney("De modtager deres aktieudbytte. Motag 1000 kr af banken",i++,1000);
        CardPile[i++] = new ReceiveMoney("De modtager deres aktieudbytte. Motag 1000 kr af banken",i++,1000);
        CardPile[i++] = new ReceiveMoney("De modtager deres aktieudbytte. Motag 1000 kr af banken",i++,1000);
        CardPile[i++] = new ReceiveMoney("Kommunen har eftergivet et kvartals skat. Hæv i banken 3000kr",i++,3000);
        CardPile[i++] = new ReceiveMoney("De havde en række med elleve rigtige i tipning modtag 1000kr",i++,1000);
        CardPile[i++] = new ReceiveMoney("Grundet dyrtiden har de fået gageforhøjelse, modtag 1000kr",i++,1000);
        CardPile[i++] = new ReceiveMoney("Deres præmieobligation er udtrykket, de modtager 1000kr",i++,1000);
        CardPile[i++] = new ReceiveMoney("Deres præmieobligation er udtrykket, de modtager 1000kr",i++,1000);
        CardPile[i++] = new ReceiveMoney("De har solgt nogle gamle møbler på auktion, modtag 1000kr",i++,1000);
        CardPile[i++] = new ReceiveMoney("Værdien af egen avl fra nyttehaven udgør 200kr som de modtager",i++,200);
        CardPile[i++] = new ReceiveMoney("De modtager Matador-legatet på 40000kr, men kun hvis dine værdier ikke overstiger 15000",i++,40000);
        CardPile[i++] = new MoneyFromPlayer("Det er deres fødselsdag, motag 200 kr fra hver medspiller",i++,200);
        CardPile[i++] = new MoneyFromPlayer("De har lagt penge ud til et sammenskudsgilde.Mærkværdigvis betaler alle straks, modtag 500kr fra hver medspiller",i++,500);
        CardPile[i++] = new MoneyFromPlayer("De skal holde familiefest og får et tilskud fra hver medspiller på 500kr",i++,500);
        CardPile[i++] = new MovetoSpecific("Ryk frem til START", i++,39 );//placement for start når vi engang for lavet brattet
        CardPile[i++] = new Move("Ryk frem til START", i++,39 );//placement for start når vi engang for lavet brattet
        CardPile[i++] = new Move("Ryk tre felter frem",i++,+3);
        CardPile[i++] = new Move("Ryk tre felter tilbage",i++,-3);
        CardPile[i++] = new Move("Ryk tre felter tilbage",i++,-3);
        CardPile[i++] = new Move("Ryk frem til Frederiksberg Alle. Hvis De passere START, indkasser da 4000kr.",i++,38);
        CardPile[i++] = new Move("Ryk frem til det nærmeste rederi og betal ejeren to gange den leje han ellers er berettiget til, hvis selskabet ikke ejes af nogen kan De købe det af banken",i++,shipping);
        CardPile[i++] = new Move("Ryk frem til det nærmeste rederi og betal ejeren to gange den leje han ellers er berettiget til, hvis selskabet ikke ejes af nogen kan De købe det af banken",i++,shipping);
        CardPile[i++] = new Move("Tag Mols-Linien, flyt brikken frem og hvis De passerer START indkasser da 4000kr",i++,25);
        CardPile[i++] = new Move("Ryk frem til Grønningen, hvis De passere start indkasser da 4000kr.",i++,29);
        CardPile[i++] = new Move("Ryk frem til Vimmelskaftet, hvis de passerer start indkasser da 4000kr.",i++,20);
        CardPile[i++] = new Move("Tag med den nærmeste færge, hvis de passerer start indkasser da 4000kr.",i++,shipping);
        CardPile[i++] = new Move("Ryk frem til Strandvejen. Hvis De passere START, indkasser da 4000kr.",i++, 39);
        CardPile[i++] = new Move("Tag til Rådhuspladsen",i++,40);
        CardPile[i++] = new JailCard("I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det, eller det kan sælges",i++,14);
        CardPile[i++] = new JailCard("I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det, eller det kan sælges",i++,14);
        CardPile[i++] = new JailCard("Gå i fængse, De indkasserer ikke 4000kr for at passere start.", i++,14);
        CardPile[i++] = new JailCard("Gå i fængse, De indkasserer ikke 4000kr for at passere start.", i++,14);
    }



}