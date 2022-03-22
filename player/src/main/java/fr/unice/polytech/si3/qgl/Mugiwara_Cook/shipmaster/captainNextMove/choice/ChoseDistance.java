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
        System.out.println("pfff"+distanceOption.size());
        DistanceOption distanceClosest = distanceOption.get(0);
        for (DistanceOption distanceOp : distanceOption) {
            if (distanceOp.getDistance() <= distance && distanceOp.getDistance() > distanceClosest.getDistance()) {
                distanceClosest = distanceOp;
            }
        }
        return distanceClosest.getOarLeftRight();
    }
    public static int[] choiceBestNbSail(int[] nbOfOarsUsed, Checkpoint checkpoint, Ship ship, Position position,Wind wind) {
        int[] nbOarAndNbSail={nbOfOarsUsed[0],nbOfOarsUsed[1],0};
        System.out.println("on passe la pour les voiles");
        double distance = CalculateDistanceHelper.remainingDistance(nbOfOarsUsed, checkpoint, ship, position);
        List<DistanceWithWindOption> distanceWithWindOptions= DistanceWithWindOption.creationDistanceOptionWithWindFromSailsCount(ship.getNbUsableSails(),ship.getNbEquipment("sail"),wind,ship.getPosition().getOrientation());
        System.out.println("LA TAILLE DE WINDOPTION "+distanceWithWindOptions.size());
        DistanceWithWindOption distanceClosest = distanceWithWindOptions.get(0);
        nbOarAndNbSail[2]=distanceClosest.getNbsails();
        for (DistanceWithWindOption distanceOp : distanceWithWindOptions) {
            System.out.println("Mais pq ca ne marche pas ..."+ distanceOp.getNbsails()+"     "+(distanceOp.getDistance() <= distance)+"     "+(distanceOp.getDistance())+"     "+ distanceClosest.getDistance());
            if (distanceOp.getDistance() <= distance && distanceOp.getDistance() > distanceClosest.getDistance()) {
                System.out.println("mokoko seed");
                distanceClosest = distanceOp;
                nbOarAndNbSail[2]=distanceOp.getNbsails();
            }
        }
        System.out.println("PQ il y tjr 0 sail ..."+nbOarAndNbSail[0]+"  "+nbOarAndNbSail[1]+"   "+nbOarAndNbSail[2]);
        return nbOarAndNbSail;
    }

    public static int[] choiceBestNbOarAndSail(AngleOption angleOption, Checkpoint checkpoint, Ship ship, Position position, Wind wind){
        int[] nbOfOarsUsed =choiceBestNbOar(angleOption,checkpoint,ship,position);
        return choiceBestNbSail(nbOfOarsUsed,checkpoint,ship,position,wind);
    }



    public static double distanceBetweenPointAndCheckpoint(Checkpoint checkpoint, double xPoint, double yPoint) {
        return (double) Math.sqrt(Math.pow(checkpoint.getPosition().getX() - xPoint, 2) + Math.pow(checkpoint.getPosition().getY() - yPoint, 2));
    }


}
