package fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.MyMapper;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shapes.Shape;

import java.io.IOException;

public class DeserializeVisibleEntities  extends StdDeserializer<VisibleEntity> {

    public DeserializeVisibleEntities() {
        this(null);
    }

    public DeserializeVisibleEntities(Class<?> vc) {
        super(vc);
    }

    @Override
    public VisibleEntity deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String type = node.get("type").asText();
        MyMapper mapper = new MyMapper();
        String positionToString = node.get("position").toString();
        Position position = mapper.readValue(positionToString, Position.class);
        String shapeToString = node.get("shape").toString();
        Shape shape = mapper.readValue(shapeToString, Shape.class);
        return createVisibleEntity(type,node,position,shape);
    }

    public VisibleEntity createVisibleEntity(String type,JsonNode node,Position position,Shape shape) throws JsonProcessingException {
        switch (type){
            case "OtherShip":
                int life = node.get("life").asInt();
                return new OtherShip(life,position,shape);
            case "Reef":
                return new Reef(position,shape);
            case "Stream":
                double strength = node.get("strength").asDouble();
                return new Stream(position,shape,strength);
            default:
                return null;
        }
    }
}
