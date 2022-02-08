package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;

public class Sailor {
    int id;
    int x;
    int y;
    String name;

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
    public int HowManyCaseFarFromOarX(Oar oar) {
        int x = oar.getX() - this.x;
        return x;
    }

    /**
     * @param oar la rame que le sailor veut atteindre
     * @return la distance en y pour atteindre la rame
     */
    public int HowManyCaseFarFromOarY(Oar oar) {
        int y = oar.getY() - this.y;
        return y;
    }


}
