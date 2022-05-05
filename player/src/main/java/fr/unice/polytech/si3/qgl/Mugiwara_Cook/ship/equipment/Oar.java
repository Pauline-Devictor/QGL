package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Setter;

/**
 * Rame
 */
public class Oar extends Equipment {
    public static final String oar = "oar";
    @JsonIgnore
    @Setter
    boolean used = false;


    public Oar(int x, int y) {
        super(oar, x, y);
    }

    public boolean isUsed() {
        return used;
    }
}
