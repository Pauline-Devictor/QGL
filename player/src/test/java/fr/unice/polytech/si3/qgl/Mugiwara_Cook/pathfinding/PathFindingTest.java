package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography.Node;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PathFindingTest {

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

        assertTrue(pathFindind.findPath(carte.get(0).get(0), carte.get(4).get(1)));

        carte.get(0).get(1).setWall(true);

        assertFalse(pathFindind.findPath(carte.get(0).get(0), carte.get(4).get(1)));

        carte.get(1).get(2).setWall(false);
        carte.get(0).get(1).setWall(false);

        assertTrue(pathFindind.findPath(carte.get(0).get(0), carte.get(4).get(1)));

        for (List<Node> subCarte2 : carte) {
            for (Node nodePath : subCarte2) {
                assertTrue(nodePath.getG() >= 0);
                System.out.print(nodePath.getColor() + " ");
            }
            System.out.println();
        }

        //For Color
        assertEquals("@", carte.get(0).get(0).getColor());
        assertEquals("@", carte.get(0).get(1).getColor());
        assertEquals("@", carte.get(0).get(2).getColor());
        assertEquals("@", carte.get(0).get(3).getColor());
        assertEquals("@", carte.get(1).get(4).getColor());
        assertEquals("@", carte.get(2).get(3).getColor());
        assertEquals("@", carte.get(3).get(2).getColor());
        assertEquals("@", carte.get(4).get(1).getColor());
        assertEquals("@", carte.get(1).get(2).getColor());

        assertEquals("#", carte.get(1).get(0).getColor());
        assertEquals("#", carte.get(1).get(1).getColor());
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

        //For G
        assertEquals(0, carte.get(0).get(0).getG());
        assertEquals(1, carte.get(0).get(1).getG());
        assertEquals(2, carte.get(0).get(2).getG());
        assertEquals(3, carte.get(0).get(3).getG());
        assertEquals(4, carte.get(0).get(4).getG());

        //For G
        assertEquals(0, carte.get(0).get(0).getF());
        assertEquals(5, carte.get(0).get(1).getF());
        assertEquals(6, carte.get(0).get(2).getF());
        assertEquals(7, carte.get(0).get(3).getF());
        assertEquals(9, carte.get(0).get(4).getF());

        //For H
        assertEquals(0.0, carte.get(0).get(0).getH());
        assertEquals(4.0, carte.get(0).get(1).getH());
        assertEquals(5.0, carte.get(0).get(4).getH());

        List<Node> listNode = pathFindind.getPath();

       assertTrue(listNode.get(0).equals(carte.get(0).get(0)));

    }

    @Test
    void heuristique() {
        Node node1 = new Node(2, 5, 0, 0, false);
        Node node2 = new Node(2, 2, 0, 0, false);

        PathFinding pathFindind = new PathFinding(null);

        assertEquals(3.0, pathFindind.heuristique(node1, node2));
    }

    @Test
    void reset() {
        List<Node> subCarte;
        List<List<Node>> carte = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            subCarte = new ArrayList<>();
            for (int k = 0; k < 5; k++) {
                subCarte.add(new Node(k, i, k, i, false));
            }
            carte.add(subCarte);
        }

        PathFinding pathFindind = new PathFinding(carte);
        assertTrue(pathFindind.findPath(carte.get(0).get(0), carte.get(4).get(1)));


        for (List<Node> subCarte2 : carte) {
            for (Node nodePath : subCarte2) {
                assertTrue(nodePath.getVoisin().size() != 0);
            }
            System.out.println();
        }

        pathFindind.reset();

        for (List<Node> subCarte2 : carte) {
            for (Node nodePath : subCarte2) {
                assertEquals(0, nodePath.getVoisin().size());
            }
            System.out.println();
        }
    }


}