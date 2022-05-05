package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import lombok.Getter;

public class LowerSail extends Action {
    public static final  String lowerSail = "LOWER_SAIL";
    @Getter
    int sailorId;

    public LowerSail(int sailorId) {
        super(lowerSail);
        this.sailorId = sailorId;
    }
}
