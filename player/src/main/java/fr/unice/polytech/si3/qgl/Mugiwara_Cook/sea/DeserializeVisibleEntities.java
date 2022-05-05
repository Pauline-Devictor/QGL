package fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.MyMapper;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes.Shape;

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
            throws IOException{
        JsonNode node = jp.getCodec().readTree(jp);
        String type = node.get("type").asText();
        MyMapper mapper = new MyMapper();
        String positionToString = node.get("position").toString();
        Position position = mapper.readValue(positionToString, Position.class);
        String shapeToString = node.get("shape").toString();
        Shape shape = mapper.readValue(shapeToString, Shape.class);
        return createVisibleEntity(type,node,position,shape);
    }

    public VisibleEntity createVisibleEntity(String type,JsonNode node,Position position,Shape shape){
        switch (type){
            case "reef":
                return new Reef(position,shape);
            case "stream":
                double strength = node.get("strength").asDouble();
                return new Stream(position,shape,strength);
            default:
                return null;
        }
    }
}
