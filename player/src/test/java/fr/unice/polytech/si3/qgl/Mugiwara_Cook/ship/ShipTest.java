package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Rudder;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Sail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    public final static String TYPE = "ship";
    Position position;
    List<Equipment> entities=new ArrayList<>();
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
        sailorArrayList = new ArrayList<>(List.of(sailor1,sailor2,sailor3,sailor4,sailor5,sailor6,sailor7,sailor8,sailor9));
        entities = new ArrayList<>(List.of(rudder,rame1,rame2,rame3,rame4,rame5,rame6,rame7,rame8));
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
    void getDataOars(){
        assertEquals(4,ship.getNbUsableOarsRight());
        assertEquals(4,ship.getNbUsableOarsLeft());
        assertEquals(8,ship.getNbOars());
    }

    @Test
    void getDataSails(){
        //Cas sails vide
        assertEquals(0,ship.getNbUsableSails());
        assertEquals(ship.getUsableSails(),new ArrayList<Equipment>());
        //Cas sails pas vide
        Sail sail = new Sail(50,0,false);
        ship.getEntities().add(sail);
        sailorArrayList.get(0).attachEquipment(sail);
        sail.setSailor(sailorArrayList.get(0));
        assertEquals(1,ship.getNbUsableSails());
        List<Sail> sails = new ArrayList<>(List.of(sail));
        assertEquals(ship.getUsableSails().toString(),sails.toString());
    }

    @Test
    void rudderTest(){
        assertEquals(1,ship.getRudder().size());
    }
}
