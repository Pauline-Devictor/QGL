package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Gouvernail
 */
public class Rudder extends Equipment{
    public final static String TYPE = "rudder";
    @JsonIgnore
    boolean used = false;

    public Rudder(int x, int y) {
        super(TYPE,x,y);
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
