package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.allmoves.Moves;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.allmoves.OarMove;

import javax.swing.*;

public class BestMove {
    NextRound nextRound;
    AllPossibility allPossibility;
    Moves bestOne;

    public BestMove(AllPossibility allPossibility, NextRound nextRound) {
        this.nextRound = nextRound;
        this.allPossibility = allPossibility;
    }

    public void processing(Checkpoint[] checkpoints) {
        AllMove allMove = new AllMove();
        allMove.calculateMove(nextRound.getShip(), allPossibility);
        allMove.getDetail();
        bestOne(allMove, checkpoints);
    }

    public void bestOne(AllMove allMove, Checkpoint[] checkpoints) {
        double angleMin = 360;
        Moves bestOne = null;
        checkpoints[0].getPosition();
        for (Moves moves : allMove.getMovesList()) {
            double angle = ((checkpoints[0].getPosition().getX() - moves.getX()) * Math.cos(moves.getOrientation()) + (checkpoints[0].getPosition().getY() - moves.getY()) * Math.sin(moves.getOrientation()))
                    / (Math.sqrt(Math.pow(checkpoints[0].getPosition().getX() - moves.getX(), 2) + Math.pow(checkpoints[0].getPosition().getY() - moves.getY(), 2)));
            angle = Math.acos(angle) * 180 / Math.PI;
            System.out.println(angle + " degree donc orientation du bateau: " + (moves.getOrientation() * 180 / Math.PI) + ":" + moves.getOrientation());
            if (angle < angleMin) {
                angleMin = angle;
                bestOne = moves;
            }
        }
        this.bestOne = bestOne;
        System.out.println((bestOne.getOrientation() * 180.0 / Math.PI) +":"+ bestOne.getX());
    }

}
