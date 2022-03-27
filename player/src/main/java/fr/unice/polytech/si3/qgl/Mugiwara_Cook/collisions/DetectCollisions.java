package fr.unice.polytech.si3.qgl.Mugiwara_Cook.collisions;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Point;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Polygon;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Reef;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;

import java.util.ArrayList;

/// Faut modifier certains points pour les remplacer par des positions
public class DetectCollisions {

    Reef reef;
    Ship ship;

    public DetectCollisions(Reef reef, Ship ship){
        this.reef=reef;
        this.ship=ship;
    }

    public Shape getReefShape(){
        return reef.getShape();
    }

    ///pour l'instant il n'y a pas de marge sur le rayon
    public boolean collisionWithCircle(Polygon safetyTriangle,double radius){
        boolean collision = false;
        Point center = new Point(reef.getPosition().getX(),reef.getPosition().getY());
        for (Point safetyTrianglePoint : safetyTriangle.getVertices()) {
            if(distance(safetyTrianglePoint,center) >= radius); // rajouter la marche ici : 2x la longeur du bateau
            collision = true;
            break;
        }
        return collision;
    }

    public boolean collisionWithPolygon(Polygon safetyTriangle,Point[] vertices) {
        Point closestReefPoint = findCLosestPointFromPolygon(vertices, safetyTriangle);
        for(int i=0;i<3;i++){
            Point A = safetyTriangle.getVertices()[i];
            Point B;
            if(i==2){
                B = safetyTriangle.getVertices()[0];
            } else {
                B = safetyTriangle.getVertices()[i+1];
            }
            if(calculDeterminant(A,B,closestReefPoint)<=0){
                return false;
            }
        }
        return true;
    }

    /// Décider comment le construire A MODIFIER pour le point fictional
    public Polygon buildSafetyTriangle(Position start,Position finish){
        Point fictionalPoint = new Point(0,0);
        Point startPoint = new Point(start.getX(),start.getY());
        Point finishPoint = new Point(finish.getX(),finish.getY());
        ///détermination du point c
        Point[] vertices ={startPoint,finishPoint,fictionalPoint};
        return new Polygon(start.getOrientation(),vertices);
    }

    public double distance(Point safetyPoint, Point reefPoint){
        return Math.sqrt(Math.pow(safetyPoint.getX() - reefPoint.getX(), 2) + Math.pow(safetyPoint.getY()- reefPoint.getY(), 2));
    }

    public Point findCLosestPointFromPolygon(Point[] reefVertices, Polygon safetyTriangle){
        Point closestPoint=reefVertices[0];
        for(Point points : reefVertices ) {
            if(distance(points,safetyTriangle.getVertices()[0])<distance(closestPoint,safetyTriangle.getVertices()[0])) {
                closestPoint = points;
            }
        }
        return closestPoint;
    }

    Float calculDeterminant(Point A,Point B,Point polygonVertice) {
        float xVectorTriangle = (float) (B.getX() - A.getX());
        float yVectorTriangle = (float) (B.getY() - A.getY());
        float xVectorPolygon = (float) (polygonVertice.getX() - A.getX());
        float yVectorPolygon = (float) (polygonVertice.getY() - A.getY());

        return xVectorTriangle*yVectorPolygon - yVectorTriangle*xVectorPolygon;
    }

    // Rajouter le cas du rectangle
    public boolean detectCollision(Position start, Position finish) {

        boolean collision;

        //delete later four lines below
        Point start2 = new Point(0,0);
        Point finish2 = new Point(0,0);
        Point finish3 = new Point(0,0);
        Point vertices[] = {start2,finish2,finish3};

        Polygon safetyTriangle = buildSafetyTriangle(start,finish);

        if(reef.getShape().equals("circle")) {
            collision = collisionWithCircle(safetyTriangle,((Circle)reef.getShape()).getRadius());
        } else {
            collision = collisionWithPolygon(safetyTriangle,((Polygon)reef.getShape()).getVertices());
        }
        return collision;
    }
}
