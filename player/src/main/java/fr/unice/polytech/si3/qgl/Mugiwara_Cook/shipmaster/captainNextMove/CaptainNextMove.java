package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.allmoves.*;

public class CaptainNextMove {
    AllPossibility allPossibility;
    ActionJSON actionJSON;
    InitGame initGame;


    public CaptainNextMove(InitGame initGame, ActionJSON actionJSON) {
        this.allPossibility = new AllPossibility(initGame);
        this.actionJSON = actionJSON;
        this.initGame = initGame;
    }

    /**
     * Calcule les possibiliters en fonction de ou sont les marins
     */
    public void calculatePossibility() {
        this.allPossibility.oarPossibility();
    }

    /**
     * Calcule de meilleur move et ecrit dans le JsonAction
     *
     * @param checkpoint
     * @param nextRound
     */
    public void calculateNextMove(Checkpoint checkpoint, NextRound nextRound) {
        this.calculatePossibility();

        BestMove bestMove = new BestMove(allPossibility, nextRound);
        bestMove.processing(checkpoint);

        Moves oarMove = bestMove.getBestMove();

        int[] oarLeftRight = oarMove.getOar();

        for (int i = 0; i < oarLeftRight[0]; i++) {
            this.actionJSON.addAction(new Oar(this.initGame.getUsableSailorLeft().get(i).getId()));
        }
        for (int i = 0; i < oarLeftRight[1]; i++) {
            this.actionJSON.addAction(new Oar(this.initGame.getUsableSailorRight().get(i).getId()));
        }
    }
}
