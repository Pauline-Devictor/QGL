package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    Node node;
    Node node2;
    Node node3;
    Node node4;
    Node node5;
    int x;
    int y;
    double xReal;
    double yReal;
    boolean wall;


    @BeforeEach
    void set() {
        node =new Node(x,y,xReal,yReal,wall);
        node2 =new Node(x,y,xReal,yReal,false);
        node3 =new Node(x,y,xReal,yReal,false);
        node4 =new Node(x,y,xReal,yReal,false);
        node5 =new Node(x,y,xReal,yReal,false);
        ArrayList voisin=new ArrayList<>();
        voisin.add(node2);
        voisin.add(node3);
        voisin.add(node4);
        voisin.add(node5);
        node.setVoisin(voisin);
        node.setColor("0");
    }

    @Test
    void setWallTrue() {
        System.out.println(node.color);
        node.setWall(true);
        assertEquals("#", node.color);
    }

    @Test
    void setWallFalse() {
        System.out.println(node.color);
        node.setWall(false);
        assertEquals(".", node.color);
    }

    @Test
    void setWallNeighborTrue(){
        node.setWallNeighborTrue();
        assertTrue(node.wall);
        assertTrue(node2.wall);
        assertTrue(node3.wall);
        assertTrue(node4.wall);
        assertTrue(node5.wall);

    }



}