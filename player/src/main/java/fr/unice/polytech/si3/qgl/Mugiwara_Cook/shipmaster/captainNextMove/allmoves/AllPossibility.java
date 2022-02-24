package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.allmoves;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;

import java.util.ArrayList;
import java.util.List;

public class AllPossibility {
    InitGame initGame;

    int nbMaxOar;
    List<int[]> listOarPossibility = new ArrayList<>();


    public AllPossibility(InitGame initGame) {
        this.initGame = initGame;
        this.nbMaxOar = initGame.getShip().getNbOars();
    }

    public void oarPossibility() {
        int oarLeft = initGame.getShip().getUsableOarsLeft().size();
        int oarRight = initGame.getShip().getUsableOarsRight().size();

        for (int i = oarLeft; i >= 0; i--) {
            for (int k = oarRight; k >= 0; k--) {
                if (i + k <= initGame.getSailors().length && i + k != 0) {
                    listOarPossibility.add(new int[]{i, k});
                }
            }
        }
    }

    public List<int[]> getAllPossibility() {
        return listOarPossibility;
    }

    public int getNbOar() {
        return nbMaxOar;
    }
}
