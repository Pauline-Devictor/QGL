package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainMoves.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.*;


public class Captain {
    InitGame initGame;
    ActionJSON actionJSON;

    CaptainNextMove captainNextMove;
    CaptainSailorMove captainSailorMove;

    Checkpoint currentCheckpoint;
    int nbCurrentCheckpoint = 0;

    public Captain(InitGame initGame, ActionJSON actionJSON) {
        this.initGame = initGame;
        this.actionJSON = actionJSON;
        this.captainNextMove = new CaptainNextMove(this.initGame, this.actionJSON);
        this.captainSailorMove = new CaptainSailorMove(this.initGame, this.actionJSON);

        if (this.initGame.getGoal().getClass() == RegattaGoal.class)
            this.currentCheckpoint = ((RegattaGoal) this.initGame.getGoal()).getCheckpoints()[this.nbCurrentCheckpoint];

        this.captainSailorMove.assignEquipement();
    }

    /**
     * Permet a la fois de deplacer les marins sur le bateau (leur assigne une rame)
     * puis calcule de meilleur moves en fonction du deplacement des marins
     *
     * @param nextRound
     */
    public void nextMove(NextRound nextRound) {

        this.captainSailorMove.moveToAssignEquipment();

        if (this.inCheckpoint(nextRound)) {
            this.nbCurrentCheckpoint++;
            this.currentCheckpoint = ((RegattaGoal) this.initGame.getGoal()).getCheckpoints()[this.nbCurrentCheckpoint];
        }

        if (initGame.allSailorIsOnAssign())
            this.captainNextMove.calculateNextMove(this.currentCheckpoint, nextRound);
    }


    /**
     * Determine if the ship is in the Checkpoint
     *
     * @return boolean
     */
    public boolean inCheckpoint(NextRound nextRound) {
        return (nextRound.getShip().getPosition().distance(currentCheckpoint.getPosition())
                <= ((Circle) currentCheckpoint.getShape()).getRadius());
    }

    public Checkpoint getCurrentCheckpoint() {
        return this.currentCheckpoint;
    }
}
