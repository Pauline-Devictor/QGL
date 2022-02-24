package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Vigie
 */
public class Watch extends Equipment {
    public final static String TYPE = "watch";
    @JsonIgnore
    boolean used = false;

    public Watch(int x, int y) {
        super(TYPE,x,y);
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
