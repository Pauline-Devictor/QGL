package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.choice;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.AngleOption;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.DistanceOption;

import java.util.List;

public class ChoiseDistance {

    public int[] choiceBestNbOar(AngleOption angleOption, Checkpoint checkpoint, Ship ship, InitGame initGame) {
        double distance = this.distanceBetweenPointAndCheckpoint(checkpoint, ship.getPosition().getX(), ship.getPosition().getY());
        List<DistanceOption> distanceOption = DistanceOption.creationDistanceOptionFromOarCount(angleOption.getDelta(), initGame.getUsableSailorLeft().size(), initGame.getUsableSailorRight().size(), ship.getNbOars());

        DistanceOption distanceClosest = distanceOption.get(0);
        for (DistanceOption distanceOp : distanceOption) {
            distanceOp.getDetail();
            if (distanceOp.getDistance() <= distance && distanceOp.getDistance() > distanceClosest.getDistance()) {
                distanceClosest = distanceOp;
            }
        }
        System.out.println("RETENU: ");
        distanceClosest.getDetail();
        return distanceClosest.getOarLeftRight();
    }

    public double distanceBetweenPointAndCheckpoint(Checkpoint checkpoint, double xPoint, double yPoint) {
        return (double) Math.sqrt(Math.pow(checkpoint.getPosition().getX() - xPoint, 2) + Math.pow(checkpoint.getPosition().getY() - yPoint, 2));
    }


}
