package fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;
import lombok.Getter;
import lombok.Setter;

public class Stream extends VisibleEntity {
    public static final String TYPE = "stream";
    @Setter
    double strength;

    public Stream(Position position, Shape shape, double strength) {
        super(TYPE, shape, position);
        this.strength = strength;
    }
}
