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
     * Assigne une rame a un marin et inversement
     */
    public void assignOar() {
        this.initGame.getShip().getOars().forEach(oar -> {
            Sailor sailor = oar.findClosestSailorWithOutAssignOar(initGame.getSailors());
            if (sailor != null)
                sailor.attachOar(oar);
        });
    }

    /**
     * Deplace les marins vers leur rame attitré
     */
    public void moveToAssignOar() {
        for (int i = 0; i < this.initGame.getSailors().length; i++) {
            if (!(this.initGame.getSailors()[i].onIsAssignOar()))
                this.initGame.getSailors()[i].moveToOar(this.actionJSON);
        }
    }

}
