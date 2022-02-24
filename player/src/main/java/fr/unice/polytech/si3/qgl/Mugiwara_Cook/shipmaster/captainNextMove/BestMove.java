package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.allmoves.*;

public class BestMove {
    NextRound nextRound;
    AllPossibility allPossibility;
    Moves bestOne;

    public BestMove(AllPossibility allPossibility, NextRound nextRound) {
        this.nextRound = nextRound;
        this.allPossibility = allPossibility;
    }

    public void processing(Checkpoint checkpoints) {
        AllMove allMove = new AllMove();
        allMove.calculateMove(nextRound.getShip(), allPossibility);
        allMove.getDetail();
        this.bestOne(allMove, checkpoints);
    }

    public void bestOne(AllMove allMove, Checkpoint checkpoints) {  //A refactor
        double angleMin = 360;
        Moves bestOne = null;
        checkpoints.getPosition();
        for (Moves moves : allMove.getMovesList()) {
            double angle = ((checkpoints.getPosition().getX() - moves.getX()) * Math.cos(moves.getOrientation()) + (checkpoints.getPosition().getY() - moves.getY()) * Math.sin(moves.getOrientation()))
                    / (Math.sqrt(Math.pow(checkpoints.getPosition().getX() - moves.getX(), 2) + Math.pow(checkpoints.getPosition().getY() - moves.getY(), 2)));
            angle = Math.acos(angle) * 180 / Math.PI;
            //System.out.println(angle + " degree donc orientation du bateau: " + (moves.getOrientation() * 180 / Math.PI) + ":" + moves.getOrientation());
            if (angle + 10 < angleMin) {
                angleMin = angle;
                bestOne = moves;
            }
        }
        this.bestOne = bestOne;
        //System.out.println(bestOne.getX() + " , " + bestOne.getY() + " , " + bestOne.getOrientation());
    }

    public Moves getBestMove() {
        return bestOne;
    }

}
