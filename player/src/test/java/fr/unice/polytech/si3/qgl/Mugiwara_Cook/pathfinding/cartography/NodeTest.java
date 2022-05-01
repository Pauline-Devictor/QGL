package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Wind;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        assertEquals(true,node.wall);
        assertEquals(true,node2.wall);
        assertEquals(true,node3.wall);
        assertEquals(true,node4.wall);
        assertEquals(true,node5.wall);

    }



}