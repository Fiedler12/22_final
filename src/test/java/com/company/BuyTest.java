package com.company;

import Fields.BoardController;
import Fields.Street;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuyTest {

    @Test
    void doubleRentTest() {
        BoardController boardController = new BoardController();
        Street rodovrevej = (Street) boardController.getField()[1];
        Street hvidovrevej = (Street) boardController.getField()[3];
        Street gronningen = (Street) boardController.getField()[24];
        rodovrevej.setOwnedID(0);
        hvidovrevej.setOwnedID(0);
        boardController.checkFields();
        assertEquals(100, rodovrevej.currentRent);
        assertEquals(100, hvidovrevej.currentRent);
        assertEquals(400, gronningen.currentRent);
        assertTrue(rodovrevej.isCanBuild());
        assertTrue(hvidovrevej.isCanBuild());
        assertFalse(gronningen.isCanBuild());
    }
}