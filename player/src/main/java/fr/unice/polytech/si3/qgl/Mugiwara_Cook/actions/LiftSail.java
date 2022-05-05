package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import lombok.Getter;

public class LiftSail extends Action {
    public static final String ActionType = "LIFT_SAIL";
    @Getter
    //@Setter
    int sailorId;

    public LiftSail(int sailorId) {
        super(ActionType);
        this.sailorId = sailorId;
    }
}
