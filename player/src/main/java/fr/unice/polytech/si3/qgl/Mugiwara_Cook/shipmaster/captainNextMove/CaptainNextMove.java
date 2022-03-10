package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Oar;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.choice.ChoseAngle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.choice.ChoseDistance;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.AngleOption;

import java.util.List;

public class CaptainNextMove {
    ActionJSON actionJSON;
    InitGame initGame;


    public CaptainNextMove(InitGame initGame, ActionJSON actionJSON) {
        this.actionJSON = actionJSON;
        this.initGame = initGame;
    }

    /**
     * Calcule de meilleur move et ecrit dans le JsonAction
     *
     * @param checkpoint
     * @param nextRound
     */
    public void calculateNextMove(Checkpoint checkpoint, NextRound nextRound) {
        List<AngleOption> angleOptionList = AngleOption.creationOptionFromOarCount(initGame.getUsableSailorLeft().size(), initGame.getUsableSailorRight().size(), initGame.getShip().getNbOars());
        AngleOption bestAngleOption = new ChoseAngle().choiceBestDelta(angleOptionList,checkpoint,nextRound.getShip());

        System.out.println("Voici celle retenue: ");
        bestAngleOption.getDetail();

        int[] oarLeftRight = new ChoseDistance().choiceBestNbOar(bestAngleOption,checkpoint,initGame);

        for (int i = 0; i < oarLeftRight[0]; i++) {
            this.actionJSON.addAction(new Oar(this.initGame.getUsableSailorLeft().get(i).getId()));
        }
        for (int i = 0; i < oarLeftRight[1]; i++) {
            this.actionJSON.addAction(new Oar(this.initGame.getUsableSailorRight().get(i).getId()));
        }
    }

}
