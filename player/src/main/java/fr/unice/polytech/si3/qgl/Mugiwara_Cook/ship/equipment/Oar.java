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
    @JsonIgnore
    Sailor sailor = null;


    public Oar(int x, int y) {
        super(TYPE, x, y);
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Sailor getSailor() {
        return this.sailor;
    }


    @Override
    public Sailor findClosestSailorWithOutAssignEquipment(Sailor[] sailors) {
        Sailor closestSailor = null;

        for (Sailor sailor : sailors) {
            if (!(sailor.assign()))
                closestSailor = sailor;
        }

        for (Sailor sailor : sailors) {
            if (closestSailor != null && sailor.sailorIsAllowedToMove(sailor.getX() - this.getX(),sailor.getY() - this.getY()) && !(sailor.assign())) {
                closestSailor = sailor;
            }
        }

        this.sailor = closestSailor;

        if (this.sailor == null)
            System.out.println(this.x + " et " + this.y + " assignee a: personne");
        else
            System.out.println(this.x + " et " + this.y + " assignee a: " + this.sailor.getId());
        return closestSailor;
    }
}
