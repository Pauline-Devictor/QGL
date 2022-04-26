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

    public void createMap(int nbSquaresSide, Position shipPosition, ArrayList<Checkpoint> checkpointArrayList) {

        double[] extremum = fourextremum(shipPosition, checkpointArrayList);

        double diffX = extremum[1] - extremum[0];
        double diffY = extremum[3] - extremum[2];

        double tailleDiffX = diffX / nbSquaresSide;
        double tailleDiffY = diffY / nbSquaresSide;

        List<Node> listNode;

        for (int i = 0; i < nbSquaresSide; i++) {
            listNode = new ArrayList<>();
            for (int k = 0; k < nbSquaresSide; k++) {
                listNode.add(new Node(k, i, tailleDiffX * k + tailleDiffX / 2 + extremum[0], tailleDiffY * i + tailleDiffY / 2 + extremum[2], false));
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
                maxY = checkpoint.getPosition().getY();
            } else if (checkpoint.getPosition().getY() < minY) {
                minY = checkpoint.getPosition().getY();
            }
        }
        return new double[]{minX, maxX, minY, maxY};
    }

    public boolean updateMap(List<VisibleEntity> visibleEntityList, Position shipPosition, Position positionCheckpoint) {
        List<Reef> newReefs = new ArrayList<>();
        boolean mofifier = false;
        CollisionDetector2 collisionDetector2 = new CollisionDetector2();
        for (VisibleEntity visibleEntity : visibleEntityList) {
            if (visibleEntity.getType().equals("reef")) {
                if (reefs.size() != 0) {
                    for (Reef reef : this.reefs) {
                        if (reef.getPosition().getX() != ((Reef) visibleEntity).getPosition().getX()) {
                            collisionDetector2.gris(((Reef) visibleEntity), this.map);
                            newReefs.add((Reef) visibleEntity);
                            mofifier = true;
                            break;
                        }
                    }
                    reefs.addAll(newReefs);
                } else {
                    System.out.println("LE PREMIER");
                    collisionDetector2.gris(((Reef) visibleEntity), this.map);
                    reefs.add((Reef) visibleEntity);
                    mofifier = true;
                }
            }
        }

        this.nodeStart = closetNodeFromPosition(shipPosition);
        this.nodeEnd = closetNodeFromPosition(positionCheckpoint);
        System.out.println("UPDATE MAP ? " + mofifier);
        return mofifier;
    }
    
    /**public boolean updateMap(List<VisibleEntity> visibleEntityList, Position shipPosition, Position positionCheckpoint) {
     List<Reef> newReefs = new ArrayList<>();
     boolean mofifier = false;
     CollisionDetector2 collisionDetector2 = new CollisionDetector2();
     mofifier = aRename(visibleEntityList, newReefs, mofifier, collisionDetector2);

     this.nodeStart = closetNodeFromPosition(shipPosition);
     this.nodeEnd = closetNodeFromPosition(positionCheckpoint);
     System.out.println("UPDATE MAP ? " + mofifier);
     return mofifier;
     }

     private boolean aRename(List<VisibleEntity> visibleEntityList, List<Reef> newReefs, boolean mofifier, CollisionDetector2 collisionDetector2) {
     for (VisibleEntity visibleEntity : visibleEntityList) {
     if (visibleEntity.getType().equals("reef")) {
     if (reefs.size() != 0) {
     mofifier = isMofifier(newReefs, mofifier, collisionDetector2, (Reef) visibleEntity);
     reefs.addAll(newReefs);
     } else {
     System.out.println("LE PREMIER");
     collisionDetector2.gris(((Reef) visibleEntity), this.map);
     reefs.add((Reef) visibleEntity);
     mofifier = true;
     }
     }
     }
     return mofifier;
     }

     private boolean isMofifier(List<Reef> newReefs, boolean mofifier, CollisionDetector2 collisionDetector2, Reef visibleEntity) {
     for (Reef reef : this.reefs) {
     if (reef.getPosition().getX() != visibleEntity.getPosition().getX()) {
     collisionDetector2.gris(visibleEntity, this.map);
     newReefs.add(visibleEntity);
     mofifier = true;
     break;
     }
     }
     return mofifier;
     }**/

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
}
