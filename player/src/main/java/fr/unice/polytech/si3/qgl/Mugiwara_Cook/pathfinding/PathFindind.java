package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Display;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography.Node;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathFindind {
    List<Node> openSet;
    List<Node> closeSet;
    boolean endAlgo = false;
    @Getter
    List<Node> path;
    @Getter
    List<Checkpoint> pathCheckpoint;

    List<List<Node>> carte;

    int pathNb;

    public PathFindind(List<List<Node>> carte) {
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
        Display.info("UPDATE PATH");
        openSet.add(start);
        for (List<Node> nodeList : carte) {
            for (Node node : nodeList) {
                node.addVoisin(carte);
            }
        }
        this.path = new ArrayList<>();
        while (true) {
            if (openSet.size() > 0) {
                int lowerIndex = 0;
                for (int i = 0; i < openSet.size(); i++) {
                    if (openSet.get(i).getF() < openSet.get(lowerIndex).getF())
                        lowerIndex = i;
                }
                Node current = openSet.get(lowerIndex);
                if (current == end) {
                    color(current);
                    endAlgo = true; //????
                    Display.info("SOLUTION ? true");
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
                Display.info("SOLUTION ? false");
                return false;
            }
        }
        nodeToCheckpoint();
        return true;
    }


    void color(Node current) {
//        for i in closeSet:
//        i.color = "."  #RED
//
//        for i in openSet:
//        i.color = "+"  #GREEN

        this.path = new ArrayList<>();
        Node tempN = current;
        path.add(tempN);
        while (tempN.getPrevious() != null) {
            path.add(tempN.getPrevious());
            tempN = tempN.getPrevious();
        }

        for (Node nodePath : path) {
//            nodePath.setColor("@" + this.pathNb); //GREEN
            nodePath.setColor("@"); //GREEN
        }
        this.pathNb++;

//
//        print("AFFICHAGE \n")
//        for i in range(len(grid)):
//        print(grid[i])
    }

    double heuristique(Node currentNode, Node end) {
        return Math.sqrt(Math.pow(end.getX() - currentNode.getX(), 2) + Math.pow(end.getY() - currentNode.getY(), 2));
    }

    void nodeToCheckpoint() {
        boolean vert = false;
        boolean horiz = false;

        this.pathCheckpoint = new ArrayList<>();
        Collections.reverse(this.path);
//        Node lastNode = this.path.get(0);
//        this.pathCheckpoint.add(new Checkpoint(new Position(lastNode.getXReal(), lastNode.getYReal(), 0), new Circle(50)));
//
//        for (int i = 1; i < this.path.size(); i++) {
//            System.out.println(lastNode.getY() + " LAST NODE " + lastNode.getX());
//            System.out.println(lastNode.getY() + "==" + this.path.get(i).getY() + "&&" + lastNode.getX() + "!=" + this.path.get(i).getX());
//            if (lastNode.getY() != this.path.get(i).getY() && lastNode.getX() == this.path.get(i).getX()) {
//                System.out.println("lastNode.getY() != this.path.get(i).getY() && lastNode.getX() == this.path.get(i).getX()");
//                vert = true;
//                if (horiz) {
//                    System.out.println("ADD1");
//                    horiz = false;
//                    this.pathCheckpoint.add(new Checkpoint(new Position(lastNode.getXReal(), lastNode.getYReal(), 0), new Circle(50)));
//                    lastNode.setColor("+");
//                }
//
//            } else if (lastNode.getY() == this.path.get(i).getY() && lastNode.getX() != this.path.get(i).getX()) {
//                System.out.println("lastNode.getY() == this.path.get(i).getY() && lastNode.getX() != this.path.get(i).getX()");
//                horiz = true;
//                if (vert) {
//                    System.out.println("ADD2");
//                    vert = false;
//                    this.pathCheckpoint.add(new Checkpoint(new Position(lastNode.getXReal(), lastNode.getYReal(), 0), new Circle(50)));
//                    lastNode.setColor("+");
//                }
//
//            } else {
//                System.out.println("DIAGO");
//                vert = false;
//                horiz = false;
//                this.pathCheckpoint.add(new Checkpoint(new Position(lastNode.getXReal(), lastNode.getYReal(), 0), new Circle(50)));
//                lastNode.setColor("+");
//
//            }
//            lastNode = this.path.get(i);
//            System.out.println("vert: " + vert + ", horiz: " + horiz);
//        }

        for (Node node : this.path) {
            this.pathCheckpoint.add(new Checkpoint(new Position(node.getXReal(), node.getYReal(), 0), new Circle(50)));
            System.out.print(node.getDetail() + ", ");
        }
        System.out.println();

//        Collections.reverse(this.pathCheckpoint);

    }
}
