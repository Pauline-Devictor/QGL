package fr.unice.polytech.si3.qgl.Mugiwara_Cook.ship.equipment;

import com.fasterxml.jackson.core.JsonParser;
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
            throws IOException{
        JsonNode node = jp.getCodec().readTree(jp);
        String type = node.get("type").asText();
        int x = node.get("x").asInt();
        int y = node.get("y").asInt();
        return createEquipment(type,node,x,y);
    }

    public Equipment createEquipment(String type,JsonNode node,int x, int y){
        switch (type){
            case "oar":
                return new Oar(x,y);
            case "rudder":
                return new Rudder(x,y);
            case "sail":
                boolean openned = node.get("openned").asBoolean();
                return new Sail(x,y,openned);
            case "watch":
                return new Watch(x,y);
            default:
                return null;
        }
    }
}
