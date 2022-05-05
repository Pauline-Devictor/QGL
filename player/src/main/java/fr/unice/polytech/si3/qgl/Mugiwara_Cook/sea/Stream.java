package fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;
import lombok.Setter;

public class Stream extends VisibleEntity {
    public static final String ENTITYTYPE = "stream";
    @Setter
    double strength;

    public Stream(Position position, Shape shape, double strength) {
        super(ENTITYTYPE, shape, position);
        this.strength = strength;
    }
}
