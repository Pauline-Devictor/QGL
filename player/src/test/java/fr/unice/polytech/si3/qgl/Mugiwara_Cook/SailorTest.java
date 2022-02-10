package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.*;

class SailorTest {

    Sailor sailor1 = new Sailor(1,4,2,"gogol");
    Sailor sailor2 = new Sailor(1,1,0,"frere de gogol");
    Oar rame1 = new Oar(0,0);
    Oar rame2 = new Oar(3,1);

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
}