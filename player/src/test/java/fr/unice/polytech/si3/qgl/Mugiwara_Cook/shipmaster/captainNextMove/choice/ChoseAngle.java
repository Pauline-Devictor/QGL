package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.choice;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.CalculateAngleHelper;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.possibleAngle.AngleOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChoseAngleTest {
    Checkpoint checkpoint;
    Position positionBoat;
    Position positionCheckpoint;
    Ship ship;


    @BeforeEach
    void setUp() {
        ship=mock(Ship.class);
        positionBoat=new Position(0,0,0);
        positionCheckpoint=new Position(300,200,0);
        when(ship.getPosition()).thenReturn(positionBoat);
        checkpoint=mock(Checkpoint.class);
        when(checkpoint.getPosition()).thenReturn(positionCheckpoint);


    }


    @Test
    void isOkayToUseOnlyTheRudderToTurn(){
        double infTo45=ChoseAngle.isOkayToUseOnlyTheRudderToTurn(checkpoint,ship);
        assertTrue(infTo45<Math.PI/4 && infTo45>-Math.PI/4);
    }

    @Test
    void isNotOkayToUseOnlyTheRudderToTurn(){
        positionBoat=new Position(100,0,0);
        when(ship.getPosition()).thenReturn(positionBoat);
        double zero=ChoseAngle.isOkayToUseOnlyTheRudderToTurn(checkpoint,ship);
        assertEquals(0, zero);
    }

    @Test
    void choseAngleForRudderPositiveAngle(){
        double angleBetweenBoatAndCheckPoint=ChoseAngle.choseAngleForRudder(checkpoint,ship, AngleOption.angle(1,1,2));
        assertTrue(angleBetweenBoatAndCheckPoint>=0);
    }
    @Test
    void choseAngleForRudderNegativeAngle(){
        positionBoat=new Position(300,400,0);
        when(ship.getPosition()).thenReturn(positionBoat);
        double angleBetweenBoatAndCheckPoint=ChoseAngle.choseAngleForRudder(checkpoint,ship, AngleOption.angle(1,1,2));
        assertTrue(angleBetweenBoatAndCheckPoint<=0);
    }



}