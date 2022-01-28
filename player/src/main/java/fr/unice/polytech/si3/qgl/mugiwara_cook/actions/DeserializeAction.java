package fr.unice.polytech.si3.qgl.mugiwara_cook.actions;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class DeserializeAction extends StdDeserializer<Action> {

    public DeserializeAction() {
        this(null);
    }

    public DeserializeAction(Class<?> vc) {
        super(vc);
    }

    @Override
    public Action deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String type = node.get("type").asText();
        int sailorId = node.get("sailorId").asInt();
        return createShape(type,sailorId,node);
    }
    //add in MyMapper
    public Action createShape(String type,int sailorId,JsonNode node) throws JsonProcessingException {
        switch (type){
            case "Aim":
                double angle = node.get("angle").asDouble();
                return new Aim(sailorId, angle);
            case "Fire":
                return new Fire(sailorId);
            case "Lift_Sail":
                return new Lift_Sail(sailorId);
            case "Lower_Sail":
                return new Lower_Sail(sailorId);
            case "Moving":
                int xdistance = node.get("xdistance").asInt();
                int ydistance = node.get("ydistance").asInt();
                return new Moving(sailorId, xdistance,ydistance);
            case "Oar":
                return new Oar(sailorId);
            case "Reload":
                return new Reload(sailorId);
            case "Turn":
                double rotation = node.get("rotation").asDouble();
                return new Turn(sailorId,rotation);
            case "Use_Watch":
                return new Use_Watch(sailorId);
            default:
                return null;
        }
    }

}
