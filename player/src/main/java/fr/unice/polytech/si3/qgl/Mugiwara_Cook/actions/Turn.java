package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import static java.lang.Math.abs;

public class Turn extends Action {
    public final static String TYPE = "TURN";
    int sailorId;
    double rotation;

    public Turn(int sailorId,double rotation){
        super(TYPE);
        this.sailorId = sailorId;
        if(abs(rotation)<=90)
            this.rotation = rotation;
        else
            rotation=0;
    }

    public int getSailorId() {
        return sailorId;
    }

    public void setSailorId(int sailorId) {
        this.sailorId = sailorId;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        if(abs(rotation)<=90)
            this.rotation = rotation;
    }

}
