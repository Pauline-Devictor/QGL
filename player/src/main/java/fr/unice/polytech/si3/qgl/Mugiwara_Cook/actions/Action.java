package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import lombok.Getter;
import lombok.Setter;

public abstract class Action {

    @Getter
    String type;
    @Getter
    @Setter
    int sailorId;
    protected Action(String type, int sailorId) {
        this.type = type;
        this.sailorId = sailorId;
    }
}
