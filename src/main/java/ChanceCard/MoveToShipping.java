package ChanceCard;

import Fields.Board;
import Fields.Shipping;
import com.company.Player;
import com.company.PlayerController;
import gui_fields.GUI_Field;


public class MoveToShipping extends ChanceCard {
    Board board = new Board();
    PlayerController playerController = new PlayerController();



    public MoveToShipping(String Text, int ID,int playerIndex) {
        super(Text, ID);



    }
}