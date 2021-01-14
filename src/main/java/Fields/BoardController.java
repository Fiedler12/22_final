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
                    gui_fields[5] = new GUI_Shipping("default","Scandlines","4.000 kr","Leje: 500 kr. \nHvis 2 redderier ejes: 1.000 kr \nHvis 3 redderier ejes: 2.000 kr \nHvis 4 redderier ejes: 4.000 kr","", new Color(13,90,184),Color.black);
                    gui_fields[15] = new GUI_Shipping("default","Mols-linien","4.000 kr","Leje: 500 kr. \nHvis 2 redderier ejes: 1.000 kr \nHvis 3 redderier ejes: 2.000 kr\nHvis 4 redderier ejes: 4.000 kr","", new Color(13,90,184),Color.black);
                    gui_fields[25] = new GUI_Shipping("default","Scandlines","4.000 kr","Leje: 500 kr. \nHvis 2 redderier ejes: 1.000 kr \nHvis 3 redderier ejes: 2.000 kr\nHvis 4 redderier ejes: 4.000 kr","", new Color(13,90,184),Color.black);
                    gui_fields[35] = new GUI_Shipping("default","Scandlines","4.000 kr","Leje: 500 kr. \nHvis 2 redderier ejes: 1.000 kr \nHvis 3 redderier ejes: 2.000 kr \nHvis 4 redderier ejes: 4.000 kr","", new Color(13,90,184),Color.black);

                }
                checkSubClass = (board.getFields()[i] instanceof Brewery);
                if (checkSubClass) {
                    gui_fields[12] = new GUI_Brewery("default", "Tuborg", "3.000 kr","Hvis en virksomhed ejes betales 100 gange så meget som øjnene viser. \n \nHvis både Tuborg og Carlsberg ejes betakes 200 gange så meget som øjnene viser","",new Color(16,88,5),Color.BLACK);
                    gui_fields[28] = new GUI_Brewery("default", "Carlsberg", "3.000 kr","Hvis en virksomhed ejes betales 100 gange så meget som øjnene viser. \n \nHvis både Tuborg og Carlsberg ejes betakes 200 gange så meget som øjnene viser","",new Color(16,88,5),Color.BLACK);
                }
                checkSubClass = (board.getFields()[i] instanceof Chancefield);
                if (checkSubClass) {
                    gui_fields[i] = new GUI_Chance("?","Prøv Lykken", "ta' et chancekort", Color.black, new Color(0,255,0));
                }
                checkSubClass = (board.getFields()[i] instanceof GoToJail);
                if (checkSubClass) {
                    gui_fields[i] = new GUI_Jail("default", "Gå i fængsel", "Gå i fængsel", "Du skal i fængsel!\nFor at komme ud af fængslet skal du, i din næste tur, enten betale 1000 kr, bruge et løsladelseskort eller slå 2 ens med terningerne", new Color(125, 125, 125), Color.BLACK);
                }
                checkSubClass = (board.getFields()[i] instanceof Jail);
                if (checkSubClass) {
                    gui_fields[i] = new GUI_Jail("default", "Fængsel", "Fængsel", "Du er nu på besøg i fængslet", new Color(125, 125, 125), Color.BLACK);
                }
                checkSubClass = (board.getFields()[i] instanceof Parking);
                if (checkSubClass) {
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
        Street rodorvevej = (Street) board.getFields()[1];
        GUI_Street guiRodovreVej = (GUI_Street) getGui_fields()[1];
        Street hvidovrevej = (Street) board.getFields()[3];
        GUI_Street guiHvidovreVej = (GUI_Street) getGui_fields()[3];
        if (rodorvevej.getOwnedID() == hvidovrevej.getOwnedID() && rodorvevej.getOwnedID() != -1) {
            rodorvevej.setCanBuild(true);
            guiRodovreVej.setRent(Integer.toString(rodorvevej.currentRent));
            hvidovrevej.setCanBuild(true);
            guiHvidovreVej.setRent(Integer.toString(hvidovrevej.currentRent));

        }
        else {
            rodorvevej.setCanBuild(false);
            guiRodovreVej.setRent(Integer.toString(rodorvevej.currentRent));
            hvidovrevej.setCanBuild(false);
            guiHvidovreVej.setRent(Integer.toString(hvidovrevej.currentRent));
        }
        Street roskildevej = (Street) board.getFields()[6];
        GUI_Street guiRoskildevej = (GUI_Street) getGui_fields()[6];
        Street valbyLanggade = (Street) board.getFields()[8];
        GUI_Street guiValbyLanggade = (GUI_Street) getGui_fields()[8];
        Street allegade = (Street) board.getFields()[9];
        GUI_Street guiAllegade = (GUI_Street) getGui_fields()[9];

        if (roskildevej.getOwnedID() > -1 && roskildevej.getOwnedID() == valbyLanggade.getOwnedID() && valbyLanggade.getOwnedID() == allegade.getOwnedID()) {
          roskildevej.setCanBuild(true);
            guiRoskildevej.setRent(Integer.toString(roskildevej.currentRent));
            valbyLanggade.setCanBuild(true);
            guiValbyLanggade.setRent(Integer.toString(valbyLanggade.currentRent));
            allegade.setCanBuild(true);
            guiAllegade.setRent(Integer.toString(allegade.currentRent));
        }
        else {
            roskildevej.setCanBuild(false);
            guiRoskildevej.setRent(Integer.toString(roskildevej.currentRent));
            valbyLanggade.setCanBuild(false);
            guiValbyLanggade.setRent(Integer.toString(valbyLanggade.currentRent));
            allegade.setCanBuild(false);
            guiAllegade.setRent(Integer.toString(allegade.currentRent));
        }
        Street frederiksbergAlle = (Street) board.getFields()[11];
        GUI_Street guiFrederiksbergAlle = (GUI_Street) getGui_fields()[11];
        Street bulowsvej = (Street) board.getFields()[13];
        GUI_Street guiBulowsvej = (GUI_Street) getGui_fields()[13];
        Street glKongevej = (Street) board.getFields()[14];
        GUI_Street guiGlKongevej = (GUI_Street) getGui_fields()[14];

        if (frederiksbergAlle.getOwnedID() < -1 && frederiksbergAlle.getOwnedID() == bulowsvej.getOwnedID() && bulowsvej.getOwnedID() == glKongevej.getOwnedID()) {
            frederiksbergAlle.setCanBuild(true);
            guiFrederiksbergAlle.setRent(Integer.toString(frederiksbergAlle.currentRent));
            bulowsvej.setCanBuild(true);
            guiBulowsvej.setRent(Integer.toString(bulowsvej.currentRent));
            glKongevej.setCanBuild(true);
            guiGlKongevej.setRent(Integer.toString(glKongevej.currentRent));
        } else {
            frederiksbergAlle.setCanBuild(false);
            guiFrederiksbergAlle.setRent(Integer.toString(frederiksbergAlle.currentRent));
            bulowsvej.setCanBuild(false);
            guiBulowsvej.setRent(Integer.toString(bulowsvej.currentRent));
            glKongevej.setCanBuild(false);
            guiGlKongevej.setRent(Integer.toString(glKongevej.currentRent));
        }
        Street bernstorffsvej = (Street) board.getFields()[16];
        GUI_Street guiBernstroffsVej = (GUI_Street) getGui_fields()[16];
        Street hellerupvej = (Street) board.getFields()[18];
        GUI_Street guiHellerupvej = (GUI_Street) getGui_fields()[18];
        Street strandvejen  = (Street) board.getFields()[19];
        GUI_Street guiStrandvejen = (GUI_Street) getGui_fields()[19];

        if (bernstorffsvej.getOwnedID() < -1 && bernstorffsvej.getOwnedID() == hellerupvej.getOwnedID() && hellerupvej.getOwnedID() == strandvejen.getOwnedID()) {
            bernstorffsvej.setCanBuild(true);
            guiBernstroffsVej.setRent(Integer.toString(bernstorffsvej.currentRent));
            hellerupvej.setCanBuild(true);
            guiHellerupvej.setRent(Integer.toString(hellerupvej.currentRent));
            strandvejen.setCanBuild(true);
            guiStrandvejen.setRent(Integer.toString(strandvejen.currentRent));
        } else {
            bernstorffsvej.setCanBuild(false);
            guiBernstroffsVej.setRent(Integer.toString(bernstorffsvej.currentRent));
            hellerupvej.setCanBuild(false);
            guiHellerupvej.setRent(Integer.toString(hellerupvej.currentRent));
            strandvejen.setCanBuild(false);
            guiStrandvejen.setRent(Integer.toString(strandvejen.currentRent));
        }
        Street trianglen = (Street) board.getFields()[21];
        GUI_Street guiTrianglen = (GUI_Street) getGui_fields()[21];
        Street osterbrogade = (Street) board.getFields()[23];
        GUI_Street guiOsterbrogade = (GUI_Street) getGui_fields()[23];
        Street gronningen = (Street) board.getFields()[24];
        GUI_Street guiGronningen = (GUI_Street) getGui_fields()[24];
        if (trianglen.getOwnedID() < -1 && trianglen.getOwnedID() == osterbrogade.getOwnedID() && osterbrogade.getOwnedID() == gronningen.getOwnedID()) {
            trianglen.setCanBuild(true);
            guiTrianglen.setRent(Integer.toString(trianglen.currentRent));
            osterbrogade.setCanBuild(true);
            guiOsterbrogade.setRent(Integer.toString(osterbrogade.currentRent));
            gronningen.setCanBuild(true);
            guiGronningen.setRent(Integer.toString(gronningen.currentRent));
        } else {
            trianglen.setCanBuild(false);
            guiTrianglen.setRent(Integer.toString(trianglen.currentRent));
            osterbrogade.setCanBuild(false);
            guiOsterbrogade.setRent(Integer.toString(osterbrogade.currentRent));
            gronningen.setCanBuild(false);
            guiGronningen.setRent(Integer.toString(gronningen.currentRent));
        }
        Street bredgade = (Street) board.getFields()[26];
        GUI_Street guiBredgade = (GUI_Street) getGui_fields()[26];
        Street kgsNytorv = (Street) board.getFields()[27];
        GUI_Street guiKgsNytorv = (GUI_Street) getGui_fields()[27];
        Street ostergade = (Street) board.getFields()[29];
        GUI_Street guiOstergade = (GUI_Street) getGui_fields()[29];
        if(bredgade.getOwnedID() < -1 && bredgade.getOwnedID() == kgsNytorv.getOwnedID() && kgsNytorv.getOwnedID() == ostergade.getOwnedID()) {
            bredgade.setCanBuild(true);
            guiBredgade.setRent(Integer.toString(bredgade.currentRent));
            kgsNytorv.setCanBuild(true);
            guiKgsNytorv.setRent(Integer.toString(kgsNytorv.currentRent));
            ostergade.setCanBuild(true);
            guiOstergade.setRent(Integer.toString(osterbrogade.currentRent));
        } else {
            bredgade.setCanBuild(false);
            guiBredgade.setRent(Integer.toString(bredgade.currentRent));
            kgsNytorv.setCanBuild(false);
            guiKgsNytorv.setRent(Integer.toString(kgsNytorv.currentRent));
            ostergade.setCanBuild(false);
            guiOstergade.setRent(Integer.toString(ostergade.currentRent));
        }
        Street amagerTorv = (Street) board.getFields()[31];
        GUI_Street guiAmagerTorv = (GUI_Street) getGui_fields()[31];
        Street vimmelskaftet = (Street) board.getFields()[32];
        GUI_Street guiVimmelskaftet = (GUI_Street) getGui_fields()[32];
        Street nygade = (Street) board.getFields()[34];
        GUI_Street guiNygade = (GUI_Street) getGui_fields()[34];
        if(amagerTorv.getOwnedID() < -1 && amagerTorv.getOwnedID() == vimmelskaftet.getOwnedID() && vimmelskaftet.getOwnedID() == nygade.getOwnedID()) {
            amagerTorv.setCanBuild(true);
            guiAmagerTorv.setRent(Integer.toString(amagerTorv.currentRent));
            vimmelskaftet.setCanBuild(true);
            guiVimmelskaftet.setRent(Integer.toString(vimmelskaftet.currentRent));
            nygade.setCanBuild(true);
            guiNygade.setRent(Integer.toString(nygade.currentRent));
        } else {
            amagerTorv.setCanBuild(false);
            guiAmagerTorv.setRent(Integer.toString(amagerTorv.currentRent));
            vimmelskaftet.setCanBuild(false);
            guiVimmelskaftet.setRent(Integer.toString(vimmelskaftet.currentRent));
            nygade.setCanBuild(false);
            guiNygade.setRent(Integer.toString(nygade.currentRent));
        }
        Street frederiksberggade = (Street) board.getFields()[37];
        GUI_Street guiFrederiksberggade = (GUI_Street) getGui_fields()[37];
        Street radhuspladsen = (Street) board.getFields()[39];
        GUI_Street guiRadhuspladsen = (GUI_Street) getGui_fields()[39];
        if(frederiksberggade.getOwnedID() < -1 && frederiksberggade.getOwnedID() == radhuspladsen.getOwnedID()) {
            frederiksberggade.setCanBuild(true);
            guiFrederiksberggade.setRent(Integer.toString(frederiksberggade.currentRent));
            radhuspladsen.setCanBuild(true);
            guiRadhuspladsen.setRent(Integer.toString(radhuspladsen.currentRent));
        } else {
            frederiksberggade.setCanBuild(false);
            guiFrederiksberggade.setRent(Integer.toString(frederiksberggade.currentRent));
            radhuspladsen.setCanBuild(false);
            guiRadhuspladsen.setRent(Integer.toString(radhuspladsen.currentRent));
        }

    }

}
