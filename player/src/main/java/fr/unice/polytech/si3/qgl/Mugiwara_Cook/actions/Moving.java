package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import lombok.Getter;



public class Moving extends Action {
    public static final  String TYPE = "MOVING";
    @Getter
    int sailorId;
    @Getter
    int xdistance;
    @Getter
    int ydistance;

    public Moving(int sailorId, int xdistance, int ydistance) {
        super(TYPE);
        this.sailorId = sailorId;
        this.xdistance = xdistance;
        this.ydistance = ydistance;
    }
}
