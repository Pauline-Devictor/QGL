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
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Rudder;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling.Moves;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdvanceSimulator {
    InitGame init;
    Cockpit cockpit = new Cockpit();
    Referee referee;
    private static final int TURN = 500;

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

        referee = new Referee(this.init);
    }

    private InitGame createInitGame() {
        Random rand = new Random();

//        Checkpoint checkpoint1 = new Checkpoint(new Position(500, 500, 0), new Circle(100));
//        Checkpoint checkpoint2 = new Checkpoint(new Position(0, -500, 0), new Circle(100));
//        Checkpoint checkpoint3 = new Checkpoint(new Position(500, -500, 0), new Circle(80));
//        Checkpoint checkpoint4 = new Checkpoint(new Position(-500, 500, 0), new Circle(100));


        Checkpoint checkpoint1 = new Checkpoint(new Position(-5586.701434159062, -195.31249999999972, 0), new Circle(200));
        Checkpoint checkpoint2 = new Checkpoint(new Position(-3259.452411994781, 1835.937500000001, 0), new Circle(200));
        Checkpoint checkpoint3 = new Checkpoint(new Position(-1401.564537157755, 4108.072916666667, 0), new Circle(200));
        Checkpoint checkpoint4 = new Checkpoint(new Position(-3650.5867014341584, 1529.9479166666674, 0), new Circle(200));
        Checkpoint checkpoint5 = new Checkpoint(new Position(-5495.436766623214, 4127.604166666669, 0), new Circle(200));
        Checkpoint checkpoint6 = new Checkpoint(new Position(-1140.8083441981753, -273.43749999999847, 0), new Circle(200));
        Checkpoint checkpoint7 = new Checkpoint(new Position(-3604.9543676662297, 2154.9479166666674, 0), new Circle(200));
        Checkpoint checkpoint8 = new Checkpoint(new Position(3337.6792698826616, 1809.8958333333335, 0), new Circle(200));
//        Checkpoint checkpoint9 = new Checkpoint(new Position(-3650.58670143416, 1842.4479166666663, 0), new Circle(100));
//        Checkpoint checkpoint5 = new Checkpoint(new Position(1132.5943610732181, 2342.236768018018, 0), new Circle(80));

        Checkpoint[] listCheckpoint = {checkpoint1, checkpoint2, checkpoint3, checkpoint4, checkpoint5, checkpoint6, checkpoint7, checkpoint8};
        Goal goal = new RegattaGoal(listCheckpoint);


        Deck deck = new Deck(3, 7);  //longeur-largeur

        Equipment oar1 = new Oar(1, 2);
        Equipment oar2 = new Oar(2, 2);
        Equipment oar3 = new Oar(3, 2);
        Equipment oar4 = new Oar(4, 2);
        Equipment oar5 = new Oar(5, 2);
        Equipment oar6 = new Oar(1, 0);
        Equipment oar7 = new Oar(2, 0);
        Equipment oar8 = new Oar(3, 0);
        Equipment oar9 = new Oar(4, 0);
        Equipment oar10 = new Oar(5, 0);


        Equipment rudder = new Rudder(3, 1);
        List<Equipment> equipmentList = new ArrayList<>(List.of(oar1, oar2, oar3, oar6, oar6, oar7, oar8, oar4, oar5, oar9, oar10, rudder));

        Shape shapeShip = new Rectangle(2, 4, 0);
//        Ship ship = new Ship(100, new Position(-3650.58670143416, 1842.4479166666663, -2.740166925631097), "BOAT", deck, equipmentList, shapeShip);
        Ship ship = new Ship(100, new Position(-3650.58670143416, 1842.4479166666663, -2.740166925631097), "BOAT", deck, equipmentList, shapeShip);

        final int NB_MARIN = 7;
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
        int checkpointNumber = 0;

        for (int i = 0; i < TURN; i++) {
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

            if (moves.inCheckpoint(checkpoint[checkpointNumber])) {
                checkpointNumber++;
            }
            if (checkpoint.length == checkpointNumber) {
                break;
            }
        }
        return route;
    }
}
