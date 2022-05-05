package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import lombok.Getter;

public class LiftSail extends Action {
    public static final String liftSail = "LIFT_SAIL";
    @Getter
    //@Setter
    int sailorId;

    public LiftSail(int sailorId) {
        super(liftSail);
        this.sailorId = sailorId;
    }
}
