package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
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
        jsonNext = new File("jsonTest//nextRound.json");
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
}