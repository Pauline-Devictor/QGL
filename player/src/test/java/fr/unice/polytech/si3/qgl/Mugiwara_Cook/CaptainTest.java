//package fr.unice.polytech.si3.qgl.Mugiwara_Cook;
//
//import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Action;
//import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Moving;
//import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
//import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Rectangle;
//import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;
//import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Deck;
//import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
//import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
//import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;
//import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.Captain;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CaptainTest {
//
//    List<Equipment> entities = new ArrayList<>();
//    Deck deck = new Deck(3,4); // x=length y=width
//    Position position = new Position(1,2,5);
//    Shape shape = new Rectangle(2,4,5);
//    Ship ship = new Ship(100,position,"Trinidad",deck,entities,shape);
//    Sailor sailor1 = new Sailor(1,2,2,"gogol1");
//    Sailor sailor2 = new Sailor(2,0,1,"gogol2");
//    Sailor sailor3 = new Sailor(3,1,0,"gogol3");
//    Sailor sailor4 = new Sailor(4,1,2,"gogol4");
//    Sailor[] sailors = {sailor1,sailor2,sailor3,sailor4};
//    Captain captain = new Captain(ship,sailors);
//
//    @Test
//    void findSailorWithId(){
//        assertEquals(sailor1,captain.findASailorWithId(1));
//        assertEquals(sailor2,captain.findASailorWithId(2));
//        assertEquals(sailor3,captain.findASailorWithId(3));
//        assertEquals(sailor4,captain.findASailorWithId(4));
//    }
//
//    @Test
//    void sailorIsAllowedToMove(){
//        assertTrue(captain.sailorIsAllowedToMove(2,3));
//        assertTrue(captain.sailorIsAllowedToMove(2,1));
//        assertFalse(captain.sailorIsAllowedToMove(4,6));
//    }
//
//    @Test
//    void sailorIsNotGoingToFallInTheSeaX(){
//        assertTrue(captain.sailorIsNotGoingToFallInTheSeaX(2,2));
//        assertTrue(captain.sailorIsNotGoingToFallInTheSeaX(2,3));
//        assertFalse(captain.sailorIsNotGoingToFallInTheSeaX(1,3));
//        assertFalse(captain.sailorIsNotGoingToFallInTheSeaX(3,4));
//    }
//
//    @Test
//    void sailorIsNotGoingToFallInTheSeaY(){
//        assertTrue(captain.sailorIsNotGoingToFallInTheSeaY(2,1));
//        assertTrue(captain.sailorIsNotGoingToFallInTheSeaY(3,1));
//        assertTrue(captain.sailorIsNotGoingToFallInTheSeaY(3,0));
//        assertFalse(captain.sailorIsNotGoingToFallInTheSeaY(1,2));
//        assertFalse(captain.sailorIsNotGoingToFallInTheSeaY(4,3));
//    }
//
//    @Test
//    void sailorIsNotGoingToFallInTheSea(){
//        assertTrue(captain.sailorIsNotGoingToFallInTheSea(1,1,0));
//        assertTrue(captain.sailorIsNotGoingToFallInTheSea(2,0,1));
//        assertTrue(captain.sailorIsNotGoingToFallInTheSea(3,2,2));
//        assertFalse(captain.sailorIsNotGoingToFallInTheSea(4,2,2));
//        assertFalse(captain.sailorIsNotGoingToFallInTheSea(2,4,1));
//        assertFalse(captain.sailorIsNotGoingToFallInTheSea(1,2,2));
//    }
//
//    @Test
//    void sailorsMoveToOars(){
//        ArrayList<Action> movesTest = new ArrayList<>();
//        Moving sailor1move = new Moving(1,0,1);
//        Moving sailor2move = new Moving(2,0,-1);
//        Moving sailor3move = new Moving(3,1,0);
//        Moving sailor4move = new Moving(4,1,-1);
//        movesTest.add(sailor1move);
//        movesTest.add(sailor2move);
//        movesTest.add(sailor3move);
//        movesTest.add(sailor4move);
//        Equipment rame1 = new Oar(3,2);
//        Equipment rame2 = new Oar(3,1);
//        Equipment rame3 = new Oar(0,0);
//        Equipment rame4 = new Oar(2,0);
//        Equipment rame5 = new Oar(2,1);
//        entities.add(rame1);
//        entities.add(rame2);
//        entities.add(rame3);
//        entities.add(rame4);
//        entities.add(rame5);
//        assertEquals(sailor1move,movesTest.get(0));
//        assertEquals(sailor2move,movesTest.get(1));
//        assertEquals(sailor3move,movesTest.get(2));
//        assertEquals(sailor4move,movesTest.get(3));
//    }
//
//
//}