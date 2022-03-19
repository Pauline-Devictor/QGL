package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.possibleDistance;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Wind;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.CalculateAngleHelper;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.CalculateDistanceHelper;

import java.util.ArrayList;
import java.util.List;

public class DistanceWithWindOption {


    int nbsails;
    double distance;

    public DistanceWithWindOption(int nbsails, double distance) {
        this.distance = distance;
        this.nbsails = nbsails;
    }

    public static List<DistanceWithWindOption> creationDistanceOptionWithWindFromSailsCount(int allSailsUsable, int allSails, Wind wind, double boatOrientation) {
        System.out.println("On pas la /////");
        List<DistanceWithWindOption> listDistanceWithWindOption = new ArrayList<>();
        double angleBetweenBoatAndWind = CalculateAngleHelper.angleBetweenBoatAndWind(boatOrientation, wind.getOrientation());
        System.out.println("l'angle poto"+ angleBetweenBoatAndWind);
        System.out.println("c'est ca qui pue "+allSailsUsable);
        for (int i = 0; i <= allSailsUsable; i++) {
            listDistanceWithWindOption.add(CalculateDistanceHelper.distanceForWind(allSails, i, angleBetweenBoatAndWind, wind));
            System.out.println("ALORS PQ CA MARCHE PAS : "+CalculateDistanceHelper.distanceForWind(allSails, i, angleBetweenBoatAndWind, wind).getDistance());
        }
        return listDistanceWithWindOption;
    }

    public int getNbsails() {
        return nbsails;
    }

    public void setNbsails(int nbsails) {
        this.nbsails = nbsails;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }


}


