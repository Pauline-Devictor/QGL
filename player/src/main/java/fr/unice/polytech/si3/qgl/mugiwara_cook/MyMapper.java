package fr.unice.polytech.si3.qgl.mugiwara_cook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import fr.unice.polytech.si3.qgl.mugiwara_cook.actions.Action;
import fr.unice.polytech.si3.qgl.mugiwara_cook.actions.DeserializeAction;
import fr.unice.polytech.si3.qgl.mugiwara_cook.goal.DeserializeGoal;
import fr.unice.polytech.si3.qgl.mugiwara_cook.goal.Goal;
import fr.unice.polytech.si3.qgl.mugiwara_cook.shapes.DeserializeShape;
import fr.unice.polytech.si3.qgl.mugiwara_cook.shapes.Shape;
import fr.unice.polytech.si3.qgl.mugiwara_cook.ship.equipment.DeserializeEquipment;
import fr.unice.polytech.si3.qgl.mugiwara_cook.ship.equipment.Equipment;

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
