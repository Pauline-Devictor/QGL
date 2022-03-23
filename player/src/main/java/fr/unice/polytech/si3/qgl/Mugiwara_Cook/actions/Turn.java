package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import lombok.Getter;
import lombok.Setter;

import static java.lang.Math.abs;

public class Turn extends Action {
    public final static String TYPE = "TURN";
    @Getter
    //@Setter
    int sailorId;
    @Getter
    double rotation;

    public Turn(int sailorId, double rotation) {
        super(TYPE);
        this.sailorId = sailorId;
        if (abs(rotation) <= Math.PI/2)
            this.rotation = rotation;
        else
            rotation = 0;
    }

    /**public void setRotation(double rotation) {
        if (abs(rotation) <= 90)
            this.rotation = rotation;
    }**/
}
