package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;

import static java.lang.Math.abs;

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
