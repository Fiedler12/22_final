package Fields;

public abstract class Ownable extends Field {
    int price;
    int ownedID;
    String name;
    public Ownable(String name, int id, int price) {
        super(id);
        this.name = name;
        this.price = price;
        ownedID = -1;
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
        int toPay;
        return toPay;
    }

}
