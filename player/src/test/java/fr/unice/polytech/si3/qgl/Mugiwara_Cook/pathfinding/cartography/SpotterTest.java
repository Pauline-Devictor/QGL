package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Rectangle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Reef;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.VisibleEntity;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Wind;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Deck;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import org.junit.jupiter.api.BeforeEach;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Reef;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.VisibleEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpotterTest {

    @Test
    void createMap() {
        Spotter spotter = new Spotter();

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
    void fourextremum() {
        Spotter spotter = new Spotter();
        double[] extremun = spotter.fourextremum(new Position(1336, 735, 0), new ArrayList<Checkpoint>(List.of(new Checkpoint(new Position(10006, 3131, 0), new Circle(5)),new Checkpoint(new Position(3552, -2532, 0), new Circle(5)),new Checkpoint(new Position(9719, 514, 0), new Circle(5)),new Checkpoint(new Position(4478, 4980, 0), new Circle(5)))));

        for (int i = 0; i < 4; i++) {
            System.out.println(extremun[i]);
        }
    }

    @Test
    void updateMap() {

        List<VisibleEntity> visibleEntityList = new ArrayList<>(List.of(new Reef(new Position(3044, 957, 0), new Rectangle(400, 1800, 0))));
        Spotter spotter = new Spotter();

        spotter.createMap(100, new Position(500, 735, 0), new ArrayList<Checkpoint>(List.of(new Checkpoint(new Position(10006, 3131, 0), new Circle(5)),new Checkpoint(new Position(3552, -2532, 0), new Circle(5)),new Checkpoint(new Position(9719, 514, 0), new Circle(5)),new Checkpoint(new Position(4478, 4980, 0), new Circle(5)))));

        System.out.println(spotter.updateMap(visibleEntityList, new Position(3044, 957, 0), new Position(10006, 3131, 0)));

        spotter.getNodeEnd().setColor("A");
        spotter.getNodeStart().setColor("D");

//        visibleEntityList = new ArrayList<>(List.of(new Reef(new Position(5,0,0),new Rectangle(5,5,0))));

//        System.out.println(spotter.updateMap(visibleEntityList));


        List<List<Node>> carte = spotter.getMap();

        System.out.println("TEST2");
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


    /**
    NextRound nextRound;
    Checkpoint currentCheckpoint;
    VisibleEntity[] visibleEntities;
    Spotter spotter;

    @BeforeEach
    void setUp(){
        List<Equipment> entities = new ArrayList<>();
        Ship ship = new Ship(100,new Position(200,200,90),"crashTest",new Deck(5,10),entities,new Rectangle(10,20,90));
        Wind wind = new Wind(90,5);
        Reef reef1 = new Reef(new Position(500,500,0),new Circle(50));
        Reef reef2 = new Reef(new Position(350,150,0),new Circle(50));

        currentCheckpoint = new Checkpoint(new Position(400,400,0),new Circle(60));
        visibleEntities = new VisibleEntity[]{reef1, reef2};
        nextRound = new NextRound(ship,wind,visibleEntities);
        spotter = new Spotter(new NextRound(ship,wind,visibleEntities),currentCheckpoint);
    }

    @Test
    void getMapHeightTest(){
        double height = Math.abs(200-400)*1.2;
        assertEquals(height,spotter.getMapHeight());
    }

    @Test
    void getMapWidthTest(){
        double width = Math.abs(200-400)*1.2;
        assertEquals(width,spotter.getMapWidth());
    }

    @Test
    void buildMapFalse(){
        List<List<Node>> nodeList = spotter.buildMap(100);
        assertFalse(nodeList.get(0).get(0).wall);
        assertFalse(nodeList.get(0).get(1).wall);
        assertFalse(nodeList.get(1).get(0).wall);
        assertFalse(nodeList.get(1).get(1).wall);
    }

    @Test
    void buildMapTrue(){
        Reef reefObstacle = new Reef(new Position(250,250,0.0),new Circle(10));
        Reef reefObstacle1 = new Reef(new Position(345,350,0.0),new Circle(10));
        visibleEntities = new VisibleEntity[]{reefObstacle,reefObstacle1};
        List<Equipment> entities = new ArrayList<>();
        Ship ship = new Ship(100,new Position(200,200,90),"crashTest",new Deck(5,10),entities,new Rectangle(10,20,90));
        Wind wind = new Wind(90,5);
        currentCheckpoint = new Checkpoint(new Position(400,400,0),new Circle(60));
        nextRound = new NextRound(ship,wind,visibleEntities);
        spotter = new Spotter(new NextRound(ship,wind,visibleEntities),currentCheckpoint);

        List<List<Node>> nodeList = spotter.buildMap(100);
        assertTrue(nodeList.get(0).get(0).wall);
        assertFalse(nodeList.get(0).get(1).wall);
        assertFalse(nodeList.get(1).get(0).wall);
        assertTrue(nodeList.get(1).get(1).wall);
    }

    @Test
    void buildMapTrue2() {
        Reef reefObstacle = new Reef(new Position(325, 225, 0.0), new Circle(50));
        visibleEntities = new VisibleEntity[]{reefObstacle};
        List<Equipment> entities = new ArrayList<>();
        Ship ship = new Ship(100, new Position(200, 200, 90), "crashTest", new Deck(5, 10), entities, new Rectangle(10, 20, 90));
        Wind wind = new Wind(90, 5);
        currentCheckpoint = new Checkpoint(new Position(400, 400, 0), new Circle(60));
        nextRound = new NextRound(ship, wind, visibleEntities);
        spotter = new Spotter(new NextRound(ship, wind, visibleEntities), currentCheckpoint);

        List<List<Node>> nodeList = spotter.buildMap(50);
        assertFalse(nodeList.get(0).get(0).wall);
        assertTrue(nodeList.get(0).get(1).wall);
        assertTrue(nodeList.get(0).get(2).wall);
        assertTrue(nodeList.get(0).get(3).wall);
    }*/

}