package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CockpitTest {

    Cockpit cockpit;

    @BeforeEach
    void setUp() {
        this.cockpit = new Cockpit();
    }

    @Test
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
    }
}