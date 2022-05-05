package fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;

public class Reef extends VisibleEntity {
    public static final String reef = "reef";

    public Reef(Position position, Shape shape) {
        super(reef, shape, position);
    }

    public Shape getShape(){ return shape; }

    public Position getPosition(){ return position; }
}
