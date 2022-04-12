package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography;

import java.util.ArrayList;

public class Node {
    int x;
    int y;
    double xReal;
    double yReal;
    int f;
    int g;
    int h;
    int color;
    ArrayList<Node> voisin;
    Node previous;
    boolean wall;

    public Node(int x, int y,double xReal,double yReal, boolean wall) {
        this.x = x;
        this.y = y;
        this.xReal = xReal;
        this.yReal = yReal;
        this.wall = wall;
        this.voisin = new ArrayList<>();
    }

}
