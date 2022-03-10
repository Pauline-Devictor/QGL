package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Oar;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.choice.ChoiceAngle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.choice.ChoiseDistance;
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
        List<AngleOption> angleOptionList = this.calculatePossibility();

        AngleOption bestAngleOption = new ChoiceAngle().choiceBestDelta(angleOptionList,checkpoint,nextRound.getShip());

        System.out.println("Voici celle retenue: ");
        bestAngleOption.getDetail();

        int[] oarLeftRight = new ChoiseDistance().choiceBestNbOar(bestAngleOption,checkpoint,nextRound.getShip(),initGame);

        for (int i = 0; i < oarLeftRight[0]; i++) {
            this.actionJSON.addAction(new Oar(this.initGame.getUsableSailorLeft().get(i).getId()));
        }
        for (int i = 0; i < oarLeftRight[1]; i++) {
            this.actionJSON.addAction(new Oar(this.initGame.getUsableSailorRight().get(i).getId()));
        }
    }

    /**
     * Calcule les possibilités en fonction de ou sont les marins
     */
    public List<AngleOption> calculatePossibility() { //TODO: a simplifier
        List<AngleOption> possibleAngle = AngleOption.creationOptionFromOarCount(initGame.getUsableSailorLeft().size(), initGame.getUsableSailorRight().size(), initGame.getShip().getNbOars());
        System.out.println("Voici toute les possibilite: ");
        for (AngleOption angleOption : possibleAngle) {
            angleOption.getDetail();
        }
        return possibleAngle;
    }
}
