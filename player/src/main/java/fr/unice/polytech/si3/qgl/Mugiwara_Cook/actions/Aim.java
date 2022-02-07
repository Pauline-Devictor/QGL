package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import static java.lang.Math.abs;

public class Aim extends Action{
    public final static String TYPE = "AIM";
    int sailorId;
    double angle;

    public Aim(int sailorId, double angle){
        super(TYPE);
        this.sailorId = sailorId;
        if(abs(angle)<=90)
            this.angle = angle;
        else
            angle=0;
    }

    public int getSailorId() {
        return sailorId;
    }

    public void setSailorId(int sailorId) {
        this.sailorId = sailorId;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        if(abs(angle)<=90)
            this.angle = angle;
    }
}
