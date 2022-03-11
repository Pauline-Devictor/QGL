package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.choice;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.AngleOption;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.DistanceOption;

import java.util.List;

public class ChoseDistance {

    public static int[] choiceBestNbOar(AngleOption angleOption, Checkpoint checkpoint, Ship ship, Position position) {
        double distance = distanceBetweenPointAndCheckpoint(checkpoint, position.getX(), position.getY());
        List<DistanceOption> distanceOption = DistanceOption.creationDistanceOptionFromOarCount(angleOption.getDelta(), ship.getNbUsableOarsLeft(), ship.getNbUsableOarsRight(), ship.getNbOars());

        DistanceOption distanceClosest = distanceOption.get(0);
        for (DistanceOption distanceOp : distanceOption) {
            if (distanceOp.getDistance() <= distance && distanceOp.getDistance() > distanceClosest.getDistance()) {
                distanceClosest = distanceOp;
            }
        }
        return distanceClosest.getOarLeftRight();
    }

    public static double distanceBetweenPointAndCheckpoint(Checkpoint checkpoint, double xPoint, double yPoint) {
        return (double) Math.sqrt(Math.pow(checkpoint.getPosition().getX() - xPoint, 2) + Math.pow(checkpoint.getPosition().getY() - yPoint, 2));
    }


}
