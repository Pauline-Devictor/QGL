package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Cockpit;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;

abstract public class Action {
    String type;

    public Action(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
