package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography;


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

    /**
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
     */

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

    @Test
    void collisionWithCircleOnBorder(){
        Reef reef = new Reef(new Position(500,500,0.0),new Circle(100));
        Point point1 =  new Point(400,500);
        Point point2 =  new Point(600,500);
        Point point3 =  new Point(500,400);
        Point point4 =  new Point(500,600);
        collisionDetector = new CollisionDetector();
        assertTrue(collisionDetector.collisionWithCircle(point1,reef));
        assertTrue(collisionDetector.collisionWithCircle(point2,reef));
        assertTrue(collisionDetector.collisionWithCircle(point3,reef));
        assertTrue(collisionDetector.collisionWithCircle(point4,reef));
    }

    @Test
    void collisionWithCircleTrue(){
        Reef reef = new Reef(new Position(300,300,0.0),new Circle(100));
        Point point1 =  new Point(350,300);
        Point point2 =  new Point(300,399);
        collisionDetector = new CollisionDetector();
        assertTrue(collisionDetector.collisionWithCircle(point1,reef));
        assertTrue(collisionDetector.collisionWithCircle(point2,reef));
    }

    @Test
    void collisionWithRectangleTrue(){
        Reef reef = new Reef(new Position(400,400,0.0),new Rectangle(10,20,0.0));
        Point point1 =  new Point(399,399);
        Point point2 =  new Point(395,400);
        Point point3 =  new Point(400,390);
        collisionDetector = new CollisionDetector();
        assertTrue(collisionDetector.collisionWithRectangle(point1,reef));
        assertTrue(collisionDetector.collisionWithRectangle(point2,reef));
        assertTrue(collisionDetector.collisionWithRectangle(point3,reef));
    }

    @Test
    void collisionWithRectangleFalse(){
        Reef reef = new Reef(new Position(400,400,0.0),new Rectangle(10,20,0.0));
        Point point1 =  new Point(389,400);
        Point point2 =  new Point(101,500);
        Point point3 =  new Point(400,389);
        collisionDetector = new CollisionDetector();
        assertFalse(collisionDetector.collisionWithRectangle(point1,reef));
        assertFalse(collisionDetector.collisionWithRectangle(point1,reef));
        assertFalse(collisionDetector.collisionWithRectangle(point1,reef));
    }

    @Test
    void detectCollisionCircleTrue(){
        Reef reef = new Reef(new Position(400,400,0.0),new Circle(100));
        Point point1 =  new Point(400,500);
        Point point2 =  new Point(400,400);
        Point point3 =  new Point(499,400);
        Point point4 =  new Point(420,457);
        collisionDetector = new CollisionDetector();
        assertTrue(collisionDetector.detectCollision(point1,reef));
        assertTrue(collisionDetector.detectCollision(point2,reef));
        assertTrue(collisionDetector.detectCollision(point3,reef));
        assertTrue(collisionDetector.detectCollision(point4,reef));
    }

    @Test
    void detectCollisionCircleFalse(){
        Reef reef = new Reef(new Position(400,400,0.0),new Circle(100));
        Point point1 =  new Point(500,500);
        Point point2 =  new Point(350,299);
        Point point3 =  new Point(199,402);
        Point point4 =  new Point(501,500);
        collisionDetector = new CollisionDetector();
        assertFalse(collisionDetector.detectCollision(point1,reef));
        assertFalse(collisionDetector.detectCollision(point2,reef));
        assertFalse(collisionDetector.detectCollision(point3,reef));
        assertFalse(collisionDetector.detectCollision(point4,reef));
    }

    @Test
    void detectCollisionRectangleFalse(){
        Reef reef = new Reef(new Position(500,500,0.0),new Rectangle(20,40,0.0));
        Point point1 =  new Point(500,541);
        Point point2 =  new Point(521,500);
        Point point3 =  new Point(479,500);
        Point point4 =  new Point(500,459);
        Point point5 =  new Point(590,205);
        Point point6 =  new Point(190,800);
        collisionDetector = new CollisionDetector();
        assertFalse(collisionDetector.detectCollision(point1,reef));
        assertFalse(collisionDetector.detectCollision(point2,reef));
        assertFalse(collisionDetector.detectCollision(point3,reef));
        assertFalse(collisionDetector.detectCollision(point4,reef));
        assertFalse(collisionDetector.detectCollision(point5,reef));
        assertFalse(collisionDetector.detectCollision(point6,reef));
    }


    @Test
    void detectCollisionRectangleTrue(){
        Reef reef = new Reef(new Position(500,500,0.0),new Rectangle(20,40,0.0));
        Point point1 =  new Point(500,500);
        Point point2 =  new Point(510,500);
        Point point3 =  new Point(490,500);
        Point point4 =  new Point(500,480);
        Point point5 =  new Point(490,490);
        Point point6 =  new Point(510,520);
        Point point7 =  new Point(490,480);
        collisionDetector = new CollisionDetector();
        assertTrue(collisionDetector.detectCollision(point1,reef));
        assertTrue(collisionDetector.detectCollision(point2,reef));
        assertTrue(collisionDetector.detectCollision(point3,reef));
        assertTrue(collisionDetector.detectCollision(point4,reef));
        assertTrue(collisionDetector.detectCollision(point5,reef));
        assertTrue(collisionDetector.detectCollision(point6,reef));
        assertTrue(collisionDetector.detectCollision(point7,reef));
    }

    @Test
    void detectCollisionPolygonFalse(){
        Point point1 =  new Point(400,399);
        Point point2 =  new Point(600,601);
        Point point3 =  new Point(350,200);
        Point point4 =  new Point(800,500);

        Point vertice1 =  new Point(500,500);
        Point vertice2 =  new Point(400,500);
        Point vertice3 =  new Point(600,500);
        Point vertice4 =  new Point(600,600);
        Point vertice5 =  new Point(400,400);
        Point[] vertices = {vertice1,vertice2,vertice3,vertice4,vertice5};
        Reef reef = new Reef(new Position(500,500,0.0),new Polygon(0.0,vertices));
        collisionDetector = new CollisionDetector();
        assertFalse(collisionDetector.collisionWithPolygon(point1,reef));
        assertFalse(collisionDetector.collisionWithPolygon(point2,reef));
        assertFalse(collisionDetector.collisionWithPolygon(point3,reef));
        assertFalse(collisionDetector.collisionWithPolygon(point4,reef));
    }
    /**
    @Test
    void dectectCollsisionPolygonTrue(){
        Point point1 =  new Point(500,500);
        Point point2 =  new Point(430,500);
        Point point3 =  new Point(590,590);

        Point vertice1 =  new Point(500,500);
        Point vertice2 =  new Point(400,500);
        Point vertice3 =  new Point(600,500);
        Point vertice4 =  new Point(600,600);
        Point vertice5 =  new Point(400,400);
        Point vertice6 = new Point(500,490);
        Point[] vertices = {vertice1,vertice2,vertice3,vertice4,vertice5};
        Reef reef = new Reef(new Position(500,500,0.0),new Polygon(0.0,vertices));
        collisionDetector = new CollisionDetector();
        assertTrue(collisionDetector.detectCollision(point1,reef));
        assertTrue(collisionDetector.collisionWithPolygon(point2,reef));
        assertTrue(collisionDetector.collisionWithPolygon(point3,reef));
    }
    */

}
