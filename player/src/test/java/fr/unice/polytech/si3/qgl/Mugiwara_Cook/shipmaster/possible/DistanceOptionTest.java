package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.possible;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.CalculateDistanceHelper;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.possibleDistance.DistanceOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        DistanceOption result = CalculateDistanceHelper.distance(1,0,1);
        assertEquals(result.getDistance(),165);
        assertEquals(result.getOarLeftRightAndSails()[0],1);
        assertEquals(result.getOarLeftRightAndSails()[1],0);
        result = CalculateDistanceHelper.distance(0,0,0);
        assertEquals(result.getDistance(),NaN);
        assertEquals(result.getOarLeftRightAndSails()[0],0);
        assertEquals(result.getOarLeftRightAndSails()[1],0);
    }
}
