package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurnTest {
    Turn turn;

    @BeforeEach
    void set(){
        turn=new Turn(1,20);
    }

    @Test
    void setRotation(){
        turn.setRotation(70);
        assertEquals(70,turn.getRotation());
    }

    @Test
    void doNotSetRotation(){
        turn.setRotation(-110);
        assertEquals(20,turn.getRotation());
    }

}