package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Vigie
 */
public class Watch extends Equipment {
    public static final String watch = "watch";
    @JsonIgnore
    //@Setter
    boolean used = false;

    public Watch(int x, int y) {
        super(watch, x, y);
    }

    public boolean isUsed() {
        return used;
    }
}
