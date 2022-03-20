package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.ActionJSON;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Wind;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.PrimaryMoves;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.choice.ChoseAngle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.choice.ChoseDistance;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.possibleAngle.AngleOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChoseActionsTest {
    Checkpoint checkpoint;
    NextRound nextRound;
    ActionJSON actionJSON;
    InitGame initGame;
    ChoseDistance choseDistance;
    ChoseAngle choseAngle;
    Ship ship;
    AngleOption angleOption;
    Position positionBoat;
    Wind wind;
    ChoseActions choseActions;

    @BeforeEach
    void set(){
        angleOption=mock(AngleOption.class);
        checkpoint=mock(Checkpoint.class);
        nextRound=mock(NextRound.class);
        initGame=mock(InitGame.class);
        actionJSON=new ActionJSON();
        choseActions=new ChoseActions(actionJSON,initGame);
        choseDistance=mock(ChoseDistance.class);
        choseAngle=mock(ChoseAngle.class);
        ship=mock(Ship.class);
        positionBoat=new Position(0.0,0.0,1.0);
        wind=new Wind(2,2);


    }

    /**@Test
    void turnWithRudderAndGoFarWithOars(){
        int[] nbOarsAndSails={1,1,1};
        when(ChoseAngle.isOkayToUseOnlyTheRudderToTurn(checkpoint,nextRound.getShip())).thenReturn(0.3);
        when(ChoseDistance.choiceBestNbOarAndSail(angleOption,checkpoint,ship,positionBoat,wind)).thenReturn(nbOarsAndSails);
        choseActions.turnWithRudderAndGoFarWithOars(checkpoint,nextRound);

    }**/


}