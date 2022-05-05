package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import org.junit.jupiter.api.BeforeEach;

class CockpitTest {

    Cockpit cockpit;

    @BeforeEach
    void setUp() {
        this.cockpit = new Cockpit();
        this.cockpit.initGame("""
                {
                  "goal": {
                    "mode": "REGATTA",
                    "checkpoints": [
                      {
                        "position": {
                          "x": 60,
                          "y": 60,
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
    }
//    @Test
//    void shipInCheckpoint(){
//        assertTrue(this.cockpit.inCheckpoint());
//    }

    /*@Test
    void nextRoundTest() {
        assertEquals("[ {\n" +
                "    \"sailorId\": 0,\n" +
                "    \"type\": \"OAR\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"sailorId\": 1,\n" +
                "    \"type\": \"OAR\"\n" +
                "  }\n" +
                "]", this.cockpit.nextRound("{}"));
    }*/
}