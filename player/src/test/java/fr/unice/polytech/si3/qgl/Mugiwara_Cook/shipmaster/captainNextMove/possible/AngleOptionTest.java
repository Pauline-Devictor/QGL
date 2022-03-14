package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.*;

class AngleOptionTest {

    @Test
    void angle2LeftOar_2RightOar_4Max() {
        AngleOption angle = AngleOption.angle(2, 2, 4);
        assertEquals(0, angle.getAngle());
        assertEquals(0, angle.getDelta());
    }

    @Test
    void angle2LeftOar_2RightOar_6Max() {
        AngleOption angle = AngleOption.angle(2, 2, 6);
        assertEquals(0, angle.getAngle());
    }

    @Test
    void angle0LeftOar_3RightOar_6Max() {
        AngleOption angle = AngleOption.angle(0, 3, 6);
        assertEquals(Math.PI / 2, angle.getAngle());
        assertEquals(3, angle.getDelta());
    }

    @Test
    void angle3LeftOar_0RightOar_6Max() {
        AngleOption angle = AngleOption.angle(3, 0, 6);
        assertEquals(-Math.PI / 2, angle.getAngle());
        assertEquals(-3, angle.getDelta());
    }

    @Test
    void creationOptionFromOarCount_2LeftOar_2RightOar_4Max() {
        List<AngleOption> angleOptionList = AngleOption.creationOptionFromOarCount(2, 2, 4);
        List<Double> angleAngleOptionList = new ArrayList<>();
        List<Integer> deltaAngleOPtionList = new ArrayList<>();
        for (AngleOption option : angleOptionList) {
            angleAngleOptionList.add(option.getAngle());
            deltaAngleOPtionList.add(option.getDelta());
        }
        assertTrue(deltaAngleOPtionList.contains(-2));
        assertTrue(deltaAngleOPtionList.contains(-1));
        assertTrue(deltaAngleOPtionList.contains(0));
        assertTrue(deltaAngleOPtionList.contains(1));
        assertTrue(deltaAngleOPtionList.contains(2));
        assertTrue(angleAngleOptionList.contains(-Math.PI/2));
        assertTrue(angleAngleOptionList.contains(-Math.PI/4));
        assertTrue(angleAngleOptionList.contains(0.0));
        assertTrue(angleAngleOptionList.contains(Math.PI/4));
        assertTrue(angleAngleOptionList.contains(Math.PI/2));
    }

    @Test
    void creationOptionFromOarCount_2LeftOar_4RightOar_8Max() {
        List<AngleOption> angleOptionList = AngleOption.creationOptionFromOarCount(2, 4, 8);
        List<Double> angleAngleOptionList = new ArrayList<>();
        List<Integer> deltaAngleOPtionList = new ArrayList<>();
        for (AngleOption option : angleOptionList) {
            option.getDetail();
            angleAngleOptionList.add(option.getAngle());
            deltaAngleOPtionList.add(option.getDelta());
        }
        assertTrue(deltaAngleOPtionList.contains(-4));
        assertTrue(deltaAngleOPtionList.contains(-3));
        assertTrue(deltaAngleOPtionList.contains(-2));
        assertTrue(deltaAngleOPtionList.contains(-1));
        assertTrue(deltaAngleOPtionList.contains(0));
        assertTrue(deltaAngleOPtionList.contains(1));
        assertTrue(deltaAngleOPtionList.contains(2));
        assertTrue(angleAngleOptionList.contains(-Math.PI/2));
        assertTrue(angleAngleOptionList.contains(-Math.PI*3/8));
        assertTrue(angleAngleOptionList.contains(-Math.PI/8));
        assertTrue(angleAngleOptionList.contains(-Math.PI/4));
        assertTrue(angleAngleOptionList.contains(0.0));
        assertTrue(angleAngleOptionList.contains(Math.PI/8));
        assertTrue(angleAngleOptionList.contains(Math.PI/4));
    }

}