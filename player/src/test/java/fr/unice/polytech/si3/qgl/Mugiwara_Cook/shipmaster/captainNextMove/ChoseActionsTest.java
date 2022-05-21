package fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Cockpit;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.ActionJSON;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Circle;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.VisibleEntity;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Wind;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Deck;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Oar;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.choice.ChoseAngle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class ChoseActionsTest {
    ActionJSON actionJSON;

    Position positionBoat;
    Wind wind;
    ChoseActions choseActions;
    Cockpit cockpit;

    Oar rame1;
    Oar rame2;
    List<Equipment> entities=new ArrayList<>();
    Checkpoint checkpoint;
    Ship ship;
    NextRound nextRound;


    @BeforeEach
    void set(){
        this.cockpit = new Cockpit();
        String initGame = """
                {
                  "goal": {
                    "mode": "REGATTA",
                    "checkpoints": [
                      {
                        "position": {
                          "x": 0,
                          "y": 0,
                          "orientation": 0
                        },
                        "shape": {
                          "type": "circle",
                          "radius": 50
                        }
                      },
                      {
                        "position": {
                          "x": 1000,
                          "y": 0,
                          "orientation": 0
                        },
                        "shape": {
                          "type": "circle",
                          "radius": 100
                        }
                      }
                    ]
                  },
                  "ship": {
                    "type": "ship",
                    "life": 100,
                    "position": {
                      "x": 0,
                      "y": 0,
                      "orientation": 0
                    },
                    "name": "Les copaings d'abord!",
                    "deck": {
                      "width": 3,
                      "length": 6
                    },
                    "entities": [
                      {
                        "x": 1,
                        "y": 0,
                        "type": "oar"
                      },
                      {
                        "x": 4,
                        "y": 2,
                        "type": "oar"
                      }
                    ],
                    "shape": {
                      "type": "rectangle",
                      "width": 50,
                      "height": 100,
                      "orientation": 0
                    }
                  },
                  "sailors": [
                    {
                      "x": 0,
                      "y": 0,
                      "id": 0,
                      "name": "Edward Teach"
                    },
                    {
                      "x": 0,
                      "y": 1,
                      "id": 1,
                      "name": "Edward Pouce"
                    }
                  ],
                  "shipCount": 1
                }""";
        this.cockpit.initGame(initGame);

        actionJSON=new ActionJSON();
        choseActions= new ChoseActions(actionJSON,this.cockpit.getInitGame());

        positionBoat=new Position(0.0,0.0,1.0);
        wind=new Wind(2,2);

        rame1 = new Oar(0,0);
        rame2 = new Oar(30,0);
        entities = new ArrayList<>(List.of(rame1,rame2));

        checkpoint = new Checkpoint(new Position(20,20,0.0),new Circle(100));
        VisibleEntity[] visibleEntities = new VisibleEntity[2];
        ship = new Ship(100,new Position(10,10,0.0),"test",new Deck(100,100),entities ,new Circle(23.0));
        nextRound = new NextRound(ship, new Wind(-2.3, 100), visibleEntities);


        spy(choseActions);
    }

    /**@Test
    void turnWithRudderAndGoFarWithOars(){
        int[] nbOarsAndSails={1,1,1};
        when(ChoseAngle.isOkayToUseOnlyTheRudderToTurn(checkpoint,nextRound.getShip())).thenReturn(0.3);
        when(ChoseDistance.choiceBestNbOarAndSail(angleOption,checkpoint,ship,positionBoat,wind)).thenReturn(nbOarsAndSails);
        choseActions.turnWithRudderAndGoFarWithOars(checkpoint,nextRound);

    }**/

    @Test
    void moveToTheNextCheckpointRudderZero(){
        choseActions.moveToTheNextCheckpoint(checkpoint,nextRound);

    }

    @Test
    void moveToTheNextCheckpointRudderNotZero(){
        nextRound.getShip().getPosition().setX(3);
        nextRound.getShip().getPosition().setY(-1);
        nextRound.getShip().getPosition().setOrientation(Math.PI/6);

        choseActions.moveToTheNextCheckpoint(checkpoint,nextRound);
    }
}