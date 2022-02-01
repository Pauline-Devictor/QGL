package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class DeserializeEquipment  extends StdDeserializer<Equipment> {

    public DeserializeEquipment() {
        this(null);
    }

    public DeserializeEquipment(Class<?> vc) {
        super(vc);
    }

    @Override
    public Equipment deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String type = node.get("type").asText();
        int x = node.get("x").asInt();
        int y = node.get("y").asInt();
        return createEquipment(type,node,x,y);
    }

    public Equipment createEquipment(String type,JsonNode node,int x, int y){
        switch (type){
            case "Canon":
                boolean loaded = node.get("loaded").asBoolean();
                double angle = node.get("angle").asDouble();
                return new Canon(x,y,loaded,angle);
            case "Oar":
                return new Oar(x,y);
            case "Rudder":
                return new Rudder(x,y);
            case "Sail":
                boolean openned = node.get("openned").asBoolean();
                return new Sail(x,y,openned);
            case "Watch":
                return new Watch(x,y);
            default:
                return null;
        }
    }
}
