package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Cockpit;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.MyMapper;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

class CaptainTest {
    Cockpit cockpit;
    Captain captain;
    MyMapper myMapper;
    File jsonNext;
    NextRound next;

    @BeforeEach
    void setUp() throws IOException {
        this.cockpit = new Cockpit();
        this.cockpit.initGame("""
                {
                  "goal": {
                    "mode": "REGATTA",
                    "checkpoints": [
                      {
                        "position": {
                          "x": 0,
                          "y": 0,
                          "orientation": 0
                        },
                        "shape": {
                          "type": "circle",
                          "radius": 50
                        }
                      },
                      {
                        "position": {
                          "x": 1000,
                          "y": 0,
                          "orientation": 0
                        },
                        "shape": {
                          "type": "circle",
                          "radius": 100
                        }
                      }
                    ]
                  },
                  "ship": {
                    "type": "ship",
                    "life": 100,
                    "position": {
                      "x": 0,
                      "y": 0,
                      "orientation": 0
                    },
                    "name": "Les copaings d'abord!",
                    "deck": {
                      "width": 3,
                      "length": 6
                    },
                    "entities": [
                      {
                        "x": 1,
                        "y": 0,
                        "type": "oar"
                      },
                      {
                        "x": 4,
                        "y": 2,
                        "type": "oar"
                      }
                    ],
                    "shape": {
                      "type": "rectangle",
                      "width": 50,
                      "height": 100,
                      "orientation": 0
                    }
                  },
                  "sailors": [
                    {
                      "x": 0,
                      "y": 0,
                      "id": 0,
                      "name": "Edward Teach"
                    },
                    {
                      "x": 0,
                      "y": 1,
                      "id": 1,
                      "name": "Edward Pouce"
                    }
                  ],
                  "shipCount": 1
                }""");
        myMapper = new MyMapper();
        jsonNext = new File("jsonTest//nextRound.json");
        next = myMapper.readValue(jsonNext, NextRound.class);
        this.captain = this.cockpit.getCaptain2();
    }
    @Test
    void shipInCheckpoint(){
        assertTrue(captain.inCheckpoint(next));
    }

    @Test
    void shipInLimitOfCheckpoint(){
        captain.getCurrentCheckpoint().getPosition().setX(-50);
        assertTrue(captain.inCheckpoint(next));
    }

    @Test
    void shipOutOfCheckpoint(){
        captain.getCurrentCheckpoint().getPosition().setX(100);
        assertFalse(captain.inCheckpoint(next));
    }

    @Test
    void shipInWithWidthCheckpoint(){
        captain.getCurrentCheckpoint().getPosition().setX(99);
        assertFalse(captain.inCheckpoint(next));
    }

    @Test
    void shipInWithHeightCheckpoint(){
        captain.getCurrentCheckpoint().getPosition().setY(100);
        assertFalse(captain.inCheckpoint(next));
    }

}
