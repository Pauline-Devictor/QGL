package fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Action;

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
