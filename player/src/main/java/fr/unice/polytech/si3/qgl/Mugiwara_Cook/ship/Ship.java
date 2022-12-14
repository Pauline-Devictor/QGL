package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Rudder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    public static final String TYPE = "ship";
    String type = TYPE;
    @Getter
    @Setter
    int life;
    @Getter
    @Setter
    Position position;
    @Getter
    @Setter
    String name;
    @Getter
    @Setter
    Deck deck;
    @Getter
    @Setter
    List<Equipment> entities;
    @Getter
    @Setter
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

    /**
     * Permet de recuperer la list des rames.
     *
     * @return la list des rames.
     */
    @JsonIgnore
    public ArrayList<Oar> getOars() {
        ArrayList<Oar> oars = new ArrayList<>();
        for (Equipment e : this.getEquipement("oar")) {
            oars.add((Oar) e);
        }
        return oars;
    }

    @JsonIgnore
    public ArrayList<Rudder> getRudder() {
        ArrayList<Rudder> rudders = new ArrayList<>();
        for (Equipment e : this.getEquipement("rudder")) {
            rudders.add((Rudder) e);
        }
        return rudders;
    }


    public ArrayList<Equipment> getEquipement(String type) {
        ArrayList<Equipment> equipments = new ArrayList<>();
        if (entities != null) {
            for (Equipment e : entities) {
                if (e != null && e.getType().equals(type)) equipments.add(e);
            }
        }
        return equipments;

    }

    @JsonIgnore
    public int getNbOars() {
        return this.getOars().size();
    }

    @JsonIgnore
    public int getNbEquipment(String equipment) {
        return this.getEquipement(equipment).size();
    }

    @JsonIgnore
    public int getNbUsableOarsLeft() {
        return this.getOars().stream()
                .filter(oar -> oar.getY() == 0)
                .filter(oar -> oar.getSailor() != null)
                .filter(oar -> oar.getSailor().onIsAssignEquipment()).toList().size();

    }
    @JsonIgnore
    public int getNbUsableSails() {
        return this.getEquipement("sail").stream()
                .filter(sail -> sail.getSailor() != null)
                .filter(sail -> sail.getSailor().onIsAssignEquipment()).toList().size();
    }
    @JsonIgnore
    public List<Equipment> getUsableSails() {
        return this.getEquipement("sail").stream()
                .filter(sail -> sail.getSailor() != null)
                .filter(sail -> sail.getSailor().onIsAssignEquipment()).toList();
    }





    @JsonIgnore
    public int  getNbUsableOarsRight() {
        return this.getOars().stream()
                .filter(oar -> (this.getDeck().getWidth() - 1) == oar.getY())
                .filter(oar -> oar.getSailor() != null)
                .filter(oar -> oar.getSailor().onIsAssignEquipment()).toList().size();
    }

    /**
     * Pour le simulateur.
     */
    @JsonIgnore
    public Position getPositionRoute() {
        return new Position(this.position.getX(), this.position.getY(), this.position.getOrientation());
    }

}
