package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove;


import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Wind;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalculateAngleHelperTest {

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

    @Test
    void realAngleBetweenPointAndCheckpointAngleEquals() {
        Position position = new Position(1,0,0);
        Shape shape = new Circle(50);
        Checkpoint checkpoint1 = new Checkpoint(position,shape);

        double angleBetweenShipOrientationAndCheckpoint = 2.0;
        positionBoat = new Position(0.0, 0.0, angleBetweenShipOrientationAndCheckpoint / 2);

        assertEquals(angleBetweenShipOrientationAndCheckpoint, CalculateAngleHelper.angleBetweenPointAndCheckpoint(checkpoint1, positionBoat.getX(), positionBoat.getY(), positionBoat.getOrientation() + (angleBetweenShipOrientationAndCheckpoint / 2)));

        assertEquals(-angleBetweenShipOrientationAndCheckpoint,CalculateAngleHelper.realAngleBetweenPointAndCheckpoint(checkpoint1, positionBoat.getX(), positionBoat.getY(), positionBoat.getOrientation(), angleBetweenShipOrientationAndCheckpoint));
    }

    @Test
    void limitAngleBetweenPointAndCheckpoint(){
        Position position = new Position(0,0,0);
        Shape shape = new Circle(50);
        Checkpoint checkpoint1 = new Checkpoint(position,shape);

        positionBoat = new Position(1, 0, 0);
        assertEquals(Math.PI,CalculateAngleHelper.angleBetweenPointAndCheckpoint(checkpoint1, positionBoat.getX(), positionBoat.getY(), positionBoat.getOrientation()));


        positionBoat = new Position(0, 1, Math.PI/2);
        assertEquals(Math.PI,CalculateAngleHelper.angleBetweenPointAndCheckpoint(checkpoint1, positionBoat.getX(), positionBoat.getY(), positionBoat.getOrientation()));


    }

    @Test
    void otherLimitAngleBetweenPointAndCheckpoint(){
        Position position = new Position(1,0,0);
        Shape shape = new Circle(50);
        Checkpoint checkpoint1 = new Checkpoint(position,shape);

        positionBoat = new Position(2, 0, 0);
        assertEquals(Math.PI,CalculateAngleHelper.angleBetweenPointAndCheckpoint(checkpoint1, positionBoat.getX(), positionBoat.getY(), positionBoat.getOrientation()));


        position = new Position(0,1,0);
        checkpoint1 = new Checkpoint(position,shape);
        positionBoat = new Position(0, 2, Math.PI/2);
        assertEquals(Math.PI,CalculateAngleHelper.angleBetweenPointAndCheckpoint(checkpoint1, positionBoat.getX(), positionBoat.getY(), positionBoat.getOrientation()));


    }


}