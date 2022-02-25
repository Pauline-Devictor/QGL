package fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Action;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal.Goal;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;

import java.util.List;

public class NextAction {
    Action[] actionList;

    public NextAction() {
        //Json
    }

    public NextAction(Action[] actionList) {
        this.actionList = actionList;
    }

    public Action[] getActionList(){
        return this.actionList;
    }
}
