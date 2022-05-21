package fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes;

import lombok.Getter;
import lombok.Setter;

public abstract class Shape {
    @Getter
    String type;

    protected Shape(String type) {
        this.type = type;
    }
}
