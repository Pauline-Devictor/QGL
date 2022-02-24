package fr.unice.polytech.si3.qgl.Mugiwara_Cook.game;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Wind;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.VisibleEntity;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;

public class NextRound {
    Ship ship;
    Wind wind;
    VisibleEntity[] visibleEntities;

    public NextRound(){
        //Json
    }

    public NextRound(Ship ship, Wind wind, VisibleEntity[] visibleEntities){
        this.ship = ship;
        this.wind = wind;
        this.visibleEntities = visibleEntities;
    }


    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public VisibleEntity[] getVisibleEntities() {
        return visibleEntities;
    }

    public void setVisibleEntities(VisibleEntity[] visibleEntities) {
        this.visibleEntities = visibleEntities;
    }
}
