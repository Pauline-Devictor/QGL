package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Point;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Rectangle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Reef;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CollisionDetector2Test {

    @Test
    void rotationRectangle() {
        Reef reef = new Reef(new Position(2, 2, Math.PI / 2), new Rectangle(2, 4, 0));

        CollisionDetector2 collisionDetector2 = new CollisionDetector2();

        List<Point> pointList = collisionDetector2.rotationRectangle(reef);

        for (Point point : pointList) {
            System.out.println(point.getX() + ":" + point.getY());
        }
    }

    @Test
    void closetNodeFromPoint() {

        List<Node> subCarte;
        List<List<Node>> carte = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            subCarte = new ArrayList<>();
            for (int k = 0; k < 10; k++) {
                subCarte.add(new Node(k, i, k * 10 + 5, i * 20 + 10, false));
            }
            carte.add(subCarte);
        }

        Point pointRecherche = new Point(22, 80);

        CollisionDetector2 collisionDetector2 = new CollisionDetector2();

        Node nodeFind = null;

        try {
            nodeFind = collisionDetector2.closestNodeFromPoint(pointRecherche, carte);
        } catch (Exception e) {
            System.out.println("Out of the map");
        }


        System.out.println(nodeFind.getX() + ":" + nodeFind.getY() + " = " + nodeFind.getXReal() + ":" + nodeFind.getYReal());
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