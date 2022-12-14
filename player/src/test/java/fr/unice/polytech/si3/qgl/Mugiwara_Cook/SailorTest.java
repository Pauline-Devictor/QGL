package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.ActionJSON;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void tooFarFromEquipement(){
        sailor2 = new Sailor(0,0,0,"un autre nom");
        rame2 = new Oar(6,0);
        assertTrue(sailor2.itemIsTooFar(rame2));
        rame2 = new Oar(0,6);
        assertTrue(sailor2.itemIsTooFar(rame2));
        rame2 = new Oar(2,3);
        assertFalse(sailor2.itemIsTooFar(rame2));
    }

    @Test
    void notToFarFromEquipement(){
        sailor2 = new Sailor(1,0,0,"un autre nom");
        rame2 = new Oar(4,1);
        assertFalse(sailor2.itemIsTooFar(rame2));
    }
    @Test
    void sailorIsOnEquipement(){
        sailor2 = new Sailor(1,0,0,"un autre nom");
        rame2 = new Oar(0,0);
        sailor2.attachEquipment(rame2);
        assertTrue(sailor2.onIsAssignEquipment());
    }
    @Test
    void sailorIsNotOnEquipement(){
        sailor2 = new Sailor(1,0,0,"un autre nom");
        rame2 = new Oar(0,1);
        sailor2.attachEquipment(rame2);
        assertFalse(sailor2.onIsAssignEquipment());
    }
    @Test
    void moveToEquipmentX(){
        ActionJSON actionJson = new ActionJSON();
        sailor2.attachEquipment(rame1);
        assertTrue(sailor2.moveToEquipment(actionJson));
        assertEquals(sailor2.getX(),rame1.getX());
        //Cas n??gatifs
        sailor2.setX(-6);
        assertFalse(sailor2.moveToEquipment(actionJson));
        assertEquals(sailor2.getX(),-1);

        sailor2.setX(-1);
        assertTrue(sailor2.moveToEquipment(actionJson));
        assertEquals(0,sailor2.getX());

        sailor2.setX(6);
        assertFalse(sailor2.moveToEquipment(actionJson));
        assertEquals(1,sailor2.getX());

        sailor2.setX(1);
        assertTrue(sailor2.moveToEquipment(actionJson));
        assertEquals(0,sailor2.getX());

        sailor2.setX(0);
        assertTrue(sailor2.moveToEquipment(actionJson));
        assertEquals(0,sailor2.getX());


    }
    @Test
    void moveToEquipmentY(){
        ActionJSON actionJson = new ActionJSON();
        assertEquals(sailor2.getY(),rame1.getY());
        sailor2.attachEquipment(rame1);
        sailor1.setX(0);

        sailor2.setY(-6);
        assertFalse(sailor2.moveToEquipment(actionJson));
        assertEquals(-1,sailor2.getY());

        sailor2.setY(1);
        assertTrue(sailor2.moveToEquipment(actionJson));
        assertEquals(0,sailor2.getY());


        sailor2.setY(-1);
        assertTrue(sailor2.moveToEquipment(actionJson));
        assertEquals(0,sailor2.getY());

        sailor2.setY(6);
        assertFalse(sailor2.moveToEquipment(actionJson));
        assertEquals(1,sailor2.getY());


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

    @Test
    void toofarBoundaries(){
        sailor1.setX(-4);
        sailor1.setY(0);
        rame1 = new Oar(2,0);
        assertTrue(sailor1.itemIsTooFar(rame1));

        sailor1.setX(0);
        sailor1.setY(-4);
        rame1 = new Oar(0,2);
        assertTrue(sailor1.itemIsTooFar(rame1));
    }
}