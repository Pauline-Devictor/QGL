package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment;

import lombok.Getter;
import lombok.Setter;

/**
 * Voile
 */
public class Sail extends Equipment {
    public static final String EQUIPMENTTYPE = "sail";
    @Getter
    @Setter
    boolean openned;

    public Sail(int x, int y, boolean openned) {
        super(EQUIPMENTTYPE, x, y);
        this.openned = openned;
    }
}
