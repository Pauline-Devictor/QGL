package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.choice;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.CalculateAngleHelper;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.possibleAngle.AngleOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        assertTrue(zero==0);
    }

    @Test
    void isOkayToUseOnlyTheRudderToTurnReturn0(){
        Checkpoint checkpoint = new Checkpoint(new Position(45,45,0.0),new Circle(45));
        when(ship.getPosition()).thenReturn(new Position(0,0,0.0));
        assertTrue(ChoseAngle.isOkayToUseOnlyTheRudderToTurn(checkpoint,ship)==0);
    }

    @Test
    void isOkayToUseOnlyTheRudderToTurnNullAngle(){
        Position position = new Position(200,200,0.0);
        when(checkpoint.getPosition()).thenReturn(position);
        when(ship.getPosition()).thenReturn(position);
        assertTrue(ChoseAngle.isOkayToUseOnlyTheRudderToTurn(checkpoint,ship)==0);
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

    @Test
    void choiceBestDeltaNull(){
        positionBoat = new Position(300,300,0);
        checkpoint = new Checkpoint(new Position(300,300,90.0),new Circle(45));
        List<AngleOption> angleOptionList = new ArrayList<>();
        angleOptionList.add(new AngleOption(60,0));
        assertEquals(ChoseAngle.choiceBestDelta(angleOptionList,checkpoint,ship),angleOptionList.get(0));
        assertNotNull(ChoseAngle.choiceBestDelta(angleOptionList, checkpoint, ship));
    }

    @Test
    void choiceBestDeltaMin(){
        positionBoat = new Position(300,300,0);
        checkpoint = new Checkpoint(new Position(300,500,45.0),new Circle(45));
        System.out.println(ChoseAngle.isOkayToUseOnlyTheRudderToTurn(checkpoint,ship));
        List<AngleOption> angleOptionList = new ArrayList<>();
        angleOptionList.add(new AngleOption(60,0));
        angleOptionList.add(new AngleOption(40,0));
        assertEquals(ChoseAngle.choiceBestDelta(angleOptionList,checkpoint,ship),angleOptionList.get(1));
    }

    @Test
    void choiceBestDeltaMinEqual(){
        positionBoat = new Position(300,300,5);
        checkpoint = new Checkpoint(new Position(300,500,45.0),new Circle(45));
        System.out.println(ChoseAngle.isOkayToUseOnlyTheRudderToTurn(checkpoint,ship));
        List<AngleOption> angleOptionList = new ArrayList<>();
        angleOptionList.add(new AngleOption(60,0));
        angleOptionList.add(new AngleOption(60,0));
        assertEquals(ChoseAngle.choiceBestDelta(angleOptionList,checkpoint,ship),angleOptionList.get(0));
    }

    @Test
    void choiceBestDeltaMin2(){
        positionBoat = new Position(300,300,20);
        checkpoint = new Checkpoint(new Position(300,500,45.0),new Circle(45));
        System.out.println(ChoseAngle.isOkayToUseOnlyTheRudderToTurn(checkpoint,ship));
        List<AngleOption> angleOptionList = new ArrayList<>();
        angleOptionList.add(new AngleOption(60,0));
        angleOptionList.add(new AngleOption(59,0));
        assertEquals(ChoseAngle.choiceBestDelta(angleOptionList,checkpoint,ship),angleOptionList.get(1));
    }



}