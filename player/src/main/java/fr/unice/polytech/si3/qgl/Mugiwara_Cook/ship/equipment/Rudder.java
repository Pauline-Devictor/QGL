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
    @JsonIgnore
    Sailor sailor = null;

    public Rudder(int x, int y) {
        super(TYPE,x,y);
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
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
        this.used=true;

        if (this.sailor == null)
            System.out.println(this.x + " et " + this.y + " assignee a: personne");
        else
            System.out.println(this.x + " et " + this.y + " assignee a: " + this.sailor.getId());
        return closestSailor;
    }
}
