package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment;

import lombok.Getter;
import lombok.Setter;

/**
 * Voile
 */
public class Sail extends Equipment {
    public final static String TYPE = "sail";
    @Getter
    @Setter
    boolean openned;

    public Sail(int x, int y, boolean openned) {
        super(TYPE, x, y);
        this.openned = openned;
    }
}
