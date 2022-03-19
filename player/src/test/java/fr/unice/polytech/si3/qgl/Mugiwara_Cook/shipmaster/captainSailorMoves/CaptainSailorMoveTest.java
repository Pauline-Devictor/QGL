package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainSailorMoves;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.MyMapper;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.ActionJSON;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CaptainSailorMoveTest {
    ActionJSON actionJSON;
    CaptainSailorMove captainSailorMove;
    File jsonInit;
    InitGame initGame;

    @BeforeEach
    void start() throws IOException {
        this.actionJSON = new ActionJSON();
        jsonInit = new File("jsonTest/initGame.json");
        initGame = new MyMapper().readValue(jsonInit, InitGame.class);
        captainSailorMove = new CaptainSailorMove(initGame.getShip(), initGame.getSailors());
        initGame.setSailors(new Sailor[]{initGame.getSailors()[0]});
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


}