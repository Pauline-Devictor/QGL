package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import java.lang.Math;

import static java.lang.Math.abs;

public class Moving extends Action{ //TODO condition xdistance and ydistance
    public final static String TYPE = "MOVING";
    int sailorId;
    int xdistance;
    int ydistance;

    public Moving(int sailorId, int xdistance, int ydistance){
        super(TYPE);
        this.sailorId = sailorId;
        this.xdistance = xdistance;
        this.ydistance = ydistance;
    }

    /**
     * @param xdistance le nombre de déplacement en x
     * @param ydistance le nombre de déplacement en y
     * @return un boolean qui dit vrai s'il respect les conditions, faux sinon
     */
    public boolean isAllowedToMove(int xdistance, int ydistance){
        if(abs(xdistance)+abs(ydistance)<=5) return true;
        return false;
    }

    //public boolean isNotGoingToFallInTheSea(int sailorId, int xdistance, int ydistance){





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
