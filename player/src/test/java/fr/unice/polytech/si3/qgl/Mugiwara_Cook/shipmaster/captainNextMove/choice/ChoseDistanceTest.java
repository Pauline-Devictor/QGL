package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.choice;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Wind;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.possibleAngle.AngleOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChoseDistanceTest {
    Ship ship;
    Wind wind;

    @BeforeEach
    void setUp() {
        wind=new Wind(0,0);
    }

    @Test
    void distanceBetweenPointAndCheckpointAxeY() {
        Checkpoint checkpointx0y500 = new Checkpoint(new Position(0, 500, 0), new Circle(100));
        double distance = ChoseDistance.distanceBetweenPointAndCheckpoint(checkpointx0y500, 0, 0);

        assertEquals(500, distance);
    }

    @Test
    void distanceBetweenPointAndCheckpointAxeXBehind() {
        Checkpoint checkpointx_500y0 = new Checkpoint(new Position(-500, 0, 0), new Circle(100));
        double distance = ChoseDistance.distanceBetweenPointAndCheckpoint(checkpointx_500y0, 0, 0);

        assertEquals(500, distance);
    }

    @Test
    void distanceBetweenPointAndCheckpointBehindNotSartingIn0_0() {
        Checkpoint checkpointx_500y0 = new Checkpoint(new Position(-500, 0, 0), new Circle(100));
        double distance = ChoseDistance.distanceBetweenPointAndCheckpoint(checkpointx_500y0, 500, 0);

        assertEquals(1000, distance);
    }

    @Test
    void choiceBestNbOarLoin() {
        ship = mock(Ship.class);
        when(ship.getNbUsableOarsLeft()).thenReturn(2);
        when(ship.getNbUsableOarsRight()).thenReturn(2);
        when(ship.getNbOars()).thenReturn(4);

        AngleOption angleOption = new AngleOption(0, 0);
        Checkpoint checkpointx500y0 = new Checkpoint(new Position(500, 0, 0), new Circle(100));
        Position positionShip = new Position(0, 0, 0);

        int[] oarsOption = ChoseDistance.choiceBestNbOar(angleOption, checkpointx500y0, ship, positionShip);
        assertEquals(2, oarsOption[0]);
        assertEquals(2, oarsOption[1]);
    }

    @Test
    void choiceBestNbOarProche() {
        ship = mock(Ship.class);
        when(ship.getNbUsableOarsLeft()).thenReturn(2);
        when(ship.getNbUsableOarsRight()).thenReturn(2);
        when(ship.getNbOars()).thenReturn(4);

        AngleOption angleOption = new AngleOption(0, 0);
        Checkpoint checkpointx100y0 = new Checkpoint(new Position(100, 0, 0), new Circle(100));
        Position positionShip = new Position(0, 0, 0);

        int[] oarsOption = ChoseDistance.choiceBestNbOar(angleOption, checkpointx100y0, ship, positionShip);
        assertEquals(1, oarsOption[0]);
        assertEquals(1, oarsOption[1]);
    }

}