package Fields;

public class TaxField {
    boolean payTax;
    boolean percentage;
    int taxPrice;
    int tenPercent;

    public TaxField(boolean percentage, int taxPrice){
        this.percentage = percentage;
        this.taxPrice = taxPrice;
        if (percentage){
            tenPercent = 10;
        }
    }

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
