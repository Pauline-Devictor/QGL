package fr.unice.polytech.si3.qgl.mugiwara_cook.actions;

public class Turn extends Action { //TODO turn condition
    public final static String TYPE = "TURN";
    int sailorId;
    double rotation;

    public Turn(int sailorId,double rotation){
        super(TYPE);
        this.sailorId = sailorId;
        this.rotation = rotation;
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
        this.rotation = rotation;
    }


}
