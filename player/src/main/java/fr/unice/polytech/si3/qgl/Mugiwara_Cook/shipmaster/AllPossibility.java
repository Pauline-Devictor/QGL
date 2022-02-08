package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;

import java.util.ArrayList;
import java.util.List;

public class AllPossibility {

    int nbMaxOar;
    List<int[]> listOarPossibility = new ArrayList<>();

    public AllPossibility(InitGame initGame) {
        this.oarPossibility(initGame);
    }

    public void oarPossibility(InitGame initGame) {
        List<Oar> listOar = new ArrayList<>();
        initGame.getShip().getEntities().forEach(ent -> {
            if (ent != null && ent.getType().equals("oar")) {
                listOar.add((Oar) ent);
            }
        });

        int oarLeft = (int) listOar.stream().filter(oar -> oar.getY() == 0).count();
        int oarRight = (int) listOar.stream().filter(oar -> (initGame.getShip().getDeck().getWidth() - 1) == oar.getY()).count();
        nbMaxOar = oarLeft + oarRight;

        for (int i = 0; i <= oarLeft; i++) {
            for (int k = 0; k <= oarRight; k++) {
                if (i + k <= initGame.getSailors().length) {
                    listOarPossibility.add(new int[]{i, k});
                }
            }
        }
    }

    public List<int[]> getAllPossibility() {
        return listOarPossibility;
    }

    public int getSize() {
        return listOarPossibility.size();
    }

    public int getNbOar(){
        return nbMaxOar;
    }

}
