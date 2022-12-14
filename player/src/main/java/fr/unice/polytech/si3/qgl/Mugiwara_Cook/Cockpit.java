package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.*;

import fr.unice.polytech.si3.qgl.regatta.cockpit.*;
import lombok.Getter;
import lombok.Setter;


public class Cockpit implements ICockpit {

    MyMapper myMapper = new MyMapper();
    ActionJSON actionJSON = new ActionJSON();

    @Setter
    @Getter
    InitGame initGame;
    NextRound nextRound;

    @Getter
    Captain captain2;

    public Cockpit() {
        //Json
    }

    /**
     * Init the game datas
     *
     * @param game A Json with the beginning data
     */
    public void initGame(String game) {

        try {
            this.initGame = myMapper.readValue(game, InitGame.class);
        } catch (JsonProcessingException e) {
            //Display.debug(e.getMessage());
        }

        captain2 = new Captain(this.initGame, this.actionJSON);

    }

    /**
     * Decide what to do with round information
     *
     * @param round Json with data for the next round
     * @return Json with the list of our actions
     */
    public String nextRound(String round) {
        try {
            nextRound = myMapper.readValue(round, NextRound.class);
        } catch (JsonProcessingException e) {
            //Display.debug(e.getMessage());
        }

        captain2.nextMove(nextRound);

        try {
            String json = this.actionJSON.getActionJson();
            this.actionJSON.clearActions();
            return json;
        } catch (JsonProcessingException e) {
            //Display.debug(e.getMessage());
        }

        return null;
    }

    @Override
    public List<String> getLogs() {
        return new ArrayList<>();
    }
}
