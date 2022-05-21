package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography;


import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Rectangle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.PathFinding;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Reef;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.VisibleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpotterTest {
    Spotter spotter;
    @BeforeEach
    void setup(){
        spotter = new Spotter();
    }

    @Test
    void createMap() {

        spotter.createMap(50, new Position(-20, -10, 0), new ArrayList<>(List.of(new Checkpoint(new Position(30, 40, 0), new Circle(5)))));

        List<List<Node>> carte = spotter.getMap();

        for (List<Node> subCarte2 : carte) {
            for (Node nodePath : subCarte2) {
                System.out.print(nodePath.getDetail() + " ");
            }
            System.out.println();
        }
    }

    @Test
    void createMap2(){
        Spotter spotter = new Spotter();
        ArrayList<Checkpoint> checkpointArrayList = new ArrayList<>((List.of(new Checkpoint(new Position(100,100,0.0),new Circle(50)))));
        Position shipPosition = new Position(0,0,0.0);
        spotter.createMap(2,shipPosition,checkpointArrayList);

        assertEquals(spotter.map.get(0).get(0).x,0);
        assertEquals(spotter.map.get(0).get(0).y,0);
        assertEquals(spotter.map.get(0).get(0).xReal,625);
        assertEquals(spotter.map.get(0).get(0).yReal,175);
        assertFalse(spotter.map.get(0).get(0).wall);

        assertEquals(spotter.map.get(0).get(1).x,1);
        assertEquals(spotter.map.get(0).get(1).y,0);
        assertEquals(spotter.map.get(0).get(1).xReal,2275);
        assertEquals(spotter.map.get(0).get(1).yReal,175);
        assertFalse(spotter.map.get(0).get(1).wall);

        assertEquals(spotter.map.size(),2);
        assertEquals(spotter.map.get(0).size(),2);
    }

    @Test
    void createMap3(){
        Spotter spotter = new Spotter();
        ArrayList<Checkpoint> checkpointArrayList = new ArrayList<>((List.of(new Checkpoint(new Position(200,200,0.0),new Circle(50)),new Checkpoint(new Position(300,300,0.0),new Circle(45)))));
        Position shipPosition = new Position(100,100,0.0);
        spotter.createMap(4,shipPosition,checkpointArrayList);

        assertEquals(spotter.map.get(1).get(0).x,0);
        assertEquals(spotter.map.get(1).get(0).y,1);
        assertEquals(spotter.map.get(1).get(0).xReal,325);
        assertEquals(spotter.map.get(1).get(0).yReal,400);
        assertFalse(spotter.map.get(1).get(0).wall);

        assertEquals(spotter.map.get(1).get(1).x,1);
        assertEquals(spotter.map.get(1).get(1).y,1);
        assertEquals(spotter.map.get(1).get(1).xReal,1175);
        assertEquals(spotter.map.get(1).get(1).yReal,400);
        assertFalse(spotter.map.get(1).get(1).wall);

    }

    @Test
    void fourextremum() {
        double[] extremun = spotter.fourextremum(new Position(1336, 735, 0), new ArrayList<>(List.of(new Checkpoint(new Position(10006, 3131, 0), new Circle(5)), new Checkpoint(new Position(3552, -2532, 0), new Circle(5)), new Checkpoint(new Position(9719, 514, 0), new Circle(5)), new Checkpoint(new Position(4478, 4980, 0), new Circle(5)))));

        for (int i = 0; i < 4; i++) {
            System.out.println(extremun[i]);
        }
    }

    @Test
    void fourExtremum(){
        Spotter spotter = new Spotter();
        ArrayList<Checkpoint> checkpointArrayList = new ArrayList<>((List.of(new Checkpoint(new Position(100,100,0.0),new Circle(50)))));
        Position shipPosition = new Position(0,0,0.0);
        double extremum[] = spotter.fourextremum(shipPosition,checkpointArrayList);
        assertEquals(extremum[0],-200);
        assertEquals(extremum[1],3100);
        assertEquals(extremum[2],0.0);
        assertEquals(extremum[3],700);
    }

    @Test
    void fourExtremum2(){
        Spotter spotter = new Spotter();
        ArrayList<Checkpoint> checkpointArrayList = new ArrayList<>((List.of(new Checkpoint(new Position(100,100,0.0),new Circle(50)),new Checkpoint(new Position(400,400,0.0),new Circle(45)),new Checkpoint(new Position(100,400,0.0),new Circle(45)))));
        Position shipPosition = new Position(300,300,0.0);
        double extremum[] = spotter.fourextremum(shipPosition,checkpointArrayList);

        assertEquals(extremum[1],checkpointArrayList.get(1).getPosition().getX()+3000);
        assertEquals(extremum[0],checkpointArrayList.get(0).getPosition().getX()-200);
        assertEquals(extremum[2],checkpointArrayList.get(0).getPosition().getX());
        assertEquals(extremum[3],checkpointArrayList.get(1).getPosition().getX()+600);
    }

    @Test
    void updateMap() {

        List<VisibleEntity> visibleEntityList = new ArrayList<>(List.of(new Reef(new Position(3000, 1000, 0), new Rectangle(400, 1800, 0))));  //2800:1900 3200:1900

        spotter.createMap(100, new Position(1336, 735, 0), new ArrayList<>(List.of(new Checkpoint(new Position(10006, 3131, 0), new Circle(5)), new Checkpoint(new Position(3552, -2532, 0), new Circle(5)), new Checkpoint(new Position(9719, 514, 0), new Circle(5)), new Checkpoint(new Position(4478, 4980, 0), new Circle(5)))));
        List<List<Node>> carte = spotter.getMap();
        PathFinding pathFindind = new PathFinding(carte);

        spotter.updateMap(new ArrayList<>(), new Position(1336, 735, 0), new Position(10006, 3131, 0));
        pathFindind.findPath(spotter.getNodeStart(), spotter.getNodeEnd());

        spotter.updateMap(visibleEntityList, new Position(1336, 735, 0), new Position(10006, 3131, 0));
        pathFindind.findPath(spotter.getNodeStart(), spotter.getNodeEnd());


        spotter.getNodeEnd().setColor("A");
        spotter.getNodeStart().setColor("D");

        System.out.println(carte.get(0).get(0).getDetail());

        for (List<Node> subCarte2 : carte) {
            for (Node nodePath : subCarte2) {
                System.out.print(nodePath.getDetail() + nodePath.getColor() + " ");
            }
            System.out.println();
        }

        for (List<Node> subCarte2 : carte) {
            for (Node nodePath : subCarte2) {
                System.out.print(nodePath.getColor());
            }
            System.out.println();
        }

    }

    @Test
    void updateMapFalse(){
        Spotter spotter = new Spotter();
        List<VisibleEntity> visibleEntityList = new ArrayList<>();
        Position shipPosition = new Position(500,500,0.0);
        Position checkpointPosition = new Position(1000,1000,0.0);
        Checkpoint checkpoint = new Checkpoint(checkpointPosition,new Circle(45));
        assertFalse(spotter.updateMap(visibleEntityList,shipPosition,checkpointPosition));
    }

    @Test
    void updateMapTrue(){
        Spotter spotter = new Spotter();
        List<VisibleEntity> visibleEntityList = new ArrayList<>();
        VisibleEntity visibleEntityReef = new Reef(new Position(600,600,0.0),new Circle(50));
        VisibleEntity visibleEntityReef2 = new Reef(new Position(800,800,0.0),new Circle(100));
        visibleEntityList.add(visibleEntityReef);
        visibleEntityList.add(visibleEntityReef2);
        Position shipPosition = new Position(500,500,0.0);
        Position checkpointPosition = new Position(1000,1000,0.0);
        Checkpoint checkpoint = new Checkpoint(checkpointPosition,new Circle(45));
        assertTrue(spotter.updateMap(visibleEntityList,shipPosition,checkpointPosition));
    }

    @Test
    void findReef(){
        List<VisibleEntity> visibleEntityReef = new ArrayList<>();
        VisibleEntity visibleEntity = new Reef(new Position(600,600,0.0),new Circle(50));
        visibleEntityReef.add(visibleEntity);
        Spotter spotter = new Spotter();
        List<Reef> reefList = new ArrayList<>(List.of(new Reef(new Position(700,700,0.0),new Circle(90))));
        boolean modifier = false;
        assertTrue(spotter.findReefInMap(visibleEntityReef,reefList,modifier,new CollisionDetector2()));
    }

    @Test
    void findReef2(){
        List<VisibleEntity> visibleEntityReef = new ArrayList<>();
        VisibleEntity visibleEntity = new Reef(new Position(600,600,0.0),new Circle(50));
        visibleEntityReef.add(visibleEntity);
        Spotter spotter = new Spotter();
        List<Reef> reefList = new ArrayList<>(List.of(new Reef(new Position(700,700,0.0),new Circle(90))));
        boolean modifier = false;
        CollisionDetector2 collisionDetector2 = new CollisionDetector2();
        spotter.updateMapWithReefNotAlreadyFound(reefList,modifier,collisionDetector2,reefList.get(0));
        assertTrue(spotter.findReefInMap(visibleEntityReef,reefList,modifier,collisionDetector2));
    }

    @Test
    void updateMapWithNewReefFalse(){
        Spotter spotter = new Spotter();
        List<Reef> newReefs = new ArrayList<>();
        boolean mofifier = false;
        CollisionDetector2 collisionDetector2 = new CollisionDetector2();
        Reef reef = new Reef(new Position(1000,1000,0.0),new Circle(50));
        spotter.reefs.add(reef);
        assertFalse(spotter.updateMapWithReefNotAlreadyFound(newReefs,mofifier,collisionDetector2,reef));
    }

    @Test
    void updateMapWithNewReefTrue(){
        Spotter spotter = new Spotter();
        List<Reef> newReefs = new ArrayList<>();
        boolean mofifier = false;
        CollisionDetector2 collisionDetector2 = new CollisionDetector2();
        Reef reef = new Reef(new Position(1000,1000,0.0),new Circle(50));
        Reef newReef = new Reef(new Position(1200,1200,0.0),new Circle(50));
        spotter.reefs.add(reef);
        assertTrue(spotter.updateMapWithReefNotAlreadyFound(newReefs,mofifier,collisionDetector2,newReef));
    }


    @Test
    void closestNodeFromPosition(){
        Spotter spotter = new Spotter();
        ArrayList<Checkpoint> checkpointArrayList = new ArrayList<>((List.of(new Checkpoint(new Position(100,100,0.0),new Circle(50)))));
        Position shipPosition = new Position(100,100,0.0);
        spotter.createMap(2,shipPosition,checkpointArrayList);
        assertEquals(spotter.map.get(0).get(0),spotter.closetNodeFromPosition(shipPosition));
    }

    @Test
    void closestNodeFromPosition2(){
        Spotter spotter = new Spotter();
        ArrayList<Checkpoint> checkpointArrayList = new ArrayList<>((List.of(new Checkpoint(new Position(100,100,0.0),new Circle(50)))));
        Position shipPosition = new Position(100,100,0.0);
        spotter.createMap(200,shipPosition,checkpointArrayList);
        assertEquals(spotter.closetNodeFromPosition(shipPosition),spotter.map.get(0).get(12));
    }
}