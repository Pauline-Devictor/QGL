package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Action;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.ActionJSON;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.*;

class SailorTest {

    Sailor sailor1;
    Sailor sailor2;
    Oar rame1;
    Oar rame2;
    Oar rameVide;

    @BeforeEach
    void set(){
        sailor1 = new Sailor(1,4,2,"un nom");
        sailor2 = new Sailor(1,1,0,"un autre nom");
        rame1 = new Oar(0,0);
        rame2 = new Oar(3,1);

    }

    @Test
    void howManyCaseFarFromOarX() {
        assertEquals(4,abs(sailor1.howManyCaseFarFromOarX(rame1)));
        assertEquals(1,abs(sailor1.howManyCaseFarFromOarX(rame2)));
    }

    @Test
    void howManyCaseFarFromOarY() {
        assertEquals(2,abs(sailor1.howManyCaseFarFromOarY(rame1)));
        assertEquals(1,abs(sailor1.howManyCaseFarFromOarY(rame2)));
    }

    @Test
    void findClosestOarFromSailor() {
        ArrayList<Oar> oarsList = new ArrayList<>();
        oarsList.add(rame1);
        oarsList.add(rame2);
        assertTrue(oarsList.get(0)==rame1);
        assertEquals(rame2,sailor1.findClosestOarFromSailor(oarsList));
        assertEquals(rame1,sailor2.findClosestOarFromSailor(oarsList));
    }

    @Test
    void findSpecificClosestEquipementFromSailor() {
        ArrayList<Equipment> entities = new ArrayList<>();
        entities.add((Equipment) rame1);
        entities.add((Equipment) rame2);
        assertEquals((Equipment) rame1,sailor2.findSpecificClosestEquipementFromSailor(sailor2,"oar",entities));
    }

    @Test
    void attach1Oar(){
        sailor1.attachEquipment(rame1);
        assertEquals(sailor1.getEquipment(),rame1);
    }

    @Test
    void attachNoOar(){
        sailor1.attachEquipment(rameVide);
        assertEquals(sailor1.getEquipment(),rameVide);

    }

    @Test
    void assignTrue(){
        sailor1.attachEquipment(rame1);
        assertTrue(sailor1.assign());
    }

    @Test
    void assignFalse(){
        sailor2.attachEquipment(rameVide);
        assertFalse(sailor1.assign());
        assertFalse(sailor2.assign());
    }

    @Test
    void toFarFromEquipement(){
        sailor2 = new Sailor(1,0,0,"un autre nom");
        rame2 = new Oar(5,1);
        boolean true1= sailor2.itemIsTooFar(rame2);
        assertTrue(true1);
    }

    @Test
    void notToFarFromEquipement(){
        sailor2 = new Sailor(1,0,0,"un autre nom");
        rame2 = new Oar(4,1);
        boolean false1= sailor2.itemIsTooFar(rame2);
        assertFalse(false1);
    }
    @Test
    void sailorIsOnEquipement(){
        sailor2 = new Sailor(1,0,0,"un autre nom");
        rame2 = new Oar(0,0);
        sailor2.attachEquipment(rame2);
        boolean true1= sailor2.onIsAssignEquipment();
        assertTrue(true1);
    }
    @Test
    void sailorIsNotOnEquipement(){
        sailor2 = new Sailor(1,0,0,"un autre nom");
        rame2 = new Oar(0,1);
        sailor2.attachEquipment(rame2);
        boolean false1= sailor2.onIsAssignEquipment();
        assertFalse(false1);
    }
    @Test
    void moveToEquipment(){
        ActionJSON actionJson = new ActionJSON();
        sailor2.attachEquipment(rame1);
        assertTrue(sailor2.moveToEquipment(actionJson));
        assertEquals(sailor2.getX(),rame1.getX());
        assertEquals(sailor2.getY(),rame1.getY());
    }
    @Test
    void dontMoveToEquipement(){
        ActionJSON actionJson = new ActionJSON();
        sailor1.attachEquipment(rame1);
        assertFalse(sailor1.moveToEquipment(actionJson));
        assertNotEquals(sailor1.getX(),rame1.getX());
        assertEquals(sailor1.getY(),rame1.getY());
    }

    @Test
    void isAllowedToMove(){
        assertFalse(sailor1.sailorIsAllowedToMove(5,6));
        assertFalse(sailor1.sailorIsAllowedToMove(5,1));
        assertTrue(sailor1.sailorIsAllowedToMove(5,0));
        assertTrue(sailor1.sailorIsAllowedToMove(0,5));
        assertTrue(sailor1.sailorIsAllowedToMove(3,2));
        assertTrue(sailor1.sailorIsAllowedToMove(0,0));
    }

    @Test //TODO
    void CanmoveToEquipment(){
    }
}