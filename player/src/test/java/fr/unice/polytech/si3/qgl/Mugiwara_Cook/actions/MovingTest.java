package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovingTest {

    Moving mooving;
    Sailor sailor;
    Moving mooving2;
    Sailor sailor2;


    @BeforeEach
    public void set(){
        sailor =new Sailor();
        sailor2=new Sailor();
    }


    @Test
    public void isAllowedToMove(){
        int x=3;
        int y=2;
        int x2=-3;
        int y2=-1;
        mooving=new Moving(sailor.getId(),x,y);
        mooving2=new Moving(sailor2.getId(),x2,y2);
        boolean true1;
        boolean true2;
        true1= mooving.isAllowedToMove(x,y);
        true2= mooving2.isAllowedToMove(x2,y2);
        assertTrue(true1);
        assertTrue((true2));
    }
    @Test
    public void isNotAllowedToMove(){
        int x=3;
        int y=-10;
        int x2=-3;
        int y2=3;
        mooving=new Moving(sailor.getId(),x,y);
        mooving2=new Moving(sailor2.getId(),x2,y2);
        boolean false1;
        boolean false2;
        false1= mooving.isAllowedToMove(x,y);
        false2= mooving2.isAllowedToMove(x2,y2);
        assertFalse(false1);
        assertFalse(false2);
    }

}