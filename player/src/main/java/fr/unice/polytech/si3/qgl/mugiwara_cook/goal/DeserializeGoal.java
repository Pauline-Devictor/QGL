package fr.unice.polytech.si3.qgl.mugiwara_cook.goal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import fr.unice.polytech.si3.qgl.mugiwara_cook.MyMapper;
import fr.unice.polytech.si3.qgl.mugiwara_cook.sea.Checkpoint;

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
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String type = node.get("type").asText();
        return createEquipment(type,node);
    }

    public Goal createEquipment(String type,JsonNode node) throws JsonProcessingException {
        switch (type){
            case "BATTLE":
               return new BattleGoal();
            case "REGATTA":
                String checkpointString = node.get("checkpoints").toString();
                MyMapper mapper = new MyMapper();
                Checkpoint[] checkpoints = mapper.readValue(checkpointString, Checkpoint[].class);
                return new RegattaGoal(checkpoints);
            default:
                return null;
        }
    }
}
