package fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @Getter
    @Setter
    Boolean essential;


    public Checkpoint() {
        essential = true;
    }

    public Checkpoint(Position position, Shape shape) {
        this.position = position;
        this.shape = shape;
        this.essential = false;
    }
}
