package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import lombok.Getter;
import lombok.Setter;

public class Lift_Sail extends Action {
    public final static String TYPE = "LIFT_SAIL";
    @Getter
    //@Setter
    int sailorId;

    public Lift_Sail(int sailorId) {
        super(TYPE);
        this.sailorId = sailorId;
    }
}
