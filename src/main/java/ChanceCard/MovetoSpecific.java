package ChanceCard;

public class MovetoSpecific extends ChanceCard {
    int fieldID;

    public MovetoSpecific (String text, int ID, int fieldID){
        super(text, ID);
        this.fieldID = fieldID;
    }

   /* @Override
    public int getSpecific(int pos) {
        return this.fieldID;
    }
*/
    public int getFieldID() {
        return fieldID;
    }
}
