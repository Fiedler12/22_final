package com.company;

import ChanceCard.*;
import Fields.*;
import gui_fields.GUI_Car;
import gui_fields.GUI_Ownable;
import gui_fields.GUI_Player;
import gui_main.GUI;
import java.awt.*;

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
            playerController.cars[var] = new GUI_Car();
            playerController.cars[var].setPrimaryColor(playerController.getColors()[var]);
            playerController.gui_players[var] = new GUI_Player(PlayerController.players[var].getName(), PlayerController.players[var].playerAccount.getBalance(), playerController.getCars()[var]);
            gui.addPlayer(playerController.getGui_players()[var]);
            gui.getFields()[PlayerController.players[var].getPos()].setCar(playerController.getGui_players()[var], true);
            var++;
        }
        gui.showMessage("I skal nu til at starte spillet");
        int var2 = 0;
        while (var2 < amount) {
            gui.getUserButtonPressed(PlayerController.players[var2].getName() + ", det er din tur, tryk på knappen for at slå", "Kast terningerne");
            playerRolls(var2);
            checkSubClasses(var2);
            var2++;

        }
    }

    public void playGame() {
        int playerIndex = 0;
        while (true) {
            if (playerIndex > PlayerController.players.length - 1) {
                playerIndex = 0;
            }
            if (PlayerController.players[playerIndex].isInJail()) {
                if (PlayerController.players[playerIndex].isHasJailCard()) {
                    boolean selection = gui.getUserLeftButtonPressed(PlayerController.players[playerIndex].getName() + " du er i fængsel og da du har et 'kom-ud-af-fængsel' -kort kan du bruge det for at komme ud\nVil du bruge dit 'kom-ud-af-fængsel' -kort", "Brug fængselskort", "Betal eller prøv at slå 2 ens med terningerne");
                    if (selection) {
                        PlayerController.players[playerIndex].setHasJailCard(false);
                        PlayerController.players[playerIndex].setInJail(false);
                    } else {
                        boolean selection1 = gui.getUserLeftButtonPressed(PlayerController.players[playerIndex].getName() + " vil du prøve at slå 2 ens eller betale 1000 kr", "Prøv at slå 2 ens", "Betal 1000 kr");
                        if (selection1) {
                            int t = 3;
                            while (t > 0) {
                                gui.getUserButtonPressed(PlayerController.players[playerIndex].getName() + " tryk på knappen for at slå", "Kast terningerne");
                                dice.roll();
                                gui.setDice(dice.die1, dice.die2);
                                if (dice.die1 == dice.die2) {
                                    gui.getUserButtonPressed("Du har slået 2 ens. Du er nu en fri mand\nDu rykker det du slog og får en ekstra tur", "Juhuu");
                                    PlayerController.players[playerIndex].setInJail(false);
                                    gui.getFields()[PlayerController.players[playerIndex].getPos()].setCar(playerController.getGui_players()[playerIndex], false);
                                    playerController.movePlayer(playerIndex, dice.getTotal());
                                    gui.getFields()[PlayerController.players[playerIndex].getPos()].setCar(playerController.getGui_players()[playerIndex], true);
                                    checkSubClasses(playerIndex);
                                    break;
                                } else {
                                    t--;
                                    gui.getUserButtonPressed("Den gik desværre ikke\nDu har " + t + " forsøg tilbage", "Fortsæt");
                                }
                            }

                        } else {
                            PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - 1000);
                            PlayerController.players[playerIndex].setInJail(false);
                            updateView(playerController.getPlayers().length);
                        }
                    }
                } else {
                    boolean selection1 = gui.getUserLeftButtonPressed(PlayerController.players[playerIndex].getName() + " du er i fængsel og disse muligheder for at komme ud\nVil du prøve at slå 2 ens eller betale 1000 kr", "Prøv at slå 2 ens", "Betal 1000 kr");
                    if (selection1) {
                        int t = 3;
                        while (t > 0) {
                            gui.getUserButtonPressed(PlayerController.players[playerIndex].getName() + " tryk på knappen for at slå", "Kast terningerne");
                            dice.roll();
                            gui.setDice(dice.die1, dice.die2);
                            if (dice.die1 == dice.die2) {
                                gui.getUserButtonPressed("Du har slået 2 ens. Du er nu en fri mand\nDu rykker det du slog og får en ekstra tur", "Juhuu");
                                PlayerController.players[playerIndex].setInJail(false);
                                gui.getFields()[PlayerController.players[playerIndex].getPos()].setCar(playerController.getGui_players()[playerIndex], false);
                                playerController.movePlayer(playerIndex, dice.getTotal());
                                gui.getFields()[PlayerController.players[playerIndex].getPos()].setCar(playerController.getGui_players()[playerIndex], true);
                                checkSubClasses(playerIndex);
                                break;
                            } else {
                                t--;
                                gui.getUserButtonPressed("Den gik desværre ikke\nDu har " + t + " forsøg tilbage", "Fortsæt");
                            }
                        }

                    } else {
                        PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - 1000);
                        PlayerController.players[playerIndex].setInJail(false);
                        updateView(playerController.getPlayers().length);
                    }
                }
            }
            if (!PlayerController.players[playerIndex].isInJail()) {
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
        String choice = gui.getUserButtonPressed(PlayerController.players[playerIndex].getName() + ", det er din tur. Vælg hvad du vil benytte din tur til:", "Køb eller sælg", "Byg hus/hotel", "Pantsæt", "Slå terningen");
        switch (choice) {
            case "Køb eller sælg":
                String choiceBuyOrSell = gui.getUserButtonPressed("Vil du handle med en anden spiller, eller sælge dine huse?", "Køb af spiller.", "Sælg mine huse", "Gå tilbage");
                switch (choiceBuyOrSell) {
                    case "Køb af spiller.":
                        String[] playerNames = new String[playerController.getPlayers().length];
                        for (int i = 0; i < PlayerController.players.length; i++) {
                            playerNames[i] = playerController.getPlayers()[i].getName();
                        }
                        boolean select = gui.getUserLeftButtonPressed("Er du sikker på at du vil handle med en anden spiller?", "Ja, jeg vil handle!", "Nej, gå tilbage");
                        if (select) {
                            String selectPlayer = gui.getUserButtonPressed("Hvilken spiller vil du handle med?", playerNames);
                            int idChosen = 0;
                            while (idChosen < playerNames.length) {
                                boolean chosen = playerController.getPlayers()[idChosen].getName().equals(selectPlayer);
                                if (chosen) {
                                    idChosen = playerController.getPlayers()[idChosen].playerID;
                                    break;
                                }
                                idChosen++;
                            }
                            if (idChosen == playerIndex) {
                                gui.displayChanceCard("Det er dig selv. Du kan desværre ikke handle med dig selv.");
                                break;
                            }
                            if (playerController.getPlayers()[idChosen].owns.size() != 0 && idChosen != playerIndex) {
                                trade(playerIndex, idChosen);
                            }
                            if(playerController.getPlayers()[idChosen].owns.size() == 0) {
                                gui.getUserButtonPressed("Denne spiller har ingen grunde.", "OK");
                            }
                            break;
                        }

                    case "Sælg mine huse":

                        break;

                    case "Gå tilbage":
                        break;
                }
            case "Byg":
                break;
            case "Pantsæt":
                if (playerController.getPlayers()[playerIndex].owns.size() == 0 && playerController.getPlayers()[playerIndex].pawned.size() == 0) {
                    gui.getUserButtonPressed("Du har ikke nogen grunde at pantsætte.", "Ok");
                    break;
                }
                if (playerController.getPlayers()[playerIndex].pawned.size() != 0) {
                    String choicePawn = gui.getUserButtonPressed("Ønsker du at købe din grund tilbage eller pantsætte en ny?", "Pantsæt en ny.", "Køb grund tilbage.");
                    switch (choicePawn) {
                        case "Pantsæt en ny.":
                            if (playerController.getPlayers()[playerIndex].owns.size() == 0) {
                                gui.getUserButtonPressed("Du har ikke flere grunde du kan pantsætte.", "Ok");
                                break;
                            }
                            if (playerController.getPlayers()[playerIndex].owns.size() != 0) {
                                playerPawns(playerIndex);
                                break;
                            }

                                case "Køb grund tilbage.":
                                    playerBuysBack(playerIndex);
                                    break;
                            }
                        } else {
                            playerPawns(playerIndex);
                        }
                        break;
                    case "Slå terningen":
                        playerRolls(playerIndex);
                        checkSubClasses(playerIndex);
                        break;
                }
                if (!choice.equals("Slå terningen")){
                    updateView(playerController.getPlayers().length);
                    turn(playerIndex);
                }
                updateView(playerController.getPlayers().length);
        }


    public void playerRolls(int playerIndex) {
        dice.roll();
        gui.setDice(dice.die1, dice.die2);
        gui.getUserButtonPressed("Du har slået: " + dice.getTotal() + ", tryk har for at rykke", "Ryk");
        gui.getFields()[PlayerController.players[playerIndex].getPos()].setCar(playerController.getGui_players()[playerIndex], false);
        playerController.movePlayer(playerIndex, dice.getTotal());
        gui.getFields()[PlayerController.players[playerIndex].getPos()].setCar(playerController.getGui_players()[playerIndex], true);
        updateView(playerController.getPlayers().length);
    }
    public void checkSubClasses (int playerIndex){
        boolean checkOwnable = (boardController.getField()[PlayerController.players[playerIndex].getPos()] instanceof Ownable);
        if (checkOwnable) {
            Ownable ownable = (Ownable) boardController.getField()[PlayerController.players[playerIndex].getPos()];
            GUI_Ownable gui_ownable = (GUI_Ownable) boardController.getGui_fields()[playerController.getPlayers()[playerIndex].getPos()];
            if (ownable.getOwnedID() == -1) {
                boolean yes = gui.getUserLeftButtonPressed("Ønsker du at købe " + ownable.getName() + "?", "Ja", "Nej");
                if (yes) {
                    playerController.playerBuys(playerIndex, PlayerController.players[playerIndex].getPos(), ownable.getPrice());
                    ownable.setOwnedID(playerIndex);
                    updateView(PlayerController.players.length);
                    gui_ownable.setOwnerName(playerController.getPlayers()[playerIndex].getName());
                    gui_ownable.setBorder(playerController.colors[playerIndex]);
                    boolean isStreet = (boardController.getField()[PlayerController.players[playerIndex].getPos()] instanceof Street);
                    if (isStreet) {
                        Street street = (Street) boardController.getField()[PlayerController.players[playerIndex].getPos()];
                        int rent = street.currentRent;
                        String stringRent;
                        stringRent = Integer.toString(rent);
                        gui_ownable.setRent(stringRent);
                    }
                    boolean isShipping = (boardController.getField()[PlayerController.players[playerIndex].getPos()] instanceof Shipping);
                    if (isShipping) {
                        PlayerController.players[playerIndex].setShippingOwned(PlayerController.players[playerIndex].getShippingOwned() + 1);
                    }
                    boolean isBrewery = (boardController.getField()[PlayerController.players[playerIndex].getPos()] instanceof Brewery);
                    if (isBrewery) {
                        PlayerController.players[playerIndex].setBreweryOwned(PlayerController.players[playerIndex].getBreweryOwned() + 1);
                    }
                    gui.getUserButtonPressed("Du har nu købt " + ownable.getName(), "Ok");
                }
            } else if (ownable.getOwnedID() == playerController.getPlayers()[playerIndex].getPlayerID()) {
                gui.displayChanceCard("Du ejer selv dette felt.");
            } else if (ownable.getOwnedID() != -1 && ownable.getOwnedID() != playerController.getPlayers()[playerIndex].getPlayerID()) {
                boolean checkStreet = (ownable instanceof Street);
                if (checkStreet) {
                    PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - ((Street) ownable).currentRent);
                    PlayerController.players[ownable.getOwnedID()].playerAccount.setBalance(PlayerController.players[ownable.getOwnedID()].playerAccount.getBalance() + ((Street) ownable).currentRent);
                    gui.getUserButtonPressed("Du er landet på " + ownable.getName() + " Dette felt er ikke ejet af dig.\nDu skal betale " + ((Street) ownable).currentRent + "kr i leje", "Betal");
                }
                boolean checkShipping = (ownable instanceof Shipping);
                if (checkShipping) {
                    if (PlayerController.players[ownable.getOwnedID()].getShippingOwned() == 1) {
                        ((Shipping) ownable).landOnowned(1);
                        PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - ((Shipping) ownable).getToPay());
                        PlayerController.players[ownable.getOwnedID()].playerAccount.setBalance(PlayerController.players[ownable.getOwnedID()].playerAccount.getBalance() + ((Shipping) ownable).getToPay());
                        gui.getUserButtonPressed("Du ejer ikke dette rederi, det gør: " + playerController.getPlayers()[ownable.getOwnedID()].getName() + ". Du skal betale 500 kr.");
                    } else if (PlayerController.players[ownable.getOwnedID()].getShippingOwned() == 2) {
                        ((Shipping) ownable).landOnowned(2);
                        PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - ((Shipping) ownable).getToPay());
                        PlayerController.players[ownable.getOwnedID()].playerAccount.setBalance(PlayerController.players[ownable.getOwnedID()].playerAccount.getBalance() + ((Shipping) ownable).getToPay());
                        gui.getUserButtonPressed("Du ejer ikke dette rederi, det gør: " + playerController.getPlayers()[ownable.getOwnedID()].getName() + ". Du skal betale 1000 kr. fordi han ejer 1 andet rederi");

                    } else if (PlayerController.players[ownable.getOwnedID()].getShippingOwned() == 3) {
                        ((Shipping) ownable).landOnowned(3);
                        PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - ((Shipping) ownable).getToPay());
                        PlayerController.players[ownable.getOwnedID()].playerAccount.setBalance(PlayerController.players[ownable.getOwnedID()].playerAccount.getBalance() + ((Shipping) ownable).getToPay());
                        gui.getUserButtonPressed("Du ejer ikke dette rederi, det gør: " + playerController.getPlayers()[ownable.getOwnedID()].getName() + ". Du skal betale 2000 kr. fordi denne person ejer 2 rederier mere.");

                    } else if (PlayerController.players[ownable.getOwnedID()].getShippingOwned() == 4) {
                        ((Shipping) ownable).landOnowned(4);
                        PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - ((Shipping) ownable).getToPay());
                        PlayerController.players[ownable.getOwnedID()].playerAccount.setBalance(PlayerController.players[ownable.getOwnedID()].playerAccount.getBalance() + ((Shipping) ownable).getToPay());
                        gui.getUserButtonPressed("Du ejer ikke dette rederi, det gør: " + playerController.getPlayers()[ownable.getOwnedID()].getName() + ". Du skal betale 4000 kr. fordi personen ejer alle rederierne.");

                    }
                    gui.getUserButtonPressed(playerController.getPlayers()[ownable.getOwnedID()].getName() + "ejer denne grund. Du har betalt: " + ((Shipping) ownable).getToPay());
                } else {
                    if (PlayerController.players[ownable.getOwnedID()].getBreweryOwned() == 1) {
                        int toPay = dice.getTotal() * 100;
                        PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - toPay);
                        PlayerController.players[ownable.getOwnedID()].playerAccount.setBalance(PlayerController.players[ownable.getOwnedID()].playerAccount.getBalance() + toPay);
                        gui.getUserButtonPressed(playerController.getPlayers()[ownable.getOwnedID()].getName() + "ejer denne grund. Du har betalt: " + toPay);

                    } else if (PlayerController.players[ownable.getOwnedID()].getBreweryOwned() == 2) {
                        int toPay = dice.getTotal() * 200;
                        PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - toPay);
                        gui.getUserButtonPressed(playerController.getPlayers()[ownable.getOwnedID()].getName() + "ejer denne grund. Du har betalt: " + toPay);
                    }
                }
            }
        }
            boolean checkGoToJail = (boardController.getField()[PlayerController.players[playerIndex].getPos()] instanceof GoToJail);
            if (checkGoToJail) {
                gui.getUserButtonPressed(PlayerController.players[playerIndex].getName() + " du er landet på 'Gå i fængsel' -feltet. Du ryger nu i fængsel uden at modtage penge for at passere start", "Fortsæt");
                PlayerController.players[playerIndex].setInJail(true);
                gui.getFields()[PlayerController.players[playerIndex].getPos()].setCar(playerController.getGui_players()[playerIndex], false);
                GoToJail goToJail = new GoToJail(30, 10);
                PlayerController.players[playerIndex].setPos(goToJail.getPrison());
                gui.getFields()[PlayerController.players[playerIndex].getPos()].setCar(playerController.getGui_players()[playerIndex], true);
            }
            boolean checkChanceField = (boardController.getField()[PlayerController.players[playerIndex].getPos()] instanceof Chancefield);
            if (checkChanceField) {
                pullCard(PlayerController.players[playerIndex].playerID);
            }

        boolean checkTaxField = (boardController.getField()[PlayerController.players[playerIndex].getPos()] instanceof TaxField);
        if (checkTaxField) {
            Player player = playerController.getPlayers()[playerIndex];
            gui.getUserButtonPressed(player.getName() + " Du er landet på et 'Betal Skat' -felt, tryk for at betale", "Betal");
            TaxField taxField = (TaxField) boardController.getField()[PlayerController.players[playerIndex].getPos()];
            if(player.getPos() == 38) {
                player.playerAccount.setBalance(player.playerAccount.getBalance() - taxField.getTaxPrice());
            }
            else if (player.getPos() == 4){
                boolean select2 = gui.getUserLeftButtonPressed(PlayerController.players[playerIndex].getName() + "Betal 10% eller 4000k ", "10%", "4000kr");
                if (select2) {
                    player.playerAccount.setBalance((int) (player.playerAccount.getBalance() * 0.9));
                } else {
                    player.playerAccount.setBalance(player.playerAccount.getBalance() - taxField.getTaxPrice());
                }
            }
            updateView(PlayerController.players.length);
            }
        }

        public void playerPawns ( int playerIndex) {
            int[] owns = new int[playerController.getPlayers()[playerIndex].owns.size()];
            String[] names = new String[playerController.getPlayers()[playerIndex].owns.size()];
            for (int i = 0; i < owns.length; i++) {
                Ownable ownable = (Ownable) boardController.getField()[playerController.getPlayers()[playerIndex].owns.get(i)];
                owns[i] = playerController.getPlayers()[playerIndex].owns.get(i);
                names[i] = ownable.getName();
            }
            int idChosen;
            boolean select = gui.getUserLeftButtonPressed("Er du sikker på at du vil pantsætte?", "Ja, jeg vil pantsætte!", "Nej, gå tilbage");
            if (select) {
                String chosenElement = gui.getUserSelection("Hvilken ejendom vil du pantsætte? ", names);
                for (idChosen = 0; idChosen < names.length; idChosen++) {
                    boolean chosen = names[idChosen].equals(chosenElement);
                    if (chosen) {
                        break;
                    }
                }
                Ownable ownableChosen = (Ownable) boardController.getField()[owns[idChosen]];
                GUI_Ownable gui_ownable = (GUI_Ownable) boardController.getGui_fields()[owns[idChosen]];
                playerController.playerPawns(playerIndex, owns[idChosen], ownableChosen.getPawnValue());
                ownableChosen.setOwnedID(-2);
                gui_ownable.setBorder(Color.magenta, playerController.colors[playerIndex]);
            }
        }
        public void playerBuysBack ( int playerIndex) {
            int[] pawned = new int[playerController.getPlayers()[playerIndex].pawned.size()];
            String[] names = new String[playerController.getPlayers()[playerIndex].pawned.size()];
            boolean select = gui.getUserLeftButtonPressed("Er du sikker?", "Ja, jeg vil købe en grund tilbage!", "Nej, gå tilbage");
            if (select) {
                for (int i = 0; i < names.length; i++) {
                    Ownable ownable = (Ownable) boardController.getField()[playerController.getPlayers()[playerIndex].pawned.get(i)];
                    pawned[i] = playerController.getPlayers()[playerIndex].pawned.get(i);
                    names[i] = ownable.getName();
                }
                int idChosen;
                String chosenElement = gui.getUserSelection("Hvilken ejendom vil du købe tilbage? ", names);
                for (idChosen = 0; idChosen < names.length; idChosen++) {
                    boolean chosen = names[idChosen].equals(chosenElement);
                    if (chosen) {
                        break;
                    }
                }
                Ownable ownableChosen = (Ownable) boardController.getField()[pawned[idChosen]];
                GUI_Ownable gui_ownable = (GUI_Ownable) boardController.getGui_fields()[pawned[idChosen]];
                playerController.buysBackPawn(playerIndex, pawned[idChosen], ownableChosen.getPawnValue());
                ownableChosen.setOwnedID(playerIndex);
                gui_ownable.setBorder(playerController.colors[playerIndex]);
            }
        }
        public void trade(int playerIDBuys, int playerIDSells) {
            int[] owns = new int[playerController.getPlayers()[playerIDSells].owns.size()];
            String[] names = new String[playerController.getPlayers()[playerIDSells].owns.size()];
            for (int i = 0; i < owns.length; i++) {
                Ownable ownable = (Ownable) boardController.getField()[playerController.getPlayers()[playerIDSells].owns.get(i)];
                owns[i] = playerController.getPlayers()[playerIDSells].owns.get(i);
                names[i] = ownable.getName();
            }
            int ownableChosen;
            String chosenElement = gui.getUserSelection("Hvilken grund ønsker du at købe? ", names);
            for (ownableChosen = 0; ownableChosen < names.length; ownableChosen++) {
                boolean chosen = names[ownableChosen].equals(chosenElement);
                if (chosen) {
                    break;
                }
            }
                Ownable tradeOwnable = (Ownable) boardController.getField()[owns[ownableChosen]];
                GUI_Ownable tradeGui_ownable = (GUI_Ownable) boardController.getGui_fields()[owns[ownableChosen]];
                int offer = gui.getUserInteger("Læg et bud på denne grund");
                String answer = gui.getUserButtonPressed(playerController.getPlayers()[playerIDSells].getName() + " ,ønsker du at sælge " + tradeOwnable.getName() + " til: " + playerController.getPlayers()[playerIDBuys].getName() + " for: " + offer + " kr.", "Ja", "Nej", "Modbud");

                switch (answer) {
                    case "Ja" :
                        playerController.trade(playerIDSells, playerIDBuys, owns[ownableChosen], offer);
                        tradeOwnable.setOwnedID(playerIDBuys);
                        updateView(PlayerController.players.length);
                        tradeGui_ownable.setOwnerName(playerController.getPlayers()[playerIDBuys].getName());
                        tradeGui_ownable.setBorder(playerController.colors[playerIDBuys]);
                        break;
                    case "Nej":
                        break;
                    case "Modbud" :
                        int counterOffer = gui.getUserInteger(playerController.getPlayers()[playerIDSells].getName() + " kom med et modbud.");
                        String counterAnswer = gui.getUserButtonPressed(playerController.getPlayers()[playerIDBuys].name + " du har modtaget et modbud på: " + counterOffer + " vil du købe grunden til denne pris: ", "Ja", "Nej");
                        switch (counterAnswer) {
                            case "Ja":
                                playerController.trade(playerIDSells, playerIDBuys, ownableChosen, counterOffer);
                                tradeOwnable.setOwnedID(playerIDBuys);
                                updateView(PlayerController.players.length);
                                tradeGui_ownable.setOwnerName(playerController.getPlayers()[playerIDBuys].getName());
                                tradeGui_ownable.setBorder(playerController.colors[playerIDBuys]);
                                break;
                            case "Nej":
                                break;

                        }
                        break;
                }
    }



        public void pullCard ( int playerIndex){
            gui.getUserButtonPressed("Du er landet på chancefeltet\nTryk på knappen for at trække et kort", "Træk kort");
            ChanceCard card = cardDeck.draw();
            gui.displayChanceCard(card.CardText);

            gui.getUserButtonPressed("Du har trukket dette kort", "Fortsæt");
            cardDeckSwitch(playerIndex, card);
        }

        public void cardDeckSwitch ( int playerIndex, ChanceCard card){
            Player player = PlayerController.players[playerIndex];

            boolean checkGetOutOfJail = (card instanceof GetOutOfJailCard);
            if (checkGetOutOfJail) {
                player.setHasJailCard(true);
            }
            //TODO at kunne sælge kortet


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
                checkSubClasses(playerIndex);
                updateView(PlayerController.players.length);
                //DONE
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
                if (cardDeck.receiveID() == 39){
                    checkSubClasses(playerIndex);
                }
                else {
              //      if ()
             //       checkSubClasses(playerIndex);
            //        updateView(PlayerController.players.length);
                }
            }

            boolean checkMoveToSpecific = (card instanceof MovetoSpecific);
            if (checkMoveToSpecific) {
                MovetoSpecific movetoSpecific = (MovetoSpecific) card;
                while (player.getPos() != movetoSpecific.getFieldID()) {
                    gui.getFields()[player.getPos()].setCar(playerController.getGui_players()[playerIndex], false);
                    player.setPos(player.getPos() + 1);
                }
                gui.getFields()[player.getPos()].setCar(playerController.getGui_players()[playerIndex], true);
                updateView(PlayerController.players.length);
                checkSubClasses(playerIndex);
                updateView(PlayerController.players.length);
            }

            boolean checkPayMoney = (card instanceof PayMoney);
            if (checkPayMoney) {
                PayMoney payMoney = (PayMoney) card;
                player.playerAccount.setBalance(player.playerAccount.getBalance() - payMoney.getPay());
                updateView(PlayerController.players.length);
            } //DONE


            boolean checkReceiveMoney = (card instanceof ReceiveMoney);
            if (checkReceiveMoney) {
                ReceiveMoney receiveMoney = (ReceiveMoney) card;
                if (cardDeck.receiveID() == 24){
                    if (player.playerAccount.getBalance() <= 12000){
                        player.playerAccount.setBalance(player.playerAccount.getBalance() + receiveMoney.getReceive());
                    }
                    else {
                        gui.showMessage("Du har for mange penge på din konto til at modtage legatet");
                    }
                }
                else {
                    player.playerAccount.setBalance(player.playerAccount.getBalance() + receiveMoney.getReceive());
                }
                updateView(PlayerController.players.length);
            } //DONE
        }
    }


