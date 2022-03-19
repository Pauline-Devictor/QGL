package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import lombok.Getter;
import lombok.Setter;

public class Lower_Sail extends Action {
    public final static String TYPE = "LOWER_SAIL";
    @Getter
    @Setter
    int sailorId;

    public Lower_Sail(int sailorId) {
        super(TYPE);
        this.sailorId = sailorId;
    }
}
