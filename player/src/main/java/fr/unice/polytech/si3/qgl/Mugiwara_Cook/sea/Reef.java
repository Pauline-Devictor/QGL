package fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;
import lombok.Getter;

public class Reef extends VisibleEntity {
    public final static String TYPE = "reef";

    public Reef(Position position, Shape shape) {
        super(TYPE, shape, position);
    }

    public Shape getShape(){ return shape; }

    public Position getPosition(){ return position; }
}
