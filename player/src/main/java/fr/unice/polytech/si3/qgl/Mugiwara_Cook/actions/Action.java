package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import lombok.Getter;

abstract public class Action {
    @Getter
    //@Setter
    String type;

    protected Action(String type) {
        this.type = type;
    }
}
