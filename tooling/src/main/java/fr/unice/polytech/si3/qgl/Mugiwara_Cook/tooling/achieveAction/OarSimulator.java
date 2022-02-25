package fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling.achieveAction;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Action;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Moving;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Oar;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OarSimulator {
    InitGame init;

    ArrayList<Integer> idUsedOarList;

    public OarSimulator(InitGame init) {
        this.init = init;
        this.idUsedOarList = new ArrayList<>();
    }

    public int[] whoRow(List<Action> movingList) {
        int[] oars = new int[2];

        movingList.forEach(action ->
        {
            if (action.getType().equals("OAR")) {
                if (onOar(getSailorFromId((Oar) action))) {
                    if (onLeft(getSailorFromId((Oar) action))) oars[0]++;
                    else oars[1]++;
                    idUsedOarList.add(((Oar) action).getSailorId());
                }
            }
        });
        return oars;
    }

    private Sailor getSailorFromId(Oar move) {
        return Arrays.stream(this.init.getSailors()).filter(sailor1 -> sailor1.getId() == move.getSailorId()).toList().get(0);
    }

    private boolean onOar(Sailor sailor) {
        for (Equipment equipment : this.init.getShip().getEntities()) {
            if (equipment.getType().equals("oar")) {
                return sailor.getX() == equipment.getX() && sailor.getY() == equipment.getY();
            }
        }
        return false;
    }

    private boolean onLeft(Sailor sailor) {
        if (sailor.getX() == 0) return true;
        return false;
    }

    private void oarRestore() {
        idUsedOarList.clear();
    }
}
