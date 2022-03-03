package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.allmoves;

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

        this.bestOne(allMove, checkpoints);
    }

    public void bestOne(AllMove allMove, Checkpoint checkpoint) {
        double angleMin = 180;
        Moves bestOne = null;

        checkpoint.getPosition();
        for (Moves moves : allMove.getMovesList()) {
            double angle = this.angle(moves, checkpoint);
            if (angle + 10 < angleMin) {
                angleMin = angle;
                bestOne = moves;

                if (angle(bestOne,checkpoint) != 0){
                    bestOne.setRudderOrientation(angle(bestOne,checkpoint));
                }
            }
        }

        this.bestOne = bestOne;
    }

    public double angle(Moves moves, Checkpoint checkpoint) {
        double angle = ((checkpoint.getPosition().getX() - moves.getX()) * Math.cos(moves.getOrientation()) + (checkpoint.getPosition().getY() - moves.getY()) * Math.sin(moves.getOrientation()))
                / (Math.sqrt(Math.pow(checkpoint.getPosition().getX() - moves.getX(), 2) + Math.pow(checkpoint.getPosition().getY() - moves.getY(), 2)));
        return Math.acos(angle) * 180 / Math.PI;
    }


    public Moves getBestMove() {
        return bestOne;
    }
}
