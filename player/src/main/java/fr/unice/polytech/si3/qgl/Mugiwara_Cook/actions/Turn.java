package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import lombok.Getter;

import static java.lang.Math.abs;

public class Turn extends Action {
    public static final  String TYPE = "TURN";
    @Getter
    int sailorId;
    @Getter
    double rotation;

    public Turn(int sailorId, double rotation) {
        super(TYPE);
        this.sailorId = sailorId;
        if (abs(rotation) <= Math.PI/2)
            this.rotation = rotation;
        else
            this.rotation = 0;
    }

    /**public void setRotation(double rotation) {
        if (abs(rotation) <= 90)
            this.rotation = rotation;
    }**/
}
