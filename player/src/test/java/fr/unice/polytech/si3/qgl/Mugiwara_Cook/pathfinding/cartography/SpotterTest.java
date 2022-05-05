package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography;


import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Rectangle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.PathFindind;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Reef;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.VisibleEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        double[] extremun = spotter.fourextremum(new Position(1336, 735, 0), new ArrayList<>(List.of(new Checkpoint(new Position(10006, 3131, 0), new Circle(5)), new Checkpoint(new Position(3552, -2532, 0), new Circle(5)), new Checkpoint(new Position(9719, 514, 0), new Circle(5)), new Checkpoint(new Position(4478, 4980, 0), new Circle(5)))));

        for (int i = 0; i < 4; i++) {
            System.out.println(extremun[i]);
        }
    }

    @Test
    void updateMap() {

        List<VisibleEntity> visibleEntityList = new ArrayList<>(List.of(new Reef(new Position(3000, 1000, 0), new Rectangle(400, 1800, 0))));  //2800:1900 3200:1900

        Spotter spotter = new Spotter();
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
}