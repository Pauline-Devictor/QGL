package fr.unice.polytech.si3.qgl.Mugiwara_Cook.collisions;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Point;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Polygon;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Rectangle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Reef;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Deck;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AvoidCollisionsTest {


    AvoidCollisions avoidCollisions;
    Ship ship;
    Checkpoint currentCheckpoint;


    @BeforeEach
    void setUp(){
        this.currentCheckpoint = new Checkpoint(new Position(1000,1000,0.0),new Circle(60));
        List<Equipment> entities = new ArrayList<>();
        this.ship = new Ship(100,new Position(300,100,90),"crashTest",new Deck(5,10),entities,new Rectangle(10,20,90));
    }

    @Test
    void isOnTheRightOfShip(){
        Reef reef = new Reef(new Position(300,100,0.0),new Circle(100));
        avoidCollisions = new AvoidCollisions(reef,ship,currentCheckpoint);
        Position reefCenterLeft = new Position(700,200,0.0);
        Position reefCenterLeft2 = new Position(1100,500,0.0);
        Position reefCenterRight= new Position(100,1000,0.0);
        Position reefCenterRight2 = new Position(999,999,0.0);
        Position reefCenterRight3 = new Position(350,300,0.0);
        Position reefCenterLeft3 = new Position(450,100,0.0);
        assertFalse(avoidCollisions.isPointOnTheRightOfShip(reefCenterLeft));
        assertFalse(avoidCollisions.isPointOnTheRightOfShip(reefCenterLeft2));
        assertFalse(avoidCollisions.isPointOnTheRightOfShip(reefCenterLeft3));
        assertTrue(avoidCollisions.isPointOnTheRightOfShip(reefCenterRight));
        assertTrue(avoidCollisions.isPointOnTheRightOfShip(reefCenterRight2));
        assertTrue(avoidCollisions.isPointOnTheRightOfShip(reefCenterRight3));
    }


    @Test
    void calculateSymetricalPosition(){
        Position reefCenter = new Position(500,500,0.0);
        Position potentialCheckPointCenter = new Position(600,600,0.0);
        Reef reef = new Reef(reefCenter,new Circle(50));
        avoidCollisions = new AvoidCollisions(reef,ship,currentCheckpoint);
        Position symetricalPoisitionExpected = avoidCollisions.calculateSymetricalPosition(reefCenter,potentialCheckPointCenter);
        assertEquals(400,symetricalPoisitionExpected.getX());
        assertEquals(400,symetricalPoisitionExpected.getY());
    }

    @Test
    void calculateSymetricalPosition2(){
        Position reefCenter = new Position(400,500,0.0);
        Position potentialCheckPointCenter = new Position(300,300,0.0);
        Reef reef = new Reef(reefCenter,new Circle(50));
        avoidCollisions = new AvoidCollisions(reef,ship,currentCheckpoint);
        Position symetricalPoisitionExpected = avoidCollisions.calculateSymetricalPosition(reefCenter,potentialCheckPointCenter);
        assertEquals(500,symetricalPoisitionExpected.getX());
        assertEquals(700,symetricalPoisitionExpected.getY());
    }

    @Test
    void findFurthestPointFromPolygon(){
        Point vertice1 = new Point(200,200);
        Point vertice2 = new Point(200,400);
        Point vertice3 = new Point(400,400);
        Point vertice4 = new Point(550,300);
        Point vertice5 = new Point(400,200);
        Point vertices[] = {vertice1,vertice2,vertice3,vertice4,vertice5};

        Polygon reefShape = new Polygon(0.0,vertices);
        Position reefCenter = new Position(350,300,0.0);
        Reef reef = new Reef(reefCenter,reefShape);
        avoidCollisions = new AvoidCollisions(reef,ship,currentCheckpoint);

        assertEquals(vertice4,avoidCollisions.findFurthestPointFromPolygon());
    }

    @Test
    void findFurthestPointFromPolygon2(){
        Point vertice1 = new Point(100,150);
        Point vertice2 = new Point(100,300);
        Point vertice3 = new Point(200,400);
        Point vertice4 = new Point(320,400);
        Point vertice5 = new Point(300,200);
        Point vertices[] = {vertice1,vertice2,vertice3,vertice4,vertice5};

        Polygon reefShape = new Polygon(0.0,vertices);
        Position reefCenter = new Position(280,400,0.0);
        Reef reef = new Reef(reefCenter,reefShape);
        Checkpoint checkpoint = new Checkpoint(new Position(100,500,0.0),new Circle(20));
        avoidCollisions = new AvoidCollisions(reef,ship,currentCheckpoint);

        assertEquals(vertice1,avoidCollisions.findFurthestPointFromPolygon());
    }

    @Test
    void determineDistance(){
        Point vertice1 = new Point(100,150);
        Point vertice2 = new Point(100,300);
        Point vertice3 = new Point(200,400);
        Point vertice4 = new Point(320,400);
        Point vertices[] = {vertice1,vertice2,vertice3,vertice4};

        Position reefCenter = new Position(280,300,0.0);
        Reef reefPolygon = new Reef(reefCenter,new Polygon(0.0,vertices));
        Reef reefCircle = new Reef(reefCenter, new Circle(100));
        Reef reefRectangle = new Reef(reefCenter,new Rectangle(20,40,0.0));

        AvoidCollisions avoidCollisionsCircle = new AvoidCollisions(reefCircle,ship,currentCheckpoint);
        AvoidCollisions avoidCollisionsRectangle = new AvoidCollisions(reefRectangle,ship,currentCheckpoint);
        AvoidCollisions avoidCollisionsPolygon = new AvoidCollisions(reefPolygon,ship,currentCheckpoint);

        double polygonDistance = Math.sqrt(Math.pow(reefCenter.getX() - vertice1.getX(),2) + Math.pow(reefCenter.getY() - vertice1.getY(),2));

        assertEquals(140,avoidCollisionsCircle.determineDistance(reefCenter.getX(),reefCenter.getY()));
        assertEquals(20 + (3/2)*40,avoidCollisionsRectangle.determineDistance(reefCenter.getX(),reefCenter.getY()));
        assertEquals(polygonDistance,avoidCollisionsPolygon.determineDistance(reefCenter.getX(),reefCenter.getY()));
    }

    /**
    @Test
    void resolveEquation(){
        double A = 1+(double)50/30;
        System.out.println("A : " + A);
        double B = -2*300*(1+(double)Math.pow(5/3,2));
        System.out.println("B : " + B);
        double C = 2500 + 900 + Math.pow(300 - 300*(5/3),2) - 2*300*(300 - 300*(5/3)) - Math.pow(160,2);
        System.out.println("C : " + C);
        double delta = Math.pow(B,2) - 4*A*C;
        System.out.println("delta : "+ delta);

        double y = (-B+Math.sqrt(delta))/2*A;
        double x = 50 + (y - 30)*5/3;
        avoidCollisions = new AvoidCollisions(new Reef(new Position(300,300,0.0),new Circle(20)),ship,currentCheckpoint);
        Position solution = avoidCollisions.resolveEquation(50,30,300,300,160);
        assertEquals(x,solution.getX());
        assertEquals(y,solution.getY());
    }
    */

    @Test
    void determineRightPosition(){
        Position potentialPosition = new Position(450,100,0.0);
        Position reefCenter = new Position(400,200,0.0);
        Reef reef = new Reef(reefCenter,new Circle(100));
        Checkpoint checkpoint = new Checkpoint(new Position(600,200,0.0),new Circle(100));
        avoidCollisions = new AvoidCollisions(reef,ship,checkpoint);
        Position result = avoidCollisions.determineRightPosition(potentialPosition.getX(),potentialPosition.getY());
        assertEquals(potentialPosition.getX(),result.getX());
        assertEquals(potentialPosition.getY(),result.getY());
    }

    @Test
    void determineRightPosition2(){
        Position potentialPosition = new Position(300,300,0.0);
        Position reefCenter = new Position(400,200,0.0);
        Reef reef = new Reef(reefCenter,new Circle(100));
        Checkpoint checkpoint = new Checkpoint(new Position(600,200,0.0),new Circle(100));
        avoidCollisions = new AvoidCollisions(reef,ship,checkpoint);
        Position result = avoidCollisions.determineRightPosition(potentialPosition.getX(),potentialPosition.getY());
        assertEquals(500,result.getX());
        assertEquals(100,result.getY());
    }

    @Test
    void calculateNewCheckpointPositionCircle(){
        Reef reefCircle = new Reef(new Position(400,300,0.0),new Circle(60));
        Checkpoint checkpoint = new Checkpoint(new Position(400,300,0.0),new Circle(25));
        avoidCollisions = new AvoidCollisions(reefCircle,ship,checkpoint);
    }

}
