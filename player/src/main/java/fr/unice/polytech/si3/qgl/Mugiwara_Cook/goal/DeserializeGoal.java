package fr.unice.polytech.si3.qgl.Mugiwara_Cook.goal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.MyMapper;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.sea.Checkpoint;

import java.io.IOException;

public class DeserializeGoal  extends StdDeserializer<Goal> {

    public DeserializeGoal() {
        this(null);
    }

    public DeserializeGoal(Class<?> vc) {
        super(vc);
    }

    @Override
    public Goal deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException{
        JsonNode node = jp.getCodec().readTree(jp);
        String mode = node.get("mode").asText();
        return createGoal(mode,node);
    }

    public Goal createGoal(String mode,JsonNode node) throws JsonProcessingException {
        if (mode.equals("REGATTA")) {
            String checkpointString = node.get("checkpoints").toString();
            MyMapper mapper = new MyMapper();
            Checkpoint[] checkpoints = mapper.readValue(checkpointString, Checkpoint[].class);
            return new RegattaGoal(checkpoints);
        }
        else {
            return null;
        }
    }
}
