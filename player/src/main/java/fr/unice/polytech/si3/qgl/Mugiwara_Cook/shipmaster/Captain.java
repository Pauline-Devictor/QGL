package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.ChoseActions;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainSailorMoves.*;
import lombok.Getter;


public class Captain {
    InitGame initGame;
    ActionJSON actionJSON;

    CaptainSailorMove captainSailorMove;
    ChoseActions choseActions;

    @Getter
    Checkpoint currentCheckpoint;
    int nbCurrentCheckpoint = 0;

    public Captain(InitGame initGame, ActionJSON actionJSON) {
        this.initGame = initGame;
        this.actionJSON = actionJSON;
        this.choseActions = new ChoseActions(this.actionJSON, this.initGame);
        this.captainSailorMove = new CaptainSailorMove(this.initGame.getShip(),this.initGame.getSailors());

        if (this.initGame.getGoal().getClass() == RegattaGoal.class)
            this.currentCheckpoint = ((RegattaGoal) this.initGame.getGoal()).getCheckpoints()[this.nbCurrentCheckpoint];

        this.captainSailorMove.assignEquipement();

        this.initGame.getShip().getEntities().forEach(equipment -> {
            if (equipment.getSailor() != null)
                System.out.println(equipment.getType() + "Lier a: " + equipment.getSailor().getName());
            else
                System.out.println("Ne pas etre lier");
        });
    }

    /**
     * Permet a la fois de deplacer les marins sur le bateau (leur assigne une rame)
     * puis calcule de meilleur moves en fonction du deplacement des marins
     *
     * @param nextRound
     */
    public void nextMove(NextRound nextRound) {

        this.captainSailorMove.moveToAssignEquipment(this.actionJSON);

        if (this.inCheckpoint(nextRound)) {
            this.nbCurrentCheckpoint++;
            this.currentCheckpoint = ((RegattaGoal) this.initGame.getGoal()).getCheckpoints()[this.nbCurrentCheckpoint];
            System.out.println("OBEJTIF CHECKPOINT: " + this.currentCheckpoint.getPosition().getY() + ":" + this.currentCheckpoint.getPosition().getX());
        }

        if (initGame.allSailorIsOnAssign())
            this.choseActions.moveToTheNextCheckpoint(this.currentCheckpoint, nextRound);
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
}
