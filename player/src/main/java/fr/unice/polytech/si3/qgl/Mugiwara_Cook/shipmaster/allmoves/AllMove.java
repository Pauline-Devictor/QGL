package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.allmoves;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;

import java.util.ArrayList;
import java.util.List;

public class AllMove {
    List<Moves> movesList;

    public AllMove() {
        movesList = new ArrayList<>();
    }

    public void calculateMove(Ship ship) {
        List<Oar> listEquipment = (List<Oar>) ship.getEntities().stream().filter(s -> s.getType().equals("Oar"));
        int oarLeft = (int) listEquipment.stream().filter(s -> s.getX() == 0).count();
        int oarRight = (int) listEquipment.stream().filter(s -> ship.getDeck().getWidth() == s.getY()).count();
        for (int i = 0; i <= oarLeft; i++) {
            for (int k = 0; k <= oarRight; k++) {
                movesList.add((Moves) new OarMove(ship.getPosition().getX(), ship.getPosition().getY(), ship.getPosition().getOrientation(), i, k, oarLeft + oarRight));
            }
        }
    }

}
