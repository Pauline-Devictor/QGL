package fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes;

import lombok.Getter;
import lombok.Setter;

public class Rectangle extends Shape {
    public static final String SHAPETYPE = "rectangle";
    @Setter
    @Getter
    double width;
    @Setter
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
