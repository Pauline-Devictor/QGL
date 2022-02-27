package fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.MyMapper;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Action;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Moving;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.allmoves.Moves;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling.achieveAction.MovingSimulator;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling.achieveAction.OarSimulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Referee {
    MyMapper myMapper = new MyMapper();
    NextAction nextAction = null;
    InitGame init;

    MovingSimulator movingSimulator;
    OarSimulator oarSimulator;
    CalculMove calculMove = new CalculMove();

    public Referee(InitGame initGame) {
        this.init = initGame;

        this.movingSimulator = new MovingSimulator(this.init);
        this.oarSimulator = new OarSimulator(this.init);
    }

    public void read(String actionJSON) {
        try {
            nextAction = new NextAction(myMapper.readValue(actionJSON, Action[].class));
        } catch (JsonProcessingException e) {
            System.out.println("MDR");
            e.printStackTrace();
        }
    }

    public Moves execute() {
        List<Action> actionList = Arrays.stream(this.nextAction.getActionList()).toList();
        movingSimulator.movingSimulator(actionList);
        int[] oars = oarSimulator.whoRow(actionList);


        Moves moves = this.calculMove.calcul(this.init.getShip(), oars);

        this.init.getShip().getPosition().setX(moves.getX());
        this.init.getShip().getPosition().setY(moves.getY());
        this.init.getShip().getPosition().setOrientation(moves.getOrientation());

//        System.out.println("OAR: " + oars[0] + " ][ " + oars[1] + "-|SAME|- x: " + init.getShip().getPosition().getX() + ", y: " + init.getShip().getPosition().getY());
    return moves;
    }

}




