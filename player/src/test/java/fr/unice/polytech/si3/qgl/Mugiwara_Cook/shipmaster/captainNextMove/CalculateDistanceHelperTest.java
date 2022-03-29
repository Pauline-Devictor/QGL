package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Wind;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.possibleDistance.DistanceWithWindOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalculateDistanceHelperTest {
    int sailsTotal;
    int sailsUsed;
    double angleBetweenBoatAndWind;
    Wind wind;
    Checkpoint checkpoint;
    Ship ship;

    @BeforeEach
    void set() {
        wind = new Wind(2, 2);
        checkpoint = mock(Checkpoint.class);
        ship = mock(Ship.class);
    }

    @Test
    void distanceForWind() {
        this.sailsTotal = 4;
        this.sailsUsed = 3;
        DistanceWithWindOption result = CalculateDistanceHelper.distanceForWind(sailsTotal, sailsUsed, angleBetweenBoatAndWind, wind);
        assertEquals(1.5, result.getDistance());
    }

    @Test
    void distanceForWindNoSail() {
        this.sailsTotal = 0;
        this.sailsUsed = 0;
        DistanceWithWindOption result = CalculateDistanceHelper.distanceForWind(sailsTotal, sailsUsed, angleBetweenBoatAndWind, wind);
        DistanceWithWindOption Null = new DistanceWithWindOption(0, 0);
        assertEquals(Null.getDistance(), result.getDistance());
        assertEquals(Null.getNbsails(), result.getNbsails());


    }

    @Test
    void distanceBrowsed() {
        int oarLeft = 2;
        int oarRight = 3;
        int oarTotal = 9;
        double result = CalculateDistanceHelper.distanceBrowsed(oarLeft, oarRight, oarTotal);
        assertEquals(result, 91.66666666666667);
    }

    @Test
    void remainingDistance() {
        int oarLeft = 2;
        int oarRight = 3;
        int[] nbOarsUsed = {oarLeft, oarRight};
        Position positionCheckpoint = new Position(300, 300, 0);
        Position positionBoat = new Position(0, 0, 1);
        when(checkpoint.getPosition()).thenReturn(positionCheckpoint);
        when(ship.getNbOars()).thenReturn(9);
        assertEquals(CalculateDistanceHelper.remainingDistance(nbOarsUsed, checkpoint, ship, positionBoat), 332.59740204526184);
    }

    @Test
    void distanceBetweenPointAndCheckpoint() {
        Position positionCheckpoint = new Position(300, 300, 0);
        Position positionBoat = new Position(0, 0, 1);
        when(checkpoint.getPosition()).thenReturn(positionCheckpoint);
        assertEquals(CalculateDistanceHelper.distanceBetweenPointAndCheckpoint(checkpoint, positionBoat.getX(), positionBoat.getY()), 424.26406871192853);
    }

}