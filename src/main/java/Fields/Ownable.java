package Fields;

abstract class Ownable extends Field {
    int price;
    int ownedID;

    public Ownable(int id, int price) {
        super(id);
        this.price = price;
        ownedID = -1;
    }
    public int getOwnedID() {
        return ownedID;
    }

    public void setOwnedID(int ownedID) {
        this.ownedID = ownedID;
    }
}
