package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Rectangle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
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

        spotter.createMap(15, new Position(-5, 0, 0), new ArrayList<>(List.of(new Checkpoint(new Position(10, 15, 0), new Circle(5)))));

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
        double[] extremun = spotter.fourextremum(new Position(-5, 0, 0), new ArrayList<Checkpoint>(List.of(new Checkpoint(new Position(10, 15, 0), new Circle(5)))));

        for (int i = 0; i < 4; i++) {
            System.out.println(extremun[i]);
        }
    }

    @Test
    void updateMap() {

        List<VisibleEntity> visibleEntityList = new ArrayList<>(List.of(new Reef(new Position(5, 5, 0), new Rectangle(2, 2, 0))));
        Spotter spotter = new Spotter();

        spotter.createMap(15, new Position(-5, 0, 0), new ArrayList<>(List.of(new Checkpoint(new Position(10, 15, 0), new Circle(5)))));

        System.out.println(spotter.updateMap(visibleEntityList, new Position(-5, 0, 0), new Position(10, 15, 0)));
//        System.out.println(spotter.updateMap(visibleEntityList));

        spotter.getNodeEnd().setColor("A");
        spotter.getNodeStart().setColor("D");

//        visibleEntityList = new ArrayList<>(List.of(new Reef(new Position(5,0,0),new Rectangle(5,5,0))));

//        System.out.println(spotter.updateMap(visibleEntityList));


        List<List<Node>> carte = spotter.getMap();

        for (List<Node> subCarte2 : carte) {
            for (Node nodePath : subCarte2) {
                System.out.print(nodePath.getDetail() + nodePath.getColor() + " ");
            }
            System.out.println();
        }

        for (List<Node> subCarte2 : carte) {
            for (Node nodePath : subCarte2) {
                System.out.print(nodePath.getColor() + " ");
            }
            System.out.println();
        }

    }

}