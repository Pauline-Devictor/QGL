package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Action;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Rectangle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal.Goal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;


class MyMapperTest {
    MyMapper myMapper;
    File jsonInit;
    File jsonNext;

    @BeforeEach
    void setUp() {
        jsonInit = new File("jsonTest/initGame.json");
        jsonNext = new File("jsonTest/nextRound.json");
        myMapper = new MyMapper();

    }

    @Test
    void initGame() {
        assertDoesNotThrow(() -> myMapper.readValue(jsonInit, InitGame.class));
    }

    @Test
    void nextRound() {
        assertDoesNotThrow(() -> myMapper.readValue(jsonNext, NextRound.class));
    }

    @Test
    void rectangleShape() throws JsonProcessingException {
        assertNotNull(myMapper.readValue("{\n" +
                "      \"type\": \"rectangle\",\n" +
                "      \"width\": 3,\n" +
                "      \"height\": 6,\n" +
                "      \"orientation\": 0\n" +
                "    }", Shape.class));
     }

    @Test
    void nullShape() throws JsonProcessingException {
        assertNull(myMapper.readValue("{\n" +
                "      \"type\": \"nothing\",\n" +
                "      \"width\": 3,\n" +
                "      \"height\": 6,\n" +
                "      \"orientation\": 0\n" +
                "    }", Shape.class));
    }

    @Test
    void nullAction() throws JsonProcessingException{
        assertNull(myMapper.readValue("{\n" +
                "      \"type\": \"nothing\",\n" +
                "      \"sailorId\": 0\n" +
                "    }", Action.class));
    }

    @Test
    void liftSailAction() throws JsonProcessingException{
        assertNotNull(myMapper.readValue("{\n" +
                "      \"type\": \"LIFT_SAIL\",\n" +
                "      \"sailorId\": 0\n" +
                "    }", Action.class));
    }
    @Test
    void lowerSailAction() throws JsonProcessingException{
        assertNotNull(myMapper.readValue("{\n" +
                "      \"type\": \"LOWER_SAIL\",\n" +
                "      \"sailorId\": 0\n" +
                "    }", Action.class));
    }
    @Test
    void movingAction() throws JsonProcessingException{
        assertNotNull(myMapper.readValue("{\n" +
                "      \"type\": \"MOVING\",\n" +
                "      \"sailorId\": \"0\",\n" +
                "      \"xdistance\": \"0\",\n" +
                "      \"ydistance\": 0\n" +
                "    }", Action.class));
    }
    @Test
    void oarAction() throws JsonProcessingException{
        assertNotNull(myMapper.readValue("{\n" +
                "      \"type\": \"OAR\",\n" +
                "      \"sailorId\": 0\n" +
                "    }", Action.class));
    }
    @Test
    void turnAction() throws JsonProcessingException{
        assertNotNull(myMapper.readValue("{\n" +
                "      \"type\": \"TURN\",\n" +
                "      \"sailorId\": \"0\",\n" +
                "      \"rotation\": 0\n" +
                "    }", Action.class));
    }
    @Test
    void nullGoal() throws IOException {
        assertNull(myMapper.readValue("{\n" +
                "  \"goal\": {\n" +
                "    \"mode\": \"Nothing\"\n" +
                "  }\n" +
                "}", InitGame.class).getGoal());
    }
}