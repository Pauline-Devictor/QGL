package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import lombok.Getter;
import lombok.Setter;

public abstract class Action {
    @Getter
    @Setter
    int sailorId;
    @Getter
    String type;

    protected Action(String type, int sailorId) {
        this.type = type;
        this.sailorId = sailorId;
    }
}
