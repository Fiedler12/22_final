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
                    gui_fields[5] = new GUI_Shipping("default","Scandlines","4.000 kr","Leje: 500 kr. \nHvis 2 redderier ejes: 1.000 kr \nHvis 3 redderier ejes: 2.000 kr \nHvis 4 redderier ejes: 4.000 kr","", new Color(13,90,184),Color.black);
                    gui_fields[15] = new GUI_Shipping("default","Mols-linien","4.000 kr","Leje: 500 kr. \nHvis 2 redderier ejes: 1.000 kr \nHvis 3 redderier ejes: 2.000 kr\nHvis 4 redderier ejes: 4.000 kr","", new Color(13,90,184),Color.black);
                    gui_fields[25] = new GUI_Shipping("default","Scandlines","4.000 kr","Leje: 500 kr. \nHvis 2 redderier ejes: 1.000 kr \nHvis 3 redderier ejes: 2.000 kr\nHvis 4 redderier ejes: 4.000 kr","", new Color(13,90,184),Color.black);
                    gui_fields[35] = new GUI_Shipping("default","Scandlines","4.000 kr","Leje: 500 kr. \nHvis 2 redderier ejes: 1.000 kr \nHvis 3 redderier ejes: 2.000 kr \nHvis 4 redderier ejes: 4.000 kr","", new Color(13,90,184),Color.black);

                }
                checkSubClass = (board.getFields()[i] instanceof Brewery);
                if (checkSubClass) {
                    Brewery brewery = (Brewery) board.getFields()[i];
                    gui_fields[12] = new GUI_Brewery("default", "Tuborg", "3.000 kr","Hvis en virksomhed ejes betales 100 gange så meget som øjnene viser. \n \nHvis både Tuborg og Carlsberg ejes betakes 200 gange så meget som øjnene viser","",new Color(16,88,5),Color.BLACK);
                    gui_fields[28] = new GUI_Brewery("default", "Carlsberg", "3.000 kr","Hvis en virksomhed ejes betales 100 gange så meget som øjnene viser. \n \nHvis både Tuborg og Carlsberg ejes betakes 200 gange så meget som øjnene viser","",new Color(16,88,5),Color.BLACK);
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
                    gui_fields[4] = new GUI_Tax("Betal indkomstskat", "","Du skal enten betale 10% af dine værdier eller 4.000 kr",new Color(100,225,247),Color.BLACK);
                    gui_fields[38] = new GUI_Tax("Ekstraordinær statskat", "Betal 2.000 kr","Du betaler nu 2.000kr til banken.",new Color(100,225,247),Color.BLACK);
                }
                i++;
            }
        }

    public GUI_Field[] getGui_fields() {
        return gui_fields;
    }

    public Field[] getField() {return board.fields;}

    public void checkFields() {
        Street rødorvevej = (Street) board.getFields()[1];
        Street hvidovrevej = (Street) board.getFields()[3];
        if (rødorvevej.getOwnedID() == hvidovrevej.getOwnedID() && rødorvevej.getOwnedID() != -1) {
            rødorvevej.setCanBuild(true);
            hvidovrevej.setCanBuild(true);
        }
        else {
            rødorvevej.setCanBuild(false);
            hvidovrevej.setCanBuild(false);
        }
        Street roskildevej = (Street) board.getFields()[6];
        Street valbyLanggade = (Street) board.getFields()[8];
        Street allegade = (Street) board.getFields()[9];

        if (roskildevej.getOwnedID() > -1 && roskildevej.getOwnedID() == valbyLanggade.getOwnedID() && valbyLanggade.getOwnedID() == allegade.getOwnedID()) {
          roskildevej.setCanBuild(true);
          valbyLanggade.setCanBuild(true);
          allegade.setCanBuild(true);
        }
        else {
            roskildevej.setCanBuild(false);
            valbyLanggade.setCanBuild(false);
            allegade.setCanBuild(false);
        }
        Street frederiksbergAlle = (Street) board.getFields()[11];
        Street bulowsvej = (Street) board.getFields()[13];
        Street glKongevej = (Street) board.getFields()[14];

        if (frederiksbergAlle.getOwnedID() < -1 && frederiksbergAlle.getOwnedID() == bulowsvej.getOwnedID() && bulowsvej.getOwnedID() == glKongevej.getOwnedID()) {
            frederiksbergAlle.setCanBuild(true);
            bulowsvej.setCanBuild(true);
            glKongevej.setCanBuild(true);
        } else {
            frederiksbergAlle.setCanBuild(false);
            bulowsvej.setCanBuild(false);
            glKongevej.setCanBuild(false);
        }
        Street bernstorffsvej = (Street) board.getFields()[16];
        Street hellerupvej = (Street) board.getFields()[18];
        Street strandvejen  = (Street) board.getFields()[19];

        if (bernstorffsvej.getOwnedID() < -1 && bernstorffsvej.getOwnedID() == hellerupvej.getOwnedID() && hellerupvej.getOwnedID() == strandvejen.getOwnedID()) {
            bernstorffsvej.setCanBuild(true);
            bulowsvej.setCanBuild(true);
            glKongevej.setCanBuild(true);
        } else {
            bernstorffsvej.setCanBuild(false);
            hellerupvej.setCanBuild(false);
            glKongevej.setCanBuild(false);
        }
        Street trianglen = (Street) board.getFields()[21];
        Street osterbrogade = (Street) board.getFields()[23];
        Street gronningen = (Street) board.getFields()[24];
        if (trianglen.getOwnedID() < -1 && trianglen.getOwnedID() == osterbrogade.getOwnedID() && osterbrogade.getOwnedID() == gronningen.getOwnedID()) {
            trianglen.setCanBuild(true);
            osterbrogade.setCanBuild(true);
            gronningen.setCanBuild(true);
        } else {
            trianglen.setCanBuild(false);
            osterbrogade.setCanBuild(false);
            gronningen.setCanBuild(false);
        }
        Street bredgade = (Street) board.getFields()[26];
        Street kgsNytorv = (Street) board.getFields()[27];
        Street ostergade = (Street) board.getFields()[29];
        if(bredgade.getOwnedID() < -1 && bredgade.getOwnedID() == kgsNytorv.getOwnedID() && kgsNytorv.getOwnedID() == ostergade.getOwnedID()) {
            bredgade.setCanBuild(true);
            kgsNytorv.setCanBuild(true);
            ostergade.setCanBuild(true);
        } else {
            bredgade.setCanBuild(false);
            kgsNytorv.setCanBuild(false);
            ostergade.setCanBuild(false);
        }
        Street amagerTorv = (Street) board.getFields()[31];
        Street vimmelskaftet = (Street) board.getFields()[32];
        Street nygade = (Street) board.getFields()[34];
        if(amagerTorv.getOwnedID() < -1 && amagerTorv.getOwnedID() == vimmelskaftet.getOwnedID() && vimmelskaftet.getOwnedID() == nygade.getOwnedID()) {
            amagerTorv.setCanBuild(true);
            vimmelskaftet.setCanBuild(true);
            nygade.setCanBuild(true);
        } else {
            amagerTorv.setCanBuild(false);
            vimmelskaftet.setCanBuild(false);
            nygade.setCanBuild(false);
        }
        Street frederiksberggade = (Street) board.getFields()[37];
        Street radhuspladsen = (Street) board.getFields()[39];
        if(frederiksberggade.getOwnedID() < -1 && frederiksberggade.getOwnedID() == radhuspladsen.getOwnedID()) {
            frederiksberggade.setCanBuild(true);
            radhuspladsen.setCanBuild(true);
        } else {
            frederiksberggade.setCanBuild(false);
            radhuspladsen.setCanBuild(false);
        }

    }

}
