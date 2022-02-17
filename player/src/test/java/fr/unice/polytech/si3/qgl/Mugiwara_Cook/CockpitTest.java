package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class CockpitTest {

    Cockpit cockpit;
    File jsonInit;

    @BeforeEach
    void setUp() {
        this.cockpit = new Cockpit();
        this.cockpit.initGame("{\n" +
                "  \"goal\": {\n" +
                "    \"mode\": \"REGATTA\",\n" +
                "    \"checkpoints\": [\n" +
                "      {\n" +
                "        \"position\": {\n" +
                "          \"x\": 60,\n" +
                "          \"y\": 60,\n" +
                "          \"orientation\": 0\n" +
                "        },\n" +
                "        \"shape\": {\n" +
                "          \"type\": \"circle\",\n" +
                "          \"radius\": 50\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"position\": {\n" +
                "          \"x\": 1000,\n" +
                "          \"y\": 0,\n" +
                "          \"orientation\": 0\n" +
                "        },\n" +
                "        \"shape\": {\n" +
                "          \"type\": \"circle\",\n" +
                "          \"radius\": 100\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"ship\": {\n" +
                "    \"type\": \"ship\",\n" +
                "    \"life\": 100,\n" +
                "    \"position\": {\n" +
                "      \"x\": 0,\n" +
                "      \"y\": 0,\n" +
                "      \"orientation\": 0\n" +
                "    },\n" +
                "    \"name\": \"Les copaings d'abord!\",\n" +
                "    \"deck\": {\n" +
                "      \"width\": 3,\n" +
                "      \"length\": 6\n" +
                "    },\n" +
                "    \"entities\": [\n" +
                "      {\n" +
                "        \"x\": 1,\n" +
                "        \"y\": 0,\n" +
                "        \"type\": \"oar\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"x\": 4,\n" +
                "        \"y\": 2,\n" +
                "        \"type\": \"oar\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"shape\": {\n" +
                "      \"type\": \"rectangle\",\n" +
                "      \"width\": 50,\n" +
                "      \"height\": 100,\n" +
                "      \"orientation\": 0\n" +
                "    }\n" +
                "  },\n" +
                "  \"sailors\": [\n" +
                "    {\n" +
                "      \"x\": 0,\n" +
                "      \"y\": 0,\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"Edward Teach\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"x\": 0,\n" +
                "      \"y\": 1,\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Edward Pouce\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"shipCount\": 1\n" +
                "}");
        this.cockpit.currentCheckpoint = this.cockpit.getCheckpoints()[0];
    }
    @Test
    void shipInCheckpoint(){
        assertTrue(this.cockpit.inCheckpoint());
    }

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