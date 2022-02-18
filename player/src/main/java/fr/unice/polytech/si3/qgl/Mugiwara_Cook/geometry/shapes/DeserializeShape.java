package fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.shapes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.MyMapper;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Point;

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

    public Shape createShape(String type,JsonNode node) throws JsonProcessingException {
        switch (type){
            case "rectangle":
                double width = node.get("width").asDouble();
                double height = node.get("height").asDouble();
                double orientation = node.get("orientation").asDouble();
                return new Rectangle(width,height,orientation);

            case "circle":
                double radius = node.get("radius").asDouble();
                return new Circle(radius);

            case "polygon":
                double orientation2 = node.get("orientation").asDouble();
                String verticesString = node.get("vertices").toString();
                MyMapper mapper = new MyMapper();
                Point[] vertices = mapper.readValue(verticesString, Point[].class);
                return new Polygon(orientation2,vertices);
            default:
                return null;
        }
    }

}