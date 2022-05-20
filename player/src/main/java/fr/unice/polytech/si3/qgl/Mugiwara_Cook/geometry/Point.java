package fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry;

import lombok.Getter;
import lombok.Setter;

public class Point {
    @Getter
    @Setter
    double x;
    @Getter
    @Setter
    double y;

    public Point(){
        //Json
        }

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }



}
