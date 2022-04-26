package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainSailorMoves;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Sail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CaptainSailorMove {
    Ship ship;
    Sailor[] sailors;

    public CaptainSailorMove(Ship ship, Sailor[] sailors) {
        this.ship = ship;
        this.sailors = sailors;
    }

    /**
     * Assigne les equipements selon une priorité qu'il faudra affiner.
     */
    public void assignEquipement() {
        int nbsailors = this.sailors.length;
        if (nbsailors >= 2 && this.ship.getEquipement("oar").size() >= 2) {
            assignSpecificEquipement("oar", 2);
            nbsailors -= 2;
        }
        if (nbsailors >= 1 && this.ship.getEquipement("rudder").size() >= 1) {

            assignSpecificEquipement("rudder", 1);
            nbsailors--;
        }
        if (nbsailors >= 1 && this.ship.getEquipement("sail").size() >= 1) {

            assignSpecificEquipement("sail", 1);
            nbsailors--;
        }
        /**if (nbsailors >= 1 && this.ship.getEquipement("watch").size() >= 1) {

            assignSpecificEquipement("watch", 1);
            nbsailors--;
        }**/
        while (nbsailors >= 2) {
            if (nbsailors >= 2 && this.ship.getEquipement("oar").size() >= 2) {
                assignSpecificEquipement("oar", 2);
                nbsailors -= 2;
            }
        }
        if (nbsailors >= 1 && this.ship.getEquipement("sail").size() >= 2) {

            assignSpecificEquipement("sail", 1);
            nbsailors--;
        }
    }

    public void assignSpecificEquipement(String equipement, int numberSailorAssign) {
        int numberAssign = 0;
        List<Equipment> equipmentArrayList = this.ship.getEquipement(equipement).stream()
                .filter(equipment -> equipment.getSailor() == null)
                .collect(Collectors.toList());
        Display.info(equipmentArrayList.get(0).getType());
        while (numberAssign < numberSailorAssign && numberAssign < this.sailors.length) {
            Sailor sailor = equipmentArrayList.get(numberAssign).findClosestSailorWithOutAssignEquipment(this.sailors);
            if (sailor != null) {
                sailor.attachEquipment(equipmentArrayList.get(numberAssign));
                equipmentArrayList.get(numberAssign).setSailor(sailor);
                numberAssign++;
            }
        }
    }

    /**
     * Deplace les marins vers leur rame attitrée
     */
    public void moveToAssignEquipment(ActionJSON actionJSON) {
        for (int i = 0; i < this.sailors.length; i++) {
            if (this.sailors[i].getEquipment() != null && !(this.sailors[i].onIsAssignEquipment()))
                this.sailors[i].moveToEquipment(actionJSON);
        }
    }
}
