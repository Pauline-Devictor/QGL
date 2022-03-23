package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import lombok.Getter;
import lombok.Setter;

public class Oar extends Action {
    public final static String TYPE = "OAR";
    @Getter
    //@Setter
    int sailorId;

    public Oar(int sailorId) {
        super(TYPE);
        this.sailorId = sailorId;
    }
}
