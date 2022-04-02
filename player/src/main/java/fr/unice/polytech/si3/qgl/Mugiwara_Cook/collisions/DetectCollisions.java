package fr.unice.polytech.si3.qgl.Mugiwara_Cook.collisions;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Point;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Polygon;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Rectangle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Reef;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;

import java.util.ArrayList;


public class DetectCollisions {

    Reef reef;
    Ship ship;

    public DetectCollisions(Reef reef, Ship ship) {
        this.reef = reef;
        this.ship = ship;
    }

    public Shape getReefShape() {
        return reef.getShape();
    }


    public boolean collisionWithCircle(Polygon safetyTriangle, double radius) {
        boolean collision = false;
        Point reefCenter = new Point(reef.getPosition().getX(), reef.getPosition().getY());
        for (Point safetyTrianglePoint : safetyTriangle.getVertices()) {
            if (distance(safetyTrianglePoint, reefCenter) <= radius + ((Rectangle) ship.getShape()).getHeight() * 2) ;
            collision = true;
            break;
        }
        return collision;
    }

    public boolean collisionWithSafetyTrianglePoint(Point safetyTrianglePoint, Point[] vertices) {
        for (int i = 0; i < vertices.length; i++) {
            Point A = vertices[i];
            Point B;
            if (i == vertices.length - 1) {
                B = vertices[0];
            } else {
                B = vertices[i + 1];
            }
            System.out.println(i+ "-ème déterminant :" + calculDeterminant(A, B, safetyTrianglePoint));
            if (calculDeterminant(A, B, safetyTrianglePoint) > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean collisionWithPolygonOrRectangle(Polygon safetyTriangle, Point[] vertices) {
        for (Point safetyTrianglePoint : safetyTriangle.getVertices()){
            if(collisionWithSafetyTrianglePoint(safetyTrianglePoint,vertices)){
                return true;
            }
        } return false;
    }

    public boolean collisionWithRectangle(Polygon safetyTriangle, Point[] rectangleVertices) {
        Point closestReefPoint = findCLosestPointFromPolygonOrRectangle(rectangleVertices, safetyTriangle);
        for (int i = 0; i < 3; i++) {
            Point A = safetyTriangle.getVertices()[i];
            Point B;
            if (i == 2) {
                B = safetyTriangle.getVertices()[0];
            } else {
                B = safetyTriangle.getVertices()[i + 1];
            }
            if (calculDeterminant(A, B, closestReefPoint) > 0) {
                return false;
            }
        }
        return true;
    }

    public Point[] getRectangleVertices() {
        double height = ((Rectangle) reef.getShape()).getHeight() / 2;
        double width = ((Rectangle) reef.getShape()).getWidth() / 2;
        double xReefCenter = reef.getPosition().getX();
        double yReefCenter = reef.getPosition().getY();
        Point vertice1 = new Point(xReefCenter + height, yReefCenter + width);
        Point vertice2 = new Point(xReefCenter + height, yReefCenter - width);
        Point vertice3 = new Point(yReefCenter - height, yReefCenter - width);
        Point vertice4 = new Point(yReefCenter - height, yReefCenter + width);
        Point rectangleVertices[] = {vertice1, vertice2, vertice3, vertice4};
        return rectangleVertices;
    }

    public Point findCLosestPointFromPolygonOrRectangle(Point[] reefVertices, Polygon safetyTriangle) {
        Point closestPoint = reefVertices[0];
        for (Point points : reefVertices) {
            if (distance(points, safetyTriangle.getVertices()[0]) < distance(closestPoint, safetyTriangle.getVertices()[0])) {
                closestPoint = points;
            }
        }
        return closestPoint;
    }

    public Polygon buildSafetyTriangle(Position start, Position finish) {
        Point fictionalPoint = new Point(0, 0);
        Point startPoint = new Point(start.getX(), start.getY());
        Point finishPoint = new Point(finish.getX(), finish.getY());

        Point[] vertices = {fictionalPoint, startPoint, finishPoint};
        return new Polygon(start.getOrientation(), vertices);
    }

    public double distance(Point safetyPoint, Point reefPoint) {
        return Math.sqrt(Math.pow(safetyPoint.getX() - reefPoint.getX(), 2) + Math.pow(safetyPoint.getY() - reefPoint.getY(), 2));
    }

    Float calculDeterminant(Point A, Point B, Point safetytrianglePoint) {
        float xVectorAB = (float) (B.getX() - A.getX());
        float yVectorAB = (float) (B.getY() - A.getY());
        float xVectorPolygon = (float) (safetytrianglePoint.getX() - A.getX());
        float yVectorPolygon = (float) (safetytrianglePoint.getY() - A.getY());
        return xVectorAB * yVectorPolygon - yVectorAB * xVectorPolygon;
    }

    public boolean detectCollision(Position start, Position finish) {

        boolean collision;
        Polygon safetyTriangle = buildSafetyTriangle(start, finish);

        if (reef.getShape().equals("circle")) {
            collision = collisionWithCircle(safetyTriangle, ((Circle) reef.getShape()).getRadius());
        } else if (reef.getShape().equals("polygon")) {
            collision = collisionWithPolygonOrRectangle(safetyTriangle, ((Polygon) reef.getShape()).getVertices());
        } else {
            collision = collisionWithRectangle(safetyTriangle, getRectangleVertices());
        }
        return collision;
    }
}
