package fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.MyMapper;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Action;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling.achieveAction.MovingSimulator;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling.achieveAction.OarSimulator;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling.achieveAction.RudderSimulation;

import java.util.Arrays;
import java.util.List;

public class Referee {
    MyMapper myMapper = new MyMapper();
    NextAction nextAction = null;
    InitGame init;
    RudderSimulation  rudderSimulation;

    MovingSimulator movingSimulator;
    OarSimulator oarSimulator;
    CalculMove calculMove = new CalculMove();

    public Referee(InitGame initGame) {
        this.init = initGame;

        this.movingSimulator = new MovingSimulator(this.init);
        this.oarSimulator = new OarSimulator(this.init);
        this.rudderSimulation = new RudderSimulation(this.init);
    }

    public void read(String actionJSON) {
        try {
            nextAction = new NextAction(myMapper.readValue(actionJSON, Action[].class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public Moves execute() {
        List<Action> actionList = Arrays.stream(this.nextAction.getActionList()).toList();
        movingSimulator.movingSimulator(actionList);

        int[] oars = oarSimulator.whoRow(actionList);
        double angleRudder = rudderSimulation.whatAngle(actionList);
//        double distanceSail =

        Moves moves = this.calculMove.calcul(this.init.getShip(), oars, angleRudder);

        this.init.getShip().getPosition().setX(moves.getX());
        this.init.getShip().getPosition().setY(moves.getY());
        this.init.getShip().getPosition().setOrientation(moves.getOrientation());

        return moves;
    }

}




