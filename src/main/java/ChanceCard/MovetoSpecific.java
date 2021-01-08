package ChanceCard;

public class MovetoSpecific extends Move {
    int fieldID;

    public MovetoSpecific (String text, int ID, int fieldID){
        super(text, ID, fieldID);
    }

    @Override
    public int getSpecific(int pos) {
        return this.fieldID;
    }
}
