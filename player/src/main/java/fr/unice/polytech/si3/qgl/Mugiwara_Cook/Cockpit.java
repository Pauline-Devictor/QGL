package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Action;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Oar;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.VisibleEntity;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.AllPossibility;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.BestMove;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.allmoves.OarMove;
import fr.unice.polytech.si3.qgl.regatta.cockpit.ICockpit;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal.RegattaGoal;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;

public class Cockpit implements ICockpit {

    MyMapper myMapper = new MyMapper();
    InitGame initGame;
    NextRound nextRound;
    Checkpoint[] checkpoints;
    AllPossibility allPossibility;
    ArrayList<Action> m;
    Captain captain;
    Checkpoint currentCheckpoint;

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
            if (this.initGame.getGoal().getClass() == RegattaGoal.class)
                this.checkpoints = ((RegattaGoal) this.initGame.getGoal()).getCheckpoints();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        allPossibility = new AllPossibility(initGame);

        this.captain = new Captain(this.initGame.getShip(), this.initGame.getSailors());
        this.m = captain.sailorsMoveToOars();
        this.currentCheckpoint = this.checkpoints[0];
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
        BestMove bestMove = new BestMove(allPossibility, nextRound);
        bestMove.processing(this.checkpoints);

        OarMove oarMove = (OarMove) bestMove.getBestMove();

        int[] oarLeftRight = oarMove.getOar();

        for (int i = 0; i < oarLeftRight[0]; i++) {
            this.m.add(new Oar(this.captain.getSailorLeft().get(i)));
        }
        for (int i = 0; i < oarLeftRight[1]; i++) {
            this.m.add(new Oar(this.captain.getSailorRight().get(i)));
        }

        try {
            String json = new ObjectMapper().writeValueAsString(m);
            m.clear();
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

    public Checkpoint[] getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(Checkpoint[] checkpoints) {
        this.checkpoints = checkpoints;
    }

    public AllPossibility getAllPossibility() {
        return allPossibility;
    }

    public void setAllPossibility(AllPossibility allPossibility) {
        this.allPossibility = allPossibility;
    }

    /**
     * Determine if the ship will collide with the given entity
     * @param visibleEntity entity to check
     * @return boolean
     */
    public boolean isCollision(VisibleEntity visibleEntity){ //TODO autre forme
        if (visibleEntity.getShape() instanceof Circle)
            return (captain.getShip().getPosition().distance(visibleEntity.getPosition())
                <=((Circle) visibleEntity.getShape()).getRadius() );
        return false;
    }

    /**
     * Determine if the ship is in the Checkpoint
     * @return boolean
     */
    public boolean inCheckpoint(){
        return (captain.getShip().getPosition().distance(currentCheckpoint.getPosition())
        <=((Circle) currentCheckpoint.getShape()).getRadius() );
    }
}
