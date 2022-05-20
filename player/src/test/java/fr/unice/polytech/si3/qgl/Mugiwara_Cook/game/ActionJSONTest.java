package fr.unice.polytech.si3.qgl.Mugiwara_Cook.game;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Action;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Turn;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActionJSONTest {
    ActionJSON actionJSON;

    @Test
    void clear(){
        Turn turn=new Turn(1,0);
        actionJSON=new ActionJSON();
        actionJSON.addAction(turn);
        assertEquals(actionJSON.getListAction().get(0),turn);
        actionJSON.clearActions();
        assertEquals(actionJSON.getListAction().isEmpty(),true);
    }

    @Test
    void getActionJson(){
        Turn turn=new Turn(1,0);
        actionJSON=new ActionJSON();
        actionJSON.addAction(turn);
        try {
            String string = actionJSON.getActionJson();
            assertEquals(string,"[{\"type\":\"TURN\",\"sailorId\":1,\"rotation\":0.0}]");
        }
        catch (JsonProcessingException e){
        }
    }


}