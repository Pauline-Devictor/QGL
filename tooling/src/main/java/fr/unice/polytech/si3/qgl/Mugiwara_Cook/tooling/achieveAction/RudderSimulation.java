package fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling.achieveAction;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Display;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Action;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Oar;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Turn;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Rudder;

import java.util.Arrays;
import java.util.List;

public class RudderSimulation {
    InitGame init;

    public RudderSimulation(InitGame initGame) {
        this.init = initGame;
    }

    public double whatAngle(List<Action> movingList) {
        for (Action action : movingList) {
            if (action.getType().equals("TURN")) {
                if (onRudder(getSailorFromId((Turn) action))) {
                    Display.info(getSailorFromId((Turn) action).getName());
                    return ((Turn) action).getRotation();
                }
            }
        }
        return 0;
    }

    public boolean onRudder(Sailor sailor) {
        for (Equipment equipment : this.init.getShip().getEntities()) {
            if (equipment.getType().equals("rudder") && sailor.getX() == equipment.getX() && sailor.getY() == equipment.getY()) {
                return true;
            }
        }
        return false;
    }

    private Sailor getSailorFromId(Turn move) {
        return Arrays.stream(this.init.getSailors()).filter(sailor1 -> sailor1.getId() == move.getSailorId()).toList().get(0);
    }

}


