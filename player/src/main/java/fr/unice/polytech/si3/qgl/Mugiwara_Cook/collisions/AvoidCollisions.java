

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

    public void setCurrentCheckpoint(Checkpoint newCurrentCheckpoint) { this.currentCheckpoint = newCurrentCheckpoint; }

    /**
     * Cette méthode regarde si un obstacle se situe à droite ou à gauche de la droite formée par le bateau et le checkpoint
     * @return un booléan qui est vrai si le point est à droite
     */
    public boolean isPointOnTheRightOfShip(Position obstaclePoint){
        double xVectorShipToCheckpoint = ship.getPosition().getX() - currentCheckpoint.getPosition().getX();
        double yVectorShipToCheckpoint = ship.getPosition().getY() - currentCheckpoint.getPosition().getY();
        double xVectorShipToObstaclepoint = ship.getPosition().getX() - obstaclePoint.getX();
        double yVectorShipToObstaclepoint = ship.getPosition().getY() - obstaclePoint.getY();

        double d = xVectorShipToCheckpoint*yVectorShipToObstaclepoint - yVectorShipToCheckpoint*xVectorShipToObstaclepoint;
        return d >= 0;
    }

    /**
     * Cette méthode envoie un checkpoint intermédiaire qui permet d'éviter la collision avec un obstacle
     * @param start position de départ donnée du bateau
     * @param finish position calculée par l'algorithme de déplacement
     */
    public Position calculateNewCheckpointPosition(Position start, Position finish){

        double yReefCenter = reef.getPosition().getY();
        double xReefCenter = reef.getPosition().getX();
        double distanceCenterToNewPosition = determineDistance(xReefCenter,yReefCenter);
        double xVectorShipToCheckpoint = currentCheckpoint.getPosition().getX() - ship.getPosition().getX();
        double yVectorShipToCheckpoint = currentCheckpoint.getPosition().getY() - ship.getPosition().getY();

        Position potentialPosition = resolveEquation(xVectorShipToCheckpoint,yVectorShipToCheckpoint,xReefCenter,yReefCenter,distanceCenterToNewPosition);

        return determineRightPosition(potentialPosition.getX(),potentialPosition.getY());

    }

    /**
     * Cette méthode résout le système à deux inconnues pour déduire les coordonées d'un nouveau checkpoint
     * @return une position qui est potentiellement du bon côté du reef
     */
    public Position resolveEquation(double xVectorShipToCheckpoint,double yVectorShipToCheckpoint, double xReefCenter, double yReefCenter,double distanceCenterToNewPoint ){

        double aCoefficient = 1 + (xVectorShipToCheckpoint/yVectorShipToCheckpoint);
        System.out.println(aCoefficient);
        double bCoefficient = -2*yReefCenter*(1 + Math.pow(xVectorShipToCheckpoint/yVectorShipToCheckpoint,2));
        System.out.println(bCoefficient);
        double cCoefficient = Math.pow(yReefCenter,2) + Math.pow(xReefCenter,2) + Math.pow(xReefCenter - (xVectorShipToCheckpoint/yVectorShipToCheckpoint)*yReefCenter,2) - 2*xReefCenter*(xReefCenter-yReefCenter*(xVectorShipToCheckpoint/yVectorShipToCheckpoint)) - Math.pow(distanceCenterToNewPoint,2);
        System.out.println(cCoefficient);
        double delta = Math.pow(bCoefficient,2) - 4*aCoefficient*cCoefficient;
        System.out.println(delta);

        double ySolution1 = (-bCoefficient + Math.sqrt(delta))/2*aCoefficient;
        double xSolution1 = xReefCenter + (ySolution1 - yReefCenter)*xVectorShipToCheckpoint/yVectorShipToCheckpoint;
        //double ySolution2 = (-bCoefficient - Math.sqrt(delta))/2*aCoefficient;
        return new Position(xSolution1,ySolution1,0.0);
    }

    /**
     * Cette méthode choisit une position entre celle de la solution et son symétrique
     * @return la bonne position
     */
    public Position determineRightPosition(double xSolution1,double ySolution1){

        Position potientialPosition = new Position(xSolution1,ySolution1,0.0);
        Position reefCenter = reef.getPosition();

        if(isPointOnTheRightOfShip(reefCenter)==isPointOnTheRightOfShip(potientialPosition)) {
            return calculateSymetricalPosition(reefCenter,potientialPosition);
        } else {
            return potientialPosition;
        }
    }

    /**
     * @param potentialCheckPointCenter position dont on veut le symétrique par rapport au position reefCenter
     */
    public Position calculateSymetricalPosition(Position reefCenter, Position potentialCheckPointCenter) {
        double xNewPosition = 2*reefCenter.getX() - potentialCheckPointCenter.getX();
        double yNewPosition = 2*reefCenter.getY() - potentialCheckPointCenter.getY();
        return new Position(xNewPosition,yNewPosition,0.0);
    }

    public double determineDistance(double xReefCenter, double yReefCenter){

        if(reef.getShape().getType().equals("circle")){
            return ((Circle)reef.getShape()).getRadius() + ((Rectangle)ship.getShape()).getHeight()*2;
        }
        else if (reef.getShape().getType().equals("rectangle")) {
            return ((Rectangle)reef.getShape()).getHeight()*(3/2) + ((Rectangle)ship.getShape()).getHeight();
        }
        else {
            Point furthestPoint = findFurthestPointFromPolygon();
            return Math.sqrt(Math.pow(xReefCenter - furthestPoint.getX(),2) + Math.pow(yReefCenter- furthestPoint.getY(),2));
        }
    }

    public Point findFurthestPointFromPolygon(){
        Point furthestPoint = ((Polygon)reef.getShape()).getVertices()[0];
        Position reefCenter = reef.getPosition();
        boolean rightOfShip = isPointOnTheRightOfShip(reefCenter);

        for( Point vertices : ((Polygon)reef.getShape()).getVertices() ){
            Position verticesPosition = new Position(vertices.getX(),vertices.getY(),0.0);
            if(rightOfShip!=isPointOnTheRightOfShip(verticesPosition) && Math.sqrt(Math.pow(reefCenter.getX() - vertices.getX(),2) + Math.pow(reefCenter.getY() - vertices.getY(),2)) >= Math.sqrt(Math.pow(reefCenter.getX() - furthestPoint.getX(),2) + Math.pow(reefCenter.getY() - furthestPoint.getY(),2))){
                furthestPoint=vertices;
            }
        }
        return furthestPoint;
    }
}

