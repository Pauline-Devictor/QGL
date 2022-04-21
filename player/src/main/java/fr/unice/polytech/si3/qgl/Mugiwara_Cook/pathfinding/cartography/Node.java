package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Node {
    @Getter
    int x;
    @Getter
    int y;
    @Getter
    double xReal;
    @Getter
    double yReal;
    @Getter
    @Setter
    int f;
    @Getter
    @Setter
    int g;
    @Getter
    @Setter
    double h;
    @Setter
    @Getter
    String color;
    @Getter
    ArrayList<Node> voisin;
    @Setter
    @Getter
    Node previous = null;
    @Getter
    boolean wall;

    public Node(int x, int y, double xReal, double yReal, boolean wall) {
        this.x = x;
        this.y = y;
        this.xReal = xReal;
        this.yReal = yReal;
        this.wall = wall;
        this.voisin = new ArrayList<>();
        if (wall) {
            color = "#";
        } else {
            color = ".";
        }
    }

    public void addVoisin(List<List<Node>> grid) {
        int cols = grid.get(0).size();
        int rows = grid.size();
        System.out.println("Colone: " + cols + " et lignes: " + rows + " 1er: " + this.x);
        if (this.x < cols - 1)
            this.voisin.add(grid.get(this.y).get(this.x + 1));
        if (this.x > 0)
            this.voisin.add(grid.get(this.y).get(this.x - 1));
        if (this.y < rows - 1)
            this.voisin.add(grid.get(this.y + 1).get(this.x));
        if (this.y > 0)
            this.voisin.add(grid.get(this.y - 1).get(this.x));
//        if (this.x < cols - 1 && this.y < rows - 1)
//            this.voisin.add(grid.get(this.x + 1).get(this.y + 1));
//        if (this.x > 0 && this.y > 0)
//            this.voisin.add(grid.get(this.x - 1).get(this.y - 1));
//        if (this.y < rows - 1 && this.x > 0)
//            this.voisin.add(grid.get(this.x - 1).get(this.y + 1));
//        if (this.y > 0 && this.x < cols - 1)
//            this.voisin.add(grid.get(this.x + 1).get(this.y - 1));

    }

    public void setWall(boolean wall) {
        this.wall = wall;
        if (this.wall) {
            color = "#";
        } else {
            color = ".";
        }
    }

    public String getDetail() {
        return this.xReal + ":" + this.yReal + "(" + this.x + ":" + this.y + ")";
    }
}
