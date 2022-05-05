package fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;
import lombok.Setter;

public class Stream extends VisibleEntity {
    public static final String stream = "stream";
    @Setter
    double strength;

    public Stream(Position position, Shape shape, double strength) {
        super(stream, shape, position);
        this.strength = strength;
    }
}
