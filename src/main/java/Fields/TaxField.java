package Fields;

public class TaxField extends Field{
    public TaxField(int id, boolean percentage, int taxPrice){
        super(id);
        setPayTax(true);
        this.percentage = percentage;
        this.taxPrice = taxPrice;
        if (percentage){
            tenPercent = 10;
        }
    }

    boolean payTax;
    boolean percentage;
    int taxPrice;
    int tenPercent;

    public boolean isPercentage() {
        return percentage;
    }

    public void setPercentage(boolean percentage) {
        this.percentage = percentage;
    }

    public boolean isPayTax() {
        return payTax;
    }
    public void setPayTax(boolean payTax) {
        this.payTax = payTax;
    }

}
