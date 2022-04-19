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
    List<List<Node>> map;
    @Getter
    List<Reef> reefs;

    public Spotter() {
        reefs = new ArrayList<>();
        map = new ArrayList<>();
    }

    public void createMap(int nbLargeur, Position shipPosition, ArrayList<Checkpoint> checkpointArrayList) {

        double[] extremum = fourextremum(shipPosition, checkpointArrayList);

        double diffX = Math.abs(extremum[1]) + Math.abs(extremum[0]);
        double diffY = Math.abs(extremum[3]) + Math.abs(extremum[2]);

        double tailleDiffX = diffX / nbLargeur;
        double tailleDiffY = diffY / nbLargeur;

        List<Node> listNode;

        for (int i = 0; i < nbLargeur; i++) {
            listNode = new ArrayList<>();
            for (int k = 0; k < nbLargeur; k++) {
                listNode.add(new Node(i, k, tailleDiffX * i + tailleDiffX / 2, tailleDiffY * k + tailleDiffY / 2, false));
            }
            map.add(listNode);
        }
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
                maxX = checkpoint.getPosition().getY();
            } else if (checkpoint.getPosition().getY() < minY) {
                minX = checkpoint.getPosition().getY();
            }
        }
        return new double[]{minX, maxX, minY, maxY};
    }

    public boolean updateMap(List<VisibleEntity> visibleEntityList) {
        CollisionDetector collisionDetector = new CollisionDetector();
        for (VisibleEntity visibleEntity : visibleEntityList) {
            if (visibleEntity.getType().equals("reef") && !reefs.contains((Reef) visibleEntity)) {
                for (List<Node> nodeList : this.map) {
                    for (Node node : nodeList) {
                        if (collisionDetector.detectCollision(new Point(node.getXReal(), node.getYReal()), (Reef) visibleEntity)) {
                            node.setWall(true);
                        }
                    }
                }
                reefs.add((Reef) visibleEntity);
            }
        }
    }

//    public void setReefs(List<VisibleEntity> visibleEntityList) {
//        for (VisibleEntity visibleEntity : visibleEntityList) {
//            if (visibleEntity.getType().equals("reef") && !reefs.contains(visibleEntity)) {
//                reefs.add((Reef) visibleEntity);
//            }
//        }
//    }

    // L'argument de cette méthode va définir la taille des carrés lors de l'échantillonage
//    public List<List<Integer>> buildMap(int squareSize) {
//
//        List<List<Integer>> map = new ArrayList<>();
//        int x = 0;
//        int y = 0;
//        CollisionDetector collisionDetector = new CollisionDetector();
//        for (double yReal = nextRound.getShip().getPosition().getY() + squareSize / 2; yReal - nextRound.getShip().getPosition().getY() < getMapHeight(); ) {
//            List mapLine = new ArrayList<>();
//
//            for (double xReal = nextRound.getShip().getPosition().getX() + squareSize / 2; xReal - nextRound.getShip().getPosition().getX() < getMapWidth(); ) {
//
//                for (Object reef : reefs) {
//                    if (collisionDetector.detectCollision(new Point(xReal, yReal), (Reef) reef)) {
//                        //mapLine.add(1);
//                        mapLine.add(new Node(x, y, xReal, yReal, true));
//                    } else {
//                        //mapLine.add(0);
//                        mapLine.add(new Node(x, y, xReal, yReal, false));
//                    }
//                }
//                xReal += squareSize;
//                x++;
//            }
//            map.add(mapLine);
//            yReal += squareSize;
//            y++;
//            x = 0;
//        }
//        return map;
//    }
}
