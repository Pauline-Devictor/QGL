package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.AllPossibility;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.BestMove;
import fr.unice.polytech.si3.qgl.regatta.cockpit.ICockpit;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal.RegattaGoal;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;

public class Cockpit implements ICockpit {

    MyMapper myMapper = new MyMapper();
    InitGame initGame;
    NextRound nextRound;
    Checkpoint[] checkpoints;
    AllPossibility allPossibility;


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

        return "[ {\n" +
                "    \"sailorId\": 0,\n" +
                "    \"type\": \"OAR\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"sailorId\": 1,\n" +
                "    \"type\": \"OAR\"\n" +
                "  }\n" +
                "]";
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
}
