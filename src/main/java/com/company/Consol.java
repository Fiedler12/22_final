package com.company;

import ChanceCard.*;
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
            boolean checkSubClass2 = (boardController.getField()[playerController.players[var2].getPos()] instanceof Chancefield);
            if (checkSubClass2) {
                pullCard(PlayerController.players[var2].playerID);
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
        gui.getUserButtonPressed("Du har slået: " + dice.getTotal() + ", tryk har for at rykke", "Ryk");
        gui.getFields()[PlayerController.players[playerIndex].getPos()].setCar(playerController.getGui_players()[playerIndex], false);
        playerController.movePlayer(playerIndex, dice.getTotal());
        gui.getFields()[PlayerController.players[playerIndex].getPos()].setCar(playerController.getGui_players()[playerIndex], true);
        boolean checkChance = (boardController.getField()[PlayerController.players[playerIndex].getPos()] instanceof Chancefield);
        if (checkChance) {
            pullCard(playerIndex);
        }
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
                }
            } else if (ownable.getOwnedID() == playerController.getPlayers()[playerIndex].getPlayerID()) {
                gui.displayChanceCard("Du ejer selv dette felt.");
            } else if (ownable.getOwnedID() != -1 && ownable.getOwnedID() != playerController.getPlayers()[playerIndex].getPlayerID()) {
                boolean checkStreet = (ownable instanceof Street);
                if (checkStreet) {
                    PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - ((Street) ownable).currentRent);
                }
                boolean checkShipping = (ownable instanceof Shipping);
                if (checkShipping) {
                    if (PlayerController.players[ownable.getOwnedID()].getShippingOwned() == 1) {
                        ((Shipping) ownable).landOnowned(1);
                        PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - ((Shipping) ownable).getToPay());
                        PlayerController.players[ownable.getOwnedID()].playerAccount.setBalance(PlayerController.players[ownable.getOwnedID()].playerAccount.getBalance() + ((Shipping) ownable).getToPay());
                    }
                    if (PlayerController.players[ownable.getOwnedID()].getShippingOwned() == 2) {
                        ((Shipping) ownable).landOnowned(2);
                        PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - ((Shipping) ownable).getToPay());
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
                } else {
                    if (PlayerController.players[ownable.getOwnedID()].getBreweryOwned() == 1) {
                        int toPay = dice.getTotal() * 10;
                        PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - toPay);
                    }
                    if (PlayerController.players[ownable.getOwnedID()].getBreweryOwned() == 2) {
                        int toPay = dice.getTotal() * 10;
                        PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - toPay);
                    }
                }
            }
        }
        updateView(PlayerController.players.length);
    }




    public void pullCard(int playerIndex) {
        gui.getUserButtonPressed("Du er landet på chancefeltet\nTryk på knappen for at trække et kort", "Træk kort");
        ChanceCard card = cardDeck.draw();
        gui.displayChanceCard(card.CardText);

        gui.getUserButtonPressed("Du har trukket dette kort", "Fortsæt");
        cardDeckSwitch(playerIndex, card);
    }

    public void cardDeckSwitch(int playerIndex, ChanceCard card) {
        Player player = PlayerController.players[playerIndex];

        boolean checkGetOutOfJail = (card instanceof GetOutOfJailCard);
        if (checkGetOutOfJail) {
            player.setHasJailCard(true);
        }
        //TODO at kunne sælge kortet (skal nok ikke laves her i switchen)
        //find ud af om det er spilleren der skal have boolean jailcard eller konstruktøren

        boolean checkGoToJailCard = (card instanceof GoToJailCard);
        if (checkGoToJailCard) {
            GoToJailCard goToJail = (GoToJailCard) card;
            gui.getFields()[player.getPos()].setCar(playerController.getGui_players()[playerIndex], false);
            player.setPos(goToJail.getGoToJail());
            gui.getFields()[player.getPos()].setCar(playerController.getGui_players()[playerIndex], true);
            player.setInJail(true);
            updateView(PlayerController.players.length);
        } //DONE

        boolean checkIncreasePrice = (card instanceof IncreasePrice);


        boolean checkMoneyFromPlayer = (card instanceof MoneyFromPlayer);
        if (checkMoneyFromPlayer) {
            MoneyFromPlayer moneyFromPlayer = (MoneyFromPlayer) card;
            player.playerAccount.setBalance(player.playerAccount.getBalance() + (playerController.players.length) * moneyFromPlayer.getReceiveFromPlayer());
            int i = 0;
            while (i < playerController.players.length) {
                playerController.players[i].playerAccount.setBalance(playerController.players[i].playerAccount.getBalance() - moneyFromPlayer.getReceiveFromPlayer());
                i++;
            }
            updateView(PlayerController.players.length);
        }

        boolean checkMove = (card instanceof Move);
        if (checkMove) {
            Move move = (Move) card;
            gui.getFields()[player.getPos()].setCar(playerController.getGui_players()[playerIndex], false);
            playerController.movePlayer(playerIndex, move.getMove());
            gui.getFields()[player.getPos()].setCar(playerController.getGui_players()[playerIndex], true);
            updateView(PlayerController.players.length);
            // vælg køb efter rykket nyt loop og måske få penge efter start??
        }

        boolean checkMoveToShipping = (card instanceof MoveToShipping);
        if (checkMoveToShipping) {
            while (true) {
                gui.getFields()[player.getPos()].setCar(playerController.getGui_players()[playerIndex], false);
                player.setPos(player.getPos() + 1);
                boolean Shipping = (boardController.getField()[player.getPos()] instanceof Shipping);
                if (Shipping) {
                    gui.getFields()[player.getPos()].setCar(playerController.getGui_players()[playerIndex], true);
                    break;
                }
            }
        }

        boolean checkMoveToSpecific = (card instanceof MovetoSpecific);
        if (checkMoveToSpecific) {
            MovetoSpecific movetoSpecific = (MovetoSpecific) card;
            gui.getFields()[player.getPos()].setCar(playerController.getGui_players()[playerIndex], false);
            player.setPos(movetoSpecific.getFieldID());
            gui.getFields()[player.getPos()].setCar(playerController.getGui_players()[playerIndex], true);
            updateView(PlayerController.players.length);
        } //Mangler at der sker noget når man lander på det specifikke felt

        boolean checkPayMoney = (card instanceof PayMoney);
        if (checkPayMoney) {
            PayMoney payMoney = (PayMoney) card;
            player.playerAccount.setBalance(player.playerAccount.getBalance() - payMoney.getPay());
            updateView(PlayerController.players.length);
        } //DONE


        boolean checkReceiveMoney = (card instanceof ReceiveMoney);
        if (checkReceiveMoney) {
            ReceiveMoney receiveMoney = (ReceiveMoney) card;
            player.playerAccount.setBalance(player.playerAccount.getBalance() + receiveMoney.getReceive());
            updateView(PlayerController.players.length);
        } //DONE
    }
}
