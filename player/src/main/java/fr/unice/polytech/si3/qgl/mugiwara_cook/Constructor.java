package fr.unice.polytech.si3.qgl.mugiwara_cook;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si3.qgl.mugiwara_cook.ship.Ship;

public class Constructor {
    MyMapper myMapper= new MyMapper();;
    public void construct(String content){
    try {
        Ship ship = myMapper.readValue(content, Ship.class);
        System.out.println(ship.getName() + ship.getType());
    }
    catch (Exception e) {
        e.printStackTrace();}
    }
}
