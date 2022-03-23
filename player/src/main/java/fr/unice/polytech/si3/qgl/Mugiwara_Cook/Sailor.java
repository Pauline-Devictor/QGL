package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Moving;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.ActionJSON;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class Sailor {
    @Getter
    int id;
    @Getter
    @Setter
    int x;
    @Getter
    @Setter
    int y;
    @Getter
    String name;

    @JsonIgnore
    @Getter
    @Setter
    Equipment equipment = null;

    public Sailor() {
    }

    public Sailor(int id, int x, int y, String name) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.name = name;
    }

    /**
     * @param equipment l'equipement que le sailor veut atteindre
     * @return la distance en x pour atteindre la rame
     */
    public int howManyCaseFarFromOarX(Equipment equipment) {
        int x = equipment.getX() - this.x;
        return x;
    }

    /**
     * @param equipment l'equipement que le sailor veut atteindre
     * @return la distance en y pour atteindre la rame
     */
    public int howManyCaseFarFromOarY(Equipment equipment) {
        int y = equipment.getY() - this.y;
        return y;
    }

    /**
     * @param sailor               un rameur donné
     * @param chosenEquipementType le type de l'equipement que l'on a choisi
     * @param entities             l'attribut entities de la classe Ship
     * @return
     */
    public Equipment findSpecificClosestEquipementFromSailor(Sailor sailor, String chosenEquipementType, ArrayList<Equipment> entities) {
        Equipment closestEquipement = null;
        boolean trouve = false;

        for (int i = 0; i < entities.size(); i++) {
            if (!trouve && entities.get(i).getType().equals(chosenEquipementType)) {
                closestEquipement = entities.get(i);
                trouve = true;
            }
            if (entities.get(i).getType().equals(chosenEquipementType) && trouve && abs(entities.get(i).getX() - sailor.getX()) <= abs(closestEquipement.getX() - sailor.getX()) && abs(entities.get(i).getY() - sailor.getY()) <= abs(closestEquipement.getY() - sailor.getY()))
                closestEquipement = entities.get(i);
        }
        return closestEquipement;
    }

    public boolean assign() {
        if (this.equipment == null) return false;
        return true;
    }

    public void attachEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public boolean itemIsTooFar(Equipment item) {
        return abs(item.getX() - getX()) + abs(item.getY() - getY()) > 5;
    }

    public boolean onIsAssignEquipment() {
        return this.getX() == this.equipment.getX() && this.getY() == this.equipment.getY();
    }

    public boolean moveToEquipment(ActionJSON actionJSON) {
        int xMove = this.howManyCaseFarFromOarX(this.equipment);
        int yMove = this.howManyCaseFarFromOarY(this.equipment);
        while (abs(xMove) + abs(yMove) > 5) {
            if (xMove != 0) {
                if (xMove < 0) xMove++;
                else xMove--;
            } else {
                if (yMove < 0) yMove++;
                else yMove--;
            }
        }
        this.x += xMove;
        this.y += yMove;
        actionJSON.addAction(new Moving(this.id, xMove, yMove));

        return this.onIsAssignEquipment();
    }

    /**
     * @param xdistance le nombre de déplacement en x
     * @param ydistance le nombre de déplacement en y
     * @return un boolean qui dit vrai s'il respect les conditions, faux sinon
     */
    public boolean sailorIsAllowedToMove(int xdistance, int ydistance) {
        if (abs(xdistance) + abs(ydistance) <= 5) return true;
        return false;
    }
}
