package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Display;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Wind;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalculateAngleHelperTest {

    double angleBetweenBoatAndWind;
    Wind wind;
    Checkpoint checkpoint;
    Ship ship;
    Position positionCheckpoint;
    Position positionBoat;

    @BeforeEach
    void set() {
        wind = new Wind(2, 2);
        checkpoint = mock(Checkpoint.class);
        ship = mock(Ship.class);
        positionCheckpoint = new Position(300.0, 300.0, 0.0);
        positionBoat = new Position(0.0, 0.0, 1.0);
        when(checkpoint.getPosition()).thenReturn(positionCheckpoint);
    }

    @Test
    void angleBetweenPointAndCheckpoint() {
        assertEquals(0.21460183660255117,CalculateAngleHelper.angleBetweenPointAndCheckpoint(checkpoint, positionBoat.getX(), positionBoat.getY(), positionBoat.getOrientation()));
    }

    @Test
    void setAngleBetweenBoatAndWind() {
        assertEquals(1,CalculateAngleHelper.angleBetweenBoatAndWind(positionBoat.getOrientation(), wind.getOrientation()));
    }

    @Test
    void realAngleBetweenPointAndCheckpointAligned() {
        assertEquals(0,CalculateAngleHelper.realAngleBetweenPointAndCheckpoint(checkpoint, 0, 0, 0, 0));
    }

    @Test
    void realAngleBetweenPointAndCheckpointAngleTurnToTheLeft() {
        double angleBetweenShipOrientationAndCheckpoint = 2.0;
        assertEquals(angleBetweenShipOrientationAndCheckpoint,CalculateAngleHelper.realAngleBetweenPointAndCheckpoint(checkpoint, positionBoat.getX(), positionBoat.getY(), positionBoat.getOrientation(), angleBetweenShipOrientationAndCheckpoint));
    }

    @Test
    void realAngleBetweenPointAndCheckpointAngleTurnToTheRight() {
        double angleBetweenShipOrientationAndCheckpoint = 2.0;
        positionBoat = new Position(0.0, 0.0, 3.0);
        assertEquals(-angleBetweenShipOrientationAndCheckpoint,CalculateAngleHelper.realAngleBetweenPointAndCheckpoint(checkpoint, positionBoat.getX(), positionBoat.getY(), positionBoat.getOrientation(), angleBetweenShipOrientationAndCheckpoint));
    }

}