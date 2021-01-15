package com.company;

import ChanceCard.*;
import Fields.*;
import gui_fields.GUI_Car;
import gui_fields.GUI_Ownable;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;
import java.awt.*;

public class Consol {
    BoardController boardController = new BoardController();
    GUI gui = new GUI(boardController.getGui_fields());
    PlayerController playerController = new PlayerController();
    Dice dice = new Dice();
    CardDeck cardDeck = new CardDeck();
    public int playersGivenUp = 0;


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
        int iDWon = 0;
        while (true) {
            if (playersGivenUp == playerController.getPlayers().length - 1) {
                for (int i = 0; i < playerController.getPlayers().length; i++) {
                    if (!playerController.getPlayers()[i].isBankrupt()) {
                        iDWon = i;
                        break;
                    }
                }
                gui.showMessage("Vinderen er nu fundet! Det blev: " + playerController.getPlayers()[iDWon].getName() + " Tillykke!");
                break;
            }
            if (playerIndex > PlayerController.players.length - 1) {
                playerIndex = 0;
            }
            if (PlayerController.players[playerIndex].isInJail()) {
                if (PlayerController.players[playerIndex].isHasJailCard()) {
                    boolean selection = gui.getUserLeftButtonPressed(PlayerController.players[playerIndex].getName() + " du er i fængsel og da du har et 'kom-ud-af-fængsel' -kort kan du bruge det for at komme ud. Vil du bruge dit 'kom-ud-af-fængsel' -kort", "Brug fængselskort", "Betal eller prøv at slå 2 ens med terningerne");
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
                    boolean selection1 = gui.getUserLeftButtonPressed(PlayerController.players[playerIndex].getName() + " du er i fængsel og har disse muligheder for at komme ud. Vil du prøve at slå 2 ens eller betale 1000 kr", "Prøv at slå 2 ens", "Betal 1000 kr");
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
            checkPlayerAccount(playerIndex);
            if (!PlayerController.players[playerIndex].isInJail()) {
                turn(playerIndex);
                updateView(playerController.getPlayers().length);
            }
            playerIndex++;
        }
    }

    public void updateView(int amount) {
        int t = 0;
        while (t < amount) {
            playerController.gui_players[t].setBalance(PlayerController.players[t].playerAccount.getBalance());
            t++;
        }
        boardController.checkFields();
        boardController.getGui_fields();
    }

    public void turn(int playerIndex) {
        if (!PlayerController.players[playerIndex].isBankrupt()) {
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
                                } else if (playerController.getPlayers()[idChosen].owns.size() != 0 && idChosen != playerIndex) {
                                    trade(playerIndex, idChosen);
                                } else if (playerController.getPlayers()[idChosen].owns.size() == 0) {
                                    gui.getUserButtonPressed("Denne spiller har ingen grunde.", "OK");
                                }
                                break;
                            }
                            break;
                        case "Sælg mine huse":
                            sellHouse(playerIndex);
                            break;

                        case "Gå tilbage":
                            break;
                    }
                    break;
                case "Byg hus/hotel":
                    if (playerController.getPlayers()[playerIndex].owns.size() != 0) {
                        build(playerIndex);
                    } else {
                        gui.getUserButtonPressed("Du ejer ingen grunde.", "Ok");
                    }
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
            checkPlayerAccount(playerIndex);
            if (!choice.equals("Slå terningen")) {
                updateView(playerController.getPlayers().length);
                turn(playerIndex);
            }

