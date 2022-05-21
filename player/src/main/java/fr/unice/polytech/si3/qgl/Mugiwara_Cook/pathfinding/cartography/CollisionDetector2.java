package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Point;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Rectangle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Reef;

import java.util.ArrayList;
import java.util.List;

public class CollisionDetector2 {

    double slopeGoal;

    public CollisionDetector2() {
        //Rien
    }


    public void coloringTheReef(Reef reef, List<List<Node>> map) {
        if (reef.getShape().getType().equals("rectangle")) {
            this.coloringRectangleReef(reef, map);
        }
        if (reef.getShape().getType().equals("circle")) {
            this.grisCircleReef(reef, map);
        }
    }

    private void grisCircleReef(Reef reef, List<List<Node>> map) {
        Reef reefSqrt = this.circleToSqrt(reef);

        for (int i = 0; i < 16; i++) {
            reefSqrt.getPosition().setOrientation(i * Math.PI / 16);
            coloringRectangleReef(reefSqrt, map);
        }

    }

    public Reef circleToSqrt(Reef reef) {
        Position positionSqrt = reef.getPosition();
        Circle circle = (Circle) reef.getShape();
        double[] left = {positionSqrt.getX() - circle.getRadius(), positionSqrt.getY()};
        double[] right = {positionSqrt.getX() + circle.getRadius(), positionSqrt.getY()};

        double side = Math.sqrt(Math.pow(left[0] - right[0], 2) + Math.pow(left[1] - right[1], 2));

        return new Reef(new Position(positionSqrt.getX(), positionSqrt.getY(), 0), new Rectangle(side, side, 0));
    }

    public void coloringRectangleReef(Reef reef, List<List<Node>> map) {
        List<Point> reefcoin = rotationRectangle(reef);
        List<Node> reefcoinNode = new ArrayList<>();

        for (Point point : reefcoin) {
            try {
                reefcoinNode.add(this.closestNodeFromPoint(point, map));
            } catch (Exception e) {
                //Display.debug(e.getMessage());
            }

        }

        int sizecoin = reefcoinNode.size();

        for (int i = 0; i < sizecoin; i++) {
            slopeGoal = 0;
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

        double xForRotation = coinRectangle.getX() - xReefCenter;
        double yForRotation = coinRectangle.getY() - yReefCenter;
        double orientationReef = positionReef.getOrientation() - (Math.PI / 2);

        return new Point(xForRotation * Math.cos(orientationReef) - yForRotation * Math.sin(orientationReef) + xReefCenter
                , (yForRotation * Math.cos(orientationReef) + xForRotation * Math.sin(orientationReef)) + yReefCenter);
    }


    public Node closestNodeFromPoint(Point point, List<List<Node>> map) {
        double squareSizeX = map.get(0).get(1).getXReal() - map.get(0).get(0).getXReal();
        double squareSizeY = map.get(1).get(0).getYReal() - map.get(0).get(0).getYReal();


        Point startMap = new Point(map.get(0).get(0).getXReal() - squareSizeX / 2, map.get(0).get(0).getYReal() - squareSizeY / 2);

        double distanceX = point.getX() - startMap.getX();
        double distanceY = point.getY() - startMap.getY();

        return map.get((int) ((int) distanceY / squareSizeY)).get((int) ((int) distanceX / squareSizeX));
    }

    public double slopeBetweenNodes(Node node1, Node node2) {
        return (node2.getYReal() - node1.getYReal()) / (node2.getXReal() - node1.getXReal());
    }


    public void coloringline(Node node1, Node node2, List<List<Node>> map) {
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
        if (slopeGoal == 0) {
            slopeGoal = slope;
        }
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
            map.get(currentNode[1]).get(--currentNode[0]).setWall(true);
        else
            map.get(currentNode[1]).get(++currentNode[0]).setWall(true);
        for (int i = 0; i > slope + slopeGoal; i--) {
            if (node2.getX() < node1.getX())
                map.get(++currentNode[1]).get(currentNode[0]).setWall(true);
            else
                map.get(--currentNode[1]).get(currentNode[0]).setWall(true);
        }
    }

    void join2NodesPositiveSlope(Node node1, Node node2, List<List<Node>> map, int[] currentNode, double slope) {  // currentNode [x, y]
        if (node2.getX() > node1.getX())
            map.get(currentNode[1]).get(++currentNode[0]).setWall(true);
        else
            map.get(currentNode[1]).get(--currentNode[0]).setWall(true);
        for (int i = 0; i < slope - slopeGoal; i++) {
            if (node2.getX() > node1.getX())
                map.get(++currentNode[1]).get(currentNode[0]).setWall(true);
            else
                map.get(--currentNode[1]).get(currentNode[0]).setWall(true);
        }
    }

    void join2nodesHorizontalSlope(Node node1, Node node2, List<List<Node>> map, int[] currentNode) {
        while (!(map.get(currentNode[1]).get(currentNode[0]).equals(node2))) {
            if (node2.getX() > node1.getX())
                map.get(currentNode[1]).get(++currentNode[0]).setWallNeighborTrue();
            else
                map.get(currentNode[1]).get(--currentNode[0]).setWallNeighborTrue();
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
