package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainMoves;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.*;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.*;

public class CaptainSailorMove {
    InitGame initGame;
    ActionJSON actionJSON;

    public CaptainSailorMove(InitGame initGame, ActionJSON actionJSON) {
        this.initGame = initGame;
        this.actionJSON = actionJSON;
    }

    /**
     * /**
     * Assigne une rame a un marin et inversement
     * <p>
     * public void assignOar() {
     * this.initGame.getShip().getOars().forEach(oar -> {
     * Sailor sailor = oar.findClosestSailorWithOutAssignEquipment(initGame.getSailors());
     * if (sailor != null)
     * sailor.attachEquipment(oar);
     * });
     * }
     **/


    public void assignEquipement() {
        String[] types = {"rudder", "oar"};
        int i = initGame.getShip().getRudder().size();
        int j = initGame.getShip().getOars().size();
        for (int k = 0; k < initGame.getSailors().length - 1; k++) {
            if (k < i) {
                this.initGame.getShip().getEquipement("rudder").forEach(equipment -> {
                    Sailor sailor = equipment.findClosestSailorWithOutAssignEquipment(initGame.getSailors());
                    if (sailor != null)
                        sailor.attachEquipment(equipment);
                });
            } else {
                this.initGame.getShip().getEquipement("oar").forEach(equipment -> {
                    Sailor sailor = equipment.findClosestSailorWithOutAssignEquipment(initGame.getSailors());
                    if (sailor != null)
                        sailor.attachEquipment(equipment);
                });
            }


        }
    }


    /**
     * Deplace les marins vers leur rame attitré
     */
    public void moveToAssignEquipment() {
        for (int i = 0; i < this.initGame.getSailors().length; i++) {
            if (!(this.initGame.getSailors()[i].onIsAssignEquipment()))
                this.initGame.getSailors()[i].moveToEquipment(this.actionJSON);
        }
    }

}
