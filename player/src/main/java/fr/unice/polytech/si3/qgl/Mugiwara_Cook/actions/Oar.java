package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import lombok.Getter;

public class Oar extends Action {
    public static final String ACTIONTYPE = "OAR";

    public Oar(int sailorId) {
        super(ACTIONTYPE,sailorId);
    }
}
