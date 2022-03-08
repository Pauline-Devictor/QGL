package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.allmoves;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.*;

public class BestMove {
    NextRound nextRound;
    AllPossibility allPossibility;
    double[] bestOne;


    public BestMove(AllPossibility allPossibility, NextRound nextRound) {
        this.nextRound = nextRound;
        this.allPossibility = allPossibility;
    }

    public void processing(Checkpoint checkpoints) {
        this.bestOne(checkpoints);
    }

    public void bestOne(Checkpoint checkpoint) {
        System.out.println("======================================================================= " + this.nextRound.getShip().getPosition().getOrientation());
        double angle = this.angleCheckpoint(checkpoint);
        System.out.println("ANGLE obj: " + angle);
        double[] bestOnePossibility = null;
        double angleMin = Math.PI;
        System.out.println("TOUT LES ANGLES");
        for (int i = 0; i < this.allPossibility.getAllPossibility().size(); i++) {
            System.out.println(this.allPossibility.getAllPossibility().get(i)[1]);
            System.out.println(this.allPossibility.getAllPossibility().get(i)[3] + " : " + this.allPossibility.getAllPossibility().get(i)[4]);
            System.out.println("Angle min: " + (angle - this.allPossibility.getAllPossibility().get(i)[1]));
            if (Math.abs(angle - this.allPossibility.getAllPossibility().get(i)[1]) < angleMin) {
                System.out.println("PIXEL");
                angleMin = Math.abs(angle - this.allPossibility.getAllPossibility().get(i)[1]);
                bestOnePossibility = this.allPossibility.getAllPossibility().get(i);
                System.out.println(bestOnePossibility[1]);
            }
        }
        this.bestOne = bestOnePossibility;
        System.out.println("Le BEST: " + bestOnePossibility[1]);
    }

    public double angleCheckpoint(Checkpoint checkpoint) {
        double angle = ((checkpoint.getPosition().getX() - nextRound.getShip().getPosition().getX()) * Math.cos(nextRound.getShip().getPosition().getOrientation()) + (checkpoint.getPosition().getY() - nextRound.getShip().getPosition().getY()) * Math.sin(nextRound.getShip().getPosition().getOrientation()))
                / (Math.sqrt(Math.pow(checkpoint.getPosition().getX() - nextRound.getShip().getPosition().getX(), 2) + Math.pow(checkpoint.getPosition().getY() - nextRound.getShip().getPosition().getY(), 2)));
//        return Math.acos(angle) * 180 / Math.PI;
        return Math.acos(angle);
    }


    public double[] getBestMove() {
        return bestOne;
    }

}
