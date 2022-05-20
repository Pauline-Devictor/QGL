package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;
import lombok.Getter;
import lombok.Setter;

public abstract class Equipment {
    @Getter
    //@Setter
    protected String type;
    @Getter
    @Setter
    protected int x;
    @Getter
    @Setter
    protected int y;

    @JsonIgnore
    boolean used = false;

    @JsonIgnore
    @Getter
    @Setter
    Sailor sailor = null;

    protected Equipment(String type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public Sailor findClosestSailorWithOutAssignEquipment(Sailor[] sailors) {
        Sailor closestSailor = null;

        for (Sailor currentSailor : sailors) {
            if (!(currentSailor.assign()))
                closestSailor = currentSailor;
        }

        for (Sailor currentSailor : sailors) {
            if (closestSailor != null && currentSailor.sailorIsAllowedToMove(currentSailor.getX() - this.getX(), currentSailor.getY() - this.getY()) && !(currentSailor.assign())) {
                closestSailor = currentSailor;
            }
        }

        this.sailor = closestSailor;
        this.used = true;

        return closestSailor;
    }

}
