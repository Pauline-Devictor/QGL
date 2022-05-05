package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Wind;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.possibleDistance.DistanceOption;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.possibleDistance.DistanceWithWindOption;

public class CalculateDistanceHelper {

    CalculateDistanceHelper() {

    }
    public static DistanceWithWindOption distanceForWind(int sailsTotal, int sailsUsed, double angleBetweenBoatAndWind, Wind wind) {
        if (sailsTotal != 0) {
            double sailsTotalDouble = (double) sailsTotal;
            double sailsUsedDouble = (double) sailsUsed;
            double distance = (sailsUsedDouble / sailsTotalDouble) * wind.getStrength() * Math.cos(angleBetweenBoatAndWind);
            return new DistanceWithWindOption(sailsUsed, distance);
        } else return new DistanceWithWindOption(0, 0);
    }

    /**
     * donne le couple distance en fonction de la composition de rames
     *
     * @param oarLeft  nombre de rames à gauche
     * @param oarRight nombre de rames à droite
     * @param oarTotal nombre de rames total
     * @return le couple distance ,
     */
    static public DistanceOption distance(int oarLeft, int oarRight, int oarTotal) {
        return new DistanceOption((165 * (oarLeft + oarRight)) / (double) oarTotal, new int[]{oarLeft, oarRight, 0});
    }

    static public double distanceBrowsed(int oarLeft, int oarRight, int oarTotal) {
        return (165 * (oarLeft + oarRight)) / (double) oarTotal;


    }

    public static double remainingDistance(int[] nbOfOarsUsed, Checkpoint checkpoint, Ship ship, Position position) {
        return distanceBetweenPointAndCheckpoint(checkpoint, position.getX(), position.getY()) - CalculateDistanceHelper.distanceBrowsed(nbOfOarsUsed[0], nbOfOarsUsed[1], ship.getNbOars());
    }


    public static double distanceBetweenPointAndCheckpoint(Checkpoint checkpoint, double xPoint, double yPoint) {
        return (double) Math.sqrt(Math.pow(checkpoint.getPosition().getX() - xPoint, 2) + Math.pow(checkpoint.getPosition().getY() - yPoint, 2));
    }

}
