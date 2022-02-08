package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shapes.Shape;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;

import java.util.List;

public class Ship {
    public final static String TYPE = "ship";
    String type = TYPE;
    int life;
    Position position;
    String name;
    Deck deck;
    List<Equipment> entities;
    Shape shape;

    public Ship() {
        // only for jackson
    }

    public Ship(int life, Position position, String name, Deck deck, List<Equipment> entities, Shape shape){
        this.life = life;
        this.position = position;
        this.name = name;
        this.deck = deck;
        this.entities = entities;
        this.shape = shape;
    }

    public String getType() {
        return TYPE;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public List<Equipment> getEntities() {
        return entities;
    }

    public void setEntities(List<Equipment> entities) {
        this.entities = entities;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
