package fr.unice.polytech.si3.qgl.mugiwara_cook.shapes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fr.unice.polytech.si3.qgl.mugiwara_cook.Point;

import java.io.IOException;

public class DeserializeShape extends StdDeserializer<Shape> {

    public DeserializeShape() {
        this(null);
    }

    public DeserializeShape(Class<?> vc) {
        super(vc);
    }

    @Override
    public Shape deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String type = node.get("type").asText();
        return createShape(type,node);
    }
    //TODO class object mapper
    public Shape createShape(String type,JsonNode node) throws JsonProcessingException {
        switch (type){
            case "Rectangle":
                double width = node.get("width").asDouble();
                double height = node.get("height").asDouble();
                double orientation = node.get("orientation").asDouble();
                return new Rectangle(width,height,orientation);

            case "Circle":
                double radius = node.get("radius").asDouble();
                return new Circle(radius);

            case "Polygon":
                double orientation2 = node.get("orientation").asDouble();
                String verticlesString = node.get("verticles").toString();
                ObjectMapper objectMapper = new ObjectMapper();
                Point[] verticles = objectMapper.readValue(verticlesString, Point[].class);
                return new Polygon(orientation2,verticles);
            default:
                return null;
        }
    }

}
