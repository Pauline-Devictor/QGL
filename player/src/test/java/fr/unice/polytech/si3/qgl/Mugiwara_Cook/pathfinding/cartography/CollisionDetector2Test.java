package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Point;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Rectangle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Reef;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CollisionDetector2Test {

    @Test
    void rotationRectangle() {
        Reef reef = new Reef(new Position(2, 2, Math.PI / 2), new Rectangle(2, 4, 0));

        CollisionDetector2 collisionDetector2 = new CollisionDetector2();

        List<Point> pointList = collisionDetector2.rotationRectangle(reef);

        for (Point point : pointList) {

            System.out.println(point.getX() + ":" + point.getY());
        }
        assertEquals(1.0, pointList.get(0).getX());
        assertEquals(0.0, pointList.get(0).getY());

        assertEquals(3.0, pointList.get(1).getX());
        assertEquals(0.0, pointList.get(1).getY());

        assertEquals(3.0, pointList.get(2).getX());
        assertEquals(4.0, pointList.get(2).getY());

        assertEquals(1.0, pointList.get(3).getX());
        assertEquals(4.0, pointList.get(3).getY());

    }

    @Test
    void rotationPoint() {
        Point coinRectangle = new Point(0, 5);
        Position positionReef = new Position(0, 0, Math.PI / 8);

        CollisionDetector2 collisionDetector2 = new CollisionDetector2();
        Point pointAfterRotation = collisionDetector2.rotationPoint(coinRectangle, positionReef);
        System.out.println(pointAfterRotation.getX() + " : " + pointAfterRotation.getY());
        assertEquals(4.619397662556434, pointAfterRotation.getX());
        assertEquals(1.9134171618254492, pointAfterRotation.getY());
    }

    @Test
    void closetNodeFromPoint() {

        List<Node> subCarte;
        List<List<Node>> carte = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            subCarte = new ArrayList<>();
            for (int k = 0; k < 10; k++) {
                subCarte.add(new Node(k, i, k * 10 + 5 + 50, i * 20 + 10 + 30, false));
            }
            carte.add(subCarte);
        }

        Point pointRecherche = new Point(50, 80);

        CollisionDetector2 collisionDetector2 = new CollisionDetector2();

        Node nodeFind = null;

        try {
            nodeFind = collisionDetector2.closestNodeFromPoint(pointRecherche, carte);
        } catch (Exception e) {
            System.out.println("Out of the map");
        }

        System.out.println(nodeFind.getX() + ":" + nodeFind.getY() + " = " + nodeFind.getXReal() + ":" + nodeFind.getYReal());

        assertEquals(0, nodeFind.getX());
        assertEquals(2, nodeFind.getY());

        assertEquals(55.0, nodeFind.getXReal());
        assertEquals(80.0, nodeFind.getYReal());

        assertEquals(nodeFind, carte.get(2).get(0));
    }


    @Test
    void pente() {
        Node node1 = new Node(0, 0, -4, 7, false);
        Node node2 = new Node(0, 0, 8, 4, false);

        CollisionDetector2 collisionDetector2 = new CollisionDetector2();
        assertEquals(-0.25, collisionDetector2.slopeBetweenNodes(node1, node2));
    }

    @Test
    void join2nodesHorizontalSlope(){


    }


    @Test
    void gris() {
        List<Node> subCarte;
        List<List<Node>> carte = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            subCarte = new ArrayList<>();
            for (int k = 0; k < 10; k++) {
                subCarte.add(new Node(k, i, k * 10 + 5, i * 10 + 5, false));
            }
            carte.add(subCarte);
        }

        Reef reef = new Reef(new Position(50, 50, 0), new Rectangle(15, 60, 0));

        CollisionDetector2 collisionDetector2 = new CollisionDetector2();
        collisionDetector2.coloringTheReef(reef, carte);

        for (List<Node> subCarte2 : carte) {
            for (Node nodePath : subCarte2) {
                System.out.print(nodePath.getColor());
            }
            System.out.println();
        }
    }

    @Test
    void grisline() {
        List<Node> subCarte;
        List<List<Node>> carte = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            subCarte = new ArrayList<>();
            for (int k = 0; k < 10; k++) {
                subCarte.add(new Node(k, i, k * 10 + 5, i * 10 + 5, false));
            }
            carte.add(subCarte);
        }

        Node node1 = carte.get(0).get(1);
        Node node2 = carte.get(4).get(7);
        System.out.println(node1.getDetail());
        System.out.println(node2.getDetail());

        CollisionDetector2 collisionDetector2 = new CollisionDetector2();
        collisionDetector2.coloringline(node1, node2, carte);

        for (List<Node> subCarte2 : carte) {
            for (Node nodePath : subCarte2) {
                System.out.print(nodePath.getColor());
            }
            System.out.println();
        }
    }


}