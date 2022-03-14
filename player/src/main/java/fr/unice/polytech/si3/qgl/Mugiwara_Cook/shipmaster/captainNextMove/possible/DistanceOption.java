package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible;

import java.util.ArrayList;
import java.util.List;

public class DistanceOption {
    int[] oarLeftRight;  //[0] rames à gauche et [1] rame à droite
    double distance;

    /**
     * Renvois une liste de couple angle et des compostion de rames
     * @param leftCount nombre de rames à gauche
     * @param rightCount nombre de rames à droite
     * @param oarTotal nombre de rame total
     * @return tous les angles possibles (associé à un delta)en fonction des compositions de rames
     */
    public static List<DistanceOption> creationDistanceOptionFromOarCount(int delta, int leftCount, int rightCount, int oarTotal) {
        List<DistanceOption> distanceOptionList = new ArrayList<>();

        for (int nbOarsLeft = 0; nbOarsLeft <= leftCount; nbOarsLeft++) {
            for (int nbOarsRight = 0; nbOarsRight <= rightCount; nbOarsRight++) {
                if (nbOarsRight - nbOarsLeft == delta) {
                    distanceOptionList.add(distance(nbOarsLeft, nbOarsRight, oarTotal));
                }
            }
        }
        return distanceOptionList;
    }

    /**
     * @param distance distance que la composition de rame nous fait parcourir
     * @param oarLeftRight [0] rames à gauche et [1] rame à droite
     */
    public DistanceOption(double distance, int[] oarLeftRight) {
        this.distance = distance;
        this.oarLeftRight = oarLeftRight;
    }

    /**
     * donne le couple distance en fonction de la composition de rames
     * @param oarLeft nombre de rames à gauche
     * @param oarRight nombre de rames à droite
     * @param oarTotal nombre de rames total
     * @return le couple distance ,
     */
    static public DistanceOption distance(int oarLeft, int oarRight, int oarTotal) {
        return new DistanceOption((165 * (oarLeft + oarRight)) / (double) oarTotal, new int[]{oarLeft, oarRight});
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
