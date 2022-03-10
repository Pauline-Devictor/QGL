package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.choice;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.ActionJSON;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.PrimaryMoves;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.AngleOption;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.List;

public class ChoseActions {


    InitGame initGame;
    PrimaryMoves moves;


    public ChoseActions(ActionJSON actionJSON, InitGame initGame) {
        this.initGame = initGame;
        moves = new PrimaryMoves(initGame, actionJSON);
    }

    public void moveToTheNextCheckpoint(Checkpoint checkpoint, NextRound nextRound) {
        System.out.println("POSITION DU BATEAU MTF");
        System.out.println(nextRound.getShip().getPosition().getX());
        System.out.println(nextRound.getShip().getPosition().getY());
        System.out.println(nextRound.getShip().getPosition().getOrientation());
        System.out.println("C'EST PAS NULL NORMLEMENT " + ChoseAngle.isOkayToUseOnlyTheRudderToTurn(checkpoint, nextRound.getShip()));
        System.out.println("C'EST PAS NULL NORMLEMENT 3 " + ChoseAngle.isOkayToUseOnlyTheRudderToTurn(checkpoint, initGame.getShip()));

        if (ChoseAngle.isOkayToUseOnlyTheRudderToTurn(checkpoint, nextRound.getShip()) != 0.0) {
            System.out.println("ON TRACE SA MERE");
            this.turnWithRudderAndGoFarWithOars(checkpoint, nextRound);
        } else {
            System.out.println("HAMDOULA LA PUISSANCE");
            this.turnWithOarsAndCorrectWithRudder(checkpoint, nextRound);
        }
    }

    public void turnWithRudderAndGoFarWithOars(Checkpoint checkpoint, NextRound nextRound) {
        moves.primaryMoveTurn(ChoseAngle.isOkayToUseOnlyTheRudderToTurn(checkpoint, nextRound.getShip()));

        int[] oarComposition = ChoseDistance.choiceBestNbOar(new AngleOption(0, 0), checkpoint, initGame);
        moves.primaryMoveOar(oarComposition[0], oarComposition[1]);

        System.out.println("LA COMPOSITION MTF1");
        System.out.println(oarComposition[0]);
        System.out.println(oarComposition[1]);
        System.out.println("L'ANGLE POUR LE RUDDER MTF1" + ChoseAngle.isOkayToUseOnlyTheRudderToTurn(checkpoint, nextRound.getShip()));

    }

    public void turnWithOarsAndCorrectWithRudder(Checkpoint checkpoint, NextRound nextRound) {
        System.out.println("YOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
        List<AngleOption> angleOptionList = AngleOption.creationOptionFromOarCount(initGame.getUsableSailorLeft().size(), initGame.getUsableSailorRight().size(), nextRound.getShip().getNbOars());
        AngleOption bestAngleOption = ChoseAngle.choiceBestDelta(angleOptionList, checkpoint, nextRound.getShip());

        int[] oarComposition = ChoseDistance.choiceBestNbOar(bestAngleOption, checkpoint, initGame);
        double angleForRudder = ChoseAngle.choseAngleForRudder(checkpoint, nextRound.getShip(), bestAngleOption);

        moves.primaryMoveOar(oarComposition[0], oarComposition[1]);
        System.out.println("LA COMPOSITION MTF2");
        System.out.println(oarComposition[0]);
        System.out.println(oarComposition[1]);
        System.out.println("L'ANGLE POUR LE RUDDER MTF2");
        System.out.println(angleForRudder);
        moves.primaryMoveTurn(angleForRudder);
    }

}
