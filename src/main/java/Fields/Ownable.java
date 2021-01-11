package Fields;

public abstract class Ownable extends Field {
    int price;
    int ownedID;
    String name;
    int toPay;
    int pawnValue;
    public Ownable(String name, int id, int price) {
        super(id);
        this.name = name;
        this.price = price;
        ownedID = -1;
        pawnValue = price / 2; // TODO Eventuelt indlæs fra argumenter.
    }
    public int getOwnedID() {
        return ownedID;
    }

    public void setOwnedID(int ownedID) {
        this.ownedID = ownedID;
    }

    public String getName() { return name; }

    public int getPrice() {return price; }

    public int landOnOwned() {
        return toPay;
    }

    public int getPawnValue() {
        return pawnValue;
    }
}
