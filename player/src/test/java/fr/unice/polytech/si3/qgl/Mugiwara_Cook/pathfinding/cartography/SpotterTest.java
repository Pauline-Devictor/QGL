package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Rectangle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpotterTest {

    @Test
    void createMap() {
        Spotter spotter = new Spotter();

        spotter.createMap(3,new Position(-5,-5,0),new ArrayList<>(List.of(new Checkpoint(new Position(10,10,0),new Circle(5)))));

        List<List<Node>> carte = spotter.getMap();

        for (List<Node> subCarte2 : carte) {
            for (Node nodePath : subCarte2) {
                System.out.print(nodePath.getColor() + " ");
            }
            System.out.println();
        }

    }

    @Test
    void fourextremum() {
    }

    @Test
    void updateMap() {
    }

}