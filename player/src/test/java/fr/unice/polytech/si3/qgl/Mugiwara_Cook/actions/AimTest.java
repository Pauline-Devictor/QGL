package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AimTest {
    Aim aim;


    @BeforeEach
    void set(){
        aim=new Aim(1,-60);

    }

    @Test
    void setAngle(){
        aim.setAngle(70);
        assertEquals(70,aim.getAngle());
    }

    @Test
    void doNotSetAngle(){
        aim.setAngle(-110);
        assertEquals(-60,aim.getAngle());
    }
}