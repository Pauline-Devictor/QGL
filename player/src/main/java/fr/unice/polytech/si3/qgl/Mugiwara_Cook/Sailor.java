package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Moving;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.ActionJSON;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class Sailor {
    int id;
    int x;
    int y;
    String name;
    @JsonIgnore
    Oar oar = null;

    public Sailor() {
    }

    public Sailor(int id, int x, int y, String name) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * @param oar la rame que le sailor veut atteindre
     * @return la distance en x pour atteindre la rame
     */
    public int howManyCaseFarFromOarX(Oar oar) {
        int x = oar.getX() - this.x;
        return x;
    }

    /**
     * @param oar la rame que le sailor veut atteindre
     * @return la distance en y pour atteindre la rame
     */
    public int howManyCaseFarFromOarY(Oar oar) {
        int y = oar.getY() - this.y;
        return y;
    }

    /**
     * @param OarList liste des rames sur le bateau
     * @return la rame la plus proche
     * Note : cette méhode trouve la rame la plus proche, elle ne prend pas en compte la limite des 5 cases
     */
    public Oar findClosestOarFromSailor(ArrayList<Oar> OarList) {
        Oar closestOar = OarList.get(0);
        for (int i = 1; i < OarList.size(); i++) {
            if (abs(OarList.get(i).getX() - this.getX()) <= abs(closestOar.getX() - this.getX()) && abs(OarList.get(i).getY() - this.getY()) <= abs(closestOar.getY() - this.getY())) {
                closestOar = OarList.get(i);
            }
        }
        return closestOar;
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
        if (this.oar == null) return false;
        return true;
    }

    public void attachOar(Oar oar) {
        this.oar = oar;
    }

    @JsonIgnore
    public Oar getOar() {
        return this.oar;
    }

    public boolean onIsAssignOar() {
        return this.getX() == this.oar.getX() && this.getY() == this.oar.getY();
    }

    public boolean moveToOar(ActionJSON actionJSON) {
        int xMove = 0;
        int yMove = 0;
        xMove = this.howManyCaseFarFromOarX(this.oar);
        yMove = this.howManyCaseFarFromOarY(this.oar);
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

        return this.onIsAssignOar();

    }

}
