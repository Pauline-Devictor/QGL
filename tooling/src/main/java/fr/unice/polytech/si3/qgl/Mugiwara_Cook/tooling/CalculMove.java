package fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.allmoves.Moves;

public class CalculMove {

    public CalculMove() {
    }

    public Moves calcul(Ship ship, int[] oars){
       return new Moves(ship, oars);
    }
}
