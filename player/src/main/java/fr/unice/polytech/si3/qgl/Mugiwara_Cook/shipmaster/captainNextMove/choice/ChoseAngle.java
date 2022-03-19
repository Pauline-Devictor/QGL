package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.choice;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.possibleAngle.AngleOption;

import java.util.List;

import static fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.CalculateAngleHelper.realAngleBetweenPointAndCheckpoint;

public class ChoseAngle {

    public static AngleOption choiceBestDelta(List<AngleOption> angleOptionList, Checkpoint checkpoint, Ship ship) {
        AngleOption bestAngleOption = null;
        if (isOkayToUseOnlyTheRudderToTurn(checkpoint, ship) != 0) {
            return angleOptionList.get(0); //Puisque la premiere option d'angle c'est 0.0 degree et un delta de 0.
        } else {
            System.out.println("ICI");
            double angleMinimun = Math.PI;
            for (AngleOption angleOption : angleOptionList) {
                if (angleBetweenPointAndCheckpoint(checkpoint, ship.getPosition().getX(), ship.getPosition().getY(), ship.getPosition().getOrientation() + angleOption.getAngle()) < angleMinimun) {
                    System.out.println("Orientation ship: " + ship.getPosition().getOrientation());
                    System.out.println("Orientation angle: " + angleOption.getAngle());
                    System.out.println(angleMinimun + " > " + angleBetweenPointAndCheckpoint(checkpoint, ship.getPosition().getX(), ship.getPosition().getY(), ship.getPosition().getOrientation() + angleOption.getAngle()));
                    bestAngleOption = angleOption;
                    angleMinimun = angleBetweenPointAndCheckpoint(checkpoint, ship.getPosition().getX(), ship.getPosition().getY(), ship.getPosition().getOrientation() + angleOption.getAngle());
//                    angleOption.setAngle(realAngleBetweenPointAndCheckpoint(checkpoint, ship.getPosition().getX(), ship.getPosition().getY(), ship.getPosition().getOrientation(), angleOption.getAngle()));
                }
            }
        }
        return bestAngleOption;
    }

    /**
     * calcule l'angle entre l'orientation du bateau et le prochain checkpoint
     *
     * @param checkpoint  prochain checkpoint
     * @param xPoint      position en x du bateau
     * @param yPoint      position en y du bateau
     * @param orientation orientation du bateau
     * @return l'angle entre l'orientation du bateau et le prochain checkpoint
     */
    public static double angleBetweenPointAndCheckpoint(Checkpoint checkpoint, double xPoint, double yPoint, double orientation) {
        double angle = ((checkpoint.getPosition().getX() - xPoint) * Math.cos(orientation) + (checkpoint.getPosition().getY() - yPoint) * Math.sin(orientation))
                / (Math.sqrt(Math.pow(checkpoint.getPosition().getX() - xPoint, 2) + Math.pow(checkpoint.getPosition().getY() - yPoint, 2)));
        return Math.acos(angle);
    }

    public static double isOkayToUseOnlyTheRudderToTurn(Checkpoint checkpoint, Ship ship) {
        double angleBetweenShipOriantationAndCheckpoint = angleBetweenPointAndCheckpoint(checkpoint, ship.getPosition().getX(), ship.getPosition().getY(), ship.getPosition().getOrientation());
        if (angleBetweenShipOriantationAndCheckpoint >= Math.PI / 4)
            return 0;
        return realAngleBetweenPointAndCheckpoint(checkpoint, ship.getPosition().getX(), ship.getPosition().getY(), ship.getPosition().getOrientation(), angleBetweenShipOriantationAndCheckpoint);
    }

    public static double choseAngleForRudder(Checkpoint checkpoint, Ship ship, AngleOption angleOption) {
        return realAngleBetweenPointAndCheckpoint(checkpoint, ship.getPosition().getX(), ship.getPosition().getY(), ship.getPosition().getOrientation() + angleOption.getAngle(), angleBetweenPointAndCheckpoint(checkpoint, ship.getPosition().getX(), ship.getPosition().getY(), ship.getPosition().getOrientation() + angleOption.getAngle()));
    }
}
