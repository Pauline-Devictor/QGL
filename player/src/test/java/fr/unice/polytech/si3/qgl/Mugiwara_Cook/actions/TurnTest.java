package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurnTest {

    Turn turn;


    @Test
    void rotationSuppToPiInTwo(){
        turn=new Turn(0,Math.PI/2+0.4);
        assertEquals(turn.getRotation(),0);
        assertEquals(turn.sailorId,0);
    }

    @Test
    void rotationInfToPiInTwo(){
        turn=new Turn(0,Math.PI/2-0.4);
        assertEquals(turn.getRotation(),Math.PI/2-0.4);

    }


}