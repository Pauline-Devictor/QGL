package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import lombok.Getter;

public class Lower_Sail extends Action {
    public static final  String TYPE = "LOWER_SAIL";
    @Getter
    int sailorId;

    public Lower_Sail(int sailorId) {
        super(TYPE);
        this.sailorId = sailorId;
    }
}
