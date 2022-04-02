/**

package fr.unice.polytech.si3.qgl.Mugiwara_Cook.collisions;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Point;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Polygon;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Rectangle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Reef;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Deck;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DetectCollisionsTest {

    DetectCollisions detectCollisions;
    Ship ship;
    Reef reef;

    @BeforeEach
    void setUp(){
        List<Equipment> entities = new ArrayList<>();
        this.ship = new Ship(100,new Position(200,200,90),"crashTest",new Deck(5,10),entities,new Rectangle(10,20,90));
    }

    @Test
    void collisionWithCircleTrue(){
        reef = new Reef(new Position(300,300,0.0),new Circle(200));
        Point vertice1 = new Point(200,100);
        Point vertice2 = new Point(100,200);
        Point vertice3 = new Point(180,220);
        Point safetyTrianglePoints[] = {vertice1,vertice2,vertice3};
        Polygon safetyTriangle = new Polygon(0.0,safetyTrianglePoints);
        detectCollisions = new DetectCollisions(reef,ship);
        assertTrue(detectCollisions.collisionWithCircle(safetyTriangle,200));
    }

    @Test
    void collisionWithCircleTrue2(){
        reef = new Reef(new Position(300,300,0.0),new Circle(200));
        Point vertice1 = new Point(300,200);
        Point vertice2 = new Point(100,200);
        Point vertice3 = new Point(200,300);
        Point safetyTrianglePoints[] = {vertice1,vertice2,vertice3};
        Polygon safetyTriangle = new Polygon(0.0,safetyTrianglePoints);
        detectCollisions = new DetectCollisions(reef,ship);
        assertTrue(detectCollisions.collisionWithCircle(safetyTriangle,200));
    }

    @Test
    void collisionWithCircleFalse(){
        reef = new Reef(new Position(300,300,0.0),new Circle(150));
        Point vertice1 = new Point(200,100);
        Point vertice2 = new Point(100,200);
        Point vertice3 = new Point(200,200);
        Point safetyTrianglePoints[] = {vertice1,vertice2,vertice3};
        Polygon safetyTriangle = new Polygon(0.0,safetyTrianglePoints);
        detectCollisions = new DetectCollisions(reef,ship);
        assertTrue(detectCollisions.collisionWithCircle(safetyTriangle,150));
    }

    @Test
    void findCLosestPointFromPolygon(){
        Point safetyTrianglePoint1 = new Point(200,100);
        Point safetyTrianglePoint2 = new Point(100,200);
        Point safetyTrianglePoint3 = new Point(200,200);
        Point safetyTrianglePoints[] = {safetyTrianglePoint1,safetyTrianglePoint2,safetyTrianglePoint3};
        Polygon safetyTriangle = new Polygon(0.0,safetyTrianglePoints);
        Point vertice1 = new Point(800,700);
        Point vertice2 = new Point(550,600);
        Point vertice3 = new Point(300,300);
        Point vertice4 = new Point(300,350);
        Point vertice5 = new Point(1000,1000);
        Point vertices[] = {vertice1,vertice2,vertice3,vertice4,vertice5};
        detectCollisions = new DetectCollisions(reef,ship);
        assertEquals(vertice3,detectCollisions.findCLosestPointFromPolygonOrRectangle(vertices,safetyTriangle));
    }

    @Test
    void findCLosestPointFromPolygon2(){
        Point safetyTrianglePoint1 = new Point(200,200);
        Point safetyTrianglePoint2 = new Point(100,200);
        Point safetyTrianglePoint3 = new Point(200,100);
        Point safetyTrianglePoints[] = {safetyTrianglePoint1,safetyTrianglePoint2,safetyTrianglePoint3};
        Polygon safetyTriangle = new Polygon(0.0,safetyTrianglePoints);
        Point vertice1 = new Point(800,700);
        Point vertice2 = new Point(550,600);
        Point vertice3 = new Point(300,350);
        Point vertice4 = new Point(200,300);
        Point vertice5 = new Point(300,200);
        Point vertices[] = {vertice1,vertice2,vertice3,vertice4,vertice5};
        detectCollisions = new DetectCollisions(reef,ship);
        assertEquals(vertice4,detectCollisions.findCLosestPointFromPolygonOrRectangle(vertices,safetyTriangle));
    }

    @Test
    void calculDeterminant(){
        float xVectorTriangle = 5;
        float yVectorTriangle = 4;
        float xVectorPolygon = 6;
        float yVectorPolygon = 6;
        Point A = new Point(4,1);
        Point B = new Point(9,5);
        Point polygonVertice = new Point(10,7);
        float determinant = 6; // yVectorTriangle*xVectorPolygon - xVectorTriangle * yVectorPolygon;
        detectCollisions = new DetectCollisions(reef,ship);
        assertEquals(determinant,detectCollisions.calculDeterminant(A,B,polygonVertice));
    }

    @Test
    void distance(){
        Point safetyPoint = new Point(100,200);
        Point reefPoint = new Point(300,200);
        detectCollisions = new DetectCollisions(reef,ship);
        assertEquals(200,detectCollisions.distance(safetyPoint,reefPoint));
    }

    @Test
    void getRectangleVertices(){
        Position reefCenter = new Position(500,500,0.0);
        Rectangle rectangle = new Rectangle(50,100,0.0);
        Reef rectangleReef = new Reef(reefCenter,rectangle);
        detectCollisions = new DetectCollisions(rectangleReef,ship);
        Point rectangleVertices1 = new Point(550,525);
        Point rectangleVertices2 = new Point(550,475);
        Point rectangleVertices3 = new Point(450,525);
        Point rectangleVertices4 = new Point(450,475);
        assertEquals(rectangleVertices1.getX(),detectCollisions.getRectangleVertices()[0].getX());
        assertEquals(rectangleVertices1.getY(),detectCollisions.getRectangleVertices()[0].getY());
        assertEquals(rectangleVertices2.getX(),detectCollisions.getRectangleVertices()[1].getX());
        assertEquals(rectangleVertices2.getY(),detectCollisions.getRectangleVertices()[1].getY());
        assertEquals(rectangleVertices3.getX(),detectCollisions.getRectangleVertices()[2].getX());
        assertEquals(rectangleVertices3.getY(),detectCollisions.getRectangleVertices()[2].getY());
        assertEquals(rectangleVertices4.getX(),detectCollisions.getRectangleVertices()[3].getX());
        assertEquals(rectangleVertices4.getY(),detectCollisions.getRectangleVertices()[3].getY());
    }



    @Test
    void collisionWithPolygonOrRectangleFalse(){
        Point safetyTrianglePoint1 = new Point(400,200);
        Point safetyTrianglePoint2 = new Point(400,300);
        Point safetyTrianglePoint3 = new Point(500,200);
        Point safetyTrianglePoints[] = {safetyTrianglePoint1,safetyTrianglePoint2,safetyTrianglePoint3};
        Polygon safetyTriangle = new Polygon(0.0,safetyTrianglePoints);
        Point vertice1 = new Point(100,100);
        Point vertice2 = new Point(250,100);
        Point vertice3 = new Point(100,300);
        Point vertice4 = new Point(400,400);
        Point vertices[] = {vertice1,vertice2,vertice3,vertice4};
        detectCollisions = new DetectCollisions(reef,ship);
        assertFalse(detectCollisions.collisionWithPolygonOrRectangle(safetyTriangle,vertices)); // ATTENTION A CE QUE CE SOIT LA BONNE METHODE ICI
    }


    @Test
    void collisionWithSafetyTrianglePointFalse(){
        Point safetyTrianglePoint1 = new Point(300,300);
        Point safetyTrianglePoint2 = new Point(200,400);
        Point safetyTrianglePoint3 = new Point(300,400);
        Point safetyTrianglePoints[] = {safetyTrianglePoint1,safetyTrianglePoint2,safetyTrianglePoint3};
        Polygon safetyTriangle = new Polygon(0.0,safetyTrianglePoints);
        Point vertice1 = new Point(300,100);
        Point vertice2 = new Point(100,100);
        Point vertice3 = new Point(100,300);
        Point vertices[] = {vertice1,vertice2,vertice3};
        detectCollisions = new DetectCollisions(reef,ship);
        assertFalse(detectCollisions.collisionWithSafetyTrianglePoint(safetyTrianglePoint1,vertices));
    }

    @Test
    void collisionWithSafetyTrianglePointTrue(){
        Point safetyTrianglePoint1 = new Point(300,300);
        Point safetyTrianglePoint2 = new Point(200,400);
        Point safetyTrianglePoint3 = new Point(300,400);
        Point safetyTrianglePoints[] = {safetyTrianglePoint1,safetyTrianglePoint2,safetyTrianglePoint3};
        Polygon safetyTriangle = new Polygon(0.0,safetyTrianglePoints);
        Point vertice1 = new Point(300,100);
        Point vertice2 = new Point(100,100);
        Point vertice3 = new Point(100,300);
        Point vertice4 = new Point(400,400);
        Point vertices[] = {vertice1,vertice2,vertice3,vertice4};
        detectCollisions = new DetectCollisions(reef,ship);
        assertTrue(detectCollisions.collisionWithSafetyTrianglePoint(safetyTrianglePoint1,vertices));
    }

    @Test
    void collisionWithPolygonOrRectangleTrue(){
        Point safetyTrianglePoint1 = new Point(300,300); //pt en contact
        Point safetyTrianglePoint2 = new Point(200,400);
        Point safetyTrianglePoint3 = new Point(300,400);
        Point safetyTrianglePoints[] = {safetyTrianglePoint1,safetyTrianglePoint2,safetyTrianglePoint3};
        Polygon safetyTriangle = new Polygon(0.0,safetyTrianglePoints);
        Point vertice1 = new Point(300,100);
        Point vertice2 = new Point(100,100);
        Point vertice3 = new Point(100,300);
        Point vertice4 = new Point(400,400);
        Point vertices[] = {vertice1,vertice2,vertice3,vertice4};
        detectCollisions = new DetectCollisions(reef,ship);
        assertTrue(detectCollisions.collisionWithPolygonOrRectangle(safetyTriangle,vertices));
    }
}

 */