            updateView(playerController.getPlayers().length);
        }
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

    public void checkSubClasses(int playerIndex) {
        boolean checkOwnable = (boardController.getField()[PlayerController.players[playerIndex].getPos()] instanceof Ownable);
        if (checkOwnable) {
            Ownable ownable = (Ownable) boardController.getField()[PlayerController.players[playerIndex].getPos()];
            GUI_Ownable gui_ownable = (GUI_Ownable) boardController.getGui_fields()[playerController.getPlayers()[playerIndex].getPos()];
            if (ownable.getOwnedID() == -2) {
                gui.getUserButtonPressed(ownable.getName() + " er pantsat.", "Ok");
            }
            if (ownable.getOwnedID() == -1) {
                if (playerController.getPlayers()[playerIndex].playerAccount.getBalance() < ownable.getPrice()) {
                    gui.showMessage("Du har ikke råd til denne grund");
                } else {
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
                }
            } else if (ownable.getOwnedID() == playerController.getPlayers()[playerIndex].getPlayerID()) {
                gui.displayChanceCard("Du ejer selv dette felt.");
            } else if (ownable.getOwnedID() != -1 && ownable.getOwnedID() != playerController.getPlayers()[playerIndex].getPlayerID() && ownable.getOwnedID() != -2) {
                boolean checkStreet = (ownable instanceof Street);
                if (checkStreet) {
                    PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - ((Street) ownable).currentRent);
                    PlayerController.players[ownable.getOwnedID()].playerAccount.setBalance(PlayerController.players[ownable.getOwnedID()].playerAccount.getBalance() + ((Street) ownable).currentRent);
                    gui.getUserButtonPressed("Du er landet på " + ownable.getName() + " Dette felt er ikke ejet af dig.\nDu skal betale " + ((Street) ownable).currentRent + "kr i leje", "Betal");
                    updateView(PlayerController.players.length);
                }
                boolean checkShipping = (ownable instanceof Shipping);
                if (checkShipping) {
                    if (PlayerController.players[ownable.getOwnedID()].getShippingOwned() == 1) {
                        ((Shipping) ownable).landOnowned(1);
                        PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - ((Shipping) ownable).getToPay());
                        PlayerController.players[ownable.getOwnedID()].playerAccount.setBalance(PlayerController.players[ownable.getOwnedID()].playerAccount.getBalance() + ((Shipping) ownable).getToPay());
                        gui.getUserButtonPressed("Du ejer ikke dette rederi, det gør: " + playerController.getPlayers()[ownable.getOwnedID()].getName() + ". Du skal betale 500 kr.", "Betal");
                        updateView(PlayerController.players.length);
                    } else if (PlayerController.players[ownable.getOwnedID()].getShippingOwned() == 2) {
                        ((Shipping) ownable).landOnowned(2);
                        PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - ((Shipping) ownable).getToPay());
                        PlayerController.players[ownable.getOwnedID()].playerAccount.setBalance(PlayerController.players[ownable.getOwnedID()].playerAccount.getBalance() + ((Shipping) ownable).getToPay());
                        gui.getUserButtonPressed("Du ejer ikke dette rederi, det gør: " + playerController.getPlayers()[ownable.getOwnedID()].getName() + ". Du skal betale 1000 kr. fordi han ejer 1 andet rederi", "Betal");
                        updateView(PlayerController.players.length);
                    } else if (PlayerController.players[ownable.getOwnedID()].getShippingOwned() == 3) {
                        ((Shipping) ownable).landOnowned(3);
                        PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - ((Shipping) ownable).getToPay());
                        PlayerController.players[ownable.getOwnedID()].playerAccount.setBalance(PlayerController.players[ownable.getOwnedID()].playerAccount.getBalance() + ((Shipping) ownable).getToPay());
                        gui.getUserButtonPressed("Du ejer ikke dette rederi, det gør: " + playerController.getPlayers()[ownable.getOwnedID()].getName() + ". Du skal betale 2000 kr. fordi denne person ejer 2 rederier mere.", "Betal");
                        updateView(PlayerController.players.length);
                    } else if (PlayerController.players[ownable.getOwnedID()].getShippingOwned() == 4) {
                        ((Shipping) ownable).landOnowned(4);
                        PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - ((Shipping) ownable).getToPay());
                        PlayerController.players[ownable.getOwnedID()].playerAccount.setBalance(PlayerController.players[ownable.getOwnedID()].playerAccount.getBalance() + ((Shipping) ownable).getToPay());
                        gui.getUserButtonPressed("Du ejer ikke dette rederi, det gør: " + playerController.getPlayers()[ownable.getOwnedID()].getName() + ". Du skal betale 4000 kr. fordi personen ejer alle rederierne.", "Betal");
                        updateView(PlayerController.players.length);
                    }
                    gui.getUserButtonPressed(playerController.getPlayers()[ownable.getOwnedID()].getName() + " ejer denne grund. Du har betalt: " + ((Shipping) ownable).getToPay());
                }
                boolean checkBrewery = (ownable instanceof Brewery);
                if (checkBrewery) {
                    if (PlayerController.players[ownable.getOwnedID()].getBreweryOwned() == 1) {
                        int toPay = dice.getTotal() * 100;
                        PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - toPay);
                        PlayerController.players[ownable.getOwnedID()].playerAccount.setBalance(PlayerController.players[ownable.getOwnedID()].playerAccount.getBalance() + toPay);
                        gui.getUserButtonPressed(playerController.getPlayers()[ownable.getOwnedID()].getName() + "ejer denne grund. Du har betalt: " + toPay, "Betal");
                        updateView(PlayerController.players.length);


                    } else if (PlayerController.players[ownable.getOwnedID()].getBreweryOwned() == 2) {
                        int toPay = dice.getTotal() * 200;
                        PlayerController.players[playerIndex].playerAccount.setBalance(PlayerController.players[playerIndex].playerAccount.getBalance() - toPay);
                        gui.getUserButtonPressed(playerController.getPlayers()[ownable.getOwnedID()].getName() + "ejer denne grund. Du har betalt: " + toPay, "Betal");
                        updateView(PlayerController.players.length);

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
            if (player.getPos() == 38) {
                player.playerAccount.setBalance(player.playerAccount.getBalance() - taxField.getTaxPrice());
            } else if (player.getPos() == 4) {
                boolean select2 = gui.getUserLeftButtonPressed(PlayerController.players[playerIndex].getName() + "Betal 10% eller 4000k ", "10%", "4000kr");
                if (select2) {
                    player.playerAccount.setBalance((int) (player.playerAccount.getBalance() * 0.9));
                } else {
                    player.playerAccount.setBalance(player.playerAccount.getBalance() - taxField.getTaxPrice());
                }
            }
            updateView(PlayerController.players.length);
        }
        boolean checkJailField = (boardController.getField()[PlayerController.players[playerIndex].getPos()] instanceof Jail);
        if (checkJailField) {
            if (!PlayerController.players[playerIndex].isInJail()) {
                gui.showMessage("Du er på besøg i fængslet.");
            }
        }
        boolean checkParkingField = (boardController.getField()[PlayerController.players[playerIndex].getPos()] instanceof Parking);
        if (checkParkingField) {
            gui.showMessage("Du er ankommet til gratis parkering.\nTag en pause :-)");
        }
        checkPlayerAccount(playerIndex);
    }

    public void checkPlayerAccount(int playerIndex) {
        Player player = playerController.getPlayers()[playerIndex];

        while (player.playerAccount.getBalance() < 0) {
            String fallit = gui.getUserButtonPressed(player.getName() + ", du har ikke råd til at betale det du skal, du har nu disse muligheder", "Pantsæt grund", "Sælg hus/hotel", "Giv op");
            switch (fallit) {
                case "Pantsæt grund":
                    playerPawns(playerIndex);
                    updateView(PlayerController.players.length);
                    if (player.playerAccount.getBalance() >= 0) {
                        gui.showMessage("Du har nu nok penge til at betale det du skylder");
                        break;
                    } else {
                        gui.showMessage("Du har stadig ikke nok penge til at betale det du skylder");
                    }
                case "Sælg hus/hotel":
                    sellHouse(playerIndex);
                    break;

                case "Giv op":
                    player.playerAccount.setBalance(0);
                    player.setBankrupt(true);
                    gui.getFields()[PlayerController.players[playerIndex].getPos()].setCar(playerController.getGui_players()[playerIndex], false);
                    updateView(PlayerController.players.length);
                    gui.showMessage(player.getName() + " du er erklæret fallit og er nu ude af spillet");
                    playersGivenUp++;
                    for (int i = 0; i < player.owns.size(); i++) {
                        Ownable ownable = (Ownable) boardController.getField()[player.owns.get(i)];
                        GUI_Ownable gui_ownable = (GUI_Ownable) boardController.getGui_fields()[i];
                        ownable.setOwnedID(-1);
                        gui_ownable.setBorder(null);
                    }
                    break;
            }
        }
    }

    public void playerPawns(int playerIndex) {
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

    public void playerBuysBack(int playerIndex) {
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

        if (offer <= PlayerController.players[playerIDBuys].playerAccount.getBalance()) {
            String answer = gui.getUserButtonPressed(playerController.getPlayers()[playerIDSells].getName() + " ,ønsker du at sælge " + tradeOwnable.getName() + " til: " + playerController.getPlayers()[playerIDBuys].getName() + " for: " + offer + " kr.", "Ja", "Nej", "Modbud");
            switch (answer) {
                case "Ja":
                    playerController.trade(playerIDSells, playerIDBuys, owns[ownableChosen], offer);
                    tradeOwnable.setOwnedID(playerIDBuys);
                    updateView(PlayerController.players.length);
                    tradeGui_ownable.setOwnerName(playerController.getPlayers()[playerIDBuys].getName());
                    tradeGui_ownable.setBorder(playerController.colors[playerIDBuys]);
                    boolean checkBrewery = (tradeOwnable instanceof Brewery);
                    if (checkBrewery) {
                        playerController.getPlayers()[playerIDSells].setBreweryOwned(playerController.getPlayers()[playerIDSells].getBreweryOwned() - 1);
                        playerController.getPlayers()[playerIDBuys].setBreweryOwned(playerController.getPlayers()[playerIDBuys].getBreweryOwned() + 1);
                    }
                    boolean checkShipping = (tradeOwnable instanceof Shipping);
                    if (checkShipping) {
                        playerController.getPlayers()[playerIDSells].setShippingOwned(playerController.getPlayers()[playerIDSells].getShippingOwned() - 1);
                        playerController.getPlayers()[playerIDBuys].setShippingOwned(playerController.getPlayers()[playerIDBuys].getShippingOwned() + 1);
                    }
                    break;
                case "Nej":
                    break;
                case "Modbud":
                    int counterOffer = gui.getUserInteger(playerController.getPlayers()[playerIDSells].getName() + " kom med et modbud.");
                    if (counterOffer <= PlayerController.players[playerIDBuys].playerAccount.getBalance()) {
                        String counterAnswer = gui.getUserButtonPressed(playerController.getPlayers()[playerIDBuys].name + " du har modtaget et modbud på: " + counterOffer + " vil du købe grunden til denne pris: ", "Ja", "Nej");
                        switch (counterAnswer) {
                            case "Ja":
                                playerController.trade(playerIDSells, playerIDBuys, owns[ownableChosen], counterOffer);
                                tradeOwnable.setOwnedID(playerIDBuys);
                                updateView(PlayerController.players.length);
                                tradeGui_ownable.setOwnerName(playerController.getPlayers()[playerIDBuys].getName());
                                tradeGui_ownable.setBorder(playerController.colors[playerIDBuys]);
                                boolean checkBrewery2 = (tradeOwnable instanceof Brewery);
                                if (checkBrewery2) {
                                    playerController.getPlayers()[playerIDSells].setBreweryOwned(playerController.getPlayers()[playerIDSells].getBreweryOwned() - 1);
                                    playerController.getPlayers()[playerIDBuys].setBreweryOwned(playerController.getPlayers()[playerIDBuys].getBreweryOwned() + 1);
                                }
                                boolean checkShipping2 = (tradeOwnable instanceof Shipping);
                                if (checkShipping2) {
                                    playerController.getPlayers()[playerIDSells].setShippingOwned(playerController.getPlayers()[playerIDSells].getShippingOwned() - 1);
                                    playerController.getPlayers()[playerIDBuys].setShippingOwned(playerController.getPlayers()[playerIDBuys].getShippingOwned() + 1);
                                }
                                break;
                            case "Nej":
                                break;
                        }
                    } else {
                        gui.showMessage(PlayerController.players[playerIDBuys].getName() + " har ikke nok penge til at gennemføre det bud");
                        break;
                    }
                    break;
            }
        } else {
            gui.showMessage("Du har ikke nok penge til at lave dette bud");
        }
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
        //TODO at kunne sælge kortet


        boolean checkGoToJailCard = (card instanceof GoToJailCard);
        if (checkGoToJailCard) {
            GoToJailCard goToJail = (GoToJailCard) card;
            gui.getFields()[player.getPos()].setCar(playerController.getGui_players()[playerIndex], false);
            player.setPos(goToJail.getGoToJail());
            gui.getFields()[player.getPos()].setCar(playerController.getGui_players()[playerIndex], true);
            player.setInJail(true);
            updateView(PlayerController.players.length);
        }

        boolean checkIncreasePrice = (card instanceof IncreasePrice);
        if (checkIncreasePrice) {
            IncreasePrice increasePrice = (IncreasePrice) card;
            int counter = 0;
            int i = 0;
            while (i < playerController.getPlayers()[playerIndex].owns.size()) {
                Ownable ownable = (Ownable) boardController.getField()[playerController.getPlayers()[playerIndex].owns.get(i)];
                boolean checkStreet = ownable instanceof Street;
                if (checkStreet) {
                    counter++;
                }
                i++;
            }
            int[] ownsStreets = new int[counter];
            int i2 = 0;
            int place = 0;
            while (i2 < playerController.getPlayers()[playerIndex].owns.size()) {
                Ownable ownable = (Ownable) boardController.getField()[playerController.getPlayers()[playerIndex].owns.get(i2)];
                boolean checkStreet = ownable instanceof Street;
                if (checkStreet) {
                    ownsStreets[place] = playerController.getPlayers()[playerIndex].owns.get(i2);
                    place++;
                }
                i2++;
            }
            if (ownsStreets.length != 0) {
                int buildCounter = 0;
                int i3 = 0;
                while (i3 < ownsStreets.length) {
                    Street street = (Street) boardController.getField()[ownsStreets[i3]];
                    if (street.isCanBuild()) {
                        buildCounter++;
                    }
                    i3++;
                }
                int[] canBuild = new int[buildCounter];
                int i4 = 0;
                int place2 = 0;
                while (i4 < ownsStreets.length) {
                    Street street = (Street) boardController.getField()[ownsStreets[i4]];
                    if (street.isCanBuild()) {
                        canBuild[place2] = ownsStreets[i4];
                        place2++;
                    }
                    i4++;
                }
                int houseBuildCounter = 0;
                for (int j = 0; j < canBuild.length; j++) {
                    Street street = (Street) boardController.getField()[canBuild[j]];
                    if (street.getHouseCount() != 0) {
                        houseBuildCounter++;
                    }
                }
                int[] streetsWithHouse = new int[houseBuildCounter];
                int place3 = 0;
                for (int j = 0; j < canBuild.length; j++) {
                    Street street = (Street) boardController.getField()[canBuild[j]];
                    if (street.getHouseCount() != 0) {
                        streetsWithHouse[place3] = canBuild[j];
                        place3++;
                    }
                }
                int houseCounter = 0;
                int hotelCounter = 0;
                for (int j = 0; j < streetsWithHouse.length; j++) {
                    Street street = (Street) boardController.getField()[streetsWithHouse[j]];
                    if (street.getHouseCount() < 5) {
                        houseCounter = houseCounter + street.getHouseCount();
                    }
                    else if (street.getHouseCount() == 5) {
                        hotelCounter++;
                    }
                }
                player.playerAccount.setBalance(player.playerAccount.getBalance() - ((hotelCounter * increasePrice.getPrHotel()) + (houseCounter * increasePrice.getPrHouse())));
            }
        }

        boolean checkMoneyFromPlayer = (card instanceof MoneyFromPlayer);
        if (checkMoneyFromPlayer) {
            MoneyFromPlayer moneyFromPlayer = (MoneyFromPlayer) card;
            player.playerAccount.setBalance(player.playerAccount.getBalance() + ((PlayerController.players.length) - playersGivenUp) * moneyFromPlayer.getReceiveFromPlayer());
            int i = 0;
            while (i < PlayerController.players.length) {
                if (!playerController.getPlayers()[i].isBankrupt()) {
                    PlayerController.players[i].playerAccount.setBalance(PlayerController.players[i].playerAccount.getBalance() - moneyFromPlayer.getReceiveFromPlayer());
                    i++;
                }
            }
            updateView(PlayerController.players.length);
        }

        boolean checkMove = (card instanceof Move);
        if (checkMove) {
            Move move = (Move) card;
            gui.getFields()[player.getPos()].setCar(playerController.getGui_players()[playerIndex], false);
            playerController.movePlayer(playerIndex, move.getMove());
            gui.getFields()[player.getPos()].setCar(playerController.getGui_players()[playerIndex], true);
            checkSubClasses(playerIndex);
            updateView(PlayerController.players.length);
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
        }


        boolean checkReceiveMoney = (card instanceof ReceiveMoney);
        if (checkReceiveMoney) {
            ReceiveMoney receiveMoney = (ReceiveMoney) card;
            if (cardDeck.receiveID() == 24) {
                if (player.playerAccount.getBalance() <= 5000) {
                    player.playerAccount.setBalance(player.playerAccount.getBalance() + receiveMoney.getReceive());
                } else {
                    gui.showMessage("Du har for mange penge på din konto til at modtage legatet");
                }
            } else {
                player.playerAccount.setBalance(player.playerAccount.getBalance() + receiveMoney.getReceive());
            }
            updateView(PlayerController.players.length);
        }
    }

    public void build(int playerIndex) {
        int counter = 0;
        int i = 0;
        while (i < playerController.getPlayers()[playerIndex].owns.size()) {
            Ownable ownable = (Ownable) boardController.getField()[playerController.getPlayers()[playerIndex].owns.get(i)];
            boolean checkStreet = ownable instanceof Street;
            if (checkStreet) {
                counter++;
            }
            i++;
        }
        int[] ownsStreets = new int[counter];
        int i2 = 0;
        int place = 0;
        while (i2 < playerController.getPlayers()[playerIndex].owns.size()) {
            Ownable ownable = (Ownable) boardController.getField()[playerController.getPlayers()[playerIndex].owns.get(i2)];
            boolean checkStreet = ownable instanceof Street;
            if (checkStreet) {
                ownsStreets[place] = playerController.getPlayers()[playerIndex].owns.get(i2);
                place++;
            }
            i2++;
        }
        if (ownsStreets.length != 0) {
            int buildCounter = 0;
            int i3 = 0;
            while (i3 < ownsStreets.length) {
                Street street = (Street) boardController.getField()[ownsStreets[i3]];
                if (street.isCanBuild()) {
                    buildCounter++;
                }
                i3++;
            }
            int[] canBuild = new int[buildCounter];
            String[] names = new String[buildCounter];
            int i4 = 0;
            int place2 = 0;
            while (i4 < ownsStreets.length) {
                Street street = (Street) boardController.getField()[ownsStreets[i4]];
                if (street.isCanBuild()) {
                    canBuild[place2] = ownsStreets[i4];
                    names[place2] = street.getName();
                    place2++;
                }
                i4++;
            }
            if (canBuild.length == 0) {
                gui.getUserButtonPressed("Du har ingen grunde du kan bygge på.", "Fortsæt");
            } else {
                String chosenElement = gui.getUserSelection("Hvilken grund ønsker du at bygge på? ", names);
                int streetChosen;
                for (streetChosen = 0; streetChosen < names.length; streetChosen++) {
                    boolean chosen = names[streetChosen].equals(chosenElement);
                    if (chosen) {
                        break;
                    }
                }
                Street buildOnStreet = (Street) boardController.getField()[canBuild[streetChosen]];
                GUI_Street buildGui_Street = (GUI_Street) boardController.getGui_fields()[canBuild[streetChosen]];
                boolean equalBuild = true;
                for (int j = 0; j < canBuild.length && equalBuild; j++) {
                    Street compareStreet = (Street) boardController.getField()[canBuild[j]];
                    if (buildOnStreet.getMainColor().equals(compareStreet.getMainColor())) {
                        if (buildOnStreet.getHouseCount() > compareStreet.getHouseCount()) {
                            equalBuild = false;
                            break;
                        } else {
                            equalBuild = true;
                        }
                    }
                }
                if (equalBuild) {
                    if (buildOnStreet.getHouseCount() < 4) {
                        buildOnStreet.build(1);
                        buildGui_Street.setHouses(buildOnStreet.getHouseCount());
                        playerController.getPlayers()[playerIndex].playerAccount.setBalance(playerController.getPlayers()[playerIndex].playerAccount.getBalance() - buildOnStreet.getHousePrice());
                        gui.getUserButtonPressed("Du har nu bygget et hus på: " + buildOnStreet.getName(), "Fortsæt");
                    } else if (buildOnStreet.getHouseCount() == 5) {
                        gui.getUserButtonPressed("Du kan ikke bygge mere på denne grund.", "Fortsæt");
                    } else {
                        buildOnStreet.build(1);
                        buildGui_Street.setHotel(true);
                        playerController.getPlayers()[playerIndex].playerAccount.setBalance(playerController.getPlayers()[playerIndex].playerAccount.getBalance() - buildOnStreet.getHousePrice());
                        gui.getUserButtonPressed("Du har nu bygget et hotel på: " + buildOnStreet.getName(), "Fortsæt");
                    }
                } else {
                    gui.getUserButtonPressed("Du skal bygge ligeligt på dine grunde af samme farve.", "Fortsæt");
                }
            }
        }
    }
        public void sellHouse (int playerIndex){
            int counter = 0;
            int i = 0;
            while (i < playerController.getPlayers()[playerIndex].owns.size()) {
                Ownable ownable = (Ownable) boardController.getField()[playerController.getPlayers()[playerIndex].owns.get(i)];
                boolean checkStreet = ownable instanceof Street;
                if (checkStreet) {
                    counter++;
                }
                i++;
            }
            int[] ownsStreets = new int[counter];
            int i2 = 0;
            int place = 0;
            while (i2 < playerController.getPlayers()[playerIndex].owns.size()) {
                Ownable ownable = (Ownable) boardController.getField()[playerController.getPlayers()[playerIndex].owns.get(i2)];
                boolean checkStreet = ownable instanceof Street;
                if (checkStreet) {
                    ownsStreets[place] = playerController.getPlayers()[playerIndex].owns.get(i2);
                    place++;
                }
                i2++;
            }
            if (ownsStreets.length != 0) {
                int buildCounter = 0;
                int i3 = 0;
                while (i3 < ownsStreets.length) {
                    Street street = (Street) boardController.getField()[ownsStreets[i3]];
                    if (street.isCanBuild()) {
                        buildCounter++;
                    }
                    i3++;
                }
                int[] canBuild = new int[buildCounter];
                int i4 = 0;
                int place2 = 0;
                while (i4 < ownsStreets.length) {
                    Street street = (Street) boardController.getField()[ownsStreets[i4]];
                    if (street.isCanBuild()) {
                        canBuild[place2] = ownsStreets[i4];
                        place2++;
                    }
                    i4++;
                }
                int houseBuildCounter = 0;
                for (int j = 0; j < canBuild.length; j++) {
                    Street street = (Street) boardController.getField()[canBuild[j]];
                    if (street.getHouseCount() != 0) {
                        houseBuildCounter++;
                    }
                }
                int[] streetsWithHouse = new int[houseBuildCounter];
                String[] names = new String[houseBuildCounter];
                int place3 = 0;
                for (int j = 0; j < canBuild.length; j++) {
                    Street street = (Street) boardController.getField()[canBuild[j]];
                    if (street.getHouseCount() != 0) {
                        streetsWithHouse[place3] = canBuild[j];
                        names[place3] = street.getName();
                        place3++;
                    }
                }
                if (names.length == 0) {
                    gui.showMessage("Du har ikke nogen huse at sælge.");
                } else {
                    String chosenElement = gui.getUserSelection("Fra hvilken grund ønsker du at sælge en bygning? ", names);
                    int houseChosen;
                    for (houseChosen = 0; houseChosen < names.length; houseChosen++) {
                        boolean chosen = names[houseChosen].equals(chosenElement);
                        if (chosen) {
                            break;
                        }
                    }
                    Street sellHouseOnStreet = (Street) boardController.getField()[canBuild[houseChosen]];
                    GUI_Street buildGui_Street = (GUI_Street) boardController.getGui_fields()[canBuild[houseChosen]];
                    if (sellHouseOnStreet.getHouseCount() == 5) {
                        sellHouseOnStreet.sellHouse(1);
                        buildGui_Street.setHotel(false);
                        buildGui_Street.setHouses(sellHouseOnStreet.getHouseCount());
                        playerController.getPlayers()[playerIndex].playerAccount.setBalance(playerController.getPlayers()[playerIndex].playerAccount.getBalance() - (sellHouseOnStreet.getHousePrice() / 2));
                    } else {
                        sellHouseOnStreet.sellHouse(1);
                        buildGui_Street.setHouses(sellHouseOnStreet.getHouseCount());
                        playerController.getPlayers()[playerIndex].playerAccount.setBalance(playerController.getPlayers()[playerIndex].playerAccount.getBalance() - (sellHouseOnStreet.getHousePrice() / 2));
                    }
                }
            }
        }
}




