package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        sailor1.attachOar(rame1);
        assertEquals(sailor1.getOar(),rame1);
    }

    @Test
    void attachNoOar(){
        sailor1.attachOar(rameVide);
        assertEquals(sailor1.getOar(),rameVide);

    }

    @Test
    void assignTrue(){
        sailor1.attachOar(rame1);
        assertTrue(sailor1.assign());
    }

    @Test
    void assignFalse(){
        sailor2.attachOar(rameVide);
        assertFalse(sailor1.assign());
        assertFalse(sailor2.assign());
    }
}