package com.company;

import Fields.BoardController;
import Fields.Field;
import Fields.Ownable;
import Fields.Shipping;
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
            if (numberInput < 2 || numberInput > 6) {
                gui.showMessage("Fejl! Der må kun være 2-6 spillere");
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
            PlayerController.players[var] = new Player();
            PlayerController.players[var].setName(navn);
            PlayerController.players[var].setPos(0);
            PlayerController.players[var].setPlayerID(var);
            PlayerController.players[var].playerAccount.setBalance(30000);
            playerController.gui_players[var] = new GUI_Player(PlayerController.players[var].getName(), PlayerController.players[var].playerAccount.getBalance());
            gui.addPlayer(playerController.getGui_players()[var]);
            gui.getFields()[PlayerController.players[var].getPos()].setCar(playerController.getGui_players()[var], true);
            var++;
        }
        int var2 = 0;
        while (var2 < amount) {
            gui.getUserButtonPressed("I skal nu til at starte spillet. " + playerController.players[var2].getName() + ", tryk på knappen for at slå", "Kast terningerne");
            dice.roll();
            gui.setDice(dice.die1, dice.die2);
            gui.getFields()[playerController.players[var2].getPos()].setCar(playerController.getGui_players()[var2], false);
            playerController.movePlayer(var2, dice.getTotal());
            gui.getFields()[playerController.players[var2].getPos()].setCar(playerController.getGui_players()[var2], true);
            updateView(playerController.players.length);
            boolean checkSubClass = (boardController.getField()[var2] instanceof Ownable);
            if (checkSubClass) {
                Ownable ownable = (Ownable) boardController.getField()[var2];
                boolean yes = gui.getUserLeftButtonPressed("Ønsker du at købe " + ownable.getName() + "?", "Ja", "Nej");
                if (yes) {
                playerController.playerBuys(var2, playerController.players[var2].getPos());
                }
            }
            var2++;
        }
    }
    public void turn(int playerIndex) {

    }
    public void updateView(int amount) {
        int t = 0;
        boardController.getGui_fields();
        while(t < amount){
            playerController.gui_players[t].setBalance(playerController.getSpillere()[t].playerAccount.getBalance());
            t++;
        }

    }

}

