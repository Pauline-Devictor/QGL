package fr.unice.polytech.si3.qgl.Mugiwara_Cook.actions;

import com.fasterxml.jackson.core.JsonParser;
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
            throws IOException{
        JsonNode node = jp.getCodec().readTree(jp);
        String type = node.get("type").asText();
        int sailorId = node.get("sailorId").asInt();
        return createShape(type,sailorId,node);
    }
    //add in MyMapper
    public Action createShape(String type,int sailorId,JsonNode node){
        switch (type){

            case "LIFT_SAIL":
                return new LiftSail(sailorId);
            case "LOWER_SAIL":
                return new LowerSail(sailorId);
            case "MOVING":
                int xdistance = node.get("xdistance").asInt();
                int ydistance = node.get("ydistance").asInt();
                return new Moving(sailorId, xdistance,ydistance);
            case "OAR":
                return new Oar(sailorId);
            case "TURN":
                double rotation = node.get("rotation").asDouble();
                return new Turn(sailorId,rotation);
            default:
                return null;
        }
    }

}
