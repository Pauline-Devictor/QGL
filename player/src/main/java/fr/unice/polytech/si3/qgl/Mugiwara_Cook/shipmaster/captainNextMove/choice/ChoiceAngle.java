package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.choice;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.AngleOption;

import java.util.List;

public class ChoiceAngle {

    public AngleOption choiceBestDelta(List<AngleOption> angleOptionList, Checkpoint checkpoint, Ship ship) {
        AngleOption bestAngleOption = null;
        System.out.println("ANGLE SOUHAITER: " + this.angleBetweenPointAndCheckpoint(checkpoint, ship.getPosition().getX(), ship.getPosition().getY(), ship.getPosition().getOrientation()));
        if (this.angleBetweenPointAndCheckpoint(checkpoint, ship.getPosition().getX(), ship.getPosition().getY(), ship.getPosition().getOrientation()) <= Math.PI / 4) {
            return angleOptionList.get(0); //Puisque la premiere option d'angle c'est 0.0 degree et un delta de 0.
        } else {
            double angleMinimun = Math.PI;
            for (AngleOption angleOption : angleOptionList) {
                if (this.angleBetweenPointAndCheckpoint(checkpoint, ship.getPosition().getX(), ship.getPosition().getY(), ship.getPosition().getOrientation() + angleOption.getAngle()) < angleMinimun) {
                    System.out.println("Nouveau angle retenu car: " + angleMinimun + " > " + this.angleBetweenPointAndCheckpoint(checkpoint, ship.getPosition().getX(), ship.getPosition().getY(), ship.getPosition().getOrientation() + angleOption.getAngle()));
                    bestAngleOption = angleOption;
                    angleMinimun = this.angleBetweenPointAndCheckpoint(checkpoint, ship.getPosition().getX(), ship.getPosition().getY(), ship.getPosition().getOrientation() + angleOption.getAngle());
                }
            }
        }
        return bestAngleOption;
    }

    public double angleBetweenPointAndCheckpoint(Checkpoint checkpoint, double xPoint, double yPoint, double orientation) {
        double angle = ((checkpoint.getPosition().getX() - xPoint) * Math.cos(orientation) + (checkpoint.getPosition().getY() - yPoint) * Math.sin(orientation))
                / (Math.sqrt(Math.pow(checkpoint.getPosition().getX() - xPoint, 2) + Math.pow(checkpoint.getPosition().getY() - yPoint, 2)));
//        return Math.acos(angle) * 180 / Math.PI;
        return Math.acos(angle);
    }

}
