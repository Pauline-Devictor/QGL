package fr.unice.polytech.si3.qgl.mugiwara_cook;

import fr.unice.polytech.si3.qgl.mugiwara_cook.sea.Wind;
import fr.unice.polytech.si3.qgl.mugiwara_cook.sea.visibleEntity;
import fr.unice.polytech.si3.qgl.mugiwara_cook.ship.Ship;

public class NextRound {
    Ship ship;
    Wind wind;
    visibleEntity[] visibleEntities;

    public NextRound(){
        //Json
    }

    public NextRound(Ship ship, Wind wind, visibleEntity[] visibleEntities){
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

    public visibleEntity[] getVisibleEntities() {
        return visibleEntities;
    }

    public void setVisibleEntities(visibleEntity[] visibleEntities) {
        this.visibleEntities = visibleEntities;
    }
}
