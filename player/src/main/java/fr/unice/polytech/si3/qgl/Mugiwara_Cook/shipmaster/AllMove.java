package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.allmoves.Moves;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.allmoves.OarMove;

import java.util.ArrayList;
import java.util.List;

public class AllMove {
    List<Moves> movesList;

    public AllMove() {
        movesList = new ArrayList<>();
    }

    public void calculateMove(Ship ship, AllPossibility allPossibility) {

        for (int[] oar : allPossibility.getAllPossibility()) {
            movesList.add((Moves) new OarMove(ship.getPosition().getX(), ship.getPosition().getY(), ship.getPosition().getOrientation(), oar[0], oar[1], allPossibility.getNbOar()));
        }
    }

    public void getDetail() {
        movesList.forEach(m -> m.getDetail());
    }

    public List<Moves> getMovesList(){
        return movesList;
    }

}
