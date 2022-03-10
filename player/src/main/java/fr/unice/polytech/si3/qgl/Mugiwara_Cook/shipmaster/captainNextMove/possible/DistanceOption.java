package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible;

import java.util.ArrayList;
import java.util.List;

public class DistanceOption {
    int[] oarLeftRight;
    double distance;

    public DistanceOption(double distance, int[] oarLeftRight) {
        this.distance = distance;
        this.oarLeftRight = oarLeftRight;
    }

    public static List<DistanceOption> creationDistanceOptionFromOarCount(int delta, int leftCount, int rightCount, int oarTotal) {
        List<DistanceOption> distanceOptionList = new ArrayList<>();
        System.out.println(leftCount + " : " + rightCount);
        for (int nbOarsLeft = 0; nbOarsLeft <= leftCount; nbOarsLeft++) {
            for (int nbOarsRight = 0; nbOarsRight <= rightCount; nbOarsRight++) {
                if (nbOarsRight - nbOarsLeft == delta) {
                    distanceOptionList.add(distance(nbOarsLeft, nbOarsRight, oarTotal));
                }
            }
        }
        return distanceOptionList;
    }

    static public DistanceOption distance(int oarLeft, int oarRight, int oarTotal) {
        return new DistanceOption((165 * (oarLeft + oarRight)) / oarTotal, new int[]{oarLeft, oarRight});
    }

    public int[] getOarLeftRight() {
        return oarLeftRight;
    }

    public double getDistance() {
        return distance;
    }

    public void getDetail() {
        System.out.println("Distance: " + this.getDistance() + "m avec les rames suivante: " + this.getOarLeftRight()[0] + ":" + this.getOarLeftRight()[1]);
    }
}
