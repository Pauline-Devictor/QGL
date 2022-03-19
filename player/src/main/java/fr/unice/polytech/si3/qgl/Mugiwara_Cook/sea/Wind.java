package fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea;

import lombok.Getter;
import lombok.Setter;

public class Wind {
    @Getter
    @Setter
    double orientation;
    @Getter
    @Setter
    double strength;

    public Wind() {
        //Json
    }

    public Wind(double orientation, double strength) {
        this.orientation = orientation;
        this.strength = strength;
    }
}
