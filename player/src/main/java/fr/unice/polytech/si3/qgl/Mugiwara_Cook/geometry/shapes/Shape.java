package fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes;

import lombok.Getter;
import lombok.Setter;

abstract public class Shape {
    @Getter
    @Setter
    String type;

    public Shape(String type) {
        this.type = type;
    }
}
