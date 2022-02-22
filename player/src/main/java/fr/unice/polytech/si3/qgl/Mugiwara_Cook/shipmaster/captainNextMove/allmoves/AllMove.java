package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.allmoves;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;

import java.util.ArrayList;
import java.util.List;

public class AllMove {
    List<Moves> movesList;

    public AllMove() {
        movesList = new ArrayList<>();
    }

    public void calculateMove(Ship ship, AllPossibility allPossibility) {
        for (int[] oar : allPossibility.getAllPossibility()) {
            movesList.add((Moves) new Moves(ship.getPosition().getX(), ship.getPosition().getY(), ship.getPosition().getOrientation(), oar[0], oar[1], allPossibility.getNbOar()));
        }
    }

    public List<Moves> getMovesList() {
        return movesList;
    }

    public void getDetail() {
        movesList.forEach(m -> m.getDetail());
    }
}
