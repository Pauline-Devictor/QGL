package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

public class Aim extends Action{ //TODO angle condition
    public final static String TYPE = "AIM";
    int sailorId;
    double angle;

    public Aim(int sailorId, double angle){
        super(TYPE);
        this.sailorId = sailorId;
        this.angle = angle;
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
        this.angle = angle;
    }
    //TODO
}
