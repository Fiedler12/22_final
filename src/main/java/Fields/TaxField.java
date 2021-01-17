package Fields;

public class TaxField extends Field{
    public TaxField(int id, boolean percentage, int taxPrice){
        super(id);
        this.percentage = percentage;
        this.taxPrice = taxPrice;
        if (percentage){
            tenPercent = 10;
        }
    }
    boolean percentage;
    int taxPrice;
    int tenPercent;

    public int getTaxPrice() {
        return taxPrice;
    }

}
