package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Point;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Reef;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.VisibleEntity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Spotter {

    NextRound nextRound;
    Checkpoint currentCheckpoint;
    @Getter
    ArrayList reefs;

    public Spotter(NextRound nextRound,Checkpoint currentCheckpoint){
        this.nextRound=nextRound;
        this.currentCheckpoint=currentCheckpoint;
        setReefs();
    }

    public void setReefs(){
        reefs = new ArrayList<Reef>();
        for(VisibleEntity visibleEntity : nextRound.getVisibleEntities()){
            if(visibleEntity.getType().equals("reef")){
                reefs.add((Reef)visibleEntity);
            }
        }
    }

    public double getMapHeight(){
        return Math.abs(nextRound.getShip().getPosition().getY()-currentCheckpoint.getPosition().getY())*1.2;
    }

    public double getMapWidth(){
        return Math.abs(nextRound.getShip().getPosition().getX()-currentCheckpoint.getPosition().getX())*1.2;
    }

    // L'argument de cette méthode va définir la taille des carrés lors de l'échantillonage
    public List<List<Integer>> buildMap(int squareSize){

        List<List<Integer>> map = new ArrayList<>();
        CollisionDetector collisionDetector= new CollisionDetector();
        for(double y=nextRound.getShip().getPosition().getY()+squareSize/2;y<getMapHeight();){
            List mapLine = new ArrayList<>();

            for(double x=nextRound.getShip().getPosition().getX()+squareSize/2;x<getMapWidth();){

                for(Object reef : reefs){
                    if(collisionDetector.detectCollision(new Point(x,y),(Reef) reef)){
                        mapLine.add(1);
                    } else {
                        mapLine.add(0);
                    }
                }
                x+=squareSize;
            }
            map.add(mapLine);
            y+=squareSize;
        }
        return map;
    }
}
