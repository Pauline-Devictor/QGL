package fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes;

public class Circle extends Shape{
    public final static String TYPE = "circle";
    double radius;

    public Circle(double radius) {
        super(TYPE);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
