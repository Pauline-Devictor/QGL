package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Deck;
import lombok.Getter;
import lombok.Setter;

import static java.lang.Math.abs;


public class Moving extends Action {
    public final static String TYPE = "MOVING";
    @Getter
    //@Setter
    int sailorId;
    @Getter
    //@Setter
    int xdistance;
    @Getter
    //@Setter
    int ydistance;

    public Moving(int sailorId, int xdistance, int ydistance) {
        super(TYPE);
        this.sailorId = sailorId;
        this.xdistance = xdistance;
        this.ydistance = ydistance;
    }
}
