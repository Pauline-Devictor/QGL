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
        List<DistanceWithWindOption> listDistanceWithWindOption = new ArrayList<>();
        double angleBetweenBoatAndWind = CalculateAngleHelper.angleBetweenBoatAndWind(boatOrientation, wind.getOrientation());
        for (int i = 0; i <= allSailsUsable; i++) {
            listDistanceWithWindOption.add(CalculateDistanceHelper.distanceForWind(allSails, i, angleBetweenBoatAndWind, wind));
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


