package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Point;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Rectangle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Reef;

import java.util.ArrayList;
import java.util.List;

public class CollisionDetector2 {

    public CollisionDetector2() {
        //Rien
    }

    ///!\/!\/!\  forme List<Point>: [x0y0,x1y0,x1y1,x0y1]  /!\/!\/!\

    public void coloringTheReef(Reef reef, List<List<Node>> map) {
        if (reef.getShape().getType().equals("rectangle")) {
            this.coloringRectangleReef(reef, map);
        }
    }

    public void coloringRectangleReef(Reef reef, List<List<Node>> map) {
        System.out.println("Un rocher rectangle");
        List<Point> reefcoin = rotationRectangle(reef);
        List<Node> reefcoinNode = new ArrayList<>();

        for (Point point : reefcoin) {
//            System.out.println("Point: " + point.getY() + "+" + point.getX());
            try {
                reefcoinNode.add(this.closestNodeFromPoint(point, map));
            } catch (Exception e) {
                System.out.println("Out of the map");
            }

        }

        int sizecoin = reefcoinNode.size();

        for (int i = 0; i < sizecoin; i++) {
            this.coloringline(reefcoinNode.get(i), reefcoinNode.get((i + 1) % (sizecoin)), map);
        }
    }

    public List<Point> rotationRectangle(Reef reef) {
        double height = ((Rectangle) reef.getShape()).getHeight() / 2;
        double width = ((Rectangle) reef.getShape()).getWidth() / 2;
        double xReefCenter = reef.getPosition().getX();
        double yReefCenter = reef.getPosition().getY();

        Point x0y0 = new Point(xReefCenter - width, yReefCenter - height);
        Point x1y0 = new Point(xReefCenter + width, yReefCenter - height);
        Point x1y1 = new Point(xReefCenter + width, yReefCenter + height);
        Point x0y1 = new Point(xReefCenter - width, yReefCenter + height);

        Point x0y0AfterRotation = this.rotationPoint(x0y0, reef.getPosition());
        Point x1y0AfterRotation = this.rotationPoint(x1y0, reef.getPosition());
        Point x1y1AfterRotation = this.rotationPoint(x1y1, reef.getPosition());
        Point x0y1AfterRotation = this.rotationPoint(x0y1, reef.getPosition());

        return List.of(x0y0AfterRotation, x1y0AfterRotation, x1y1AfterRotation, x0y1AfterRotation);
    }

    public Point rotationPoint(Point coinRectangle, Position positionReef) {
        double xReefCenter = positionReef.getX();
        double yReefCenter = positionReef.getY();
        double orientationReef = Math.abs(positionReef.getOrientation());

        //(xa-xg).cos théta - (ya-yg).sin théta + xg  || (ya-yg).cos théta + (xa-xg).sin théta + yg
        return new Point((coinRectangle.getX() - xReefCenter) * Math.cos(orientationReef) - (coinRectangle.getY() - yReefCenter) * Math.sin(orientationReef) + xReefCenter
                , (coinRectangle.getY() - yReefCenter) * Math.cos(orientationReef) + (coinRectangle.getX() - xReefCenter) * Math.sin(orientationReef) + yReefCenter);
    }


    public Node closestNodeFromPoint(Point point, List<List<Node>> map) throws Exception {
        double squareSizeX = map.get(0).get(1).getXReal() - map.get(0).get(0).getXReal();
        double squareSizeY = map.get(1).get(0).getYReal() - map.get(0).get(0).getYReal();

//        System.out.println(squareSizeX + "]" + squareSizeY);

        Point startMap = new Point(map.get(0).get(0).getXReal() - squareSizeX / 2, map.get(0).get(0).getYReal() - squareSizeY / 2);
        System.out.println(startMap.getX() + "[" + startMap.getY());

        double distanceX = point.getX() - startMap.getX();
        double distanceY = point.getY() - startMap.getY();

        System.out.println((int) ((int) distanceX / squareSizeX) + " {1} " + (int) ((int) distanceY / squareSizeY));
        System.out.println(distanceX + " {2} " + distanceY + " pour un ptn: " + point.getX() + ":" + point.getY());

        return map.get((int) ((int) distanceY / squareSizeY)).get((int) ((int) distanceX / squareSizeX));
    }

    public double slopeBetweenNodes(Node node1, Node node2) {
//        System.out.println(node2.getY() + " " + node1.getY() + " ++ " + node2.getX() + " " + node1.getX());
        return ((double) (node2.getY() - node1.getY())) / (node2.getX() - node1.getX());
    }

