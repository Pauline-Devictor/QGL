package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainSailorMoves;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import java.util.List;

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
        int oars=this.ship.getEquipement("oar").size();
        int sail=this.ship.getEquipement("sail").size();
        if (nbsailors >= 2 && oars >= 2) {
            assignSpecificEquipement("oar", 2);
            nbsailors -= 2;
            oars-=2;
        }
        if (nbsailors >= 1 && !this.ship.getEquipement("rudder").isEmpty()) {

            assignSpecificEquipement("rudder", 1);
            nbsailors--;
        }
        if (nbsailors >= 1 && sail>0) {

            assignSpecificEquipement("sail", 1);
            sail--;
            nbsailors--;
        }
        if (nbsailors >= 1 && !this.ship.getEquipement("watch").isEmpty()) {

            assignSpecificEquipement("watch", 1);
            nbsailors--;
        }
        while (nbsailors >= 2) {
            if (oars >= 2) {
                assignSpecificEquipement("oar", 2);
                nbsailors -= 2;
                oars-=2;
            }
            else break;

        }
        if (nbsailors >= 1 && sail>= 1) {

            assignSpecificEquipement("sail", 1);
        }

    }

    public void assignSpecificEquipement(String equipement, int numberSailorAssign) {
        int numberAssign = 0;
        List<Equipment> equipmentArrayList = this.ship.getEquipement(equipement).stream()
                .filter(equipment -> equipment.getSailor() == null).toList();
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
        for (Sailor sailor : this.sailors) {
            if (sailor.getEquipment() != null && !(sailor.onIsAssignEquipment()))
                sailor.moveToEquipment(actionJSON);
        }
    }
}
