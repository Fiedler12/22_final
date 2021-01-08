package com.company;

import Fields.*;
import gui_fields.GUI_Ownable;
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
            boolean checkSubClass = (boardController.getField()[PlayerController.players[var2].getPos()] instanceof Ownable);
            if (checkSubClass) {
                Ownable ownable = (Ownable) boardController.getField()[PlayerController.players[var2].getPos()];
                if (ownable.getOwnedID() == -1) {
                    boolean yes = gui.getUserLeftButtonPressed("Ønsker du at købe " + ownable.getName() + "?", "Ja", "Nej");
                    if (yes) {
                        playerController.playerBuys(var2, PlayerController.players[var2].getPos(), ownable.getPrice());
                        ownable.setOwnedID(var2);
                        updateView(PlayerController.players.length);
                        boolean isShipping = (boardController.getField()[PlayerController.players[var2].getPos()] instanceof Shipping);
                        if (isShipping) {
                            PlayerController.players[var2].setShippingOwned(PlayerController.players[var2].getShippingOwned() + 1);
                        }
                        boolean isBrewery = (boardController.getField()[PlayerController.players[var2].getPos()] instanceof Brewery);
                        if (isBrewery) {
                            PlayerController.players[var2].setBreweryOwned(PlayerController.players[var2].getBreweryOwned() + 1);
                        }
                    }
                }
            }
            var2++;
        }
    }

    public void playGame() {
        int t = 0;
        while (true) {
            if (t > PlayerController.players.length - 1) {
                t = 0;
            }
            if (PlayerController.players[t].isInJail()) {
                if (playerController.getPlayers()[t].isHasJailCard()) {
                    boolean selection = gui.getUserLeftButtonPressed("Vil du bruge dit 'kom-ud-af-fængselskort'", "Brug fængselskort", "Betal M1");
                    if (selection) {
                        playerController.getPlayers()[t].setHasJailCard(false);
                    }
                }
            }
            turn(t);
            t++;
            updateView(playerController.getPlayers().length);
        }
    }

    public void updateView(int amount) {
        int t = 0;
        boardController.getGui_fields();
        while (t < amount) {
            playerController.gui_players[t].setBalance(PlayerController.players[t].playerAccount.getBalance());
            t++;
        }

    }

    public void turn(int playerIndex) {
        String choice = gui.getUserButtonPressed(PlayerController.players[playerIndex].getName() + ", det er din tur. Vælg hvad du vil benytte din tur til:", "Køb eller sælg", "Byg", "Pantsæt", "Slå terningen");
        switch (choice) {
            case "Køb eller sælg":
                break;
            case "Byg":
                break;
            case "Pantsæt":
                break;
            case "Slå terningen":
                playerRolls(playerIndex);
                break;
        }
    }

    public void playerRolls(int playerIndex) {
        dice.roll();
        gui.setDice(dice.die1, dice.die2);
        gui.getFields()[PlayerController.players[playerIndex].getPos()].setCar(playerController.getGui_players()[playerIndex], false);
        playerController.movePlayer(playerIndex, dice.getTotal());
        gui.getFields()[PlayerController.players[playerIndex].getPos()].setCar(playerController.getGui_players()[playerIndex], true);
        boolean checkSubClass = (boardController.getField()[PlayerController.players[playerIndex].getPos()] instanceof Ownable);
        if (checkSubClass) {
            Ownable ownable = (Ownable) boardController.getField()[PlayerController.players[playerIndex].getPos()];
            if (ownable.getOwnedID() == -1) {
                boolean yes = gui.getUserLeftButtonPressed("Ønsker du at købe " + ownable.getName() + "?", "Ja", "Nej");
                if (yes) {
                    playerController.playerBuys(playerIndex, PlayerController.players[playerIndex].getPos(), ownable.getPrice());
                    ownable.setOwnedID(playerIndex);
                    updateView(PlayerController.players.length);
                    boolean isShipping = (boardController.getField()[PlayerController.players[playerIndex].getPos()] instanceof Shipping);
                    if (isShipping) {
                        PlayerController.players[playerIndex].setShippingOwned(PlayerController.players[playerIndex].getShippingOwned() + 1);
                    }
                    boolean isBrewery = (boardController.getField()[PlayerController.players[playerIndex].getPos()] instanceof Brewery);
                    if (isBrewery) {
                        PlayerController.players[playerIndex].setBreweryOwned(PlayerController.players[playerIndex].getBreweryOwned() + 1);
                    }
                } else if (ownable.getOwnedID() == playerController.getPlayers()[playerIndex].getPlayerID()) {
                    gui.displayChanceCard("Du ejer selv dette felt.");
                } else if (ownable.getOwnedID() != -1) {
                    ownable.landOnOwned();
                    boolean checkStreet = (ownable instanceof Street);
                    if (checkStreet) {
                        PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - ((Street) ownable).currentRent);
                    }
                    boolean checkShipping = (ownable instanceof Brewery);
                    if (checkShipping) {
                        if (PlayerController.players[ownable.getOwnedID()].getShippingOwned() == 1) {
                            ((Shipping) ownable).landOnowned(1);
                            PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - ((Shipping) ownable).getToPay());
                            PlayerController.players[ownable.getOwnedID()].playerAccount.setBalance(playerController.players[ownable.getOwnedID()].playerAccount.getBalance() + ((Shipping) ownable).getToPay());
                        }
                        if (playerController.players[ownable.getOwnedID()].getShippingOwned() == 2) {
                            ((Shipping) ownable).landOnowned(2);
                            playerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - ((Shipping) ownable).getToPay());
                            PlayerController.players[ownable.getOwnedID()].playerAccount.setBalance(PlayerController.players[ownable.getOwnedID()].playerAccount.getBalance() + ((Shipping) ownable).getToPay());

                        }
                        if (PlayerController.players[ownable.getOwnedID()].getShippingOwned() == 3) {
                            ((Shipping) ownable).landOnowned(3);
                            PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - ((Shipping) ownable).getToPay());
                            PlayerController.players[ownable.getOwnedID()].playerAccount.setBalance(PlayerController.players[ownable.getOwnedID()].playerAccount.getBalance() + ((Shipping) ownable).getToPay());

                        }
                        if (PlayerController.players[ownable.getOwnedID()].getShippingOwned() == 4) {
                            ((Shipping) ownable).landOnowned(4);
                            PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - ((Shipping) ownable).getToPay());
                            PlayerController.players[ownable.getOwnedID()].playerAccount.setBalance(PlayerController.players[ownable.getOwnedID()].playerAccount.getBalance() + ((Shipping) ownable).getToPay());

                        }
                    }
                    else {
                            if(PlayerController.players[ownable.getOwnedID()].getBreweryOwned() == 1) {
                                int toPay = dice.getTotal() * 10;
                                PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - toPay);
                            }
                            if(PlayerController.players[ownable.getOwnedID()].getBreweryOwned() == 2) {
                                int toPay = dice.getTotal() * 10;
                                PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - toPay);
                            }
                        }
                    }
                }
            }
        updateView(PlayerController.players.length);
    }
}



