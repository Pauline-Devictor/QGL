package fr.unice.polytech.si3.qgl.Mugiwara_Cook.collisions;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Point;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Polygon;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Rectangle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Reef;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;

public class AvoidCollisions {

    Reef reef;
    Ship ship;
    Checkpoint currentCheckpoint;

    public AvoidCollisions(Reef reef,Ship ship,Checkpoint currentCheckpoint){
        this.reef=reef;
        this.ship=ship;
        this.currentCheckpoint=currentCheckpoint;
    }

    public Checkpoint getCurrentCheckpoint() {
        return currentCheckpoint;
    }

    public void setCurrentCheckpoint(Checkpoint newCurrentCheckpoint) { this.currentCheckpoint = currentCheckpoint; }

    /**
     * Cette méthode regarde si un obstacle se situe à droite ou à gauche de la droite formée par le bateau et le checkpoint
     * @param obstaclePoint
     * @return
     */
    public boolean isPointOnTheRightOfShip(Point obstaclePoint){
        double xVectorShipToCheckpoint = currentCheckpoint.getPosition().getX() - ship.getPosition().getX();
        double yVectorShipToCheckpoint = currentCheckpoint.getPosition().getY() - ship.getPosition().getY();
        double xVectorShipToObstaclepoint = obstaclePoint.getX() - ship.getPosition().getX();
        double yVectorShipToObstaclepoint = obstaclePoint.getY() - ship.getPosition().getY();

        double d = xVectorShipToCheckpoint*yVectorShipToObstaclepoint - yVectorShipToCheckpoint*xVectorShipToObstaclepoint;
        if(d>0) { return true; }
        else { return false; }
    }

    /**
    public Checkpoint avoidCircleReef(){
        Position checkpointPosition;
        return;
    }
     */

    // A changer si la forme du bateau change !!!!
    public Position calculateNewPoint(Point start, Point finish){

        double distanceCenterToNewPoint = ((Circle)reef.getShape()).getRadius() + ((Rectangle)ship.getShape()).getHeight()*2;
        double yReefCenter = reef.getPosition().getY();
        double xReefCenter = reef.getPosition().getX();
        double xVectorShipToCheckpoint = currentCheckpoint.getPosition().getX() - ship.getPosition().getX();
        double yVectorShipToCheckpoint = currentCheckpoint.getPosition().getY() - ship.getPosition().getY();

        /// methode resolve equation ?
        double aCoefficient = 1 + (xVectorShipToCheckpoint/yVectorShipToCheckpoint);
        double bCoefficient = -2*yReefCenter*(1 + Math.pow(xVectorShipToCheckpoint/yVectorShipToCheckpoint,2));
        double cCoefficient = Math.pow(yReefCenter,2) + Math.pow(xReefCenter,2) + Math.pow(xReefCenter - (xVectorShipToCheckpoint/yVectorShipToCheckpoint)*yReefCenter,2) - 2*xReefCenter*(xReefCenter-yReefCenter*(xVectorShipToCheckpoint/yVectorShipToCheckpoint)) - Math.pow(distanceCenterToNewPoint,2);
        double delta = Math.pow(bCoefficient,2) - 4*aCoefficient*cCoefficient;

        double ySolution1 = (-bCoefficient + Math.sqrt(delta))/2*aCoefficient;
        double xSolution1 = xReefCenter + (ySolution1 - yReefCenter)*xVectorShipToCheckpoint/yVectorShipToCheckpoint;
        //double ySolution2 = (-bCoefficient - Math.sqrt(delta))/2*aCoefficient;

        return determineRightPosition(ySolution1,xSolution1,xReefCenter,yReefCenter);

    }

    public Position determineRightPosition(double ySolution1,double xSolution1,double xreefCenter,double yReefCenter){

        Point potientialPoint = new Point(xSolution1,ySolution1);
        Point reefCenter = new Point(xreefCenter,yReefCenter);
        if(isPointOnTheRightOfShip(reefCenter)) {
            if(isPointOnTheRightOfShip(reefCenter)) { return new Position(xSolution1,ySolution1,0.0); }
            else {  return calculateOppositePosition(reefCenter,potientialPoint); }
        } else {
            if((!isPointOnTheRightOfShip(reefCenter))) { return new Position(xSolution1,ySolution1,0.0); }
            else { return calculateOppositePosition(reefCenter,potientialPoint); }
        }
    }

    /**
     * @param reefCenter
     * @param potentialCheckPointCenter position dont on veut le symétrique par rapport au position reefCenter
     */
    public Position calculateOppositePosition(Point reefCenter, Point potentialCheckPointCenter) {
        double xNewPosition = 2*reefCenter.getX() - potentialCheckPointCenter.getX();
        double yNewPosition = 2*reefCenter.getY() - potentialCheckPointCenter.getY();
        return new Position(xNewPosition,yNewPosition,0.0);
    }

    // determineRightPoint verifie en fonction de si c'est a droite ou pas la bonne solution

    /**
    public Checkpoint avoidPolygon(){
        return
    }
     */
}
