package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import lombok.Getter;



public class Moving extends Action {
    public static final String moving = "MOVING";
    @Getter
    int sailorId;
    @Getter
    int xdistance;
    @Getter
    int ydistance;

    public Moving(int sailorId, int xdistance, int ydistance) {
        super(moving);
        this.sailorId = sailorId;
        this.xdistance = xdistance;
        this.ydistance = ydistance;
    }
}
