package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import lombok.Getter;

public class Oar extends Action {
    public static final String ActionType = "OAR";
    @Getter
    int sailorId;

    public Oar(int sailorId) {
        super(ActionType);
        this.sailorId = sailorId;
    }
}
