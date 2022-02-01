package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.Action;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions.DeserializeAction;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal.DeserializeGoal;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal.Goal;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shapes.DeserializeShape;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shapes.Shape;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.DeserializeEquipment;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment.Equipment;

public class MyMapper extends ObjectMapper {

    public MyMapper(){
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Shape.class, new DeserializeShape());
        module.addDeserializer(Equipment.class,new DeserializeEquipment());
        module.addDeserializer(Action.class, new DeserializeAction());
        module.addDeserializer(Goal.class, new DeserializeGoal());
        registerModule(module);

    }
}
