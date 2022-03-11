package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainSailorMoves;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;

import java.util.List;
import java.util.stream.Collectors;

public class CaptainSailorMove {
    InitGame initGame;
    ActionJSON actionJSON;

    public CaptainSailorMove(InitGame initGame, ActionJSON actionJSON) {
        this.initGame = initGame;
        this.actionJSON = actionJSON;
    }

    public void assignEquipement() {
        assignSpecificEquipement("oar", 10);
        assignSpecificEquipement("rudder",1);
    }

    private void assignSpecificEquipement(String equipement, int numberSailorAssign) {
        int numberAssign = 0;
        List<Equipment> equipmentArrayList = this.initGame.getShip().getEquipement(equipement).stream()
                .filter(equipment -> equipment.getSailor() == null)
                .collect(Collectors.toList());
        System.out.println(equipmentArrayList.get(0).getType());
        while (numberAssign < numberSailorAssign && numberAssign < this.initGame.getSailors().length) {
            Sailor sailor = equipmentArrayList.get(numberAssign).findClosestSailorWithOutAssignEquipment(initGame.getSailors());
            if (sailor != null) {
                sailor.attachEquipment(equipmentArrayList.get(numberAssign));
                equipmentArrayList.get(numberAssign).setSailor(sailor);
                numberAssign++;
            }
        }
    }

    /**
     * Deplace les marins vers leur rame attitré
     */
    public void moveToAssignEquipment() {
        for (int i = 0; i < this.initGame.getSailors().length; i++) {
            if (this.initGame.getSailors()[i].getEquipment() != null && !(this.initGame.getSailors()[i].onIsAssignEquipment()))
                this.initGame.getSailors()[i].moveToEquipment(this.actionJSON);
        }
    }

}
