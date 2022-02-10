package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Gouvernail
 */
public class Rudder extends Equipment{
    public final static String TYPE = "rudder";
    int x;
    int y;
    @JsonIgnore
    boolean used = false;

    public Rudder(int x, int y) {
        super(TYPE,x,y);
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
