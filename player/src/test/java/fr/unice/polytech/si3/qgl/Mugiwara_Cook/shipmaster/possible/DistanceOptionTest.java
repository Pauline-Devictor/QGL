package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.possible;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.DistanceOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.*;

public class DistanceOptionTest {
    DistanceOption distanceOption;
    int[] oarArrayInt = new int[2];

    @BeforeEach
    void set(){
        oarArrayInt[0] = 2;
        oarArrayInt[1] = 2;
        distanceOption = new DistanceOption(200,oarArrayInt);
    }

    @Test
    void distanceOptionCalcul(){
        DistanceOption result = distanceOption.distance(1,0,1);
        assertEquals(result.getDistance(),165);
        assertEquals(result.getOarLeftRight()[0],1);
        assertEquals(result.getOarLeftRight()[1],0);
        result = distanceOption.distance(0,0,0);
        assertEquals(result.getDistance(),NaN);
        assertEquals(result.getOarLeftRight()[0],0);
        assertEquals(result.getOarLeftRight()[1],0);
    }
}