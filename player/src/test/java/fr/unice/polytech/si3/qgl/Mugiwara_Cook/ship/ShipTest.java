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
    ArrayList<Sailor> sailorArrayList;

    @BeforeEach
    public void set(){
        position= new Position(2.3,2.5,9.0);
        Sailor sailor1 = new Sailor(1,50,0,"Marin gouvernail");
        Sailor sailor2 = new Sailor(2,0,0,"Marin rame1");
        Sailor sailor3 = new Sailor(3,30,0,"Marin rame2");
        Sailor sailor4 = new Sailor(4,60,0,"Marin rame3");
        Sailor sailor5 = new Sailor(5,90,0,"Marin rame4");
        Sailor sailor6 = new Sailor(6,0,99,"Marin rame5");
        Sailor sailor7 = new Sailor(7,30,99,"Marin rame6");
        Sailor sailor8 = new Sailor(8,60,99,"Marin rame7");
        Sailor sailor9 = new Sailor(9,90,99,"Marin rame8");
        Oar rame1 = new Oar(0,0);
        Oar rame2 = new Oar(30,0);
        Oar rame3 = new Oar(60,0);
        Oar rame4 = new Oar(90,0);
        Oar rame5 = new Oar(0,99);
        Oar rame6 = new Oar(30,99);
        Oar rame7 = new Oar(60,99);
        Oar rame8 = new Oar(90,99);
        Rudder rudder = new Rudder(50,0);
        rudder.setSailor(sailor1);
        rame1.setSailor(sailor2);
        rame2.setSailor(sailor3);
        rame3.setSailor(sailor4);
        rame4.setSailor(sailor5);
        rame5.setSailor(sailor6);
        rame6.setSailor(sailor7);
        rame7.setSailor(sailor8);
        rame8.setSailor(sailor9);
        sailor1.attachEquipment(rudder);
        sailor2.attachEquipment(rame1);
        sailor3.attachEquipment(rame2);
        sailor4.attachEquipment(rame3);
        sailor5.attachEquipment(rame4);
        sailor6.attachEquipment(rame5);
        sailor7.attachEquipment(rame6);
        sailor8.attachEquipment(rame7);
        sailor9.attachEquipment(rame8);
        entities.add(rudder);
        entities.add(rame1);
        entities.add(rame2);
        entities.add(rame3);
        entities.add(rame4);
        entities.add(rame5);
        entities.add(rame6);
        entities.add(rame7);
        entities.add(rame8);
        ship = new Ship(100,position,"test",new Deck(100,100),entities,new Circle(23.0));
    }

    /**
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
    }*/

    @Test
    void getNbUsableOarsRight(){
        assertEquals(ship.getNbUsableOarsRight(),4);
    } @Test
    void getNbUsableOarsLeft(){
        assertEquals(ship.getNbUsableOarsRight(),4);
    }
}
