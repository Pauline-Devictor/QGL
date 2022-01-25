package fr.unice.polytech.si3.qgl.mugiwara_cook;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si3.qgl.regatta.cockpit.ICockpit;

public class Cockpit implements ICockpit {

	public void initGame(String game) {
		System.out.println("Init game input: " + game);
		Constructor constructor = new Constructor();
		constructor.construct(game);
	}

	public String nextRound(String round) {
		System.out.println("Next round input: " + round);
		return "[]";
	}

	@Override
	public List<String> getLogs() {
		return new ArrayList<>();
	}
}
