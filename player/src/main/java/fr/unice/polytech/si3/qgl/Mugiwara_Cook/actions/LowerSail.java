package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import lombok.Getter;

public class LowerSail extends Action {
    public static final  String actionType = "LOWER_SAIL";
    @Getter
    int sailorId;

    public LowerSail(int sailorId) {
        super(actionType);
        this.sailorId = sailorId;
    }
}
