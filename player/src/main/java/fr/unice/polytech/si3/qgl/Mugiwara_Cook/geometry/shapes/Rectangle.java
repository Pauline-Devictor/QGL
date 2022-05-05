package fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes;

import lombok.Getter;

public class Rectangle extends Shape {
    public static final String SHAPETYPE = "rectangle";
    @Getter
    double width;
    @Getter
    double height;
    @Getter
    double orientation;

    public Rectangle(double width, double height, double orientation) {
        super(SHAPETYPE);
        this.width = width;
        this.height = height;
        this.orientation = orientation;
    }
}
