package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Point;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Polygon;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Rectangle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Reef;


public class CollisionDetector {

    // Cette classe va servir a détecter quelles cases ne sont pas libres pour le pathfinding
    // A noter que les méthodes de détection ne prennent en compte aucune marge de sécurité

    public CollisionDetector() {

    }

    public boolean collisionWithCircle(Point mapPoint, Reef reef) {
        return distance(mapPoint, reef.getPosition()) <= ((Circle) reef.getShape()).getRadius();
    }

    //Les deux méthodes sont pour l'instant séparés elles vont être réecrite pour éviter la duplication de code
    public boolean collisionWithRectangle(Point mapPoint, Reef reef) {
        Point rectangleVertices[] = getRectangleVertices(reef);
        for (int i = 0; i < rectangleVertices.length; i++) {
            Point A = rectangleVertices[i];
            Point B;
            if (i == rectangleVertices.length - 1) {
                B = rectangleVertices[0];
            } else {
                B = rectangleVertices[i + 1];
            }
            if (calculDeterminant(A, B, mapPoint) > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean collisionWithPolygon(Point mapPoint, Reef reef) {
        Point polygonVertices[] = ((Polygon) reef.getShape()).getVertices(); // Cette méthode ne peut pas marcher tant qu'on a pas rangé l'ordre des points comme on veut !
        for (int i = 0; i < polygonVertices.length; i++) {
            Point A = polygonVertices[i];
            Point B;
            if (i == polygonVertices.length - 1) {
                B = polygonVertices[0];
            } else {
                B = polygonVertices[i + 1];
            }
            if (calculDeterminant(A, B, mapPoint) > 0) {
                return false;
            }
        }
        return true;
    }


    public boolean detectCollision(Point mapPoint, Reef reef) {
        if (reef.getShape().getType().equals("circle")) {
            return collisionWithCircle(mapPoint, reef);
        }
        if (reef.getShape().getType().equals("rectangle")) {
            return collisionWithRectangle(mapPoint, reef);
        }
        if (reef.getShape().getType().equals("polygon")) {
            return collisionWithPolygon(mapPoint, reef);
        }
        return false;
    }

    public Point[] getRectangleVertices(Reef reef) {
        double height = ((Rectangle) reef.getShape()).getHeight() / 2;
        double width = ((Rectangle) reef.getShape()).getWidth() / 2;
        double xReefCenter = reef.getPosition().getX();
        double yReefCenter = reef.getPosition().getY();
        Point vertice1 = new Point(xReefCenter + width, yReefCenter + height);
        Point vertice2 = new Point(xReefCenter + width, yReefCenter - height);
        Point vertice3 = new Point(yReefCenter - width, yReefCenter - height);
        Point vertice4 = new Point(yReefCenter - width, yReefCenter + height);
        Point rectangleVertices[] = {vertice1, vertice2, vertice3, vertice4};
        return rectangleVertices;
    }

    public double distance(Point mapPoint, Position reefPosition) {
        return Math.sqrt(Math.pow(mapPoint.getX() - reefPosition.getX(), 2) + Math.pow(mapPoint.getY() - reefPosition.getY(), 2));
    }

    Float calculDeterminant(Point A, Point B, Point mapPoint) {
        float xVectorAB = (float) (B.getX() - A.getX());
        float yVectorAB = (float) (B.getY() - A.getY());
        float xVectorPolygon = (float) (mapPoint.getX() - A.getX());
        float yVectorPolygon = (float) (mapPoint.getY() - A.getY());
        return xVectorAB * yVectorPolygon - yVectorAB * xVectorPolygon;
    }
}
