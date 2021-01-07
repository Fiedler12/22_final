package Fields;

import gui_fields.*;

public class BoardController {
    Board board;
    GUI_Field[] gui_fields;
    boolean checkSubClass;

    public BoardController() {
            board = new Board();
            gui_fields = new GUI_Field[40];
            int i = 0;
            while (i < 40) {
                checkSubClass = (board.getFields()[i] instanceof Start);
                if (checkSubClass) {
                    gui_fields[i] = new GUI_Start();
                }
                checkSubClass = (board.getFields()[i] instanceof Street);
                if (checkSubClass) {
                    Street street = (Street) board.getFields()[i];
                    gui_fields[i] = new GUI_Street(street.name, street.subText, street.description, street.rent, street.mainColor, street.secondaryColor);
                }
                checkSubClass = (board.getFields()[i] instanceof Shipping);
                if (checkSubClass) {
                    Shipping shipping = (Shipping) board.getFields()[i];
                    gui_fields[i] = new GUI_Shipping();
                }
                checkSubClass = (board.getFields()[i] instanceof Brewery);
                if (checkSubClass) {
                    Brewery brewery = (Brewery) board.getFields()[i];
                    gui_fields[i] = new GUI_Brewery();
                }
                checkSubClass = (board.getFields()[i] instanceof Chancefield);
                if (checkSubClass) {
                    Chancefield chancefield = (Chancefield) board.getFields()[i];
                    gui_fields[i] = new GUI_Chance();
                }
                checkSubClass = (board.getFields()[i] instanceof GoToJail);
                if (checkSubClass) {
                    GoToJail goToJail = (GoToJail) board.getFields()[i];
                    gui_fields[i] = new GUI_Jail();
                }
                checkSubClass = (board.getFields()[i] instanceof Jail);
                if (checkSubClass) {
                    Jail jail = (Jail) board.getFields()[i];
                    gui_fields[i] = new GUI_Jail();
                }
                checkSubClass = (board.getFields()[i] instanceof Parking);
                if (checkSubClass) {
                    gui_fields[i] =
                }
                checkSubClass = (board.getFields()[i] instanceof TaxField);
                if (checkSubClass) {
                    gui_fields[i] =
                }
                i++;
            }
        }
    }
