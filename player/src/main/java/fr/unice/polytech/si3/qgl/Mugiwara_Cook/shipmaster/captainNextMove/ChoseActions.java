package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.ActionJSON;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.PrimaryMoves;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.choice.ChoseAngle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.choice.ChoseDistance;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.AngleOption;

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
        System.out.println("turnWithRudderAndGoFarWithOars");
        moves.primaryMoveTurn(ChoseAngle.isOkayToUseOnlyTheRudderToTurn(checkpoint, nextRound.getShip()));

        int[] oarComposition = ChoseDistance.choiceBestNbOar(new AngleOption(0, 0), checkpoint, initGame.getShip(), nextRound.getShip().getPosition());

        moves.primaryMoveOar(oarComposition[0], oarComposition[1]);
    }

    public void turnWithOarsAndCorrectWithRudder(Checkpoint checkpoint, NextRound nextRound) {
        System.out.println("turnWithOarsAndCorrectWithRudder");
        List<AngleOption> angleOptionList = AngleOption.creationOptionFromOarCount(initGame.getShip().getNbUsableOarsLeft(), initGame.getShip().getNbUsableOarsRight(), nextRound.getShip().getNbOars());
//        angleOptionList.forEach(angleOption -> angleOption.getDetail());
        AngleOption bestAngleOption = ChoseAngle.choiceBestDelta(angleOptionList, checkpoint, nextRound.getShip());
//        System.out.print("Meilleur angle: ");
//        bestAngleOption.getDetail();
        int[] oarComposition = ChoseDistance.choiceBestNbOar(bestAngleOption, checkpoint, initGame.getShip(), nextRound.getShip().getPosition());
        double angleForRudder = ChoseAngle.choseAngleForRudder(checkpoint, nextRound.getShip(), bestAngleOption);

        System.out.println("angleForRudder: " + angleForRudder);
        if (angleForRudder > Math.PI / 4) angleForRudder = Math.PI / 4;
        if (angleForRudder < -Math.PI / 4) angleForRudder = -Math.PI / 4;
//        System.out.println("OARS: " + oarComposition[0] + "][" + oarComposition[1]);
        moves.primaryMoveOar(oarComposition[0], oarComposition[1]);
        moves.primaryMoveTurn(angleForRudder);
    }

}
