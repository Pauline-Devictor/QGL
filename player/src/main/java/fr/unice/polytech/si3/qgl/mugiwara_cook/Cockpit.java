package fr.unice.polytech.si3.qgl.mugiwara_cook;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.regatta.cockpit.ICockpit;

public class Cockpit implements ICockpit {

	public void initGame(String game) {
		System.out.println("Init game input: " + game);
		MyMapper myMapper= new MyMapper();
		try {
			myMapper.readValue(game, InitGame.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	public String nextRound(String round) {
		System.out.println("Next round input: " + round);
		return "[ {\n" +
				"    \"sailorId\": 0,\n" +
				"    \"type\": \"OAR\"\n" +
				"  },\n" +
				"  {\n" +
				"    \"sailorId\": 1,\n" +
				"    \"type\": \"OAR\"\n" +
				"  }\n" +
				"]";
	}

	@Override
	public List<String> getLogs() {
		return new ArrayList<>();
	}
}
