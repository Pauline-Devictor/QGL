package fr.unice.polytech.si3.qgl.mugiwara_cook.actions;

public class Moving extends Action{ //TODO condition xdistance and ydistance
    public final static String TYPE = "Moving";
    String type = TYPE;
    int sailorId;
    int xdistance;
    int ydistance;

    public Moving(int sailorId, int xdistance, int ydistance){
        super(TYPE);
        this.sailorId = sailorId;
        this.xdistance = xdistance;
        this.ydistance = ydistance;
    }

    public int getSailorId() {
        return sailorId;
    }

    public void setSailorId(int sailorId) {
        this.sailorId = sailorId;
    }

    public int getXdistance() {
        return xdistance;
    }

    public void setXdistance(int xdistance) {
        this.xdistance = xdistance;
    }

    public int getYdistance() {
        return ydistance;
    }

    public void setYdistance(int ydistance) {
        this.ydistance = ydistance;
    }
}
