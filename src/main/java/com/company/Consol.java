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
        int playerIndex = 0;
        while (playerIndex < amount) {
            String name = gui.getUserString("Indtast spillernes navne");
            PlayerController.players[playerIndex] = new Player();
            PlayerController.players[playerIndex].setName(name);
            PlayerController.players[playerIndex].setPos(0);
            PlayerController.players[playerIndex].setShippingOwned(0);
            PlayerController.players[playerIndex].setPlayerID(playerIndex);
            PlayerController.players[playerIndex].playerAccount.setBalance(30000);
            playerController.gui_players[playerIndex] = new GUI_Player(PlayerController.players[playerIndex].getName(), PlayerController.players[playerIndex].playerAccount.getBalance());
            gui.addPlayer(playerController.getGui_players()[playerIndex]);
            gui.getFields()[PlayerController.players[playerIndex].getPos()].setCar(playerController.getGui_players()[playerIndex], true);
            playerIndex++;
        }
        int playerIndex1 = 0;
        Player player = PlayerController.players[playerIndex1];
        while (playerIndex1 < amount) { //første tur (giver ikke mulighed for andet end at slå med terning
            gui.getUserButtonPressed(PlayerController.players[playerIndex1].getName() + " det er din tur, tryk på knappen for at slå", "Kast terningerne");
            dice.roll();
            gui.setDice(dice.die1, dice.die2);
            gui.getFields()[PlayerController.players[playerIndex1].getPos()].setCar(playerController.getGui_players()[playerIndex1], false);
            playerController.movePlayer(playerIndex1, dice.getTotal());
            gui.getFields()[PlayerController.players[playerIndex1].getPos()].setCar(playerController.getGui_players()[playerIndex1], true);
            updateView(PlayerController.players.length);
            boolean checkSubClass1 = (boardController.getField()[PlayerController.players[playerIndex1].getPos()] instanceof Ownable);
            if (checkSubClass1) {
                Ownable ownable = (Ownable) boardController.getField()[PlayerController.players[playerIndex1].getPos()];
                if (ownable.getOwnedID() == -1) {
                    boolean yes = gui.getUserLeftButtonPressed("Ønsker du at købe " + ownable.getName() + "?", "Ja", "Nej");
                    if (yes) {
                        playerController.playerBuys(playerIndex1, PlayerController.players[playerIndex1].getPos(), ownable.getPrice());
                        ownable.setOwnedID(playerIndex1);
                        updateView(PlayerController.players.length);
                        boolean isShipping = (boardController.getField()[PlayerController.players[playerIndex1].getPos()] instanceof Shipping);
                        if (isShipping) {
                            PlayerController.players[playerIndex1].setShippingOwned(PlayerController.players[playerIndex1].getShippingOwned() + 1);
                        }
                        boolean isBrewery = (boardController.getField()[PlayerController.players[playerIndex1].getPos()] instanceof Brewery);
                        if (isBrewery) {
                            PlayerController.players[playerIndex1].setBreweryOwned(PlayerController.players[playerIndex1].getBreweryOwned() + 1);
                        }
                    }
                }
            }
            boolean checkChanceField = (boardController.getField()[PlayerController.players[playerIndex1].getPos()] instanceof Chancefield);
            if (checkChanceField) {
                pullCard(PlayerController.players[playerIndex1].playerID);
            }
            boolean checkGoToJail = (boardController.getField()[PlayerController.players[playerIndex1].getPos()] instanceof GoToJail);
            if (checkGoToJail) {
                PlayerController.players[playerIndex1].setInJail(true);
                gui.getFields()[PlayerController.players[playerIndex1].getPos()].setCar(playerController.getGui_players()[playerIndex], false);
                GoToJail goToJail = new GoToJail(30,10);
                PlayerController.players[playerIndex1].setPos(goToJail.getPrison());
                gui.getFields()[PlayerController.players[playerIndex1].getPos()].setCar(playerController.getGui_players()[playerIndex1], true);
                updateView(PlayerController.players.length);
            }
            playerIndex1++;
        }
    }

    public void playGame() {
        int playerIndex = 0;
        while (true) {
            if (playerIndex > PlayerController.players.length - 1) {
                playerIndex = 0;
            }
            Player player = playerController.getPlayers()[playerIndex];
            if (player.isInJail()) {
                if (player.isHasJailCard()) {
                    boolean selection = gui.getUserLeftButtonPressed("Vil du bruge dit 'kom-ud-af-fængselskort'", "Brug fængselskort", "Betal eller prøv at slå 2 ens med terningerne");
                    if (selection) {
                        player.setHasJailCard(false);
                        player.setInJail(false);
                    } else {
                        boolean selection1 = gui.getUserLeftButtonPressed("Vil du prøve at slå 2 ens eller betale 1000 kr", "Prøv at slå 2 ens", "Betal 1000 kr");
                        if (selection1) {
                            dice.roll();
                            gui.setDice(dice.die1, dice.die2);
                            if (dice.die1 == dice.die2) {
                                player.setInJail(false);
                                gui.getFields()[PlayerController.players[playerIndex].getPos()].setCar(playerController.getGui_players()[playerIndex], false);
                                playerController.movePlayer(playerIndex, dice.getTotal());
                                gui.getFields()[PlayerController.players[playerIndex].getPos()].setCar(playerController.getGui_players()[playerIndex], true);
                            } else {

                            }
                        }
                    }
                } else {
                    boolean selection1 = gui.getUserLeftButtonPressed("Vil du prøve at slå 2 ens eller betale 1000 kr", "Prøv at slå 2 ens (Du har 3 forsøg)", "Betal 1000 kr");
                    if (selection1) {
                        int t = 0;
                        while (t < 3) {
                            gui.getUserButtonPressed(player.getName() + "tryk på knappen for at slå", "Kast terningerne");
                            dice.roll();
                            gui.setDice(dice.die1, dice.die2);
                            if (dice.die1 == dice.die2) {
                                player.setInJail(false);
                                gui.getFields()[PlayerController.players[playerIndex].getPos()].setCar(playerController.getGui_players()[playerIndex], false);
                                playerController.movePlayer(playerIndex, dice.getTotal());
                                gui.getFields()[PlayerController.players[playerIndex].getPos()].setCar(playerController.getGui_players()[playerIndex], true);
                                break;
                            } else {
                                gui.getUserButtonPressed("Den gik desværre ikke prøv igen", "Fortsæt");
                                t++;
                            }
                        }

                    } else {
                        player.playerAccount.setBalance(player.playerAccount.getBalance() - 1000);
                        player.setInJail(false);
                        updateView(playerController.getPlayers().length);
                    }
                }
            }
            if (!player.isInJail()) {
                turn(playerIndex);
                updateView(playerController.getPlayers().length);
            }
            playerIndex++;
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
                playerPawns();
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
        Player player = PlayerController.players[playerIndex];
        boolean checkGoToJail = (boardController.getField()[player.getPos()] instanceof GoToJail);
        if (checkGoToJail) {
            gui.getUserButtonPressed("Du er landet på 'Gå i fængsel' -feltet. Du ryger nu i fængsel uden at modtage penge for at passere start","Fortsæt");
            player.setInJail(true);
            gui.getFields()[player.getPos()].setCar(playerController.getGui_players()[playerIndex], false);
            GoToJail goToJail = new GoToJail(30,10);
            player.setPos(goToJail.getPrison());
            gui.getFields()[player.getPos()].setCar(playerController.getGui_players()[playerIndex], true);
        }
        updateView(PlayerController.players.length);
    }

    public void playerPawns() {



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
            player.playerAccount.setBalance(player.playerAccount.getBalance() + (PlayerController.players.length) * moneyFromPlayer.getReceiveFromPlayer());
            int i = 0;
            while (i < PlayerController.players.length) {
                PlayerController.players[i].playerAccount.setBalance(PlayerController.players[i].playerAccount.getBalance() - moneyFromPlayer.getReceiveFromPlayer());
                i++;
            }
            updateView(PlayerController.players.length);
        } //DONE

        boolean checkMove = (card instanceof Move);
        if (checkMove) {
            Move move = (Move) card;
            gui.getFields()[player.getPos()].setCar(playerController.getGui_players()[playerIndex], false);
            playerController.movePlayer(playerIndex, move.getMove());
            gui.getFields()[player.getPos()].setCar(playerController.getGui_players()[playerIndex], true);
            updateView(PlayerController.players.length);
            // vælg køb efter rykket nyt loop og måske få penge efter start??
            //Hvis man får -3 kortet ved første chancefelt får man position -1 hvilket den ikke er glad for
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
        } //mangler at tillade køb af shipping eller betale leje

        boolean checkMoveToSpecific = (card instanceof MovetoSpecific);
        if (checkMoveToSpecific) {
            MovetoSpecific movetoSpecific = (MovetoSpecific) card;
            gui.getFields()[player.getPos()].setCar(playerController.getGui_players()[playerIndex], false);
            player.setPos(movetoSpecific.getFieldID());
            gui.getFields()[player.getPos()].setCar(playerController.getGui_players()[playerIndex], true);
            updateView(PlayerController.players.length);
        } //Mangler at der sker noget når man lander på det specifikke felt og får start penge

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
