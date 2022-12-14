package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography.Node;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathFinding {
    List<Node> openSet;
    List<Node> closeSet;
    boolean endAlgo = false;
    @Getter
    List<Node> path;
    @Getter
    List<Checkpoint> pathCheckpoint;

    List<List<Node>> carte;

    int pathNb;

    public PathFinding(List<List<Node>> carte) {
        this.carte = carte;
        this.openSet = new ArrayList<>();
        this.closeSet = new ArrayList<>();
        this.pathNb = 0;
    }

    public void reset() {
        this.openSet = new ArrayList<>();
        this.closeSet = new ArrayList<>();
        for (List<Node> nodeList : carte) {
            for (Node node : nodeList) {
                node.reset();
            }
        }
    }

    public boolean findPath(Node start, Node end) {  //return null si pas de chemin
        this.reset();
        openSet.add(start);
        for (List<Node> nodeList : carte) {
            for (Node node : nodeList) {
                node.addVoisin(carte);
            }
        }

        this.path = new ArrayList<>();
        while (true) {
            if (!openSet.isEmpty()) {
                int lowerIndex = 0;
                for (int i = 0; i < openSet.size(); i++) {
                    if (openSet.get(i).getF() < openSet.get(lowerIndex).getF())
                        lowerIndex = i;
                }
                Node current = openSet.get(lowerIndex);
                if (current == end) {
                    color(current);
                    endAlgo = true;
                    break;
                }

                openSet.remove(current);
                closeSet.add(current);

                ArrayList<Node> voisin = current.getVoisin();
                for (Node i : voisin) {
                    if (!closeSet.contains(i) && !i.isWall()) {
                        int tempG = current.getG() + 1;
                        boolean newPath = false;
                        if (openSet.contains(i)) {
                            if (tempG < i.getG()) {
                                i.setG(tempG);
                                newPath = true;
                            }
                        } else {
                            i.setG(tempG);
                            newPath = true;
                            openSet.add(i);
                        }

                        if (newPath) {
                            i.setH(heuristique(i, end));
                            i.setF((int) (i.getG() + i.getH()));
                            i.setPrevious(current);
                        }
                    }
                }

            } else {
                return false;
            }
        }
        nodeToCheckpoint();
        return true;
    }


    void color(Node current) {
        this.path = new ArrayList<>();
        Node tempN = current;
        path.add(tempN);
        while (tempN.getPrevious() != null) {
            path.add(tempN.getPrevious());
            tempN = tempN.getPrevious();
        }

        for (Node nodePath : path) {
            nodePath.setColor("@");
        }
//        this.pathNb++;
    }

    double heuristique(Node currentNode, Node end) {
        return Math.sqrt(Math.pow(end.getX() - currentNode.getX(), 2) + Math.pow(end.getY() - currentNode.getY(), 2));
    }

    void nodeToCheckpoint() {

        this.pathCheckpoint = new ArrayList<>();
        Collections.reverse(this.path);
        for (Node node : this.path) {
            this.pathCheckpoint.add(new Checkpoint(new Position(node.getXReal(), node.getYReal(), 0), new Circle(50)));
        }

    }
}
