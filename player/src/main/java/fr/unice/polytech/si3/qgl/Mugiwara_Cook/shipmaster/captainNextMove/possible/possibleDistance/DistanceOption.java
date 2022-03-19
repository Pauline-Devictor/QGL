package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.possibleDistance;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.CalculateDistanceHelper;

import java.util.ArrayList;
import java.util.List;

public class DistanceOption {
    int[] oarLeftRightAndSails;  //[0] rames à gauche et [1] rame à droite
    int sails;
    double distance;


    public DistanceOption(int[] oarLeftRight,int sails,double distance){
        this.oarLeftRightAndSails=oarLeftRight;
        this.sails=sails;
        this.distance=distance;

    }
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
                    distanceOptionList.add(CalculateDistanceHelper.distance(nbOarsLeft, nbOarsRight, oarTotal));
                }
            }
        }

        return distanceOptionList;
    }

    public static List<DistanceOption> addOarDistanceWithWindDistance(List<DistanceWithWindOption> distanceWithWindOptions,List<DistanceOption> distanceOptions) {
        List<DistanceOption> distanceOptionList = new ArrayList<>();
        System.out.println("sans vent"+distanceOptionList.size());
        System.out.println("avec vent"+distanceWithWindOptions.size());
        for(int i=0;i< distanceOptions.size();i++){
            for(int j=0;j< distanceWithWindOptions.size();j++){
                double distanceOar =distanceOptions.get(i).getDistance();
                double distanceWind=distanceWithWindOptions.get(j).getDistance();
                System.out.println("ICIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");;
                System.out.println("ca   "+distanceOar+"   "+distanceWind+"   "+distanceOar+distanceWind);

                distanceOptions.get(i).getOarLeftRight()[2]=distanceWithWindOptions.get(j).getNbsails();
                DistanceOption option=new DistanceOption(distanceOptions.get(i).getOarLeftRight(),distanceWithWindOptions.get(j).getNbsails(),distanceOar+distanceWind);
                System.out.println(option.getDistance());
                distanceOptionList.add(option);
            }
        }
        System.out.println("on passe aussi la et "+distanceOptionList.size());
        return distanceOptionList;
    }

    /**
     * @param distance distance que la composition de rame nous fait parcourir
     * @param oarLeftRight [0] rames à gauche et [1] rame à droite
     */
    public DistanceOption(double distance, int[] oarLeftRight) {
        this.distance = distance;
        this.oarLeftRightAndSails = oarLeftRight;
    }



    public int[] getOarLeftRight() {
        return oarLeftRightAndSails;
    }

    public double getDistance() {
        return distance;
    }

    public void getDetail() {
        System.out.println("Distance: " + this.getDistance() + "m avec les rames suivante: " + this.getOarLeftRight()[0] + ":" + this.getOarLeftRight()[1]);
    }
    public int getSails() {
        return sails;
    }

    public void setSails(int sails) {
        this.sails = sails;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
