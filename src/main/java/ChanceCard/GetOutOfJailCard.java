package ChanceCard;
// Denne klasse nedarver fra Chancekort så vi kan hente kort teksten og kort ID som vi skal bruge ved alle typer chancekort
public class GetOutOfJailCard extends ChanceCard{
    int sellValue;

    // Her har vi så lavet kontruktøren til chancekortne af typen GetOutOfJail med alle de attributer som vi nu mener den får brug for derudover dens nedarvning
    public GetOutOfJailCard (String Text, int ID,int sellValue){
        super(Text,ID);
        this.sellValue = sellValue;
    }
}

