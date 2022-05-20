package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import lombok.Getter;

public class LiftSail extends Action {
    public static final String ACTIONTYPE = "LIFT_SAIL";

    public LiftSail(int sailorId) {
        super(ACTIONTYPE, sailorId);
    }
}
