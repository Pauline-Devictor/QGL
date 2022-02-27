package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.allmoves.Moves;
import fr.unice.polytech.si3.qgl.regatta.cockpit.*;


public class Cockpit implements ICockpit {

    MyMapper myMapper = new MyMapper();
    ActionJSON actionJSON = new ActionJSON();

    InitGame initGame;
    NextRound nextRound;

    Captain2 captain2;

    public Cockpit() {
        //Json
    }

    /**
     * Init the game datas
     *
     * @param game A Json with the beginning data
     */
    public void initGame(String game) {
        System.out.println("Init game input: " + game);

        try {
            this.initGame = myMapper.readValue(game, InitGame.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        captain2 = new Captain2(this.initGame, this.actionJSON);

    }

    /**
     * Decide what to do with round information
     *
     * @param round Json with data for the next round
     * @return Json with the list of our actions
     */
    public String nextRound(String round) {
        System.out.println("Next round input: " + round);

        try {
            nextRound = myMapper.readValue(round, NextRound.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        captain2.nextMove(nextRound);

        try {
            String json = this.actionJSON.getActionJson();
            this.actionJSON.clearActions();
            System.out.println(json);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<String> getLogs() {
        return new ArrayList<>();
    }

    public MyMapper getMyMapper() {
        return myMapper;
    }

    public void setMyMapper(MyMapper myMapper) {
        this.myMapper = myMapper;
    }

    public InitGame getInitGame() {
        return initGame;
    }

    public void setInitGame(InitGame initGame) {
        this.initGame = initGame;
    }

    public NextRound getNextRound() {
        return nextRound;
    }

    public void setNextRound(NextRound nextRound) {
        this.nextRound = nextRound;
    }

    public Captain2 getCaptain2() {
        return captain2;
    }

//    /**
//     * Determine if the ship will collide with the given entity
//     * @param visibleEntity entity to check
//     * @return boolean
//     */
//    public boolean isCollision(VisibleEntity visibleEntity){ //TODO autre forme
//        if (visibleEntity.getShape() instanceof Circle)
//            return (captain.getShip().getPosition().distance(visibleEntity.getPosition())
//                <=((Circle) visibleEntity.getShape()).getRadius() );
//        return false;
//    }

//    /**
//     * Determine if the ship is in the Checkpoint
//     * @return boolean
//     */
//    public boolean inCheckpoint(){
//        return (captain.getShip().getPosition().distance(currentCheckpoint.getPosition())
//        <=((Circle) currentCheckpoint.getShape()).getRadius() );
//    }
}
