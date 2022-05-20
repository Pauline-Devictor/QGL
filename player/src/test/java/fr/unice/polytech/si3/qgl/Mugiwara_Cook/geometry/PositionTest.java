package fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    Position position1;
    Position position2;
    Position position3;
    Position position4;

    @BeforeEach
    void setup(){
        position1 = new Position(1,0,0);
        position2 = new Position(3,0,0);
        position3 = new Position(0,1,0);
        position4 = new Position(0,3,0);

    }//81-16

    @Test
    void distance(){
        assertEquals(2,position1.distance(position2));
        assertEquals(2,position3.distance(position4));
    }

    @Test
    void printPosition(){
        assertEquals("[1.0,0.0,0.0]",position1.getListPosition());
        assertEquals("[0.0,1.0,0.0]",position3.getListPosition());
    }
}
