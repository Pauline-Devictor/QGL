package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;
import lombok.Getter;
import lombok.Setter;

import static java.lang.Math.abs;

/**
 * Gouvernail
 */
public class Rudder extends Equipment {
    public final static String TYPE = "rudder";
    @JsonIgnore
    @Setter
    boolean used = false;

    public Rudder(int x, int y) {
        super(TYPE, x, y);
    }

    public boolean isUsed() {
        return used;
    }
}
