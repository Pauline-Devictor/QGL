package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography;


import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Rectangle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.PathFindind;
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
    void updateMap() {

        List<VisibleEntity> visibleEntityList = new ArrayList<>(List.of(new Reef(new Position(3000, 1000, 0), new Rectangle(400, 1800, 0))));  //2800:1900 3200:1900

        spotter.createMap(100, new Position(1336, 735, 0), new ArrayList<>(List.of(new Checkpoint(new Position(10006, 3131, 0), new Circle(5)), new Checkpoint(new Position(3552, -2532, 0), new Circle(5)), new Checkpoint(new Position(9719, 514, 0), new Circle(5)), new Checkpoint(new Position(4478, 4980, 0), new Circle(5)))));
        List<List<Node>> carte = spotter.getMap();
        PathFindind pathFindind = new PathFindind(carte);

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
    void findNoReef(){

    }
}