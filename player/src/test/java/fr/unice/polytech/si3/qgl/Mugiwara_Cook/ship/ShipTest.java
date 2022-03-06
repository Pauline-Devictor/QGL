package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Rudder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    public final static String TYPE = "ship";
    Position position;
    String name;
    Deck deck;
    List<Equipment> entities=new ArrayList<>();
    Shape shape;
    Ship ship;

    @BeforeEach
    public void set(){
        position= new Position(2.3,2.5,9.0);
        for (int i=0;i<10;i++){
            Oar oar= new Oar(i+4,i+5);
            Rudder rudder=new Rudder(i+1,i+2);
            entities.add(oar);
            entities.add(rudder);
            ship=new Ship(100,position,"test",new Deck(100,100),entities,new Circle(23.0));
        }


    }
    @Test
    public void Oars(){
        ArrayList<Oar> oars =new ArrayList<>();
        for (int i=0;i<10;i++) {
            Oar oar = new Oar(i + 4, i + 5);
            oars.add(oar);
        }
        for(int i= 0;i<10;i++){
            assertEquals(ship.getOars().get(i).getX(),oars.get(i).getX());
            assertEquals(ship.getOars().get(i).getY(),oars.get(i).getY());
            assertEquals(ship.getOars().get(i).getType(),oars.get(i).getType());
        }
    }

    @Test
    public void Rudder(){
        ArrayList<Rudder> rudders =new ArrayList<>();
        for (int i=0;i<10;i++) {
            Rudder rudder=new Rudder(i+1,i+2);
            rudders.add(rudder);
        }
        for(int i= 0;i<10;i++){
            assertEquals(ship.getRudder().get(i).getX(),rudders.get(i).getX());
            assertEquals(ship.getRudder().get(i).getY(),rudders.get(i).getY());
            assertEquals(ship.getRudder().get(i).getType(),rudders.get(i).getType());
        }


    }

    /**
    @Test
    public void UsableLeftOar(){
        List<Equipment> oars =new ArrayList<>();
        for (int i=0;i<10;i++) {
            Oar oarl = new Oar(i + 4, 0);
            Oar oarr =new Oar(i,99);
            oars.add(oarl);
            oars.add(oarr);
        }
        for (int i=0;i<10;i++) {
            Oar oarl = new Oar(i + 4, 0);
            Sailor sailor= new Sailor();
            sailor.setX(i+4);
            sailor.setY(0);
            oarl.setSailor(sailor);
            Oar oarr =new Oar(i,99);
            oars.add(oarl);
            oars.add(oarr);
        }
        ship=new Ship(100,position,"test",new Deck(100,100),oars,new Circle(23.0));
        assertEquals(ship.getUsableOarsLeft().size(),10);
    }**/
}
