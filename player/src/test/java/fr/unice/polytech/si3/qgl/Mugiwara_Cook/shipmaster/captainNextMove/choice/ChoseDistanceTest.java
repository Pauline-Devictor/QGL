package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.choice;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Wind;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.CalculateDistanceHelper;
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
        wind = new Wind(0, 0);
    }

    @Test
    void distanceBetweenPointAndCheckpointAxeYorX() {
        Checkpoint checkpointx0y500 = new Checkpoint(new Position(0, 500, 0), new Circle(100));
        double distance = CalculateDistanceHelper.distanceBetweenPointAndCheckpoint(checkpointx0y500, 0, 0);

        assertEquals(500, distance);

        Checkpoint checkpointx_500y0 = new Checkpoint(new Position(-500, 0, 0), new Circle(100));
        distance = CalculateDistanceHelper.distanceBetweenPointAndCheckpoint(checkpointx_500y0, 0, 0);

        assertEquals(500, distance);
    }


    @Test
    void distanceBetweenPointAndCheckpointBehindNotSartingIn0_0() {
        Checkpoint checkpointx_500y0 = new Checkpoint(new Position(-500, 0, 0), new Circle(100));
        double distance = CalculateDistanceHelper.distanceBetweenPointAndCheckpoint(checkpointx_500y0, 500, 0);

        assertEquals(1000, distance);

        Checkpoint checkpointx0y500 = new Checkpoint(new Position(0, 500, 0), new Circle(100));
        distance = CalculateDistanceHelper.distanceBetweenPointAndCheckpoint(checkpointx0y500, 500, 0);

        assertEquals(Math.round(707.1067811865476 * 100.0) / 100.0, Math.round(distance * 100.0) / 100.0);
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
    void choiceBestNbOarMiddle() {
        ship = mock(Ship.class);
        when(ship.getNbUsableOarsLeft()).thenReturn(4);
        when(ship.getNbUsableOarsRight()).thenReturn(4);
        when(ship.getNbOars()).thenReturn(8);

        AngleOption angleOption = new AngleOption(0, 0);
        Checkpoint checkpointx500y0 = new Checkpoint(new Position(20, 0, 0), new Circle(100));
        Position positionShip = new Position(0, 0, 0);

        int[] oarsOption = ChoseDistance.choiceBestNbOar(angleOption, checkpointx500y0, ship, positionShip);
        assertEquals(0, oarsOption[0]);
        assertEquals(0, oarsOption[1]);
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

    @Test
    void choiceBestNbOarPile() {
        ship = mock(Ship.class);
        when(ship.getNbUsableOarsLeft()).thenReturn(2);
        when(ship.getNbUsableOarsRight()).thenReturn(2);
        when(ship.getNbOars()).thenReturn(4);

        AngleOption angleOption = new AngleOption(0, 0);
        Checkpoint checkpointx100y0 = new Checkpoint(new Position(165, 0, 0), new Circle(100));
        Position positionShip = new Position(0, 0, 0);

        int[] oarsOption = ChoseDistance.choiceBestNbOar(angleOption, checkpointx100y0, ship, positionShip);
        assertEquals(2, oarsOption[0]);
        assertEquals(2, oarsOption[1]);
    }

    @Test
    void choiceBestNbOarProcheSail() {
        Wind wind = new Wind(0, 100);
        ship = mock(Ship.class);

        Checkpoint checkpointx100y0 = new Checkpoint(new Position(50, 0, 0), new Circle(100));
        Position positionShip = new Position(0, 0, 0);
        when(ship.getPosition()).thenReturn(positionShip);
        when(ship.getNbEquipment("sail")).thenReturn(2);
        when(ship.getNbUsableSails()).thenReturn(1);
        when(ship.getNbOars()).thenReturn(4);

        int[] oarsOption = ChoseDistance.choiceBestNbSail(new int[]{0, 0}, checkpointx100y0, ship, positionShip, wind);
        assertEquals(0, oarsOption[0]);
        assertEquals(0, oarsOption[1]);
        assertEquals(1, oarsOption[2]);
    }

    @Test
    void choiceBestNbOarPileSail() {
        Wind wind = new Wind(0, 100);
        ship = mock(Ship.class);

        Checkpoint checkpointx100y0 = new Checkpoint(new Position(165, 0, 0), new Circle(100));
        Position positionShip = new Position(0, 0, 0);
        when(ship.getPosition()).thenReturn(positionShip);

        int[] oarsOption = ChoseDistance.choiceBestNbSail(new int[]{2, 2}, checkpointx100y0, ship, positionShip, wind);
        assertEquals(2, oarsOption[0]);
        assertEquals(2, oarsOption[1]);
        assertEquals(0, oarsOption[2]);
    }

     @Test
    void choiceNbOarAndSailPile(){
         ship = mock(Ship.class);
         when(ship.getNbUsableOarsLeft()).thenReturn(2);
         when(ship.getNbUsableOarsRight()).thenReturn(2);
         when(ship.getNbOars()).thenReturn(4);

         AngleOption angleOption = new AngleOption(0, 0);
         Checkpoint checkpointx100y0 = new Checkpoint(new Position(165, 0, 0), new Circle(100));
         Position positionShip = new Position(0, 0, 0);
         when(ship.getPosition()).thenReturn(positionShip);

         int[] oarsAndSailOption = ChoseDistance.choiceBestNbOarAndSail(angleOption,checkpointx100y0,ship,positionShip,wind);

         assertEquals(2, oarsAndSailOption[0]);
         assertEquals(2, oarsAndSailOption[1]);
         assertEquals(0, oarsAndSailOption[2]);


     }

    @Test
    void choiceBestNbOarAndSailProche() {
        ship = mock(Ship.class);
        when(ship.getNbUsableOarsLeft()).thenReturn(2);
        when(ship.getNbUsableOarsRight()).thenReturn(2);
        when(ship.getNbOars()).thenReturn(4);

        AngleOption angleOption = new AngleOption(0, 0);
        Checkpoint checkpointx100y0 = new Checkpoint(new Position(100, 0, 0), new Circle(100));
        Position positionShip = new Position(0, 0, 0);
        when(ship.getPosition()).thenReturn(positionShip);

        int[] oarsAndSailOption = ChoseDistance.choiceBestNbOarAndSail(angleOption,checkpointx100y0,ship,positionShip,wind);

        assertEquals(1, oarsAndSailOption[0]);
        assertEquals(1, oarsAndSailOption[1]);
        assertEquals(0, oarsAndSailOption[2]);

    }

    @Test
    void choiceBestNbOarAndSailMiddle() {
        ship = mock(Ship.class);
        when(ship.getNbUsableOarsLeft()).thenReturn(4);
        when(ship.getNbUsableOarsRight()).thenReturn(4);
        when(ship.getNbOars()).thenReturn(8);

        AngleOption angleOption = new AngleOption(0, 0);
        Checkpoint checkpointx500y0 = new Checkpoint(new Position(20, 0, 0), new Circle(100));
        Position positionShip = new Position(0, 0, 0);
        when(ship.getPosition()).thenReturn(positionShip);

        int[] oarsAndSailOption = ChoseDistance.choiceBestNbOarAndSail(angleOption,checkpointx500y0,ship,positionShip,wind);

        assertEquals(0, oarsAndSailOption[0]);
        assertEquals(0, oarsAndSailOption[1]);
        assertEquals(0, oarsAndSailOption[2]);
    }


}