package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Cockpit;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.MyMapper;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.ActionJSON;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Rectangle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Reef;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.VisibleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CaptainTest {
    Cockpit cockpit;
    Captain captain;
    MyMapper myMapper;
    File jsonNext;
    NextRound next;

    @BeforeEach
    void setUp() throws IOException {
        this.cockpit = new Cockpit();
        this.cockpit.initGame("{\n" +
                "  \"goal\": {\n" +
                "    \"mode\": \"REGATTA\",\n" +
                "    \"checkpoints\": [\n" +
                "      {\n" +
                "        \"position\": {\n" +
                "          \"x\": 0,\n" +
                "          \"y\": 0,\n" +
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

    @Test
    void updateMapNewCheckpoints(){
        VisibleEntity[] visibleEntityList =  new VisibleEntity[1];
        visibleEntityList[0] = new Reef(new Position(6, 6, 0), new Rectangle(20, 20, 0));
        next.setVisibleEntities(visibleEntityList);
        ArrayList<Checkpoint> checkpoints = captain.checkpointsPath;

        captain.updateMap(next);
        assertTrue(checkpoints.size() != captain.checkpointsPath.size());
    }

}
