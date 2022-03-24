package fr.unice.polytech.si3.qgl.Mugiwara_Cook;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal.Goal;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal.RegattaGoal;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Deck;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InitGameTest {
    InitGame initGame;
    @BeforeEach
    void setup(){
        Position position = new Position(0,0,0);
        Shape shape = new Circle(50);

        Checkpoint checkpoint = new Checkpoint(position,shape);
        Checkpoint[] checkpoints = new Checkpoint[1];
        checkpoints[0] = checkpoint;
        Goal goal = new RegattaGoal(checkpoints);

        List< Equipment > entities = new ArrayList<>();
        Sailor[] sailors = new Sailor[2];
        sailors[0] = new Sailor(0,0,0,"Bob");
        sailors[1] = new Sailor(1,1,1,"Billy");
        Ship ship = new Ship(20,new Position(100,100,0),"Bateau",new Deck(100,200),entities,shape);
        initGame = new InitGame(goal,ship,sailors,1);
    }

    @Test
    void testSailorIsOnAssign(){
        //pas d'equipement assigne donc True
        assertTrue(initGame.allSailorIsOnAssign());
        //avec equipement
        initGame.getSailors()[0].setEquipment(new Oar(0,0));
        initGame.getSailors()[1].setEquipment(new Oar(0,1));
        assertFalse(initGame.allSailorIsOnAssign());
        initGame.getSailors()[1].setEquipment(new Oar(1,1));
        assertTrue(initGame.allSailorIsOnAssign());
    }

    @Test
    void sailorRudderNull(){
        assertFalse(initGame.sailorRudder());
        assertEquals(initGame.getUsableSailorRudder(),null);
    }
    @Test
    void sailorUsableTest(){
        assertTrue(initGame.getUsableSailorSail().isEmpty());
        //TODO rajouter le cas ou des sailors sont utilisables

    }
}
