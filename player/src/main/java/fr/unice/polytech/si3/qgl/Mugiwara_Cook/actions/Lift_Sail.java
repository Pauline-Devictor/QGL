package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import lombok.Getter;

public class Lift_Sail extends Action {
    public static final  String TYPE = "LIFT_SAIL";
    @Getter
    //@Setter
    int sailorId;

    public Lift_Sail(int sailorId) {
        super(TYPE);
        this.sailorId = sailorId;
    }
}
