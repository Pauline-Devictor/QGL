package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.NextRound;
import fr.unice.polytech.si3.qgl.regatta.cockpit.ICockpit;

public class Cockpit implements ICockpit {
	MyMapper myMapper= new MyMapper();
	InitGame initGame;

	public Cockpit(){
		//Json
	}

	/**
	 *Init the game datas
	 * @param game A Json with the beginning data
	 */
	public void initGame(String game) {
		System.out.println("Init game input: " + game);
		try {
			this.initGame = myMapper.readValue(game, InitGame.class);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	/**
	 *Decide what to do with round information
	 * @param round Json with data for the next round
	 * @return Json with the list of our actions
	 */
	public String nextRound(String round) {
		System.out.println("Next round input: " + round);
		try {
			NextRound nextRound = myMapper.readValue(round, NextRound.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
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
