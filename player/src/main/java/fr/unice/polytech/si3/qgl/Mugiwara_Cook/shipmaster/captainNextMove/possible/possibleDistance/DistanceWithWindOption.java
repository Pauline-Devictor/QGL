package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.possibleDistance;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Wind;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.CalculateAngleHelper;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.CalculateDistanceHelper;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class DistanceWithWindOption {
    @Getter
    @Setter
    int nbsails;
    @Getter
    @Setter
    double distance;

    public static List<DistanceWithWindOption> creationDistanceOptionWithWindFromSailsCount(int allSailsUsable, int allSails, Wind wind, double boatOrientation) {
        List<DistanceWithWindOption> listDistanceWithWindOption = new ArrayList<>();
        double angleBetweenBoatAndWind = CalculateAngleHelper.angleBetweenBoatAndWind(boatOrientation, wind.getOrientation());
        for (int i = 0; i <= allSailsUsable; i++) {
            listDistanceWithWindOption.add(CalculateDistanceHelper.distanceForWind(allSails, i, angleBetweenBoatAndWind, wind));
        }
        return listDistanceWithWindOption;
    }

    public DistanceWithWindOption(int nbsails, double distance) {
        this.distance = distance;
        this.nbsails = nbsails;
    }
}


