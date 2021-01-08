package com.company;

import ChanceCard.CardDeck;
import Fields.*;
import gui_fields.GUI_Player;
import gui_main.GUI;

public class Consol {
    BoardController boardController = new BoardController();
    GUI gui = new GUI(boardController.getGui_fields());
    PlayerController playerController = new PlayerController();
    Dice dice = new Dice();
    CardDeck cardDeck = new CardDeck();


    public void startGame() {
        cardDeck.mix();
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
            String name = gui.getUserString("Indtast spillernes navne");
            PlayerController.players[var] = new Player();
            PlayerController.players[var].setName(name);
            PlayerController.players[var].setPos(0);
            PlayerController.players[var].setShippingOwned(0);
            PlayerController.players[var].setPlayerID(var);
            PlayerController.players[var].playerAccount.setBalance(30000);
            playerController.gui_players[var] = new GUI_Player(PlayerController.players[var].getName(), PlayerController.players[var].playerAccount.getBalance());
            gui.addPlayer(playerController.getGui_players()[var]);
            gui.getFields()[PlayerController.players[var].getPos()].setCar(playerController.getGui_players()[var], true);
            var++;
        }
        int var2 = 0;
        while (var2 < amount) {
            gui.getUserButtonPressed("I skal nu til at starte spillet. " + PlayerController.players[var2].getName() + ", tryk på knappen for at slå", "Kast terningerne");
            dice.roll();
            gui.setDice(dice.die1, dice.die2);
            gui.getFields()[PlayerController.players[var2].getPos()].setCar(playerController.getGui_players()[var2], false);
            playerController.movePlayer(var2, dice.getTotal());
            gui.getFields()[PlayerController.players[var2].getPos()].setCar(playerController.getGui_players()[var2], true);
            updateView(PlayerController.players.length);
            boolean checkSubClass1 = (boardController.getField()[PlayerController.players[var2].getPos()] instanceof Ownable);
            if (checkSubClass1) {
                Ownable ownable = (Ownable) boardController.getField()[PlayerController.players[var2].getPos()];
                if (ownable.getOwnedID() == -1) {
                    boolean yes = gui.getUserLeftButtonPressed("Ønsker du at købe " + ownable.getName() + "?", "Ja", "Nej");
                    if (yes) {
                        playerController.playerBuys(var2, PlayerController.players[var2].getPos(), ownable.getPrice());
                        ownable.setOwnedID(var2);
                        updateView(PlayerController.players.length);
                        boolean isShipping = (boardController.getField()[PlayerController.players[var2].getPos()] instanceof Shipping);
                        if(isShipping) {
                            PlayerController.players[var2].setShippingOwned(PlayerController.players[var2].getShippingOwned() + 1);
                        }
                        boolean isBrewery = (boardController.getField()[PlayerController.players[var2].getPos()] instanceof Brewery);
                        if(isBrewery) {
                            PlayerController.players[var2].setBreweryOwned(PlayerController.players[var2].getBreweryOwned() + 1);
                        }
                    }
                }
            }
            boolean checkSubClass2 = (boardController.getField()[playerController.players[var2].getPos()] instanceof Chancefield);
            if (checkSubClass2) {
                pullCard();
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
    public void pullCard(){
        gui.getUserButtonPressed("Du er landet på chancefeltet\nTryk på knappen for at trække et kort", "Træk kort");
        cardDeck.draw();
        gui.displayChanceCard(cardDeck.recieveT());
        gui.getUserButtonPressed("Du har trukket dette kort", "Fortsæt");
    }
    public void cardDeckSwitch(){

    }

}

