package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Oar;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Turn;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.ActionJSON;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;

import java.util.ArrayList;

public class PrimaryMoves {

    InitGame initGame;
    ActionJSON actionJSON;

    public PrimaryMoves(InitGame initGame, ActionJSON actionJson) {
        this.initGame = initGame;
        this.actionJSON = actionJson;
    }


    public void primaryMoveOar(int leftOar, int rightOar) {
        for (int i = 0; i < leftOar; i++) {
            actionJSON.addAction(new Oar(initGame.getUsableSailorLeft().get(i).getId()));
        }
        for (int i = 0; i < rightOar; i++) {
            actionJSON.addAction(new Oar(initGame.getUsableSailorRight().get(i).getId()));
        }
    }

    public void primaryMoveTurn(double angle) {
        if (initGame.getUsableSailorRudder() != null)
            actionJSON.addAction(new Turn(initGame.getUsableSailorRudder().getId(), angle));
    }
}
