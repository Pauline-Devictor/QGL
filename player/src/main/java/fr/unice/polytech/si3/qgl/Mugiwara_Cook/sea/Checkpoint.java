package fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;
import lombok.Getter;
import lombok.Setter;

public class Checkpoint {
    @Getter
    @Setter
    Position position;
    @Getter
    @Setter
    Shape shape;

    public Checkpoint() {
        //Json
    }

    public Checkpoint(Position position, Shape shape) {
        this.position = position;
        this.shape = shape;
    }
}
