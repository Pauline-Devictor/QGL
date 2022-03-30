package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.choice;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Wind;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.CalculateDistanceHelper;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.possibleAngle.AngleOption;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.possibleDistance.DistanceOption;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.possibleDistance.DistanceWithWindOption;

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
        return distanceClosest.getOarLeftRightAndSails();
    }

    public static int[] choiceBestNbSail(int[] nbOfOarsUsed, Checkpoint checkpoint, Ship ship, Position position, Wind wind) {
        int[] nbOarAndNbSail = {nbOfOarsUsed[0], nbOfOarsUsed[1], 0};
        double distance = CalculateDistanceHelper.remainingDistance(nbOfOarsUsed, checkpoint, ship, position);
        List<DistanceWithWindOption> distanceWithWindOptions = DistanceWithWindOption.creationDistanceOptionWithWindFromSailsCount(ship.getNbUsableSails(), ship.getNbEquipment("sail"), wind, ship.getPosition().getOrientation());
        DistanceWithWindOption distanceClosest = distanceWithWindOptions.get(0);
        nbOarAndNbSail[2] = distanceClosest.getNbsails();
        for (DistanceWithWindOption distanceOp : distanceWithWindOptions) {
            if (distanceOp.getDistance() <= distance && distanceOp.getDistance() > distanceClosest.getDistance()) {
                distanceClosest = distanceOp;
                nbOarAndNbSail[2] = distanceOp.getNbsails();
            }
        }
        return nbOarAndNbSail;
    }

    public static int[] choiceBestNbOarAndSail(AngleOption angleOption, Checkpoint checkpoint, Ship ship, Position position, Wind wind) {
        int[] nbOfOarsUsed = choiceBestNbOar(angleOption, checkpoint, ship, position);
        return choiceBestNbSail(nbOfOarsUsed, checkpoint, ship, position, wind);
    }

    public static double distanceBetweenPointAndCheckpoint(Checkpoint checkpoint, double xPoint, double yPoint) {
        return (double) Math.sqrt(Math.pow(checkpoint.getPosition().getX() - xPoint, 2) + Math.pow(checkpoint.getPosition().getY() - yPoint, 2));
    }
}
