package fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;
import lombok.Getter;
import lombok.Setter;

public abstract class VisibleEntity {
    @Setter
    @Getter
    String type;
    @Setter
    Shape shape;
    @Setter
    Position position;

    protected VisibleEntity(String type, Shape shape, Position position) {
        this.type = type;
        this.shape = shape;
        this.position = position;
    }
}
