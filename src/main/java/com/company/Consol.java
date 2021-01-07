package com.company;

import Fields.BoardController;
import gui_fields.GUI_Player;
import gui_main.GUI;

public class Consol {
    BoardController boardController = new BoardController();
    GUI gui = new GUI(boardController.getGui_fields());
    PlayerController playerController = new PlayerController();
    Dice dice = new Dice();


    public void startGame() {
        while (true) {
            int numberInput = gui.getUserInteger("Hvor mange skal spille med? I kan være mellem 2 og 6 spillere.");
            playerController.makePlayers(numberInput);
            if (numberInput < 2 || numberInput > 4) {
                gui.showMessage("Fejl Der må kun være 2-4 spillere");
            } else {
                askName(numberInput);
                break;
            }
        }
    }

    public void askName(int amount) {
        int var = 0;
        while (var < amount) {
            String navn = gui.getUserString("Indtast spillernes navne");
            playerController.players[var] = new Player();
            playerController.players[var].setName(navn);
            playerController.players[var].setPos(0);
            playerController.players[var].setPlayerID(var);
            playerController.players[var].playerAccount.setBalance(30000);
            playerController.gui_players[var] = new GUI_Player(playerController.players[var].getName(), playerController.players[var].playerAccount.getBalance());
            gui.addPlayer(playerController.getGui_players()[var]);
            gui.getFields()[playerController.players[var].getPos()].setCar(playerController.getGui_players()[var], true);
            var++;
        }
    }
}

