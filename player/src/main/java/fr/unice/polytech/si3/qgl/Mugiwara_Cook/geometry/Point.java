package fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry;

import lombok.Getter;
import lombok.Setter;

public class Point {
    @Getter
    @Setter
    int x;
    @Getter
    @Setter
    int y;

    public Point() {
        //Json
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
