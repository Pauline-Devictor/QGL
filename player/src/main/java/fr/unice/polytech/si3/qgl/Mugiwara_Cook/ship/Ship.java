package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public Ship(int life, Position position, String name, Deck deck, List<Equipment> entities, Shape shape) {
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

    /**
     * permet de recuperer la list des rames.
     *
     * @return la list des rames.
     */
    @JsonIgnore
    public ArrayList<Oar> getOars() {
        ArrayList<Oar> oars = new ArrayList<>();
        for (Equipment e : entities) {
            if (e instanceof Oar) oars.add((Oar) e);
        }
        return oars;
    }

    @JsonIgnore
    public int getNbOars() {
        return this.getOars().size();
    }
    @JsonIgnore
    public List<Oar> getUsableOarsLeft() {
        return this.getOars().stream()
                .filter(oar -> oar.getY() == 0)
                .filter(oar -> oar.getSailor() != null)
                .filter(oar -> oar.getSailor().onIsAssignOar() == true)
                .collect(Collectors.toList());
    }
    @JsonIgnore
    public List<Oar> getUsableOarsRight() {
        return this.getOars().stream()
                .filter(oar -> (this.getDeck().getWidth() - 1) == oar.getY())
                .filter(oar -> oar.getSailor() != null)
                .filter(oar -> oar.getSailor().onIsAssignOar() == true)
                .collect(Collectors.toList());
    }
}
