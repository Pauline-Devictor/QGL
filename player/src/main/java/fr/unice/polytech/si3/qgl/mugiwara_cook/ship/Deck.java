package fr.unice.polytech.si3.qgl.mugiwara_cook.ship;

public class Deck {

    int width;
    int length;

    Deck(int width, int length){

        this.width=width;
        this.length=length;
    }

    int getWidth(){
        return width;
    }
    int getLength(){
        return length;
    }
}
