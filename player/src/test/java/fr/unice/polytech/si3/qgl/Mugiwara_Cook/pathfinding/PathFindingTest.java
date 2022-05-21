package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography.Node;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PathFindindTest {

    @Test
    void findPath5x5() {
        List<Node> subCarte;
        List<List<Node>> carte = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            subCarte = new ArrayList<>();
            for (int k = 0; k < 5; k++) {
                subCarte.add(new Node(k, i, k, i, false));
            }
            carte.add(subCarte);
        }

        carte.get(1).get(0).setWall(true);
        carte.get(1).get(1).setWall(true);
        carte.get(1).get(2).setWall(true);
        carte.get(1).get(3).setWall(true);
        carte.get(1).get(1).setWall(true);
        carte.get(3).get(4).setWall(true);
        carte.get(2).get(0).setWall(true);
        carte.get(2).get(1).setWall(true);
        carte.get(2).get(2).setWall(true);
//        carte.get(2).get(3).setWall(true);
        carte.get(2).get(1).setWall(true);

        PathFinding pathFindind = new PathFinding(carte);
        pathFindind.findPath(carte.get(0).get(0), carte.get(4).get(1));

        for (List<Node> subCarte2 : carte) {
            for (Node nodePath : subCarte2) {
                System.out.print(nodePath.getColor() + " ");
            }
            System.out.println();
        }

        assertEquals("@", carte.get(0).get(0).getColor());
        assertEquals("@", carte.get(0).get(1).getColor());
        assertEquals("@", carte.get(0).get(2).getColor());
        assertEquals("@", carte.get(0).get(3).getColor());
        assertEquals("@", carte.get(1).get(4).getColor());
        assertEquals("@", carte.get(2).get(3).getColor());
        assertEquals("@", carte.get(3).get(2).getColor());
        assertEquals("@", carte.get(4).get(1).getColor());

        assertEquals("#", carte.get(1).get(0).getColor());
        assertEquals("#", carte.get(1).get(1).getColor());
        assertEquals("#", carte.get(1).get(2).getColor());
        assertEquals("#", carte.get(1).get(3).getColor());
        assertEquals("#", carte.get(2).get(0).getColor());
        assertEquals("#", carte.get(2).get(1).getColor());
        assertEquals("#", carte.get(2).get(2).getColor());
        assertEquals("#", carte.get(3).get(4).getColor());

        assertEquals(".", carte.get(0).get(4).getColor());
        assertEquals(".", carte.get(2).get(4).getColor());
        assertEquals(".", carte.get(3).get(0).getColor());
        assertEquals(".", carte.get(3).get(1).getColor());
        assertEquals(".", carte.get(3).get(3).getColor());
        assertEquals(".", carte.get(4).get(0).getColor());
        assertEquals(".", carte.get(4).get(2).getColor());
        assertEquals(".", carte.get(4).get(3).getColor());
        assertEquals(".", carte.get(4).get(4).getColor());

    }

}