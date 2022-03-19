package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship;

import lombok.Getter;
import lombok.Setter;

public class Deck {
    @Getter
    @Setter
    int width;
    @Getter
    @Setter
    int length;

    public Deck() {
        //Json
    }

    public Deck(int width, int length) {
        this.width = width;
        this.length = length;
    }
}
