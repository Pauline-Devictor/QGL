package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.collisions.DetectCollisions;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Point;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Polygon;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Rectangle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Reef;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionDetectorTest {

    CollisionDetector collisionDetector;

    @Test
    void calculDeterminant(){
        float xVectorTriangle = 5;
        float yVectorTriangle = 4;
        float xVectorPolygon = 6;
        float yVectorPolygon = 6;
        Point A = new Point(4,1);
        Point B = new Point(9,5);
        Point polygonVertice = new Point(10,7);
        float determinant = 6;
        collisionDetector = new CollisionDetector();
        assertEquals(determinant,collisionDetector.calculDeterminant(A,B,polygonVertice));
    }

    @Test
    void distance(){
        Point safetyPoint = new Point(100,200);
        Position reefPoint = new Position(300,200,0.0);
        collisionDetector = new CollisionDetector();
        assertEquals(200,collisionDetector.distance(safetyPoint,reefPoint));
    }

    @Test
    void getRectangleVertices(){
        Position reefCenter = new Position(500,500,0.0);
        Rectangle rectangle = new Rectangle(50,100,0.0);
        Reef rectangleReef = new Reef(reefCenter,rectangle);
        collisionDetector = new CollisionDetector();
        Point rectangleVertices1 = new Point(550,525);
        Point rectangleVertices2 = new Point(550,475);
        Point rectangleVertices3 = new Point(450,525);
        Point rectangleVertices4 = new Point(450,475);
        assertEquals(rectangleVertices1.getX(),collisionDetector.getRectangleVertices(rectangleReef)[0].getX());
        assertEquals(rectangleVertices1.getY(),collisionDetector.getRectangleVertices(rectangleReef)[0].getY());
        assertEquals(rectangleVertices2.getX(),collisionDetector.getRectangleVertices(rectangleReef)[1].getX());
        assertEquals(rectangleVertices2.getY(),collisionDetector.getRectangleVertices(rectangleReef)[1].getY());
        assertEquals(rectangleVertices3.getX(),collisionDetector.getRectangleVertices(rectangleReef)[3].getX());
        assertEquals(rectangleVertices3.getY(),collisionDetector.getRectangleVertices(rectangleReef)[3].getY());
        assertEquals(rectangleVertices4.getX(),collisionDetector.getRectangleVertices(rectangleReef)[2].getX());
        assertEquals(rectangleVertices4.getY(),collisionDetector.getRectangleVertices(rectangleReef)[2].getY());
    }

    @Test
    void collisionWithCircleFalse(){
        Reef reef = new Reef(new Position(300,300,0.0),new Circle(50));
        Point vertice1 = new Point(200,100);
        Point vertice2 = new Point(100,200);
        Point vertice3 = new Point(180,220);
        collisionDetector = new CollisionDetector();
        assertFalse(collisionDetector.collisionWithCircle(vertice1,reef));
        assertFalse(collisionDetector.collisionWithCircle(vertice2,reef));
        assertFalse(collisionDetector.collisionWithCircle(vertice3,reef));
    }
}
