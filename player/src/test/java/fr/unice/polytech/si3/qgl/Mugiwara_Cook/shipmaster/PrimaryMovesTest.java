package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Cockpit;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Display;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.MyMapper;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Sailor;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Action;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Turn;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.ActionJSON;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Rectangle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal.Goal;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal.RegattaGoal;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Deck;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Rudder;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Sail;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainSailorMoves.CaptainSailorMove;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PrimaryMovesTest {
    ActionJSON actionJSON;
    InitGame initGame;
    CaptainSailorMove captainSailorMove;
    PrimaryMoves primaryMoves;

    @BeforeEach
    void setActionJSON(){
        actionJSON=new ActionJSON();
        initGame=createInitGame();
        captainSailorMove=new CaptainSailorMove(initGame.getShip(),initGame.getSailors());
        primaryMoves=new PrimaryMoves(initGame,actionJSON);

    }

    private InitGame createInitGame() {
        Random rand = new Random();
        Checkpoint checkpoint1 = new Checkpoint(new Position(-5586.701434159062, -195.31249999999972, 0), new Circle(200));


        Checkpoint[] listCheckpoint = {checkpoint1};
        Goal goal = new RegattaGoal(listCheckpoint);

        Deck deck = new Deck(3, 7);  //longeur, largeur

        Equipment oar1 = new Oar(1, 2);
        Equipment oar2 = new Oar(2, 2);
        Equipment oar3 = new Oar(3, 2);
        Equipment oar4 = new Oar(3, 0);
        Equipment oar5 = new Oar(4, 0);
        Equipment oar6 = new Oar(5, 0);
        Equipment sail1=new Sail(3,1,false);
        Equipment sail2=new Sail(5,1,false);
        Equipment rudder = new Rudder(6, 1);
        List<Equipment> equipmentList = new ArrayList<>(List.of( oar1,oar2, oar3,oar4, oar5,oar6, rudder,sail1,sail2));

        Shape shapeShip = new Rectangle(2, 4, 0);
        Ship ship = new Ship(100, new Position(-3650.58670143416, 1842.4479166666663, -2.740166925631097), "BOAT", deck, equipmentList, shapeShip);


        Sailor sailor1= new Sailor(1,1,2,"a");
        Sailor sailor2= new Sailor(2,2,2,"b");
        Sailor sailor3= new Sailor(3,3,2,"c");
        Sailor sailor4= new Sailor(4,3,0,"d");
        Sailor sailor5= new Sailor(5,4,0,"e");
        Sailor sailor6= new Sailor(6,5,0,"f");
        Sailor sailor7= new Sailor(7,3,1,"g");
        Sailor sailor8= new Sailor(8,5,1,"h");
        Sailor sailor9= new Sailor(9,6,1,"I");
        Sailor[] listSailor={sailor7,sailor2,sailor3,sailor5,sailor6,sailor4,sailor8,sailor1,sailor9};



        int shipCount = 1;
        return new InitGame(goal, ship, listSailor, shipCount);
    }

    @Test  //todo
    void primaryMoveOar0left0right(){
        captainSailorMove.assignSpecificEquipement("oar",4);
        primaryMoves.primaryMoveOar(0,0);
        String actions=actionJSON.getListAction().toString();
        assertEquals("[]",actions);
    }
    @Test  //todo
    void primaryMoveOar1left1right(){
        captainSailorMove.assignSpecificEquipement("oar",6);
        primaryMoves.primaryMoveOar(1,1);
        String actions=actionJSON.getListAction().get(0).getType();
        assertEquals(2,actionJSON.getListAction().size());
        assertEquals("OAR", actions);
    }


    @Test
    void primaryMoveTurn(){
        captainSailorMove.assignSpecificEquipement("rudder",1);
        primaryMoves.primaryMoveTurn(1.2);
        String actions=actionJSON.getListAction().get(0).getType();
        assertEquals(1,actionJSON.getListAction().size());
        assertEquals("TURN", actions);
        Turn turn=(Turn) actionJSON.getListAction().get(0);
        assertEquals(1.2,turn.getRotation());
    }

    @Test
    void primaryMoveTurnNegativRotation(){
        captainSailorMove.assignSpecificEquipement("rudder",1);
        primaryMoves.primaryMoveTurn(-1.2);
        String actions=actionJSON.getListAction().get(0).getType();
        assertEquals(1,actionJSON.getListAction().size());
        assertEquals("TURN", actions);
        Turn turn=(Turn) actionJSON.getListAction().get(0);
        assertEquals(-1.2,turn.getRotation());
    }

    @Test
    void PrimaryMovesSail(){
        InitGame initGame1=mock(InitGame.class);
        Ship ship=mock(Ship.class);
        when(initGame1.getShip()).thenReturn(ship);
        ActionJSON actionJSON1=new ActionJSON();
        PrimaryMoves primaryMoves1=new PrimaryMoves(initGame1,actionJSON1);
        List<Equipment> sails=  new ArrayList<>();
        Equipment sail1=new Sail(3,1,false);
        Equipment sail2=new Sail(5,1,false);
        Equipment sail3=new Sail(4,1,false);
        Equipment sail4=new Sail(2,1,false);
        sails.add(sail1);
        sails.add(sail2);
        sails.add(sail3);
        sails.add(sail4);
        when(ship.getUsableSails()).thenReturn(sails);
        List<Sailor> sailorsAssignedToSail=new ArrayList<>();
        Sailor sailor1= new Sailor(1,3,1,"a");
        Sailor sailor2= new Sailor(2,5,1,"b");
        Sailor sailor3= new Sailor(3,4,1,"c");
        Sailor sailor4= new Sailor(4,2,1,"d");
        sailor1.setEquipment(sail1);
        sailor2.setEquipment(sail2);
        sailor3.setEquipment(sail3);
        sailorsAssignedToSail.add(sailor1);
        sailorsAssignedToSail.add(sailor2);
        sailorsAssignedToSail.add(sailor3);
        when(initGame1.allSailorAssignedTo("sail")).thenReturn(sailorsAssignedToSail);
        primaryMoves1.primaryMoveSail(0);
        assertEquals(0,primaryMoves1.actionJSON.getListAction().size());
        when(initGame1.getUsableSailorSail()).thenReturn(sailorsAssignedToSail);
        System.out.println(sailorsAssignedToSail.size());
        primaryMoves1.primaryMoveSail(2);
        List<Action> actions=actionJSON1.getListAction();
        System.out.println(sailorsAssignedToSail.size());
        assertEquals(2,actions.size());
        assertEquals("LIFT_SAIL",actions.get(0).getType());
        assertEquals("LIFT_SAIL",actions.get(1).getType());
        primaryMoves1.primaryMoveSail(1);
        assertEquals(3,actions.size());
        assertEquals("LOWER_SAIL",actions.get(2).getType());

    }






}