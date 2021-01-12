package com.company;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import java.awt.*;

public class PlayerController {
        static Player[] players;
        GUI_Player[] gui_players;
        Color[] colors = new Color[6];
        GUI_Car[] cars = new GUI_Car[6];
        public PlayerController() {
            colors[0] = Color.white;
            colors[1] = Color.yellow;
            colors[2] = Color.blue;
            colors[3] = Color.PINK;
            colors[4] = Color.black;
            colors[5] = Color.GREEN;
        }

        public Player[] getPlayers () {
            return players;
        }

        public GUI_Player[] getGui_players () {
            return gui_players;
        }

        public void makePlayers(int amount) {
            this.players = new Player[amount];
            this.gui_players = new GUI_Player[amount];
        }

        public void movePlayer(int playerIndex, int roll) {
            players[playerIndex].setPos(players[playerIndex].getPos() + roll);
        }
        public void playerBuys(int playerIndex, int fieldID, int price) {
            players[playerIndex].buys(fieldID, price);
        }

        public void playerPawns(int playerIndex, int fieldID, int value) {
            players[playerIndex].pawns(fieldID, value);
        }

        public void buysBackPawn(int playerIndex, int fieldID, int value) {
            players[playerIndex].buyBackPawn(fieldID, value);
        }

    public Color[] getColors() {
        return colors;
    }

    public GUI_Car[] getCars() {
        return cars;
    }

    public void trade(int playerSelling, int playerBuying,int fieldID, int price) {
            players[playerSelling].sells(fieldID, price);
            players[playerBuying].buys(fieldID, price);
    }
}
