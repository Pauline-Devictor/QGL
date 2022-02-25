package fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Cockpit;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Rectangle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal.Goal;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal.RegattaGoal;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.VisibleEntity;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Wind;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Deck;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Sail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdvanceSimulator {
    InitGame init;
    Cockpit cockpit = new Cockpit();
    Referee referee;

    public AdvanceSimulator() {
        this.init = this.createInitGame();

        String initGamejSON = null;
        try {
            initGamejSON = new ObjectMapper().writeValueAsString(init);
            System.out.println("InitGame cree: " + initGamejSON);
            cockpit.initGame(initGamejSON);
        } catch (Exception e) {
            e.printStackTrace();
        }

        cockpit.initGame(initGamejSON);

        referee = new Referee(this.init);
    }

    private InitGame createInitGame() {
        Random rand = new Random();

        Shape shapeCheckpoint = new Circle(50);
        Checkpoint checkpoint1 = new Checkpoint(new Position(0, -500, 0), shapeCheckpoint);
        Checkpoint[] listCheckpoint = {checkpoint1};
        Goal goal = new RegattaGoal(listCheckpoint);


        Deck deck = new Deck(3, 6);  //longeur-largeur

        Equipment oar1 = new Oar(1, 0);
        Equipment oar2 = new Oar(1, 2);
        Equipment oar3 = new Oar(2, 0);
        Equipment oar4 = new Oar(2, 2);
        List<Equipment> equipmentList = new ArrayList<>(List.of(oar1, oar2, oar3, oar4));

        Shape shapeShip = new Rectangle(3, 6, 0);
        Ship ship = new Ship(100, new Position(0, 0, 0), "BOAT", deck, equipmentList, shapeShip);

        final int NB_MARIN = 4;
        Sailor[] listSailor = new Sailor[NB_MARIN];
        for (int i = 0; i < NB_MARIN; i++) {
            listSailor[i] = new Sailor(i, rand.nextInt(deck.getLength()), rand.nextInt(deck.getWidth()), "Marin" + i);
        }

        int shipCount = 1;

        return new InitGame(goal, ship, listSailor, shipCount);
    }

    public void createNextRound() {
        NextRound nextRound = new NextRound(this.init.getShip(), new Wind(0, 0), null);

        for (int i = 0; i < 100; i++) {
            String nextRoundjSON = null;
            String action = null;
            try {
                nextRoundjSON = new ObjectMapper().writeValueAsString(nextRound);
                System.out.println("NextRound cree: " + nextRoundjSON);
                action = cockpit.nextRound(nextRoundjSON);
                System.out.println("STOP...............");
            } catch (Exception e) {
                System.out.println("STOOOOOOOOOOOOOoooooooooooooooo...............");
                e.printStackTrace();
            }

            System.out.println("Action recup: " + action);

            referee.read(action);  //.substring(1, action.length() - 1)
            referee.execute();
        }
    }
}
