package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.choice;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.possibleAngle.AngleOption;

import java.util.List;

import static fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.CalculateAngleHelper.angleBetweenPointAndCheckpoint;
import static fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.CalculateAngleHelper.realAngleBetweenPointAndCheckpoint;
import static java.lang.Math.abs;

public class ChoseAngle {

    ChoseAngle(){

    }
    public static AngleOption choiceBestDelta(List<AngleOption> angleOptionList, Checkpoint checkpoint, Ship ship) {
        AngleOption bestAngleOption = null;
        if (isOkayToUseOnlyTheRudderToTurn(checkpoint, ship) != 0) {
            return angleOptionList.get(0); //Puisque la premiere option d'angle c'est 0.0 degree et un delta de 0.
        } else {
            double angleMinimun = Math.PI;
            for (AngleOption angleOption : angleOptionList) {
                if (angleBetweenPointAndCheckpoint(checkpoint, ship.getPosition().getX(), ship.getPosition().getY(), ship.getPosition().getOrientation() + angleOption.getAngle()) < angleMinimun) {
                    bestAngleOption = angleOption;
                    angleMinimun = angleBetweenPointAndCheckpoint(checkpoint, ship.getPosition().getX(), ship.getPosition().getY(), ship.getPosition().getOrientation() + angleOption.getAngle());
                }
            }
        }
        return bestAngleOption;
    }



    public static double isOkayToUseOnlyTheRudderToTurn(Checkpoint checkpoint, Ship ship) {
        double angleBetweenShipOriantationAndCheckpoint = angleBetweenPointAndCheckpoint(checkpoint, ship.getPosition().getX(), ship.getPosition().getY(), ship.getPosition().getOrientation());
        if (abs(angleBetweenShipOriantationAndCheckpoint) >= Math.PI / 4)
            return 0;
        return realAngleBetweenPointAndCheckpoint(checkpoint, ship.getPosition().getX(), ship.getPosition().getY(), ship.getPosition().getOrientation(), angleBetweenShipOriantationAndCheckpoint);
    }

    public static double choseAngleForRudder(Checkpoint checkpoint, Ship ship, AngleOption angleOption) {
        return realAngleBetweenPointAndCheckpoint(checkpoint, ship.getPosition().getX(), ship.getPosition().getY(), ship.getPosition().getOrientation() + angleOption.getAngle(), angleBetweenPointAndCheckpoint(checkpoint, ship.getPosition().getX(), ship.getPosition().getY(), ship.getPosition().getOrientation() + angleOption.getAngle()));
    }
}
