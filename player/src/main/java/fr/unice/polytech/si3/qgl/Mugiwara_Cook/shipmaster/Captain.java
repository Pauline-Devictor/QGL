package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Display;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.PathFinding;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography.Node;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography.Spotter;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.ChoseActions;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainSailorMoves.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class Captain {
    InitGame initGame;
    ActionJSON actionJSON;
    @Getter
    CaptainSailorMove captainSailorMove;
    ChoseActions choseActions;

    Spotter spotter;
    @Getter
    Checkpoint currentCheckpoint;
    int nbCurrentCheckpoint = 0;

    PathFinding pathFindind;

    ArrayList<Checkpoint> checkpointsPath = new ArrayList<>();
    ArrayList<Checkpoint> defaultCheckpoints = new ArrayList<>();

    boolean visibleEntitiesOn;

    public Captain(InitGame initGame, ActionJSON actionJSON) {
        visibleEntitiesOn = false;
        this.initGame = initGame;
        this.actionJSON = actionJSON;
        this.choseActions = new ChoseActions(this.actionJSON, this.initGame);
        this.captainSailorMove = new CaptainSailorMove(this.initGame.getShip(), this.initGame.getSailors());

        if (this.initGame.getGoal().getClass() == RegattaGoal.class) {
            this.currentCheckpoint = ((RegattaGoal) this.initGame.getGoal()).getCheckpoints()[this.nbCurrentCheckpoint];
            defaultCheckpoints.addAll(List.of(((RegattaGoal) this.initGame.getGoal()).getCheckpoints()));
        }

        this.captainSailorMove.assignEquipement();

        spotter = new Spotter();
        spotter.createMap(150, initGame.getShip().getPosition(), defaultCheckpoints);
        pathFindind = new PathFinding(spotter.getMap());
    }

    /**
     * Constructor for test
     */
    public Captain(InitGame initGame, ActionJSON actionJSON, CaptainSailorMove mockedOne) {
        visibleEntitiesOn = false;
        this.initGame = initGame;
        this.actionJSON = actionJSON;
        this.choseActions = new ChoseActions(this.actionJSON, this.initGame);
        this.captainSailorMove = mockedOne;


        this.currentCheckpoint = ((RegattaGoal) this.initGame.getGoal()).getCheckpoints()[this.nbCurrentCheckpoint];
        defaultCheckpoints.addAll(List.of(((RegattaGoal) this.initGame.getGoal()).getCheckpoints()));


        this.captainSailorMove.assignEquipement();

        spotter = new Spotter();
        spotter.createMap(150, initGame.getShip().getPosition(), defaultCheckpoints);
        pathFindind = new PathFinding(spotter.getMap());
    }

    /**
     * Permet a la fois de deplacer les marins sur le bateau (leur assigne une rame)
     * puis calcule de meilleur moves en fonction du deplacement des marins
     */
    public void nextMove(NextRound nextRound) {
        this.captainSailorMove.moveToAssignEquipment(this.actionJSON);

        if (nextRound.getVisibleEntities().length != 0)
            visibleEntitiesOn = true;
        if (nextRound.getVisibleEntities().length != 0 || checkpointsPath.isEmpty())
            updateMap(nextRound);


        if (this.inCheckpoint(nextRound)) {
            this.nbCurrentCheckpoint++;
            this.currentCheckpoint = ((RegattaGoal) this.initGame.getGoal()).getCheckpoints()[this.nbCurrentCheckpoint];
            this.checkpointsPath = new ArrayList<>();
            updateMap(nextRound);
        }

        if (this.inCheckpoint(nextRound, checkpointsPath.get(0))) {
            if (checkpointsPath.size() < 4) {
                checkpointsPath.remove(0);
            } else {
                checkpointsPath.remove(0);
                checkpointsPath.remove(0);
                checkpointsPath.remove(0);
            }
        }

        if (initGame.allSailorIsOnAssign())
            this.choseActions.moveToTheNextCheckpoint(checkpointsPath.get(0), nextRound);

        spotter.getNodeEnd().setColor("A");
        spotter.getNodeStart().setColor("D");
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

    public boolean inCheckpoint(NextRound nextRound, Checkpoint checkpoint) {
        return (nextRound.getShip().getPosition().distance(checkpoint.getPosition())
                <= ((Circle) checkpoint.getShape()).getRadius());
    }

    public void updateMap(NextRound nextRound) {
        ArrayList<VisibleEntity> visibleEntitiesArray = new ArrayList<>(List.of(nextRound.getVisibleEntities()));
        if ((spotter.updateMap(visibleEntitiesArray, nextRound.getShip().getPosition(), currentCheckpoint.getPosition()) || checkpointsPath.isEmpty()) &&
                (pathFindind.findPath(this.spotter.getNodeStart(), this.spotter.getNodeEnd()))) {
            if (nextRound.getVisibleEntities() != null) {
                checkpointsPath = new ArrayList<>(pathFindind.getPathCheckpoint());
            }
            if (!visibleEntitiesOn) {
                checkpointsPath = new ArrayList<>(List.of(((RegattaGoal) this.initGame.getGoal()).getCheckpoints()[this.nbCurrentCheckpoint]));
            }
        }
    }
}
