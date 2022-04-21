package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Point;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Reef;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.VisibleEntity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Spotter {
    @Getter
    Node nodeStart;
    @Getter
    Node nodeEnd;

    @Getter
    List<List<Node>> map;
    @Getter
    List<Reef> reefs;

    public Spotter() {
        reefs = new ArrayList<>();
        map = new ArrayList<>();
    }

    public void createMap(int nbLargeur, Position shipPosition, ArrayList<Checkpoint> checkpointArrayList) {

        double[] extremum = fourextremum(shipPosition, checkpointArrayList);

        double diffX = extremum[1] - extremum[0];
        double diffY = extremum[3] - extremum[2];

        double tailleDiffX = diffX / nbLargeur;
        double tailleDiffY = diffY / nbLargeur;

        List<Node> listNode;

        for (int i = 0; i < nbLargeur; i++) {
            listNode = new ArrayList<>();
            for (int k = 0; k < nbLargeur; k++) {
                listNode.add(new Node(k, i, tailleDiffX * k + tailleDiffX / 2 + extremum[0], tailleDiffY * i + tailleDiffY / 2 + extremum[2], false));
            }
            map.add(listNode);
        }
    }


    public Node closetNodeFromPosition(Position position) {
        double disMin = 1000000;
        Node nodeMin = null;
        for (List<Node> nodeList : this.map) {
            for (Node node : nodeList) {
                if (Math.sqrt(Math.pow(node.getXReal() - position.getX(), 2) + Math.pow(node.getYReal() - position.getY(), 2)) < disMin) {
                    disMin = Math.sqrt(Math.pow(node.getXReal() - position.getX(), 2) + Math.pow(node.getYReal() - position.getY(), 2));
                    nodeMin = node;
                }
            }
        }
        return nodeMin;
    }

    public double[] fourextremum(Position shipPosition, ArrayList<Checkpoint> checkpointArrayList) {
        double minX = shipPosition.getX();
        double maxX = shipPosition.getX();
        double minY = shipPosition.getY();
        double maxY = shipPosition.getY();

        for (Checkpoint checkpoint : checkpointArrayList) {
            if (checkpoint.getPosition().getX() > maxX) {
                maxX = checkpoint.getPosition().getX();
            } else if (checkpoint.getPosition().getX() < minX) {
                minX = checkpoint.getPosition().getX();
            }

            if (checkpoint.getPosition().getY() > maxY) {
                maxY = checkpoint.getPosition().getY();
            } else if (checkpoint.getPosition().getY() < minY) {
                minY = checkpoint.getPosition().getY();
            }
        }
        return new double[]{minX, maxX, minY, maxY};
    }

    public boolean updateMap(List<VisibleEntity> visibleEntityList, Position shipPosition, Position positionCheckpoint) {
        boolean mofifier = false;
        CollisionDetector collisionDetector = new CollisionDetector();
        for (VisibleEntity visibleEntity : visibleEntityList) {
            if (visibleEntity.getType().equals("reef") && !reefs.contains((Reef) visibleEntity)) {
                for (List<Node> nodeList : this.map) {
                    for (Node node : nodeList) {
                        if (collisionDetector.detectCollision(new Point(node.getXReal(), node.getYReal()), (Reef) visibleEntity)) {
                            System.out.println("Il est bon le NODE: " + node.getXReal() + "+" + node.getYReal());
                            node.setWall(true);
                            mofifier = true;
                        }
                    }
                }
                reefs.add((Reef) visibleEntity);
            }
        }

        this.nodeStart = closetNodeFromPosition(shipPosition);
        this.nodeEnd = closetNodeFromPosition(positionCheckpoint);

        return mofifier;
    }


    /**
    public double getMapHeight() {
        return Math.abs(nextRound.getShip().getPosition().getY() - currentCheckpoint.getPosition().getY()) * 1.2;
    }

    public double getMapWidth() {
        return Math.abs(nextRound.getShip().getPosition().getX() - currentCheckpoint.getPosition().getX()) * 1.2;
    }

    // L'argument de cette méthode va définir la taille des carrés lors de l'échantillonage
    public List<List<Node>> buildMap(int squareSize) {

        List<List<Node>> map = new ArrayList<>();
        int x = 0;
        int y = 0;
        CollisionDetector collisionDetector = new CollisionDetector();
        for (double yReal = nextRound.getShip().getPosition().getY() + squareSize / 2; yReal - nextRound.getShip().getPosition().getY() <= getMapHeight(); ) {
            List mapLine = new ArrayList<>();

            for (double xReal = nextRound.getShip().getPosition().getX() + squareSize / 2; xReal - nextRound.getShip().getPosition().getX() <= getMapWidth(); ) {

                boolean wall = false;

                for (Reef reef : reefs) {

                    if (collisionDetector.detectCollision(new Point(xReal, yReal), reef)) {
                        //mapLine.add(new Node(x, y, xReal, yReal, true));
                        wall = true;
                        //break;
                    }
                    //mapLine.add(new Node(x,y,xReal,yReal,false));
                }
                mapLine.add(new Node(x, y, xReal, yReal, wall));
                xReal += squareSize;
                x++;
            }
            map.add(mapLine);
            yReal += squareSize;
            y++;
            x = 0;
        }
        return map;
    }
     */
}
