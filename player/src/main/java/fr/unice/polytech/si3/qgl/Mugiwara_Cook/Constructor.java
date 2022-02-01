package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.Ship;

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
