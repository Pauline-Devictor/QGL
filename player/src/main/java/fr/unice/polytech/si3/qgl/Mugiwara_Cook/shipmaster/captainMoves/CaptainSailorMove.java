package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainMoves;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;

import java.util.ArrayList;
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
        assignSpecificEquipement("oar", 6);
//        assignSpecificEquipement("rudder", 1);
//        assignSpecificEquipement("oar", 4);
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
                numberAssign++;
            }
        }
    }

//    public ArrayList<Integer> howManySailorsForEachTypeOfEquipement() {
//        int maxOarLeft = this.initGame.getShip().getOarsLeft().size();
//        int maxOarRight = this.initGame.getShip().getOarsRight().size();
//        int oarLeft = 1;
//        int oarRight = 1;
//        int rudder = 0;
//
//        int sailor = this.initGame.getSailors().length - 2;
//        if (sailor - 1 > 0) {
//            rudder++;
//            sailor--;
//        }
//        while (sailor > 0) {
//            if (sailor > 1) {
//                oarLeft++;
//                oarRight++;
//                sailor -= 2;
//            } else {
//                oarLeft++;
//                sailor--;
//            }
//        }
//
//        return new ArrayList<Integer>(List.of(oarLeft, oarRight, rudder));
//    }


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
