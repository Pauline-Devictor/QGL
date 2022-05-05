package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import lombok.Getter;

public class Oar extends Action {
    public static final String ACTIONTYPE = "OAR";
    @Getter
    int sailorId;

    public Oar(int sailorId) {
        super(ACTIONTYPE);
        this.sailorId = sailorId;
    }
}
