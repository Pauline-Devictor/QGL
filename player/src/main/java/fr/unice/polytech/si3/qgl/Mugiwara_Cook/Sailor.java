package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

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

    /**
     *
     * @param sailor un sailor dont on veut la plus proche rame
     * @param OarList liste des rames sur le bateau
     * @return la rame la plus proche
     * Note : cette méhode trouve la rame la plus proche, elle ne prend pas en compte la limite des 5 cases
     */
    public Oar FindClosestOarFromSailor(Sailor sailor, ArrayList<Oar> OarList){
        Oar closestOar = OarList.get(0);
        for(int i=1;i<OarList.size();i++){
            if(abs(OarList.get(i).getX() - sailor.getX()) <= abs(closestOar.getX() - sailor.getX()) && abs(OarList.get(i).getY() - sailor.getY()) <= abs(closestOar.getY() - sailor.getY())){
                closestOar=OarList.get(i);
            }
        }
        return closestOar;
    }

    //WORK IN PROGRESS
    /**public Equipment FindSpecificClosestEquipementFromSailor(Sailor sailor, String chosenEquipementType, ArrayList<Equipment> entities,Class classe){
        Equipment closestEquipement;
        //chosenEquipementType.getClass()
        entities.stream()
                .filter(equipment -> equipment.getType().equals(chosenEquipementType))
                .forEach(equipment -> {
                    if(abs((classe) equipment))
                });
        for(int i=1;i<OarList.size();i++){
            if(OarList.get(i).getX()<= closestOar.getX() && OarList.get(i).getY()<= closestOar.getY()){
                closestOar=OarList.get(i);
            }
        }
        return closestEquipement;
    }*/
}
