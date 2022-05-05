package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Gouvernail
 */
public class Rudder extends Equipment {
    public static final String equipmentType = "rudder";

    public Rudder(int x, int y) {
        super(equipmentType, x, y);
    }


}
