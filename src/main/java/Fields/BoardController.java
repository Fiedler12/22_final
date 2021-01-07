package Fields;

import gui_fields.*;

import java.awt.*;

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
                    gui_fields[i] = new GUI_Start("Start", "Få 4.000 kr", "Tillykke du har nu passeret start og vil nu modtage 4.000 kr", new Color(250,20,5), new Color(255,255,255));
                }
                checkSubClass = (board.getFields()[i] instanceof Street);
                if (checkSubClass) {
                    Street street = (Street) board.getFields()[i];
                    gui_fields[i] = new GUI_Street(street.name, street.subText, street.description, street.rent, street.mainColor, street.secondaryColor);
                }
                checkSubClass = (board.getFields()[i] instanceof Shipping);
                if (checkSubClass) {
                    Shipping shipping = (Shipping) board.getFields()[i];
                    gui_fields[i] = new GUI_Shipping("default","Scandlines","4.000 kr","Leje: 500 kr. \n Hvis 2 redderier ejes: 1.000 \n Hvis 3 redderier ejes: 2.000 \n Hvis 4 redderier ejes: 4.000","", new Color(13,90,184),Color.black);
                    gui_fields[5] = new GUI_Shipping("default", "Scandlines \n Helsingør-\n Helsingborg","4.000 kr","Leje: 500 kr. \n Hvis 2 redderier ejes: 1.000 \n Hvis 3 redderier ejes: 2.000 \n Hvis 4 redderier ejes: 4.000","", new Color(13,90,184),Color.black);
                }
                checkSubClass = (board.getFields()[i] instanceof Brewery);
                if (checkSubClass) {
                    Brewery brewery = (Brewery) board.getFields()[i];
                    gui_fields[i] = new GUI_Brewery();
                }
                checkSubClass = (board.getFields()[i] instanceof Chancefield);
                if (checkSubClass) {
                    Chancefield chancefield = (Chancefield) board.getFields()[i];
                    gui_fields[i] = new GUI_Chance("?","Prøv Lykken", "ta' et chancekort", Color.black, new Color(0,255,0));
                }
                checkSubClass = (board.getFields()[i] instanceof GoToJail);
                if (checkSubClass) {
                    GoToJail goToJail = (GoToJail) board.getFields()[i];
                    gui_fields[i] = new GUI_Jail("default", "Gå i fængsel", "Gå i fængsel", "Du skal i fængsel!\nFor at komme ud af fængslet skal du, i din næste tur, enten betale 1000 kr, bruge et løsladelseskort eller slå 2 ens med terningerne", new Color(125, 125, 125), Color.BLACK);
                }
                checkSubClass = (board.getFields()[i] instanceof Jail);
                if (checkSubClass) {
                    Jail jail = (Jail) board.getFields()[i];
                    gui_fields[i] = new GUI_Jail("default", "Fængsel", "Fængsel", "Du er nu på besøg i fængslet", new Color(125, 125, 125), Color.BLACK);
                }
                checkSubClass = (board.getFields()[i] instanceof Parking);
                if (checkSubClass) {
                    Parking parking = (Parking) board.getFields()[i];
                    gui_fields[i] = new GUI_Refuge("default", "Gratis parkering", "Parkering", "Ta' en pause, du har helle", Color.WHITE, Color.BLACK);
                }
                checkSubClass = (board.getFields()[i] instanceof TaxField);
                if (checkSubClass) {
                    gui_fields[i] = new GUI_Tax();

                }
                i++;
            }
        }

    public GUI_Field[] getGui_fields() {
        return gui_fields;
    }
}
