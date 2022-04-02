package fr.unice.polytech.si3.qgl.Mugiwara_Cook.pathfinding.cartography;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Point;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Reef;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.VisibleEntity;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;
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
        for(double y=nextRound.getShip().getPosition().getY();y<getMapHeight();){
            List mapLine = new ArrayList<>();
            y+=squareSize/2;
            for(double x=nextRound.getShip().getPosition().getX();x<getMapWidth();){
                x+=squareSize/2;
                for(Object reef : reefs){
                    if(collisionDetector.detectCollision(new Point(x,y),(Reef) reef)){
                        mapLine.add(1);
                    } else {
                        mapLine.add(0);
                    }
                }
            }
            map.add(mapLine);
        }
        return isThereAPath(map) ? map : buildMap((int)(squareSize*1.3));
    }


    public boolean isThereAPath(List<List<Integer>>map){
        for(int i=0;i<map.size();i++){
            for(int j=0;j<map.get(0).size();j++){
                if(map.get(i).get(j)!=0){
                    return true;
                }
            }
        }
        return false;
    }
}