    /**public void grisline(Node node1, Node node2, List<List<Node>> map) {
        node1.setWallNeighborTrue();
        node2.setWallNeighborTrue();

        int[] currentNode = {node1.getX(), node1.getY()};

        if (node2.getX() - node1.getX() == 0) {
            while (!(map.get(currentNode[1]).get(currentNode[0]).equals(node2))) {
                if (node2.getY() > node1.getY())
                    map.get(++currentNode[1]).get(currentNode[0]).setWallNeighborTrue();
                else
                    map.get(--currentNode[1]).get(currentNode[0]).setWallNeighborTrue();
            }
        } else if (node2.getY() - node1.getY() == 0) {
            while (!(map.get(currentNode[1]).get(currentNode[0]).equals(node2))) {
                if (node2.getX() > node1.getX())
                    map.get(currentNode[1]).get(++currentNode[0]).setWallNeighborTrue();
                else
                    map.get(currentNode[1]).get(--currentNode[0]).setWallNeighborTrue();
//                System.out.println(map.get(currentNode[1]).get(currentNode[0]).getDetail());
            }
        } else {
            double slope = slopeBetweenNodes(map.get(currentNode[1]).get(currentNode[0]), node2);
//            System.out.println("slope: " + slope);
            if (slope > 0) {
                if (node2.getX() > node1.getX())
                    map.get(currentNode[1]).get(++currentNode[0]).setWallNeighborTrue();
                else
                    map.get(currentNode[1]).get(--currentNode[0]).setWallNeighborTrue();
//                System.out.println(map.get(currentNode[1]).get(currentNode[0]).getDetail());
                for (int i = 0; i < slope; i++) {
                    if (node2.getX() > node1.getX())
                        map.get(++currentNode[1]).get(currentNode[0]).setWallNeighborTrue();
                    else
                        map.get(--currentNode[1]).get(currentNode[0]).setWallNeighborTrue();
//                    System.out.println(map.get(currentNode[1]).get(currentNode[0]).getDetail());
                }
            }
            if (slope < 0) {
                if (node2.getX() < node1.getX())
                    map.get(currentNode[1]).get(--currentNode[0]).setWallNeighborTrue();
                else
                    map.get(currentNode[1]).get(++currentNode[0]).setWallNeighborTrue();
//                System.out.println(map.get(currentNode[1]).get(currentNode[0]).getDetail());
                for (int i = 0; i > slope; i--) {
                    if (node2.getX() < node1.getX())
                        map.get(++currentNode[1]).get(currentNode[0]).setWallNeighborTrue();
                    else
                        map.get(--currentNode[1]).get(currentNode[0]).setWallNeighborTrue();
//                    System.out.println(map.get(currentNode[1]).get(currentNode[0]).getDetail());
                }
            }
            if (!(map.get(currentNode[1]).get(currentNode[0]).equals(node2))) {
                this.coloringline(map.get(currentNode[1]).get(currentNode[0]), node2, map);
            }

        }
    }**/

    public void coloringline(Node node1, Node node2, List<List<Node>> map) {
        System.out.println("grislineLARGE");
        node1.setWallNeighborTrue();
        node2.setWallNeighborTrue();

        int[] currentNode = {node1.getX(), node1.getY()};

        if (node2.getX() - node1.getX() == 0) {
            join2nodesVerticalSlope(node1, node2, map, currentNode);
        } else if (node2.getY() - node1.getY() == 0) {
            join2nodesHorizontalSlope(node1, node2, map, currentNode);
        } else {
            join2NodesComplexSlope(node1, node2, map, currentNode);

        }
    }

    void join2NodesComplexSlope(Node node1, Node node2, List<List<Node>> map, int[] currentNode) {
        double slope = slopeBetweenNodes(map.get(currentNode[1]).get(currentNode[0]), node2);
        //            System.out.println("slope: " + slope);
        if (slope > 0) {
            join2NodesPositiveSlope(node1, node2, map, currentNode, slope);
        }
        if (slope < 0) {
            join2NodesNegativeSlope(node1, node2, map, currentNode, slope);
        }
        if (!(map.get(currentNode[1]).get(currentNode[0]).equals(node2))) {
            this.coloringline(map.get(currentNode[1]).get(currentNode[0]), node2, map);
        }
    }

    void join2NodesNegativeSlope(Node node1, Node node2, List<List<Node>> map, int[] currentNode, double slope) {
        if (node2.getX() < node1.getX())
            map.get(currentNode[1]).get(--currentNode[0]).setWallNeighborTrue();
        else
            map.get(currentNode[1]).get(++currentNode[0]).setWallNeighborTrue();
        //                System.out.println(map.get(currentNode[1]).get(currentNode[0]).getDetail());
        for (int i = 0; i > slope; i--) {
            if (node2.getX() < node1.getX())
                map.get(++currentNode[1]).get(currentNode[0]).setWallNeighborTrue();
            else
                map.get(--currentNode[1]).get(currentNode[0]).setWallNeighborTrue();
            //                    System.out.println(map.get(currentNode[1]).get(currentNode[0]).getDetail());
        }
    }

    void join2NodesPositiveSlope(Node node1, Node node2, List<List<Node>> map, int[] currentNode, double slope) {
        if (node2.getX() > node1.getX())
            map.get(currentNode[1]).get(++currentNode[0]).setWallNeighborTrue();
        else
            map.get(currentNode[1]).get(--currentNode[0]).setWallNeighborTrue();
        //                System.out.println(map.get(currentNode[1]).get(currentNode[0]).getDetail());
        for (int i = 0; i < slope; i++) {
            if (node2.getX() > node1.getX())
                map.get(++currentNode[1]).get(currentNode[0]).setWallNeighborTrue();
            else
                map.get(--currentNode[1]).get(currentNode[0]).setWallNeighborTrue();
            //                    System.out.println(map.get(currentNode[1]).get(currentNode[0]).getDetail());
        }
    }

    void join2nodesHorizontalSlope(Node node1, Node node2, List<List<Node>> map, int[] currentNode) {
        while (!(map.get(currentNode[1]).get(currentNode[0]).equals(node2))) {
            if (node2.getX() > node1.getX())
                map.get(currentNode[1]).get(++currentNode[0]).setWallNeighborTrue();
            else
                map.get(currentNode[1]).get(--currentNode[0]).setWallNeighborTrue();
            //                System.out.println(map.get(currentNode[1]).get(currentNode[0]).getDetail());
        }
    }

    void join2nodesVerticalSlope(Node node1, Node node2, List<List<Node>> map, int[] currentNode) {
        while (!(map.get(currentNode[1]).get(currentNode[0]).equals(node2))) {
            if (node2.getY() > node1.getY())
                map.get(++currentNode[1]).get(currentNode[0]).setWallNeighborTrue();
            else
                map.get(--currentNode[1]).get(currentNode[0]).setWallNeighborTrue();
        }
    }
}
