package fr.unice.polytech.si3.qgl.Mugiwara_Cook.game;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Action;

import java.util.ArrayList;
import java.util.List;

public class ActionJSON {
    List<Action> listAction = new ArrayList<>();

    public ActionJSON() {
        //Json
    }

    public void addAction(Action action) {
        listAction.add(action);
    }
}
