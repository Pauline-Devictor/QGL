package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import lombok.Getter;

public class LowerSail extends Action {
    public static final  String ACTIONTYPE = "LOWER_SAIL";
    @Getter
    int sailorId;

    public LowerSail(int sailorId) {
        super(ACTIONTYPE);
        this.sailorId = sailorId;
    }
}
