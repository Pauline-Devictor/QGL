package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Action;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.VisibleEntity;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
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
        assertNotNull(myMapper.readValue("""
                {
                      "type": "rectangle",
                      "width": 3,
                      "height": 6,
                      "orientation": 0
                    }""", Shape.class));
     }

    @Test
    void nullShape() throws JsonProcessingException {
        assertNull(myMapper.readValue("""
                {
                      "type": "nothing",
                      "width": 3,
                      "height": 6,
                      "orientation": 0
                    }""", Shape.class));
    }

    @Test
    void nullAction() throws JsonProcessingException{
        assertNull(myMapper.readValue("""
                {
                      "type": "nothing",
                      "sailorId": 0
                    }""", Action.class));
    }

    @Test
    void liftSailAction() throws JsonProcessingException{
        assertNotNull(myMapper.readValue("""
                {
                      "type": "LIFT_SAIL",
                      "sailorId": 0
                    }""", Action.class));
    }
    @Test
    void lowerSailAction() throws JsonProcessingException{
        assertNotNull(myMapper.readValue("""
                {
                      "type": "LOWER_SAIL",
                      "sailorId": 0
                    }""", Action.class));
    }
    @Test
    void movingAction() throws JsonProcessingException{
        assertNotNull(myMapper.readValue("""
                {
                      "type": "MOVING",
                      "sailorId": "0",
                      "xdistance": "0",
                      "ydistance": 0
                    }""", Action.class));
    }
    @Test
    void oarAction() throws JsonProcessingException{
        assertNotNull(myMapper.readValue("""
                {
                      "type": "OAR",
                      "sailorId": 0
                    }""", Action.class));
    }
    @Test
    void turnAction() throws JsonProcessingException{
        assertNotNull(myMapper.readValue("""
                {
                      "type": "TURN",
                      "sailorId": "0",
                      "rotation": 0
                    }""", Action.class));
    }
    @Test
    void nullGoal() throws IOException {
        assertNull(myMapper.readValue("""
                {
                  "goal": {
                    "mode": "Nothing"
                  }
                }""", InitGame.class).getGoal());
    }
    @Test
    void nullVisibleEntities()throws IOException {
        assertNull(myMapper.readValue("""
                {
                     "type": "nothing",
                     "position": {
                       "x": 500,
                       "y": 0,
                       "orientation": 0
                     },
                     "shape": {
                       "type": "rectangle",
                       "width": 50,
                       "height": 500,
                       "orientation": 0
                     },
                     "strength": 40
                   }""".indent(1), VisibleEntity.class));
    }
    @Test
    void streamEntities()throws IOException {
        assertNotNull(myMapper.readValue("""
                {
                      "type": "stream",
                      "position": {
                        "x": 500,
                        "y": 0,
                        "orientation": 0
                      },
                      "shape": {
                        "type": "rectangle",
                        "width": 50,
                        "height": 500,
                        "orientation": 0
                      },
                      "strength": 40
                    }""", VisibleEntity.class));
    }
    @Test
    void reefEntities()throws IOException {
        assertNotNull(myMapper.readValue("""
                {
                      "type": "reef",
                      "position": {
                        "x": 240,
                        "y": 40,
                        "orientation": 0
                      },
                      "shape":{
                        "type": "circle",
                        "radius": 30
                      }
                    }""", VisibleEntity.class));
    }
    @Test
    void createRudder() throws JsonProcessingException {
        assertNotNull(myMapper.readValue("""
                {
                "type":"rudder",
                "x":"0",
                "y":"0"
                }
                """, Equipment.class));
    }
    @Test
    void createSail() throws JsonProcessingException {
        assertNotNull(myMapper.readValue("""
                {
                "type":"sail",
                "x":"0",
                "y":"0",
                "openned":"true"
                }
                """, Equipment.class));
    }
    @Test
    void createWatch() throws JsonProcessingException {
        assertNotNull(myMapper.readValue("""
                {
                "type":"watch",
                "x":"0",
                "y":"0"
                }
                """, Equipment.class));
    }
    @Test
    void createAnotherShip() throws JsonProcessingException {
        assertNotNull(myMapper.readValue("""
                 {
                      "type": "ship",
                      "position": {
                        "x": 0,
                        "y": 0,
                        "orientation": 0
                      },
                      "shape":{
                        "type": "circle",
                        "radius": 30
                      },
                      "life": 10
                    }""", VisibleEntity.class));
    }
}