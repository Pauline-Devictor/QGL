package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;

import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Rame
 */
public class Oar extends Equipment {
    public final static String TYPE = "oar";
    @JsonIgnore
    boolean used = false;


    public Oar(int x, int y) {
        super(TYPE, x, y);
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

}
