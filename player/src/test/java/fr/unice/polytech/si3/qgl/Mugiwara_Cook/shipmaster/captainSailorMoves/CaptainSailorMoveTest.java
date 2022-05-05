package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainSailorMoves;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.MyMapper;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.ActionJSON;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Deck;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Rudder;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Sail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CaptainSailorMoveTest {
    ActionJSON actionJSON;
    CaptainSailorMove captainSailorMove;
    File jsonInit;
    InitGame initGame;
    Ship ship;
    List<Equipment> equipmentList;


    @BeforeEach
    void start() throws IOException {
        this.actionJSON = new ActionJSON();
        jsonInit = new File("jsonTest/initGame.json");
        initGame = new MyMapper().readValue(jsonInit, InitGame.class);
        captainSailorMove = new CaptainSailorMove(initGame.getShip(), initGame.getSailors());
        initGame.setSailors(new Sailor[]{initGame.getSailors()[0]});
        ship = mock(Ship.class);


        Equipment oar1 = new Oar(1, 2);
        Equipment oar2 = new Oar(2, 2);
        Equipment oar3 = new Oar(3, 2);
        Equipment oar4 = new Oar(3, 0);
        Equipment oar5 = new Oar(4, 0);
        Equipment oar6 = new Oar(5, 0);
        Equipment sail1 = new Sail(3, 1, false);
        Equipment sail2 = new Sail(5, 1, false);
        Equipment rudder = new Rudder(6, 1);
        equipmentList = new ArrayList<>(List.of(oar1, oar2, oar3, oar4, oar5, oar6, rudder, sail1, sail2));
        Position postion = mock(Position.class);
        Shape shape = mock(Shape.class);
        Deck deck = mock(Deck.class);
        ship = new Ship(100, postion, "bateau", deck, equipmentList, shape);
    }

    @Test
    void moveToAssignEquipmentAndNoOnThisAssignEquipementWith1OarAnd1Sailor() {
        Sailor sailor0 = this.initGame.getSailors()[0];
        Equipment theOar = initGame.getShip().getEquipement("oar").get(0);
        assertFalse(sailor0.assign());
        sailor0.attachEquipment(theOar);
        assertTrue(sailor0.assign());
        assertEquals(0, this.actionJSON.getListAction().size());
        this.captainSailorMove.moveToAssignEquipment(this.actionJSON);
        assertEquals(1, this.actionJSON.getListAction().size());
    }

    @Test
    void moveToAssignEquipmentAndOnThisAssignEquipementWith1OarAnd1Sailor() {
        Sailor sailor0 = this.initGame.getSailors()[0];
        Equipment theOar = initGame.getShip().getEquipement("oar").get(0);
        assertFalse(sailor0.assign());
        sailor0.attachEquipment(theOar);
        assertTrue(sailor0.assign());

        sailor0.setX(theOar.getX());
        sailor0.setY(theOar.getY());

        assertEquals(0, this.actionJSON.getListAction().size());
        this.captainSailorMove.moveToAssignEquipment(this.actionJSON);
        assertEquals(0, this.actionJSON.getListAction().size());
    }


    @Test
    void assignEquipement() {
        Sailor sailor1 = new Sailor(1, 1, 2, "a");
        Sailor sailor2 = new Sailor(2, 2, 2, "b");
        Sailor sailor3 = new Sailor(3, 3, 2, "c");
        Sailor sailor4 = new Sailor(4, 3, 0, "d");
        Sailor sailor5 = new Sailor(5, 4, 0, "e");
        Sailor sailor6 = new Sailor(6, 5, 0, "f");
        Sailor sailor7 = new Sailor(7, 3, 1, "g");
        Sailor[] listSailor = {sailor7, sailor2, sailor3, sailor5, sailor6, sailor4, sailor1};
        CaptainSailorMove captainSailorMove1 = new CaptainSailorMove(ship, listSailor);
        captainSailorMove1.assignEquipement();
        int countOar = 0;
        int countSail = 0;
        int countRudder = 0;
        for (Sailor sailor : listSailor) {
            switch (sailor.getEquipment().getType()) {
                case "oar" -> countOar++;
                case "sail" -> countSail++;
                case "rudder" -> countRudder++;
            }
        }
        assertEquals(4, countOar);
        assertEquals(2, countSail);
        assertEquals(1, countRudder);


    }

    @Test
    void assignSpecificEquipement() {
        Sailor sailor1 = new Sailor(1, 1, 2, "a");
        Sailor sailor2 = new Sailor(2, 2, 2, "b");
        Sailor sailor3 = new Sailor(3, 3, 2, "c");
        Sailor sailor4 = new Sailor(4, 3, 0, "d");
        Sailor sailor5 = new Sailor(5, 4, 0, "e");
        Sailor sailor6 = new Sailor(6, 5, 0, "f");
        Sailor sailor7 = new Sailor(7, 3, 1, "g");
        Sailor[] listSailor = {sailor7, sailor2, sailor3, sailor5, sailor6, sailor4, sailor1};
        CaptainSailorMove captainSailorMove1 = new CaptainSailorMove(ship, listSailor);
        captainSailorMove1.assignSpecificEquipement("oar", 5);
        int countOar = 0;
        int countSail = 0;
        int countRudder = 0;
        for (Sailor sailor : listSailor) {
            if (sailor.getEquipment() != null) {
                switch (sailor.getEquipment().getType()) {
                    case "oar" -> countOar++;
                    case "sail" -> countSail++;
                    case "rudder" -> countRudder++;
                }
            }

        }
        assertEquals(5, countOar);
        assertEquals(0, countSail);
        assertEquals(0, countRudder);

    }

    @Test
    void assignSpecificEquipementWithOneSailors() {
        Sailor sailor1 = new Sailor(1, 1, 2, "a");
        Sailor[] listSailor = {sailor1};
        CaptainSailorMove captainSailorMove1 = new CaptainSailorMove(ship, listSailor);
        captainSailorMove1.assignEquipement();
        int countOar = 0;
        int countSail = 0;
        int countRudder = 0;
        for (Sailor sailor : listSailor) {
            switch (sailor.getEquipment().getType()) {
                case "oar" -> countOar++;
                case "sail" -> countSail++;
                case "rudder" -> countRudder++;
            }
        }
        assertEquals(0, countOar);
        assertEquals(0, countSail);
        assertEquals(1, countRudder);
    }

    @Test
    void assignSpecificEquipementWith5Sailors() {
        Sailor sailor1 = new Sailor(1, 1, 2, "a");
        Sailor sailor2 = new Sailor(2, 2, 2, "b");
        Sailor sailor3 = new Sailor(3, 3, 2, "c");
        Sailor sailor4 = new Sailor(4, 3, 0, "d");
        Sailor sailor5 = new Sailor(5, 4, 0, "e");
        Sailor[] listSailor = {sailor1,sailor2,sailor3,sailor4,sailor5};
        CaptainSailorMove captainSailorMove1 = new CaptainSailorMove(ship, listSailor);
        captainSailorMove1.assignEquipement();
        int countOar = 0;
        int countSail = 0;
        int countRudder = 0;
        for (Sailor sailor : listSailor) {
            switch (sailor.getEquipment().getType()) {
                case "oar" -> countOar++;
                case "sail" -> countSail++;
                case "rudder" -> countRudder++;
            }
        }
        assertEquals(2, countOar);
        assertEquals(2, countSail);
        assertEquals(1, countRudder);
    }



}