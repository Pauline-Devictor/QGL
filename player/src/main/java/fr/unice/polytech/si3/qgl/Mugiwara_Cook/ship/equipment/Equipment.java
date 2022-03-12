package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;

import static java.lang.Math.abs;

abstract public class Equipment {
    protected String type;
    protected int x;
    protected int y;

    @JsonIgnore
    boolean used = false;

    @JsonIgnore
    Sailor sailor = null;


    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }


    public Equipment(String type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public void setSailor(Sailor sailor) {
        this.sailor = sailor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Sailor getSailor() {
        return this.sailor;
    }

    public Sailor findClosestSailorWithOutAssignEquipment(Sailor[] sailors) {
        Sailor closestSailor = null;

        for (Sailor sailor : sailors) {
            if (!(sailor.assign()))
                closestSailor = sailor;
        }

        for (Sailor sailor : sailors) {
            if (closestSailor != null && sailor.sailorIsAllowedToMove(sailor.getX() - this.getX(), sailor.getY() - this.getY()) && !(sailor.assign())) {
                closestSailor = sailor;
            }
        }

        this.sailor = closestSailor;
        this.used = true;

        if (this.sailor == null)
            System.out.println(this.x + " et " + this.y + " assignee a: personne");
        else
            System.out.println(this.x + " et " + this.y + " assignee a: " + this.sailor.getId());
        return closestSailor;
    }

}
