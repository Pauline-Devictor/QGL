package fr.unice.polytech.si3.qgl.Mugiwara_Cook.game;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Wind;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.VisibleEntity;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import lombok.Getter;
import lombok.Setter;

public class NextRound {
    @Getter
    @Setter
    Ship ship;
    @Getter
    @Setter
    Wind wind;
    @Getter
    @Setter
    VisibleEntity[] visibleEntities;

    public NextRound() {
        //Json
    }

    public NextRound(Ship ship, Wind wind, VisibleEntity[] visibleEntities) {
        this.ship = ship;
        this.wind = wind;
        this.visibleEntities = visibleEntities;
    }
}
