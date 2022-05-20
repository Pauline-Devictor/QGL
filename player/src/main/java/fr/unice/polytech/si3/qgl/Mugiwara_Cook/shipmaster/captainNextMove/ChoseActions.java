package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Display;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.ActionJSON;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.PrimaryMoves;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.choice.ChoseAngle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.choice.ChoseDistance;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.possibleAngle.AngleOption;

import java.util.List;

public class ChoseActions {
    InitGame initGame;
    PrimaryMoves moves;

    public ChoseActions(ActionJSON actionJSON, InitGame initGame) {
        this.initGame = initGame;
        moves = new PrimaryMoves(initGame, actionJSON);
    }

    public void moveToTheNextCheckpoint(Checkpoint checkpoint, NextRound nextRound) {
        if (ChoseAngle.isOkayToUseOnlyTheRudderToTurn(checkpoint, nextRound.getShip()) != 0) {
            this.turnWithRudderAndGoFarWithOars(checkpoint, nextRound);
        } else {
            this.turnWithOarsAndCorrectWithRudder(checkpoint, nextRound);
        }
    }

    public void turnWithRudderAndGoFarWithOars(Checkpoint checkpoint, NextRound nextRound) {
        moves.primaryMoveTurn(ChoseAngle.isOkayToUseOnlyTheRudderToTurn(checkpoint, nextRound.getShip()));

        int[] oarAndSailComposition = ChoseDistance.choiceBestNbOarAndSail(new AngleOption(0, 0), checkpoint, initGame.getShip(), nextRound.getShip().getPosition(),nextRound.getWind());

        moves.primaryMoveOar(oarAndSailComposition[0], oarAndSailComposition[1]);
        //Display.info("nb sail   " + oarAndSailComposition[2]);
        moves.primaryMoveSail(oarAndSailComposition[2]);
    }

    public void turnWithOarsAndCorrectWithRudder(Checkpoint checkpoint, NextRound nextRound) {
        List<AngleOption> angleOptionList = AngleOption.creationOptionFromOarCount(initGame.getShip().getNbUsableOarsLeft(), initGame.getShip().getNbUsableOarsRight(), nextRound.getShip().getNbOars());
        AngleOption bestAngleOption = ChoseAngle.choiceBestDelta(angleOptionList, checkpoint, nextRound.getShip());
        int[] oarAndSailComposition = ChoseDistance.choiceBestNbOarAndSail(bestAngleOption, checkpoint, initGame.getShip(), nextRound.getShip().getPosition(),nextRound.getWind());
        double angleForRudder = ChoseAngle.choseAngleForRudder(checkpoint, nextRound.getShip(), bestAngleOption);

        //Display.info("angleForRudder: " + angleForRudder);
        if (angleForRudder > Math.PI / 4) angleForRudder = Math.PI / 4;
        if (angleForRudder < -Math.PI / 4) angleForRudder = -Math.PI / 4;
        moves.primaryMoveOar(oarAndSailComposition[0], oarAndSailComposition[1]);
        //Display.info("voila la compo :"+oarAndSailComposition[0]+"  "+oarAndSailComposition[1]+"   "+oarAndSailComposition[2]);
        moves.primaryMoveTurn(angleForRudder);
        //Display.info("nb sail   "+oarAndSailComposition[2]);
        moves.primaryMoveSail(oarAndSailComposition[2]);
    }

}
