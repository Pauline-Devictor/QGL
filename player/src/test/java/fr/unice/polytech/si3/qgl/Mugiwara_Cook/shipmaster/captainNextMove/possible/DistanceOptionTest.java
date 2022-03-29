package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.CalculateDistanceHelper;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.possible.possibleDistance.DistanceOption;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DistanceOptionTest {

    @Test
    void distanceOption2LeftOar_2RightOar_4Max() {
        DistanceOption distanceOption = CalculateDistanceHelper.distance(2, 2, 4);
        assertEquals(165, distanceOption.getDistance());
        assertEquals(2, distanceOption.getOarLeftRightAndSails()[0]);
        assertEquals(2, distanceOption.getOarLeftRightAndSails()[1]);
    }

    @Test
    void distanceOption2LeftOar_2RightOar_8Max() {
        DistanceOption distanceOption = CalculateDistanceHelper.distance(2, 2, 8);
        assertEquals(82.5, distanceOption.getDistance());
        assertEquals(2, distanceOption.getOarLeftRightAndSails()[0]);
        assertEquals(2, distanceOption.getOarLeftRightAndSails()[1]);
    }

    @Test
    void distanceOption2LeftOar_4RightOar_8Max() {
        DistanceOption distanceOption = CalculateDistanceHelper.distance(2, 4, 8);
        assertEquals(123.75, distanceOption.getDistance());
        assertEquals(2, distanceOption.getOarLeftRightAndSails()[0]);
        assertEquals(4, distanceOption.getOarLeftRightAndSails()[1]);
    }

    @Test
    void distanceOption4LeftOar_1RightOar_8Max() {
        DistanceOption distanceOption = CalculateDistanceHelper.distance(4, 1, 8);
        assertEquals(103.125, distanceOption.getDistance());
        assertEquals(4, distanceOption.getOarLeftRightAndSails()[0]);
        assertEquals(1, distanceOption.getOarLeftRightAndSails()[1]);
    }

    @Test
    void distanceOptionFromOarCount_2LeftOar_2RightOar_4Max() {
        List<DistanceOption> distanceOptionList = DistanceOption.creationDistanceOptionFromOarCount(0, 2, 2, 4);
        List<Double> distanceDistanceOptionList = new ArrayList<>();
        List<int[]> oarsDistanceOptionList = new ArrayList<>();
        for (DistanceOption option : distanceOptionList) {
            distanceDistanceOptionList.add(option.getDistance());
            oarsDistanceOptionList.add(option.getOarLeftRightAndSails());
        }
        assertTrue(distanceDistanceOptionList.contains(0.0));
        assertEquals(0, oarsDistanceOptionList.get(0)[0]);
        assertEquals(0, oarsDistanceOptionList.get(0)[1]);

        assertTrue(distanceDistanceOptionList.contains(82.5));
        assertEquals(1, oarsDistanceOptionList.get(1)[0]);
        assertEquals(1, oarsDistanceOptionList.get(1)[1]);

        assertTrue(distanceDistanceOptionList.contains(165.0));
        assertEquals(2, oarsDistanceOptionList.get(2)[0]);
        assertEquals(2, oarsDistanceOptionList.get(2)[1]);

        assertEquals(3, oarsDistanceOptionList.size());
    }

    @Test
    void distanceOptionFromOarCount_3LeftOar_4RightOar_8Max() {
        List<DistanceOption> distanceOptionList = DistanceOption.creationDistanceOptionFromOarCount(2, 3, 4, 8);
        List<Double> distanceDistanceOptionList = new ArrayList<>();
        List<int[]> oarsDistanceOptionList = new ArrayList<>();
        for (DistanceOption option : distanceOptionList) {
            distanceDistanceOptionList.add(option.getDistance());
            oarsDistanceOptionList.add(option.getOarLeftRightAndSails());
        }
        assertTrue(distanceDistanceOptionList.contains(41.25));
        assertEquals(0, oarsDistanceOptionList.get(0)[0]);
        assertEquals(2, oarsDistanceOptionList.get(0)[1]);

        assertTrue(distanceDistanceOptionList.contains(82.5));
        assertEquals(1, oarsDistanceOptionList.get(1)[0]);
        assertEquals(3, oarsDistanceOptionList.get(1)[1]);

        assertTrue(distanceDistanceOptionList.contains(123.75));
        assertEquals(2, oarsDistanceOptionList.get(2)[0]);
        assertEquals(4, oarsDistanceOptionList.get(2)[1]);

        assertEquals(3, distanceOptionList.size());
    }


    @Test
    void distanceOptionFromOarCount_4LeftOar_1RightOar_8Max() {
        List<DistanceOption> distanceOptionList = DistanceOption.creationDistanceOptionFromOarCount(-2, 4, 1, 8);
        List<Double> distanceDistanceOptionList = new ArrayList<>();
        List<int[]> oarsDistanceOptionList = new ArrayList<>();
        for (DistanceOption option : distanceOptionList) {
            option.getDetail();
            distanceDistanceOptionList.add(option.getDistance());
            oarsDistanceOptionList.add(option.getOarLeftRightAndSails());
        }
        assertTrue(distanceDistanceOptionList.contains(41.25));
        assertEquals(2, oarsDistanceOptionList.get(0)[0]);
        assertEquals(0, oarsDistanceOptionList.get(0)[1]);

        assertTrue(distanceDistanceOptionList.contains(82.5));
        assertEquals(3, oarsDistanceOptionList.get(1)[0]);
        assertEquals(1, oarsDistanceOptionList.get(1)[1]);

        assertEquals(2, distanceOptionList.size());
    }

    @Test
    void distanceOptionFromOarCount_4LeftOar_1RightOar_8MaxImpossible() {
        List<DistanceOption> distanceOptionList = DistanceOption.creationDistanceOptionFromOarCount(2, 4, 1, 8);
        assertEquals(0, distanceOptionList.size());

        DistanceOption distanceOption = CalculateDistanceHelper.distance(2, 4, 8);
        assertEquals(123.75, distanceOption.getDistance());
        assertEquals(2, distanceOption.getOarLeftRightAndSails()[0]);
        assertEquals(4, distanceOption.getOarLeftRightAndSails()[1]);
    }
}

