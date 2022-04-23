package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Display;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.PathFindind;
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

    CaptainSailorMove captainSailorMove;
    ChoseActions choseActions;

    Spotter spotter;
    @Getter
    Checkpoint currentCheckpoint;
    int nbCurrentCheckpoint = 0;

    PathFindind pathFindind;

    ArrayList<Checkpoint> checkpointsPath = new ArrayList<>();
    ArrayList<Checkpoint> defaultCheckpoints = new ArrayList<>();

    public Captain(InitGame initGame, ActionJSON actionJSON) {
        this.initGame = initGame;
        this.actionJSON = actionJSON;
        this.choseActions = new ChoseActions(this.actionJSON, this.initGame);
        this.captainSailorMove = new CaptainSailorMove(this.initGame.getShip(), this.initGame.getSailors());

        if (this.initGame.getGoal().getClass() == RegattaGoal.class) {
            this.currentCheckpoint = ((RegattaGoal) this.initGame.getGoal()).getCheckpoints()[this.nbCurrentCheckpoint];
            defaultCheckpoints.addAll(List.of(((RegattaGoal) this.initGame.getGoal()).getCheckpoints()));
        }

        this.captainSailorMove.assignEquipement();

        this.initGame.getShip().getEntities().forEach(equipment -> {
            if (equipment.getSailor() != null)
                Display.info(equipment.getType() + "Lier a: " + equipment.getSailor().getName());
            else
                Display.info("Ne pas etre lier");
        });

        spotter = new Spotter();
        spotter.createMap(100, initGame.getShip().getPosition(), defaultCheckpoints);
        pathFindind = new PathFindind(spotter.getMap());
    }

    /**
     * Permet a la fois de deplacer les marins sur le bateau (leur assigne une rame)
     * puis calcule de meilleur moves en fonction du deplacement des marins
     *
     * @param nextRound
     */
    public void nextMove(NextRound nextRound) {
        this.captainSailorMove.moveToAssignEquipment(this.actionJSON);

        if (nextRound.getVisibleEntities() != null || checkpointsPath.size() == 0)
            updateMap(nextRound);


        if (this.inCheckpoint(nextRound)) {
            this.nbCurrentCheckpoint++;
            this.currentCheckpoint = ((RegattaGoal) this.initGame.getGoal()).getCheckpoints()[this.nbCurrentCheckpoint];
            this.checkpointsPath = new ArrayList<>();
            updateMap(nextRound);
//            Display.info("OBEJTIF CHECKPOINT: " + this.currentCheckpoint.getPosition().getY() + ":" + this.currentCheckpoint.getPosition().getX());
        }

//        System.out.println(checkpointsPath.get(0).getPosition().getX() + ":" + checkpointsPath.get(0).getPosition().getY());

        if (this.inCheckpoint(nextRound, checkpointsPath.get(0))) {
            System.out.println("CHECKPOINTPATH ATEINT");
            checkpointsPath.remove(0);
        }

        if (initGame.allSailorIsOnAssign())
            this.choseActions.moveToTheNextCheckpoint(checkpointsPath.get(0), nextRound);

        spotter.getNodeEnd().setColor("A");
        spotter.getNodeStart().setColor("D");

        for (List<Node> subCarte2 : spotter.getMap()) {
            for (Node nodePath : subCarte2) {
                System.out.print(nodePath.getColor() + "");
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------------------------------------");

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
        if (spotter.updateMap(visibleEntitiesArray, nextRound.getShip().getPosition(), currentCheckpoint.getPosition()) || checkpointsPath.size() == 0) {
            if (pathFindind.findPath(this.spotter.getNodeStart(), this.spotter.getNodeEnd())) {
                checkpointsPath = new ArrayList<>(pathFindind.getPathCheckpoint());
            }
        }
    }
}
