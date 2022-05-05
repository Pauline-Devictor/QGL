package fr.unice.polytech.si3.qgl.Mugiwara_Cook.game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Action;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ActionJSON {
    @Getter
    List<Action> listAction;

    public ActionJSON() {
        listAction = new ArrayList<>();
    }

    public void addAction(Action action) {
        listAction.add(action);
    }

    public void addAction(ArrayList<Action> arraylistAction) {
        arraylistAction.forEach(this::addAction);
    }

    public void clearActions() {
        listAction.clear();
    }

    public String getActionJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(listAction);
    }
}
