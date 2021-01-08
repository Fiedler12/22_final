package com.company;
import gui_fields.GUI_Player;
import java.awt.*;

public class PlayerController {
        static Player[] players;
        GUI_Player[] gui_players;
        Color[] colors = new Color[6];
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
        public void playerSells(int playerIndex, int removeNumber) {
            players[playerIndex].sells(removeNumber);
        }

        public int[] getOwnedIDs(int playerIndex) {
            int[] playerOwns = new int[players[playerIndex].owns.size()];
            players[playerIndex].getOwns();
            return playerOwns;
        }

}
