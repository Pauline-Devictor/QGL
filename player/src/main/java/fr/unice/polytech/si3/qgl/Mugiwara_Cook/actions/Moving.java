package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import lombok.Getter;



public class Moving extends Action {
    public static final String ACTIONTYPE = "MOVING";
    @Getter
    int xdistance;
    @Getter
    int ydistance;

    public Moving(int sailorId, int xdistance, int ydistance) {
        super(ACTIONTYPE,sailorId);
        this.xdistance = xdistance;
        this.ydistance = ydistance;
    }
}
