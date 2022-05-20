package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;

public class CalculateAngleHelper {

    CalculateAngleHelper(){

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
        if((checkpoint.getPosition().getX() - xPoint)==0 && (checkpoint.getPosition().getY() - yPoint)==0)
            return 0;
        double angle = ((checkpoint.getPosition().getX() - xPoint) * Math.cos(orientation) + (checkpoint.getPosition().getY() - yPoint) * Math.sin(orientation))
                / (Math.sqrt(Math.pow(checkpoint.getPosition().getX() - xPoint, 2) + Math.pow(checkpoint.getPosition().getY() - yPoint, 2)));
        System.out.println("angle : " + angle);
        System.out.println("Arcos : " + Math.acos(angle));
        return Math.acos(angle);
    }

    public static double angleBetweenBoatAndWind(double boatOrientation,double windOrientation){
        return windOrientation-boatOrientation;
    }


    /**
     * calcule l'angle entre l'orientation du bateau et le prochain checkpoint mais cette fois si on sait si on doit tourner vers la droite ou la gauche
     *
     * @param checkpoint      prochain checkpoint
     * @param xPoint          position en x du bateau
     * @param yPoint          position en y du bateau
     * @param orientationShip orientation du bateau
     * @return l'angle entre l'orientation du bateau et le prochain checkpoint(angle peut etre negat
     */
    public static double realAngleBetweenPointAndCheckpoint(Checkpoint checkpoint, double xPoint, double yPoint, double orientationShip, double angleBetweenShipOriantationAndCheckpoint) {
        if (angleBetweenShipOriantationAndCheckpoint == 0) return 0;
        if (angleBetweenShipOriantationAndCheckpoint > angleBetweenPointAndCheckpoint(checkpoint, xPoint, yPoint, orientationShip + (angleBetweenShipOriantationAndCheckpoint / 2)))
            return angleBetweenShipOriantationAndCheckpoint;
        else return -angleBetweenShipOriantationAndCheckpoint;
    }
}
