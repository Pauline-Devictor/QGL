package fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes;

import lombok.Getter;
import lombok.Setter;

public class Circle extends Shape {
    public final static String TYPE = "circle";
    @Getter
    double radius;

    public Circle(double radius) {
        super(TYPE);
        this.radius = radius;
    }
}
