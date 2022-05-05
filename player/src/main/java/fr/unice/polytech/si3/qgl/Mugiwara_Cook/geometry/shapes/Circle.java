package fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes;

import lombok.Getter;

public class Circle extends Shape {
    public static final String SHAPETYPE = "circle";
    @Getter
    double radius;

    public Circle(double radius) {
        super(SHAPETYPE);
        this.radius = radius;
    }
}
