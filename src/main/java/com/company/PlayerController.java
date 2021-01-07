package com.company;
import gui_fields.GUI_Player;
import java.awt.*;

public class PlayerController {
        static Player[] players;
        GUI_Player[] gui_players;
        Color[] colors = new Color[6];
        public PlayerController() { }

        public Player[] getSpillere () {
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
        public void playerBuys(int fieldID) {

        }
}
