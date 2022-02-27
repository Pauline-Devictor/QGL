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
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Wind;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Deck;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.allmoves.Moves;

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

        Checkpoint checkpoint1 = new Checkpoint(new Position(1890.4823989569727, -1738.2812500000007, 0), new Circle(100));
        Checkpoint checkpoint2 = new Checkpoint(new Position(-143.41590612777003, 1861.9791666666665, 0), new Circle(80));
        Checkpoint[] listCheckpoint = {checkpoint1, checkpoint2};
        Goal goal = new RegattaGoal(listCheckpoint);


        Deck deck = new Deck(2, 4);  //longeur-largeur

        Equipment oar1 = new Oar(1, 0);
        Equipment oar2 = new Oar(1, 1);
        Equipment oar3 = new Oar(2, 0);
        Equipment oar4 = new Oar(2, 1);
        Equipment oar5 = new Oar(3, 0);
        Equipment oar6 = new Oar(3, 1);
        List<Equipment> equipmentList = new ArrayList<>(List.of(oar1, oar2, oar3, oar4, oar5, oar6));

        Shape shapeShip = new Rectangle(2, 4, 0);
        Ship ship = new Ship(100, new Position(0, 0, 0), "BOAT", deck, equipmentList, shapeShip);

        final int NB_MARIN = 6;
        Sailor[] listSailor = new Sailor[NB_MARIN];
        for (int i = 0; i < NB_MARIN; i++) {
            listSailor[i] = new Sailor(i, rand.nextInt(deck.getLength()), rand.nextInt(deck.getWidth()), "Marin" + i);
        }

        int shipCount = 1;

        return new InitGame(goal, ship, listSailor, shipCount);
    }

    public Route createNextRound() {
        Route route = new Route(((RegattaGoal) this.init.getGoal()).getCheckpoints());
        route.addPosition(this.init.getShip().getPositionRoute());
        Checkpoint[] checkpoint = ((RegattaGoal) this.init.getGoal()).getCheckpoints();
        int checkpointNumber =0;

        for (int i = 0; i < 1000; i++) {
            NextRound nextRound = new NextRound(this.init.getShip(), new Wind(0, 0), null);

            String nextRoundjSON;
            String action = null;
            try {
                nextRoundjSON = new ObjectMapper().writeValueAsString(nextRound);
                action = cockpit.nextRound(nextRoundjSON);
            } catch (Exception e) {
                e.printStackTrace();
            }

            referee.read(action);
            Moves moves = referee.execute();
            route.addPosition(moves.getDetailPosition());

            if (moves.inCheckpoint(checkpoint[checkpointNumber])){
                checkpointNumber++;
            }
            if (checkpoint.length == checkpointNumber){
                break;
            }
        }
        return route;
    }
}
