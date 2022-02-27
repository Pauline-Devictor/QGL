package fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling.achieveAction;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Action;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Moving;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovingSimulator {
    ArrayList<Sailor> sailorsDie;
    InitGame init;
    ArrayList<Sailor> alreadyMove;

    public MovingSimulator(InitGame initGame) {
        this.init = initGame;
<<<<<<< HEAD

        this.alreadyMove = new ArrayList<>();
        this.sailorsDie = new ArrayList<>();
=======
        this.sailorsDie = new ArrayList<>();
        this.alreadyMove = new ArrayList<>();
        this.sailorsMove = new ArrayList<>();
>>>>>>> b1bc42d191a49314e07eaa82b5acb0337e0b0661
    }

    public void movingSimulator(List<Action> movingList) {
        movingList.forEach(action ->
        {
            if (action.getType().equals("MOVING")) {
                Sailor sailor = this.getSailorFromId(((Moving) action));
                this.moveSailor((Moving) action, sailor);
            }
        });
        this.moveSailorRestore();
    }

    private void moveSailor(Moving move, Sailor sailor) {
        if (Math.abs(move.getXdistance()) + Math.abs(move.getYdistance()) <= 5 && !(alreadyMove.contains(sailor))) {
            if (move.getXdistance() + sailor.getX() > this.init.getShip().getDeck().getLength() ||
                    move.getYdistance() + sailor.getY() > this.init.getShip().getDeck().getWidth()) {
                this.sailorsDie.add(sailor);
            } else {
                sailor.setX(sailor.getX() + move.getXdistance());
                sailor.setY(sailor.getY() + move.getYdistance());
            }
            alreadyMove.add(sailor);
        } else if (!(alreadyMove.contains(sailor)))
            alreadyMove.add(sailor);
    }

    private Sailor getSailorFromId(Moving move) {
        return Arrays.stream(this.init.getSailors()).filter(sailor1 -> sailor1.getId() == move.getSailorId()).toList().get(0);
    }

    private void moveSailorRestore() {
        alreadyMove.clear();
    }
}
