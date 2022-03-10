package fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;


public class CalculMove {

    public CalculMove() {
    }

    public Moves calcul(Ship ship, int[] oars, double angleRudder) {
        return new Moves(ship, oars, angleRudder);
    }
}
