package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography.Node;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PathFindindTest {

    @Test
    void findPath2x2() {
        List<Node> subCarte;
        List<List<Node>> carte = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            subCarte = new ArrayList<>();
            for (int k = 0; k < 5; k++) {
                subCarte.add(new Node(i, k, i, k, false));
            }
            carte.add(subCarte);
        }

        carte.get(1).get(0).setWall(true);
        carte.get(1).get(1).setWall(true);
        carte.get(1).get(2).setWall(true);
        carte.get(1).get(3).setWall(true);
        carte.get(1).get(1).setWall(true);
        carte.get(3).get(4).setWall(true);

        PathFindind pathFindind = new PathFindind(carte.get(0).get(0), carte.get(4).get(4), carte);
        List<Node> nodeList = null;
        if (pathFindind.findPath())
            nodeList = pathFindind.getPath();

        System.out.println();
        for (List<Node> subCarte2 : carte) {
            for (Node nodePath : subCarte2) {
                System.out.print(nodePath.getColor() + " ");
            }
            System.out.println();
        }
    }

}